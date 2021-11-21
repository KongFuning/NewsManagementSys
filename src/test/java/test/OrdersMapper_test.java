package test;

import com.yy.dao.OrdersMapper;
import com.yy.dao.UserMapper;
import com.yy.pojo.Newspaper;
import com.yy.pojo.User;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void getAllNewsId_test(){
        List<Integer> allNewsId = mapper.getAllNewsId();
        for (Integer integer : allNewsId) {
            System.out.println(integer);
        }
    }

    @Test
    public void getAllUsersOrderOneNew_test(){
        List<User> allUsersOrderOneNew = mapper.getAllUsersOrderOneNew(1);
        for (User user : allUsersOrderOneNew) {
            System.out.println(user);
        }
    }

    @Test
    public void getNewspaperByNewsId_test(){
        Newspaper newspaperByNewsId = mapper.getNewspaperByNewsId(1);
        System.out.println(newspaperByNewsId);
    }

    @Test
    public void checkOrder_test(){
        Map map = new HashMap();
        map.put("user_id",9);
        map.put("news_id",1);
        System.out.println(mapper.checkOrder(map));;
    }

    @Test
    public void orderNew_test(){
        Map map = new HashMap();
        map.put("user_id",9);
        map.put("news_id",1);
        System.out.println(mapper.orderNew(map));
    }


    @After
    public void destroy(){
        sqlSession.close();
    }
}
