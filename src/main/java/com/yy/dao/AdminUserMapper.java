package com.yy.dao;

import com.yy.pojo.AdminUser;

import java.util.List;
import java.util.Map;

public interface AdminUserMapper {

    //获取所有管理员
    List<AdminUser> getAllAdminUser();

    //根据用户名查询管理员
    AdminUser getAdminUserByName(String username);

    //根据用户名和密码查询是否匹配数据库 （登录判断）
    Integer checkAdmin(Map map);

}
