package com.wms.longman.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.longman.entity.WarehouseManager;
import com.wms.longman.mapper.WarehouseManagerMapper;
import com.wms.longman.service.WarehouseManagerService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseManagerServiceImpl extends ServiceImpl<WarehouseManagerMapper, WarehouseManager> implements WarehouseManagerService {
    @Resource
    private WarehouseManagerMapper warehouseManagerMapper;

    public List<WarehouseManager> select_all_warehouse_manager() throws Exception {
        return warehouseManagerMapper.select_all_warehouse_manager();
    }

    public boolean insert_new_manager(WarehouseManager warehouseManager) throws Exception {
        return warehouseManagerMapper.insert_new_manager(warehouseManager);
    }

}
