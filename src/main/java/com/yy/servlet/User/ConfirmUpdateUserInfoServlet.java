package com.yy.servlet.User;

import com.yy.pojo.User;
import com.yy.services.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//普通用户确认修改个人信息模块
@WebServlet("/confirmUpdateUserInfoServlet")
public class ConfirmUpdateUserInfoServlet extends HttpServlet {

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
        User user = (User)session.getAttribute("user");
        System.out.println(user);

        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String realName = req.getParameter("realName");
        String cardId = req.getParameter("cardId");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String depart = req.getParameter("userOrAdmin");
        //为了修改信息时不修改部门的时候，不会出现默认值格式类型转换错误
        System.out.println(depart);
        int departId;
        if(depart == null){
            departId = user.getDepart_id();
        }else {
            departId = Integer.parseInt(req.getParameter("userOrAdmin"));
        }

        User user1 = new User();
        user1.setId(user.getId());
        user1.setUser_name(userName);
        user1.setUser_password(passWord);
        user1.setUser_realname(realName);
        user1.setUser_cardid(cardId);
        user1.setUser_phone(phone);
        user1.setUser_address(address);
        user1.setDepart_id(departId);

        Integer result = userService.updateUser(user1);
        if(result != null){
            //修改成功
            resp.getWriter().print("<script language=\"javascript\">alert(\"修改成功,请重新登录！\");" +
                    "location.href='index.html'</script>");
        }else {
            //修改失败
            resp.getWriter().print("<script language=\"javascript\">alert(\"修改失败！\");" +
                    "location.href='/updateUserInfoServlet'</script>");
        }

    }
}
