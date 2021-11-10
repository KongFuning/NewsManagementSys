package com.yy.services.impl;

import com.yy.dao.OrdersMapper;
import com.yy.pojo.Newspaper;
import com.yy.pojo.User;
import com.yy.services.OrdersServiceMapper;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrdersServiceImpl implements OrdersServiceMapper {
    private SqlSessionFactory sqlSessionFactory = myBatisUtils.getSqlSessionFactory();
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }

    @Override
    public List<Newspaper> getNewspaperByUserId(int user_id) {
        return getSqlSession().getMapper(OrdersMapper.class).getNewspaperByUserId(user_id);
    }

    @Override
    public List<Integer> getAllUsers() {
        return getSqlSession().getMapper(OrdersMapper.class).getAllUsers();
    }

    @Override
    public List<Integer> getAllNewsId() {
        return getSqlSession().getMapper(OrdersMapper.class).getAllNewsId();
    }

    @Override
    public List<User> getAllUsersOrderOneNew(int id) {
        return getSqlSession().getMapper(OrdersMapper.class).getAllUsersOrderOneNew(id);
    }

    @Override
    public Newspaper getNewspaperByNewsId(int id) {
        return getSqlSession().getMapper(OrdersMapper.class).getNewspaperByNewsId(id);
    }
}
