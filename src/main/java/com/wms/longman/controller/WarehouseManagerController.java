package com.wms.longman.controller;

import com.wms.longman.entity.WarehouseManager;
import com.wms.longman.entity.utilities.ManagerLevel;
import com.wms.longman.service.WarehouseManagerService;
import com.wms.longman.utilities.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


@CrossOrigin
@RestController
@Slf4j
@RequestMapping(value = "/warehouse-manager")
public class WarehouseManagerController {

    private static final String excel_save_path = "C:\\Users\\llt02\\Desktop\\Excels\\Files";
    @Autowired
    private WarehouseManagerService warehouseManagerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<WarehouseManager> get_all_warehouse_manager() throws Exception {
        return warehouseManagerService.select_all_warehouse_manager();
    }

    @RequestMapping(value = "/add-manager", method = RequestMethod.POST)
    public Result addManager(@RequestBody WarehouseManager warehouseManager) throws Exception {
        log.info("warehouseManager = {}" + warehouseManager);
        warehouseManagerService.insert_new_manager(warehouseManager);
        return Result.success();
    }

    @RequestMapping(value = "/delete-manager", method = RequestMethod.DELETE)
    public Result deleteManager(@RequestParam String managerID) throws Exception {
        log.info("manager_id = {}" + managerID);
        warehouseManagerService.delete_manager(managerID);
        return Result.success();
    }

    @RequestMapping(value = "/update-manager", method = RequestMethod.PUT)
    public Result updateManager(@RequestBody WarehouseManager warehouseManager) throws Exception {
        log.info("warehouseManager = {}" + warehouseManager);
        warehouseManagerService.update_manager(warehouseManager);
        return Result.success();
    }

    @RequestMapping(value = "/get-table", method = RequestMethod.POST)
    public ResponseEntity<Object> saveExcel(@RequestParam("file")MultipartFile file) {
        if(file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(setResponse(("no file is uploaded")));
        }
        try {
            File directory = new File(excel_save_path);
            if(!directory.exists()) {
                directory.mkdirs();
            }
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID() + "-" + originalFilename;
            String filePath = excel_save_path + File.separator + uniqueFilename;

            file.transferTo(new File(filePath));

            saveExcelDataToDataBase(parseExcel(excel_save_path));

            deleteFiles(directory);

            return ResponseEntity.status(HttpStatus.OK).body(setResponse("file uploaded"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(setResponse("upload error"));
        }
    }

    private List<List<Object>> parseExcel(String filePath) throws RuntimeException, IOException {
        File directory = new File(filePath);
        List<File> excelFiles = new ArrayList<>();
        if(!directory.exists()) {
            throw new IOException("directory not exists");
        }

        File[] fileList = directory.listFiles();
        if(fileList == null) {
            throw new IOException("file not uploaded");
        }
        for(File file : fileList) {
            excelFiles.add(file);
        }

        List<List<Object>> allData = new ArrayList<>();
        for(File excelFile : excelFiles) {
            List<List<Object>> excelData = readExcel(excelFile);
            allData.addAll(excelData);
        }
        return allData;
    }

    private void saveExcelDataToDataBase(List<List<Object>> excelData) throws Exception {
        List<WarehouseManager> warehouseManagers = new ArrayList<>();
        for(List<Object> row : excelData) {
            if(row.size() != 5) {
                throw new Exception("excel data error, should be 5 columns");
            }
            WarehouseManager manager = new WarehouseManager();
            manager.setManager_id(UUID.randomUUID().toString());
            manager.setManager_name((String) row.get(0));
            manager.setManager_pwd((String) row.get(1));
            String manager_level = (String) row.get(2);
            manager.setManager_level(ManagerLevel.valueOf(manager_level));

            //process date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse((String) row.get(4));
            manager.setLast_login_datetime(new Timestamp(date.getTime()));

            manager.setManager_email((String) row.get(3));
            warehouseManagers.add(manager);
        }

        for(WarehouseManager manager : warehouseManagers) {
            warehouseManagerService.insert_new_manager(manager);
        }
    }

    private static List<List<Object>> readExcel(File file) throws IOException {
        List<List<Object>> excelData = new ArrayList<>();

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for(Row row : sheet) {
            List<Object> rowData = new ArrayList<>();
            for(Cell cell : row) {
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if(DateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String formattedDate = simpleDateFormat.format(cell.getDateCellValue());
                            rowData.add(formattedDate);
                        } else {
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                        }
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    default:
                        rowData.add("");
                }
                System.out.println(cell);
            }
            excelData.add(rowData);
        }
        workbook.close();
        fis.close();

        return excelData;
    }

    private static void deleteFiles(File directory) throws Exception {
        if(directory.exists()) {
            if(directory.isDirectory()) {
                File[] files = directory.listFiles();
                for(File file : files) {
                    if(file.isFile()) {
                        file.delete();
                    }
                    else if(file.isDirectory()) {
                        deleteFiles(file);
                    }
                }
            }
        } else {
            throw new Exception("path not exists");
        }
    }

    private static Map<String, Object> setResponse(String msg, Object... args) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        for(int i = 0; i < args.length; i++) {
            map.put("arg" + i, args[i]);
        }
        return map;
    }
}
