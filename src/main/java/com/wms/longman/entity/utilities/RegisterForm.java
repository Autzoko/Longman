package com.wms.longman.entity.utilities;

public class RegisterForm {
    private String user_name;
    private String user_pwd;
    private String user_phone;

    public void setUser_name(String user_name) {this.user_name = user_name;}
    public void setUser_pwd(String user_pwd) {this.user_pwd = user_pwd;}
    public void setUser_phone(String user_phone) {this.user_phone = user_phone; }
    public String getUser_name() {return this.user_name;}
    public String getUser_pwd() {return this.user_pwd;}
    public String getUser_phone() {return this.user_phone;}
}
