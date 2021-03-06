package com.yy.servlet;

import com.yy.dao.AdminUserMapper;
import com.yy.dao.UserMapper;
import com.yy.pojo.AdminUser;
import com.yy.pojo.User;
import com.yy.services.impl.AdminUserServiceImpl;
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
import java.util.HashMap;
import java.util.Map;

//登录模块
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
//    private UserServiceImpl userService = new UserServiceImpl();
//    private AdminUserServiceImpl adminUserService = new AdminUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String userOrAdmin = req.getParameter("userOrAdmin");

        // 1-如果选中 普通用户 登录 （查询普通用户表）
        if(userOrAdmin.equals("普通用户")){
            //根据用户名获取该用户
            User user = sqlSession.getMapper(UserMapper.class).getUserByName(userName);
            if(user == null){
                //如果用户不存在，提示去注册！
                resp.getWriter().print("<script language=\"javascript\">alert(\"该用户不存在！请先去注册！\");" +
                    "location.href='index.html'</script>");
            }else {
                //如果用户存在，判断密码是否正确！
                Map<String,Object> map = new HashMap<>();
                map.put("userName",userName);
                map.put("passWord",passWord);
                User user1 = new User();
                user1 = sqlSession.getMapper(UserMapper.class).getUserByName(userName);
                Integer result = sqlSession.getMapper(UserMapper.class).checkUser(map);
                if(result != null){
                    //登录成功 将user保存到session
                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("user",user1);
                    //重定向至普通用户系统首页
                    resp.sendRedirect("/userMainServlet");
                }else {
                      //登录失败 提示密码错误
                      resp.getWriter().print("<script language=\"javascript\">alert(\"密码错误！\");" +
                        "location.href='index.html'</script>");
                }
            }
        }else {
        // 2-选中 管理员 登录 （查询管理员表）
            //查询管理员表中是否有该管理员
            AdminUser adminUser = sqlSession.getMapper(AdminUserMapper.class).getAdminUserByName(userName);
            if(adminUser == null){
                //该管理员不存在！
                resp.getWriter().print("<script language=\"javascript\">alert(\"该管理员不存在！\");" +
                        "location.href='index.html'</script>");
            }else {
                //该管理员存在，判断密码是否正确！
                Map<String,Object> map = new HashMap<>();
                map.put("userName",userName);
                map.put("passWord",passWord);
                AdminUser adminUser1 = new AdminUser();
                adminUser1.setAdmin_name(userName);
                adminUser1.setAdmin_password(passWord);
                Integer result = sqlSession.getMapper(AdminUserMapper.class).checkAdmin(map);
                if(result != null){
                    //登录成功 将user保存到session
                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("adminUser",adminUser1);
                    //重定向至管理员系统首页
                    resp.sendRedirect("/adminMainServlet");
                }else {
                    //登录失败 提示密码错误
                    resp.getWriter().print("<script language=\"javascript\">alert(\"密码错误！\");" +
                            "location.href='index.html'</script>");
                }
            }
        }

        //释放SqlSession资源
        sqlSession.close();
    }
}
