package com.wms.longman.service;

import com.wms.longman.entity.User;

import java.util.List;

public interface UserService {
    void insertUser(User user) throws Exception;
    List<User> selectUserById(String user_id) throws Exception;
    List<User> selectUserByName(String user_name) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUserById(String user_id) throws Exception;
}
