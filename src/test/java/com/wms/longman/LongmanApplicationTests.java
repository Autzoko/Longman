package com.wms.longman;

import com.wms.longman.entity.WarehouseManager;
import com.wms.longman.mapper.WarehouseManagerMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class LongmanApplicationTests {
	@Resource
	private WarehouseManagerMapper warehouseManagerMapper;

	@Autowired
	DataSource datasource;
	//database connection test
	@Test
	public void DatabaseConnectionTest() throws Exception {
		System.out.println("Get connection with: " + datasource.getConnection());
	}

	//data fetching test
	@Test
	public void WarehouseManagerMapperTest() {
		List<WarehouseManager> warehouseManagers = warehouseManagerMapper.select_all_warehouse_manager();
		for(WarehouseManager warehouseManager : warehouseManagers) {
			System.out.println(warehouseManager);
		}
	}

}
