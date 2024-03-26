package com.wms.longman.controller;

import com.wms.longman.entity.WarehouseManager;
import com.wms.longman.service.WarehouseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/warehouse-manager")
public class WarehouseManagerController {
    @Autowired
    private WarehouseManagerService warehouseManagerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<WarehouseManager> get_all_warehouse_manager() throws Exception {
        return warehouseManagerService.select_all_warehouse_manager();
    }
}
