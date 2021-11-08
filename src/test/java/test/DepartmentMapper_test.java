package test;

import com.yy.dao.AdminUserMapper;
import com.yy.dao.DepartmentMapper;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DepartmentMapper_test {
    private SqlSession sqlSession;
    private DepartmentMapper mapper;

    @Before
    public void init(){
        sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(DepartmentMapper.class);
    }

    @Test
    public void getDepartName_test(){
        String departName = mapper.getDepartName(2);
        System.out.println(departName);
    }


    @After
    public void destroy(){
        sqlSession.close();
    }
}
