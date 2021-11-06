package com.yy.services.impl;

import com.yy.dao.AdminUserMapper;
import com.yy.pojo.AdminUser;
import com.yy.services.AdminUserServiceMapper;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public class AdminUserServiceImpl implements AdminUserServiceMapper {

    private SqlSessionFactory sqlSessionFactory = myBatisUtils.getSqlSessionFactory();
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
    @Override
    public List<AdminUser> getAllAdminUser() {
        return getSqlSession().getMapper(AdminUserMapper.class).getAllAdminUser();
    }

    @Override
    public AdminUser getAdminUserByName(String username) {
        return getSqlSession().getMapper(AdminUserMapper.class).getAdminUserByName(username);
    }

    @Override
    public Integer checkAdmin(Map map) {
        return getSqlSession().getMapper(AdminUserMapper.class).checkAdmin(map);
    }
}
