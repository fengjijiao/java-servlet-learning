package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "RequestParamServlet", value = "/RequestParamServlet")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");//解决post中文乱码
        PrintWriter writer = response.getWriter();
        String realname= request.getParameter("realname");
        String password= request.getParameter("password");
        String sex= request.getParameter("sex");
        String[] language= request.getParameterValues("language");
        String city= request.getParameter("city");
        writer.write(
                "姓名："+realname+"<br>"+
                "密码："+password+"<br>"+
                "性别："+sex+"<br>"+
                "城市："+city+"<br>"+
                "使用过的语言："+ Arrays.toString(language)+"<br>"
        );
        writer.close();
        /**
         * 姓名：ads
         * 密码：ffff
         * 性别：male
         * 城市：shanghai
         * 使用过的语言：[JAVA, C]
         */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
