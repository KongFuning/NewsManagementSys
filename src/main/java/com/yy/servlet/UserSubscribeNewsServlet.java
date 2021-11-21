package com.yy.servlet;

import com.yy.pojo.AdminUser;
import com.yy.pojo.Newspaper;
import com.yy.pojo.User;
import com.yy.services.impl.NewspaperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/userSubscribeNewsServlet")
public class UserSubscribeNewsServlet extends HttpServlet {
    NewspaperServiceImpl newspaperService = new NewspaperServiceImpl();
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

        //获取报刊集合
        List<Newspaper> newspapers = newspaperService.getAllNewspaper();
        StringBuilder sb = new StringBuilder(); // 生成主要页面
        //如果User不为空，则表示会话生效
        if(user != null){
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "\t<meta charset=\"UTF-8\">\n" +
                    "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                    "\t<title>订阅报刊</title>\n" +
                    "</head>\n" +
                    "<body onload=\"iniEvent()\">\n" +
                    "<form action=\"/userConfirmSubscribeNewsServlet\" method=\"post\" name=\"myForm\">\n" +
                    "\t<table id=\"topic_table\">\n" +
                    "\t\t<tr><th>报刊代号</th><th>报刊名</th><th>季度报价</th><th>订阅</th>");
            for (Newspaper newspaper : newspapers) {
                sb.append("<tr><td name=\"id\">"+newspaper.getId()+
                        "</td><td name=\"name\">"+newspaper.getName()+
                        "</td><td name=\"price\">"+newspaper.getOffer()+
                        "</td><td><input type=\"checkbox\" name=\"one\"  value=\""+newspaper.getOffer()+"\"><input type=\"hidden\" name=\"hidden\" value=\""+newspaper.getId()+"\"></td>");
            }
            sb.append("<tr><td>合计:</td><td colspan=\"2\"><input id=\"ids\" name=\"ids\" type=\"text\"></td><td><p><span id=\"sumMoney\"></span></p></td>");
            sb.append("\t</table>\n" +
                    "\t<input name=\"addNews\" type=\"submit\" value=\"订阅\">\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "<script type=\"text/javascript\" src=\"js/jquery-1.4.2.min.js\"></script>\n" +
                    "<script>\n" +
                    "\tfunction calculateNewsId() {\n" +
                    "\t\tvar ids = [];\n" +
                    "\t\tvar chkItems = document.getElementsByName(\"one\");\n" +
                    "\t\tvar idItems = document.getElementsByName(\"hidden\");\n" +
                    "\t\tfor (var i = 0; i < chkItems.length; i++) {\n" +
                    "\t\t\tif (chkItems[i].checked) {\n" +
                    "\t\t\t\tids.push(idItems[i].value);\n" +
                    "\t\t\t\tdocument.getElementById(\"ids\").value = ids;\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t\tconsole.log(ids);\n" +
                    "\t}\n" +
                    "\tfunction iniEvent() {\n" +
                    "\t\tvar chkItems = document.getElementsByName(\"one\");\n" +
                    "\t\tfor (var i = 0; i < chkItems.length; i++) {\n" +
                    "\t\t\tchkItems[i].onclick = calculateNewsId;\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "</script>\n" +
                    "</html>");

            PrintWriter out = resp.getWriter();
            out.write(sb.toString());

        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }
    }
}
