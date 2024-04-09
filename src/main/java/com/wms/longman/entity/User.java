package com.wms.longman.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    private String user_id;
    private String user_name;
    private String user_pwd;
    private String user_phone;
}
