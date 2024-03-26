package com.wms.longman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.longman.entity.WarehouseManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseManagerMapper extends BaseMapper<WarehouseManager> {
    List<WarehouseManager> select_all_warehouse_manager();
}
