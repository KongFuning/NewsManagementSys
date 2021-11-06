package com.yy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;

//mybatis连接数据库的工具类
public class myBatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    static{
        try {
            //获取sqlSessionFactory工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream("mybatis-config.xml")
            );
        } catch (IOException e) {
            throw new ExceptionInInitializerError("初始化异常！");
        }
    }

    //获取SqlSession对象
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}
