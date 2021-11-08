package com.yy.servlet;

import com.yy.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userMainServlet")
public class UserMainServlet extends HttpServlet {
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

        //生成主要页面
        StringBuilder sb = new StringBuilder();
        if(user != null){
            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n" +
                    "    <title>报刊订阅管理系统</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"./plugins/layui/css/layui.css\" media=\"all\">\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.jq22.com/jquery/font-awesome.4.6.0.css\">\n" +
                    "    <link rel=\"stylesheet\" href=\"./build/css/app.css\" media=\"all\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"layui-layout layui-layout-admin kit-layout-admin\">\n" +
                    "        <div class=\"layui-header\">\n" +
                    "            <div class=\"layui-logo\">报刊订阅管理系统</div>\n" +
                    "            <div class=\"layui-logo kit-logo-mobile\">K</div>\n" +
                    "            <ul class=\"layui-nav layui-layout-right kit-nav\">\n" +
                    "                <li class=\"layui-nav-item\">\n" +
                    "                    <a href=\"javascript:;\">\n" +
                    "                        "+user.getUser_name()+",欢迎您\n" +
                    "                    </a>\n" +
                    "                </li>\n" +
                    "                <li class=\"layui-nav-item\"><a href=\"/exitServlet\"><i class=\"fa fa-sign-out\" aria-hidden=\"true\"></i> 注销</a></li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "        <div class=\"layui-side layui-bg-black kit-side\">\n" +
                    "            <div class=\"layui-side-scroll\">\n" +
                    "                <div class=\"kit-side-fold\"><i class=\"fa fa-navicon\" aria-hidden=\"true\"></i></div>\n" +
                    "                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->\n" +
                    "                <ul class=\"layui-nav layui-nav-tree\" lay-filter=\"kitNavbar\" kit-navbar>\n" +
                    "                    <li class=\"layui-nav-item\">\n" +
                    "                        <a href=\"/userSubscribeNewsServlet\" data-url=\"/userSubscribeNewsServlet\" data-name=\"table\" kit-loader><i class=\"fa fa-plug\" aria-hidden=\"true\"></i><span> 订阅报刊</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li class=\"layui-nav-item\">\n" +
                    "                        <a href=\"javascript:;\" data-url=\"#\" data-name=\"form\" kit-loader><i class=\"fa fa-plug\" aria-hidden=\"true\"></i><span> 查询订阅</span></a>\n" +
                    "                    </li>\n" +
                    "                    <li class=\"layui-nav-item\">\n" +
                    "                        <a href=\"javascript:;\" data-url=\"#\" data-name=\"form\" kit-loader><i class=\"fa fa-plug\" aria-hidden=\"true\"></i><span> 统计</span></a>\n" +
                    "                    </li>\n" +
                    "                </ul>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"layui-body\" id=\"container\">\n" +
                    "            <!-- 内容主体区域 -->\n" +
                    "            <div style=\"padding: 15px;\">请点击左侧菜单...</div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <script type=\"text/javascript\">\n" +
                    "        var cnzz_protocol = ((\"https:\" == document.location.protocol) ? \" https://\" : \" http://\");\n" +
                    "        document.write(unescape(\"%3Cspan id='cnzz_stat_icon_1264021086'%3E%3C/span%3E%3Cscript src='\" + cnzz_protocol + \"s22.cnzz.com/z_stat.php%3Fid%3D1264021086%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E\"));\n" +
                    "    </script>\n" +
                    "    <script src=\"./plugins/layui/layui.js\"></script>\n" +
                    "    <script>\n" +
                    "        var message;\n" +
                    "        layui.config({\n" +
                    "            base: 'build/js/'\n" +
                    "        }).use(['app', 'message'], function() {\n" +
                    "            var app = layui.app,\n" +
                    "                $ = layui.jquery,\n" +
                    "                layer = layui.layer;\n" +
                    "            //将message设置为全局以便子页面调用\n" +
                    "            message = layui.message;\n" +
                    "            //主入口\n" +
                    "            app.set({\n" +
                    "                type: 'page'\n" +
                    "            }).init();\n" +
                    "            $('#pay').on('click', function() {\n" +
                    "                layer.open({\n" +
                    "                    title: false,\n" +
                    "                    type: 1,\n" +
                    "                    content: '<img src=\"/build/images/pay.png\" />',\n" +
                    "                    area: ['500px', '250px'],\n" +
                    "                    shadeClose: true\n" +
                    "                });\n" +
                    "            });\n" +
                    "        });\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "<script type=\"text/javascript\" src=\"js/myJS.js\"></script>\n" +
                    "</html>\n");
            resp.getWriter().write(sb.toString());
        }else {
            //会话失效
            resp.sendRedirect("index.html");
        }
    }
}
