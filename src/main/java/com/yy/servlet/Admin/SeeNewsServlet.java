package com.yy.servlet.Admin;

import com.yy.dao.OrdersMapper;
import com.yy.pojo.AdminUser;
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

@WebServlet("/seeNewsServlet")
public class SeeNewsServlet extends HttpServlet {
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

        //获取管理员登录后传来的adminUser
        HttpSession session = req.getSession();
        AdminUser adminUser = (AdminUser)session.getAttribute("adminUser");

        //获取传过来的报刊id
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("查看的报刊id是：" + id);
        if(adminUser != null){
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "\t<meta charset=\"UTF-8\">\n" +
                    "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                    "\t<link rel=\"stylesheet\" href=\"css/main.css\"/>\n" +
                    "\t<title>查看报刊</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form action=\"/updateNewsServlet?id="+id+"\" method=\"post\" name=\"myForm\">\n" +
                    "\t<table>\n" +
                    "\t\t<tr style=\"text-align: left;\">\n" +
                    "\t\t\t<td><label>报刊名：</label></td>\n" +
                    "\t\t\t<td><input type=\"text\" class=\"input\" id=\"newsName\" name=\"newsName\" value=\""+sqlSession.getMapper(OrdersMapper.class).getNewspaperByNewsId(id).getName()+"\"></td>\n" +
                    "\t\t</tr>\n" +
                    "\t\t<tr>\n" +
                    "\t\t\t<td><label>内容：</label></td>\n" +
                    "\t\t\t<td><textarea class=\"input\" style=\"width: 400px; height: 300px;\" id=\"newsContent\" name=\"newsContent\">"+sqlSession.getMapper(OrdersMapper.class).getNewspaperByNewsId(id).getContent()+"</textarea></td>\n" +
                    "\t\t</tr>\n" +
                    "\t\t<tr>\n" +
                    "\t\t\t<td colspan=\"2\"><input name=\"addNews\" type=\"submit\" value=\"修改\"  class=\"btn\"></td>\n" +
                    "\t\t</tr>\n" +
                    "\t</table>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>\n");
            resp.getWriter().write(sb.toString());
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }


        //释放SqlSession
        sqlSession.close();
    }
}
