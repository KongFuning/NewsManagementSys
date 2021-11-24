package com.yy.servlet.Admin;

import com.yy.dao.UserMapper;
import com.yy.pojo.AdminUser;
import com.yy.pojo.User;
import com.yy.services.impl.UserServiceImpl;
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

//管理员用户管理模块
@WebServlet("/adminUserManageServlet")
public class AdminUserManageServlet extends HttpServlet {
//    private UserServiceImpl userService = new UserServiceImpl();
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

        //获取用户集合
        List<User> allUser = sqlSession.getMapper(UserMapper.class).getAllUser();
        StringBuilder sb = new StringBuilder(); // 生成主要页面
        //如果adminUser不为空，则表示会话生效
        if(adminUser != null){
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\t<head>\n" +
                    "\t\t<meta charset=\"UTF-8\">\n" +
                    "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                    "\t\t<title>新用户录入</title>\n" +
                    "\t</head>\n" +
                    "\t<body>\n" +
                    "\t\t\n" +
                    "\t<table id=\"topic_table\">\n" +
                    "\t\t<tr><th>id</th><th>用户名</th><th>密码</th><th>真实姓名</th><th>身份证号码</th><th>手机号码</th><th>住址</th><th>部门</th><th>操作</th></tr>");
            for (User user : allUser) {
                sb.append("<tr><td>"+user.getId()+"</td><td>"+user.getUser_name()+
                        "</td><td>"+user.getUser_password()+"</td><td>"+user.getUser_realname()+
                        "</td><td>"+user.getUser_cardid()+"</td><td>"+user.getUser_phone()+
                        "</td><td>"+user.getUser_address()+
                        "</td><td>"+user.getDepart_id()+"</td><td><a href=\"/confirmDeleteUserServlet?id="+user.getId()+"\" style=\"color: red; font-weight: bold;\">删除</a></td></tr>");
            }
            sb.append("</table>\n" +
                    "\t<a href=\"Admin_addUser.html\"><input id=\"addUser\" name=\"addUser\" type=\"button\" value=\"添加用户\"></a>\n" +
                    "\t</body>");
            resp.getWriter().write(sb.toString());
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }

        //释放SqlSession
        sqlSession.close();
    }
}
