package com.wms.longman.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.longman.entity.WarehouseManager;
import com.wms.longman.mapper.WarehouseManagerMapper;
import com.wms.longman.service.WarehouseManagerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseManagerServiceImpl extends ServiceImpl<WarehouseManagerMapper, WarehouseManager> implements WarehouseManagerService {
    @Resource
    private WarehouseManagerMapper warehouseManagerMapper;

    public List<WarehouseManager> select_all_warehouse_manager() throws Exception {
        return warehouseManagerMapper.select_all_warehouse_manager();
    }

    public void insert_new_manager(WarehouseManager warehouseManager) throws Exception {
        warehouseManagerMapper.insert_new_manager(warehouseManager);
    }

    public void delete_manager(String manager_id) throws Exception {
        warehouseManagerMapper.delete_manager(manager_id);
    }

    public void update_manager(WarehouseManager warehouseManager) throws Exception {
        warehouseManagerMapper.update_manager(warehouseManager);
    }

    public WarehouseManager select_warehouse_manager_by_id(String manager_id) throws Exception{
        return warehouseManagerMapper.select_warehouse_manager_by_id(manager_id);
    }

}
