package com.yy.dao;

import com.yy.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //查询所有用户
    List<User> getAllUser();

    //根据用户名查询用户
    User getUserByName(String username);

    //查询用户总数
    Integer getUserNum();

    //添加用户
    void addUser(User user);

    //添加用户 所有信息
    Integer addUserAll(User user);

    //根据用户名和密码查询是否匹配数据库 （登录判断）
    Integer checkUser(Map map);

}
