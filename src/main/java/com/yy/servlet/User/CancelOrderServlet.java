package com.yy.servlet.User;

import com.yy.dao.NewspaperMapper;
import com.yy.dao.OrdersMapper;
import com.yy.pojo.AdminUser;
import com.yy.pojo.User;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取用户登录后传来的User
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        if(user != null){
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("你要取消订阅的报刊id: " + id);
            //取消订阅
            Map map = new HashMap();
            map.put("user_id",user.getId());
            map.put("news_id",id);
            if(sqlSession.getMapper(OrdersMapper.class).cancelOrder(map) != 0){
                //取消订阅成功
                System.out.println("取消订阅第"+id+"号报刊成功！");
                //重定向至首页
                resp.sendRedirect("/userMainServlet");
            }else {
                //取消订阅失败
                System.out.println("取消订阅失败！");
                //重定向至首页
                resp.sendRedirect("/adminMainServlet");
            }
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }

        //释放SqlSession
        sqlSession.close();
    }
}
