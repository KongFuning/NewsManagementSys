package com.yy.services.impl;

import com.yy.dao.NewspaperMapper;
import com.yy.pojo.Newspaper;
import com.yy.services.NewspaperServiceMapper;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class NewspaperServiceImpl implements NewspaperServiceMapper {
    private SqlSessionFactory sqlSessionFactory = myBatisUtils.getSqlSessionFactory();
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
    @Override
    public List<Newspaper> getAllNewspaper() {
        return getSqlSession().getMapper(NewspaperMapper.class).getAllNewspaper();
    }

    @Override
    public Integer addNews(Newspaper newspaper) {
        return getSqlSession().getMapper(NewspaperMapper.class).addNews(newspaper);
    }
}
