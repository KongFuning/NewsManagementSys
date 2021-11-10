package test;

import com.yy.dao.AdminUserMapper;
import com.yy.dao.DepartmentMapper;
import com.yy.pojo.Department;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


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

    @Test
    public void getAllDepartment_test(){
        List<Department> allDepartment = mapper.getAllDepartment();
        for (Department department : allDepartment) {
            System.out.println(department);
        }
    }


    @After
    public void destroy(){
        sqlSession.close();
    }
}
