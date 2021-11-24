package com.yy.servlet.Admin;

import com.yy.dao.UserMapper;
import com.yy.pojo.User;
import com.yy.services.impl.UserServiceImpl;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//管理员添加新用户
@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
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

        //获取添加用户的信息
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String realName = req.getParameter("realName");
        String cardId = req.getParameter("cardId");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Integer depart = Integer.parseInt(req.getParameter("depart"));

        System.out.println(userName + " " + passWord + " " + realName + " " + cardId + " " + phone + " "
        + address + " " + depart);

        User userByName = sqlSession.getMapper(UserMapper.class).getUserByName(userName);
        //先判断用户名和密码是否为空
        if(userName.equals("") || passWord.equals("")){
            resp.getWriter().print("<script language=\"javascript\">alert(\"用户名或密码为空！\");" +
                    "location.href='Admin_addUser.html'</script>");
        }else if(userByName!=null){
            //数据库中已存在该用户
            resp.getWriter().print("<script language=\"javascript\">alert(\"该用户已存在！\");" +
                    "location.href='Admin_addUser.html'</script>");
        }else {
            //添加至数据库
            User user = new User();
            user.setUser_name(userName);
            user.setUser_password(passWord);
            user.setUser_realname(realName);
            user.setUser_cardid(cardId);
            user.setUser_phone(phone);
            user.setUser_address(address);
            user.setDepart_id(depart);

            sqlSession.getMapper(UserMapper.class).addUserAll(user);
            resp.getWriter().print("<script language=\"javascript\">alert(\"添加成功！\");" +
                    "location.href='AdminMain.jsp'</script>");
        }

        //释放SqlSession
        sqlSession.close();

    }
}
