package com.yy.servlet.User;

import com.yy.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ConfirmCancelOrderServlet")
public class ConfirmCancelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取用户登录后传来的User
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        if(user != null){
            //获取点击取消订阅后传来的报刊id
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("你要取消订阅的报刊id: " + id);
            resp.getWriter().print("<script language=\"javascript\">alert(\"确认取消订阅吗？\");" +
                    "location.href=\"/cancelOrderServlet?id="+id+"\"</script>");
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }
    }
}
