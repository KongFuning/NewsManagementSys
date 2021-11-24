package com.yy.servlet.User;

import com.yy.dao.DepartmentMapper;
import com.yy.pojo.User;
import com.yy.services.impl.DepartmentServiceImpl;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//普通用户修改个人信息模块
@WebServlet("/updateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
//    DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

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
        System.out.println(user);

        //生成页面
        StringBuilder sb = new StringBuilder();
        if(user != null){
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\t<head>\n" +
                    "\t\t<meta charset=\"utf-8\" />\n" +
                    "\t\t<link rel=\"shortcut icon\" href=\"favicon.ico\" >\n" +
                    "\t\t<link rel=\"stylesheet\" href=\"css/main.css\"/>\n" +
                    "\t\t<title>个人信息</title>\n" +
                    "\t</head>\n" +
                    "\t<body>\n" +
                    "\t\t<div class=\"tittle-name\">个人信息 </div>\n" +
                    "\t\t<div class=\"loginOut\">\n" +
                    "\t\t\t<form id=\"loginForm\" action=\"/confirmUpdateUserInfoServlet\" method=\"post\" onsubmit=\"ret()\"> \n" +
                    "\t\t\t\t<table id=\"loginTable\">\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td>用户名： </td>\n" +
                    "\t\t\t\t\t\t<td><input id=\"userName\" name=\"userName\" type=\"text\" class=\"input\" value=\""+user.getUser_name()+"\"></td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td>密&nbsp;&nbsp;&nbsp;&nbsp;码： </td>\n" +
                    "\t\t\t\t\t\t<td><input id=\"passWord\" name=\"passWord\" type=\"text\" class=\"input\" value=\""+user.getUser_password()+"\"></td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td>真实姓名： </td>\n" +
                    "\t\t\t\t\t\t<td><input id=\"realName\" name=\"realName\" type=\"text\" class=\"input\" value=\""+user.getUser_realname()+"\"></td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td>身份证号码： </td>\n" +
                    "\t\t\t\t\t\t<td><input id=\"cardId\" name=\"cardId\" type=\"text\" class=\"input\" value=\""+user.getUser_cardid()+"\"></td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td>手机号码： </td>\n" +
                    "\t\t\t\t\t\t<td><input id=\"phone\" name=\"phone\" type=\"text\" class=\"input\" value=\""+user.getUser_phone()+"\"></td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td>地址： </td>\n" +
                    "\t\t\t\t\t\t<td><input id=\"address\" name=\"address\" type=\"text\" class=\"input\" value=\""+user.getUser_address()+"\"></td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "<tr>\n" +
                    "\t\t\t\t\t\t<td>部门： </td>\n" +
                    "\t\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t\t<select id=\"userOrAdmin\" name=\"userOrAdmin\">\n" +
                    "\t\t\t\t\t\t\t\t<option value=\"none\" selected disabled hidden>"+sqlSession.getMapper(DepartmentMapper.class).getDepartName(user.getDepart_id())+"</option>" +
                    "\t\t\t\t\t\t\t\t<option value=\"1\">A部门</option>\n" +
                    "\t\t\t\t\t\t\t\t<option value=\"2\">B部门</option>\n" +
                    "\t\t\t\t\t\t\t\t<option value=\"3\">C部门</option>\n" +
                    "\t\t\t\t\t\t\t</select>\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>" +
                    "\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t\t<a><input id=\"registerBtn\" name=\"registerBtn\" type=\"submit\" value=\"修 改\" class=\"btn\"></a>\n" +
                    "\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t<td></td>\n" +
                    "\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t</table>\n" +
                    "\t\t\t</form>\n" +
                    "\t\t</div>\n" +
                    "\t</body>\n" +
                    "\t<script>\n" +
                    "\tfunction ret(){\n" +
                    "\t\tif(confirm(\"确认修改吗？\")) {\n" +
                    "\t\t\treturn true;\n" +
                    "\t\t};\n" +
                    "\t\treturn false;\n" +
                    "\t}\n" +
                    "\t</script>" +
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
