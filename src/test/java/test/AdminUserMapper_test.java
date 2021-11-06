package test;

import com.yy.dao.AdminUserMapper;
import com.yy.dao.UserMapper;
import com.yy.pojo.AdminUser;
import com.yy.pojo.User;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUserMapper_test {
    private SqlSession sqlSession;
    private AdminUserMapper mapper;

    @Before
    public void init(){
        sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(AdminUserMapper.class);
    }

    @Test
    public void getAllAdminUser_test(){
        List<AdminUser> adminUsers = mapper.getAllAdminUser();
        for (AdminUser adminUser : adminUsers) {
            System.out.println(adminUser);
        }
    }

    @Test
    public void getAdminUserByName_test(){
        AdminUser admin = mapper.getAdminUserByName("admin");
        System.out.println(admin);
    }

    @Test
    public void checkAdmin_test(){
        Map<String,Object> map = new HashMap<>();
        map.put("userName","admin");
        map.put("passWord","admin");
        Integer result = mapper.checkAdmin(map);
        System.out.println(result);
    }
    @After
    public void destroy(){
        sqlSession.close();
    }
}
