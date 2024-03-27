package com.wms.longman.controller;

import com.wms.longman.entity.WarehouseManager;
import com.wms.longman.service.WarehouseManagerService;
import com.wms.longman.utilities.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping(value = "/warehouse-manager")
public class WarehouseManagerController {
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
}
