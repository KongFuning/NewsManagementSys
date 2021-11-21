package com.yy.servlet;

import com.yy.pojo.Newspaper;
import com.yy.pojo.User;
import com.yy.services.impl.NewspaperServiceImpl;
import com.yy.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//管理员添加新报刊
@WebServlet("/addNewsServlet")
public class AddNewsServlet extends HttpServlet {
    private NewspaperServiceImpl newspaperService = new NewspaperServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取添加报刊的信息
        String name = req.getParameter("name");
        String publisher = req.getParameter("publisher");
        String cycle = req.getParameter("cycle");
        Double offer = Double.parseDouble(req.getParameter("offer"));
        String content = req.getParameter("content");
        Integer depart = Integer.parseInt(req.getParameter("depart"));

        //添加到数据库
        Newspaper newspaper = new Newspaper();
        newspaper.setName(name);
        newspaper.setPublisher(publisher);
        newspaper.setCycle(cycle);
        newspaper.setOffer(offer);
        newspaper.setContent(content);
        newspaper.setClassify_id(depart);
        newspaperService.addNews(newspaper);
        resp.getWriter().print("<script language=\"javascript\">alert(\"添加成功！\");" +
                "location.href='AdminMain.jsp'</script>");
    }
}
