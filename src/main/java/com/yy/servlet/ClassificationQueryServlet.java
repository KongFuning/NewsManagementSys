package com.yy.servlet;


import com.yy.pojo.AdminUser;
import com.yy.pojo.Newspaper;
import com.yy.pojo.User;
import com.yy.services.impl.OrdersServiceImpl;
import com.yy.services.impl.UserServiceImpl;

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
    UserServiceImpl userService = new UserServiceImpl();
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
                sb.append("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                        "\t<title>分类查询——人员</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\t<table id=\"topic_table\">");
                for (Integer userId : users) {
                    List<Newspaper> newspaperByUserId = ordersService.getNewspaperByUserId(userId);
                    sb.append("<tr><th>报刊代号</th><th>报刊名</th>\n" +
                            "\t\t<tr><td colspan=\"2\" style=\"font-weight:bold;text-align: left\" >用户："+
                            userService.getUserById(userId).getUser_name()+"</td></tr>");
                    for (Newspaper newspaper : newspaperByUserId) {
                        sb.append("<tr><td name=\"id\">"+newspaper.getId()+
                                "</td><td name=\"name\">"+newspaper.getName()+"</td></tr>\n");
                    }
                }
                sb.append("</table>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().write(sb.toString());

            }
            //2.按报刊查询
            if(classify == 2){
                //先查出所有订阅了的报刊的id
                List<Integer> allNewsId = ordersService.getAllNewsId();
                sb.append("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                        "\t<title>分类查询——报刊</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\t<table id=\"topic_table\">\n");
                for (Integer newsId : allNewsId) {
                    sb.append("\t\t<tr><td colspan=\"2\" style=\"font-weight:bold;text-align: left\" >报刊："+
                            ordersService.getNewspaperByNewsId(newsId).getName()+"</td></tr>");
                    sb.append("\t\t<tr><th>id</th><th>用户名</th>");
                    List<User> users = ordersService.getAllUsersOrderOneNew(newsId);
                    for (User user : users) {
                        sb.append("<tr><td name=\"id\">"+user.getId()+
                                "</td><td name=\"name\">"+user.getUser_name()+"</td></tr>");
                    }
                }
                sb.append("\t</table>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().write(sb.toString());
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
