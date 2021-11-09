package test;

import com.yy.dao.OrdersMapper;
import com.yy.dao.UserMapper;
import com.yy.pojo.Newspaper;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrdersMapper_test {
    private SqlSession sqlSession;
    private OrdersMapper mapper;

    @Before
    public void init(){
        sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(OrdersMapper.class);
    }

    @Test
    public void getNewspaperByUserId_test(){
        List<Newspaper> newspaperByUserId = mapper.getNewspaperByUserId(9);
        for (Newspaper newspaper : newspaperByUserId) {
            System.out.println(newspaper);
        }
    }

    @Test
    public void getAllUsers_test(){
        List<Integer> allUsers = mapper.getAllUsers();
        for (Integer allUser : allUsers) {
            System.out.println(allUser);
        }
    }

    @After
    public void destroy(){
        sqlSession.close();
    }
}
