package com.yy.services.impl;

import com.yy.dao.DepartmentMapper;
import com.yy.services.DepartmentServiceMapper;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class DepartmentServiceImpl implements DepartmentServiceMapper {
    private SqlSessionFactory sqlSessionFactory = myBatisUtils.getSqlSessionFactory();
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
    @Override
    public String getDepartName(int id) {
        return getSqlSession().getMapper(DepartmentMapper.class).getDepartName(id);
    }
}
