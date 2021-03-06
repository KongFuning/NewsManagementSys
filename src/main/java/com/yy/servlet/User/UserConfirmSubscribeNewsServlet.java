package com.yy.servlet.User;

import com.yy.dao.OrdersMapper;
import com.yy.pojo.User;
import com.yy.services.impl.OrdersServiceImpl;
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

//普通用户确认订阅报刊模块
@WebServlet("/userConfirmSubscribeNewsServlet")
public class UserConfirmSubscribeNewsServlet extends HttpServlet {
//    OrdersServiceImpl ordersService = new OrdersServiceImpl();
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

        if (user != null){
            String ids = req.getParameter("ids");
            String[] id = ids.split(",");
            Map map = new HashMap();
            for (String s : id) {
                map.put("user_id",user.getId());
                map.put("news_id",Integer.parseInt(s));
                if(sqlSession.getMapper(OrdersMapper.class).checkOrder(map) == null){ //未订阅的报刊 才订阅
                    System.out.println("报刊：" + s + " 未订阅");
                    //订阅报刊
                    sqlSession.getMapper(OrdersMapper.class).orderNew(map);
                }else {
                    //已订阅的报刊 就不订阅了
                    continue;
                }
                map.clear();
            }
            resp.getWriter().print("<script language=\"javascript\">alert(\"订阅成功！\");" +
                    "location.href='/userMainServlet'</script>");
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }

        //释放SqlSession
        sqlSession.close();
    }
}
