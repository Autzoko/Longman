package com.wms.longman.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.longman.entity.User;
import com.wms.longman.mapper.UserMapper;
import com.wms.longman.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    public void insertUser(User user) throws Exception{
        userMapper.insertUser(user);
    }

    public List<User> selectUserById(String user_id) throws Exception {
        return userMapper.selectUserById(user_id);
    }

    public List<User> selectUserByName(String user_name) throws Exception {
        return userMapper.selectUserByName(user_name);
    }

    public void updateUser(User user) throws Exception {
        userMapper.updateUser(user);
    }

    public void deleteUserById(String user_id) throws Exception {
        userMapper.deleteUserById(user_id);
    }
}
