package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "DoServlet", value = "/DoServlet")
public class DoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String webName = (String)request.getAttribute("webName");
        String url = (String)request.getAttribute("url");
        String welcome = (String)request.getAttribute("welcome");
        if (webName != null) {
            writer.write("<h3>"+webName+"</h3>");
        }
        if (url != null) {
            writer.write("<h3>"+url+"</h3>");
        }
        if (welcome != null) {
            writer.write("<h3>"+welcome+"</h3>");
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String city = request.getParameter("city");
        String[] language = request.getParameterValues("language");
        writer.write(
                "用户名："+username+"<br>"+
                "密码："+password+"<br>"+
                "性别："+sex+"<br>"+
                "城市："+city+"<br>"+
                "使用的语言："+ Arrays.toString(language)+"<br>"
        );
        /**
         * iiiv
         * www.iiiv.org
         * welcome to this site.
         * 用户名：asd
         * 密码：sdad
         * 性别：男
         * 城市：上海
         * 使用的语言：[JAVA, PHP]
         */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
