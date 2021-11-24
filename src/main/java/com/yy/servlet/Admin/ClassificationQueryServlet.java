package com.yy.servlet.Admin;


import com.yy.dao.DepartmentMapper;
import com.yy.dao.OrdersMapper;
import com.yy.dao.UserMapper;
import com.yy.pojo.AdminUser;
import com.yy.pojo.Department;
import com.yy.pojo.Newspaper;
import com.yy.pojo.User;
import com.yy.services.impl.DepartmentServiceImpl;
import com.yy.services.impl.OrdersServiceImpl;
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

//管理员分类查询模块
@WebServlet("/classificationQueryServlet")
public class ClassificationQueryServlet extends HttpServlet {
//    OrdersServiceImpl ordersService = new OrdersServiceImpl();
//    UserServiceImpl userService = new UserServiceImpl();
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
        AdminUser adminUser = (AdminUser)session.getAttribute("adminUser");


        int classify = Integer.parseInt(req.getParameter("classify"));
        StringBuilder sb = new StringBuilder();
        //会话生效
        if(adminUser != null){
            //1.按人员查询
            if(classify == 1){
                //先查出所有订阅了报刊的用户
                List<Integer> users = sqlSession.getMapper(OrdersMapper.class).getAllUsers();
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
                    List<Newspaper> newspaperByUserId = sqlSession.getMapper(OrdersMapper.class).getNewspaperByUserId(userId);
                    sb.append("<tr><td colspan=\"2\" style=\"font-weight:bold;text-align: left\" >用户："+
                            sqlSession.getMapper(UserMapper.class).getUserById(userId).getUser_name()+"</td></tr>\n" +
                            "\t\t<tr><th>报刊代号</th><th>报刊名</th>");
                    for (Newspaper newspaper : newspaperByUserId) {
                        sb.append("<tr><td name=\"id\">"+newspaper.getId()+
                                "</td><td name=\"name\">"+newspaper.getName()+"</td></tr>\n");
                    }
                    sb.append("<tr><td colspan=\"2\" ><hr style=\"height: 3px; border: #D4CBCB; background: #D4CBCB;\"></td></tr>");
                }
                sb.append("</table>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().write(sb.toString());

            }
            //2.按报刊查询
            if(classify == 2){
                //先查出所有订阅了的报刊的id
                List<Integer> allNewsId = sqlSession.getMapper(OrdersMapper.class).getAllNewsId();
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
                            sqlSession.getMapper(OrdersMapper.class).getNewspaperByNewsId(newsId).getName()+"</td></tr>");
                    sb.append("\t\t<tr><th>id</th><th>用户名</th>");
                    List<User> users = sqlSession.getMapper(OrdersMapper.class).getAllUsersOrderOneNew(newsId);
                    for (User user : users) {
                        sb.append("<tr><td name=\"id\">"+user.getId()+
                                "</td><td name=\"name\">"+user.getUser_name()+"</td></tr>");
                    }
                    sb.append("<tr><td colspan=\"2\" ><hr style=\"height: 3px; border: #D4CBCB; background: #D4CBCB;\"></td></tr>");
                }
                sb.append("\t</table>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().write(sb.toString());
            }
            //3.按部门查询
            if(classify == 3){
                //先查出所有的部门
                List<Department> allDepartment = sqlSession.getMapper(DepartmentMapper.class).getAllDepartment();
                sb.append("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/admin.css\">\n" +
                        "\t<title>分类查询——部门</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<table id=\"topic_table\">");
                for (Department department : allDepartment) {
                    sb.append("<tr><td colspan=\"2\" style=\"font-weight:bold;font-size:30px;text-align: left\" >部门："+
                            department.getName()+"</td></tr>");
                    List<User> allUserByDepartId = sqlSession.getMapper(UserMapper.class).getAllUserByDepartId(department.getId());
                    for (User user : allUserByDepartId) {
                        sb.append("\t<tr><td colspan=\"2\" style=\"font-weight:bold;text-align: left\" >用户："+
                                user.getUser_name()+"</td></tr>");
                        sb.append("\t<tr><th>报刊代号</th><th>报刊名</th>");
                        List<Newspaper> newspaperByUserId = sqlSession.getMapper(OrdersMapper.class).getNewspaperByUserId(user.getId());
                        for (Newspaper newspaper : newspaperByUserId) {
                            sb.append("<tr><td name=\"id\">"+newspaper.getId()+
                                    "</td><td name=\"name\">"+newspaper.getName()+"</td></tr>\n\n\n");
                        }
                        sb.append("<tr><td colspan=\"2\" ><hr style=\"height: 3px; border: #D4CBCB; background: #D4CBCB;\"></td></tr>");
                    }
                }
                sb.append("</table>\n" +
                        "</body>\n" +
                        "</html>");
                resp.getWriter().write(sb.toString());
            }
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }

        //释放SqlSession
        sqlSession.close();
    }
}
