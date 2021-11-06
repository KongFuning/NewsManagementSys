package test;

import com.yy.dao.UserMapper;
import com.yy.pojo.User;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserMapper_test {
    private SqlSession sqlSession;
    private UserMapper mapper;

    @Before
    public void init(){
        sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void getAllUser_test(){
        List<User> users = mapper.getAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getUserByName_test(){
        User user = mapper.getUserByName("测试用户");
        System.out.println(user);
    }

    @Test
    public void addUser_test(){
        User user = new User();
        user.setUser_name("测试2");
        user.setUser_password("12321");
        mapper.addUser(user);
    }

    @Test
    public void getUserNum_test(){
        System.out.println(mapper.getUserNum());
    }


    @After
    public void destroy(){
        sqlSession.close();
    }
}
