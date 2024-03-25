package com.wms.longman.entity;


import com.wms.longman.entity.utilities.ManagerLevel;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class WarehouseManager {
    private String manager_id;
    private String manager_name;
    private String manager_pwd;
    private ManagerLevel manager_level;
    private Timestamp last_login_datetime;
    private String manager_email;
}