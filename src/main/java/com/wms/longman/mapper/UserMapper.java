package com.wms.longman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.longman.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void insertUser(User user);
    List<User> selectUserById(String user_id);
    List<User> selectUserByName(String user_name);
    void updateUser(User user);
    void deleteUserById(String user_id);
}
