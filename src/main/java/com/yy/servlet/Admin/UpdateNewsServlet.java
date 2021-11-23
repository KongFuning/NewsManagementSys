package com.yy.servlet.Admin;

import com.yy.pojo.AdminUser;
import com.yy.services.impl.NewspaperServiceImpl;
import com.yy.services.impl.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/updateNewsServlet")
public class UpdateNewsServlet extends HttpServlet {
    NewspaperServiceImpl newspaperService = new NewspaperServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取管理员登录后传来的adminUser
        HttpSession session = req.getSession();
        AdminUser adminUser = (AdminUser)session.getAttribute("adminUser");

        int id = Integer.parseInt(req.getParameter("id"));
        String newsName = req.getParameter("newsName");
        String newsContent = req.getParameter("newsContent");
        if(adminUser != null){
            Map map = new HashMap();
            map.put("id",id);
            map.put("name",newsName);
            map.put("content",newsContent);
            if(newspaperService.updateNews(map) != 0){
                //更新成功
                resp.getWriter().print("<script language=\"javascript\">alert(\"更新成功！\");" +
                        "location.href=\"/seeNewsServlet?id="+id+"\"</script>");
            }else {
                //更新失败
                resp.getWriter().print("<script language=\"javascript\">alert(\"更新失败！\");" +
                        "location.href=\"/seeNewsServlet?id="+id+"\"</script>");
            }
            //更新报刊
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }

    }
}
