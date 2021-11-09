package com.yy.servlet;


import com.yy.pojo.AdminUser;
import com.yy.services.impl.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/classificationQueryServlet")
public class ClassificationQueryServlet extends HttpServlet {
    OrdersServiceImpl ordersService = new OrdersServiceImpl();
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
        AdminUser adminUser = (AdminUser)session.getAttribute("adminUser");


        int classify = Integer.parseInt(req.getParameter("classify"));
        StringBuilder sb = new StringBuilder();
        //会话生效
        if(adminUser != null){
            //1.按人员查询
            if(classify == 1){
                //先查出所有订阅了报刊的用户
                List<Integer> users = ordersService.getAllUsers();

            }
            //2.按报刊查询
            if(classify == 2){

            }
            //3.按部门查询
            if(classify == 3){

            }
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }
    }
}
