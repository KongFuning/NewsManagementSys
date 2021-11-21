package com.yy.servlet;

import com.yy.pojo.User;
import com.yy.services.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//注册模块
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        System.out.println(userName + "---" + passWord);

        //注册进用户数据库！
        //1.先判断数据库中有没有该用户
        User user = userService.getUserByName(userName);
        System.out.println(user);
        if(user != null){
            //该用户已存在
            resp.getWriter().print("<script language=\"javascript\">alert(\"该用户已存在！\");" +
                    "location.href='register.html'</script>");
        }else {
            //该用户不存在，注册进数据库
            User userNew = new User();
            userNew.setUser_name(userName);
            userNew.setUser_password(passWord);
            userService.addUser(userNew);
            resp.getWriter().write("注册成功！ 3秒后将返回主页...");
            //定时跳转
            resp.setHeader("Refresh", "3;URL=index.html");
        }
        //释放连接
        userService.getSqlSession().close();
    }
}
