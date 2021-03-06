package com.yy.services;

import com.yy.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserServiceMapper {

    //查询所有用户
    List<User> getAllUser();

    //根据用户名查询用户
    User getUserByName(String username);

    //添加用户
    void addUser(User user);

    //根据用户名和密码查询是否匹配数据库 （登录判断）
    Integer checkUser(Map map);

    //添加用户 所有信息
    Integer addUserAll(User user);

    //根据id获取用户
    User getUserById(int id);

    //查询用户总数
    Integer getUserNum();

    //更新用户信息
    Integer updateUser(User user);

    //根据部门id查询用户
    List<User> getAllUserByDepartId(int id);

    //根据id删除用户
    Integer deleteUserById(int id);
}
