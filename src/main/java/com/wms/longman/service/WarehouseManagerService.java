package com.wms.longman.service;

import com.wms.longman.entity.WarehouseManager;

import java.util.List;

public interface WarehouseManagerService {
    List<WarehouseManager> select_all_warehouse_manager() throws Exception;
    void insert_new_manager(WarehouseManager warehouseManager) throws Exception;
    void delete_manager(String manager_id) throws Exception;
    void update_manager(WarehouseManager warehouseManager) throws Exception;
    WarehouseManager select_warehouse_manager_by_id(String manager_id) throws Exception;
}
