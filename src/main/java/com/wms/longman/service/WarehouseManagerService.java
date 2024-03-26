package com.wms.longman.service;

import com.wms.longman.entity.WarehouseManager;

import java.util.List;

public interface WarehouseManagerService {
    List<WarehouseManager> select_all_warehouse_manager() throws Exception;
}
