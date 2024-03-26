package com.wms.longman.service.Impl;

import com.wms.longman.entity.WarehouseManager;
import com.wms.longman.mapper.WarehouseManagerMapper;
import com.wms.longman.service.WarehouseManagerService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseManagerServiceImpl implements WarehouseManagerService {
    @Resource
    private WarehouseManagerMapper warehouseManagerMapper;

    @Override
    public List<WarehouseManager> select_all_warehouse_manager() throws Exception {
        return warehouseManagerMapper.select_all_warehouse_manager();
    }

}
