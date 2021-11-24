package com.yy.servlet.Admin;

import com.yy.dao.NewspaperMapper;
import com.yy.pojo.AdminUser;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteNewsServlet")
public class DeleteNewsServlet extends HttpServlet {
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

        if(adminUser != null){
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("你要删除的报刊id: " + id);
            //根据id删除报刊
            if(sqlSession.getMapper(NewspaperMapper.class).deleteNewsById(id) != 0){
                //删除成功
                System.out.println("删除第"+id+"号成功！");
                //重定向至首页
                resp.sendRedirect("/adminMainServlet");
            }else {
                //删除失败
                System.out.println("删除失败！");
                //重定向至首页
                resp.sendRedirect("/adminMainServlet");
            }
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }

        //释放SqlSession
        sqlSession.close();
    }
}
