package com.yy.servlet.Admin;

import com.yy.pojo.AdminUser;
import com.yy.pojo.Newspaper;
import com.yy.services.impl.NewspaperServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//管理员报刊管理模块
@WebServlet("/adminNewsManageServlet")
public class AdminNewsManageServlet extends HttpServlet {
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

        //获取报刊集合
        List<Newspaper> newspapers = newspaperService.getAllNewspaper();
        StringBuilder sb = new StringBuilder(); // 生成主要页面
        //如果adminUser不为空，则表示会话生效
        if(adminUser != null){
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\t<head>\n" +
                    "\t\t<meta charset=\"UTF-8\">\n" +
                    "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                    "\t\t<title>新报刊录入</title>\n" +
                    "\t</head>\n" +
                    "\t<body>\n" +
                    "\t\t\n" +
                    "\t<table id=\"topic_table\">\n" +
                    "\t\t<tr><th>报刊代号</th><th>报刊名</th><th>出版社</th><th>出版周期</th><th>季度报价</th><th>内容介绍</th><th>分类编号</th><th>操作</th></tr>");
            for (Newspaper newspaper : newspapers) {
                sb.append("<tr><td>"+newspaper.getId()+"</td><td>"+newspaper.getName()+
                        "</td><td>"+newspaper.getPublisher()+
                        "</td><td>"+newspaper.getCycle()+
                        "</td><td>"+newspaper.getOffer()+"</td><td>"+newspaper.getContent()+
                        "</td><td>"+newspaper.getClassify_id()+
                        "</td><td><a href=\"/seeNewsServlet?id="+newspaper.getId()+"\" style=\"color: green; font-weight: bold;\">查看</a>/<a href=\"javascript:\" style=\"color: red; font-weight: bold;\">删除</a></td></tr>");
            }
            sb.append("\t</table>\n" +
                    "\t<a href=\"Admin_addNews.html\"><input id=\"addNews\" name=\"addNews\" type=\"button\" value=\"添加新报刊\"></a>\n" +
                    "\t</body>\n" +
                    "</html>");
            resp.getWriter().write(sb.toString());
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }
    }
}
