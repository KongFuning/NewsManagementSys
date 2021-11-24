package com.yy.servlet.Admin;

import com.yy.pojo.AdminUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/confirmDeleteNewsServlet")
public class ConfirmDeleteNewsServlet extends HttpServlet {
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

        if(adminUser != null){
            //获取点击删除后传来的报刊id
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("你要删除的报刊id: " + id);
            resp.getWriter().print("<script language=\"javascript\">alert(\"确认删除吗？\");" +
                    "location.href=\"/deleteNewsServlet?id="+id+"\"</script>");
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }
    }
}
