package com.wms.longman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.longman.entity.WarehouseManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseManagerMapper extends BaseMapper<WarehouseManager> {
    List<WarehouseManager> select_all_warehouse_manager();
    void insert_new_manager(WarehouseManager warehouseManager);
    void delete_manager(String manager_id);
    void update_manager(WarehouseManager warehouseManager);
    WarehouseManager select_warehouse_manager_by_id(String manager_id);
}
