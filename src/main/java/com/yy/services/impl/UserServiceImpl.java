package com.yy.services.impl;

import com.yy.dao.UserMapper;
import com.yy.pojo.User;
import com.yy.services.UserServiceMapper;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserServiceMapper {

    private SqlSessionFactory sqlSessionFactory = myBatisUtils.getSqlSessionFactory();

    public  SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
    @Override
    public List<User> getAllUser() {
        return getSqlSession().getMapper(UserMapper.class).getAllUser();
    }

    @Override
    public User getUserByName(String username) {
        return getSqlSession().getMapper(UserMapper.class).getUserByName(username);
    }

    @Override
    public void addUser(User user) {
        getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public Integer checkUser(Map map) {
        return getSqlSession().getMapper(UserMapper.class).checkUser(map);
    }

    @Override
    public Integer addUserAll(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUserAll(user);
    }

    @Override
    public User getUserById(int id) {
        return getSqlSession().getMapper(UserMapper.class).getUserById(id);
    }

    @Override
    public Integer getUserNum() {
        return getSqlSession().getMapper(UserMapper.class).getUserNum();
    }

    @Override
    public Integer updateUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).updateUser(user);
    }

    @Override
    public List<User> getAllUserByDepartId(int id) {
        return getSqlSession().getMapper(UserMapper.class).getAllUserByDepartId(id);
    }
}
