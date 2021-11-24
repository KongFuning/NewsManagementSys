package com.yy.servlet.User;

import com.yy.dao.OrdersMapper;
import com.yy.pojo.Newspaper;
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
import java.util.List;

//普通用户查询订阅模块
@WebServlet("/userSeachOrderServlet")
public class UserSeachOrderServlet extends HttpServlet {
//    OrdersServiceImpl ordersService = new OrdersServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取用户登录后传来的User
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println(user);

        //根据当前user_id获取订阅报刊
        List<Newspaper> newspaperByUserId = sqlSession.getMapper(OrdersMapper.class).getNewspaperByUserId(user.getId());
        //生成页面
        StringBuilder sb = new StringBuilder();

        if(user != null){
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\t<head>\n" +
                    "\t\t<meta charset=\"UTF-8\">\n" +
                    "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                    "\t\t<title>查询订阅</title>\n" +
                    "\t</head>\n" +
                    "\t<body>\n" +
                    "\t<table id=\"topic_table\">\n" +
                    "\t\t<tr><th>报刊代号</th><th>报刊名</th><th>出版社</th><th>出版周期</th><th>季度报价</th><th>内容介绍</th><th>分类编号</th><th>操作</th></tr>");
            for (Newspaper newspaper : newspaperByUserId) {
                sb.append("<tr><td>"+newspaper.getId()+"</td><td>"+newspaper.getName()+"</td><td>"+
                        newspaper.getPublisher()+"</td><td>"+newspaper.getCycle()+
                        "</td><td>"+newspaper.getOffer()+"</td><td>"+newspaper.getContent()+
                        "</td><td>"+newspaper.getClassify_id()+
                        "</td><td><a href=\"javascript:\" style=\"color: green; font-weight: bold;\">查看</a>/<a href=\"javascript:\" style=\"color: red; font-weight: bold;\">取消订阅</a></td></tr>");
            }
            sb.append("</table>\n" +
                    "\t</body>\n" +
                    "</html>");
            resp.getWriter().write(sb.toString());
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }

        //释放SqlSession
        sqlSession.close();
    }
}
