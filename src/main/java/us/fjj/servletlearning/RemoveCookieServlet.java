package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//删除Cookie
@WebServlet(name = "RemoveCookieServlet", value = "/RemoveCookieServlet")
public class RemoveCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Cookie
        Cookie cookie = new Cookie("lastTime", "");
        //设置有效时间为0,删除Cookie
        cookie.setMaxAge(0);
        //设置有效路径，必须要与删除的Cookie路径一致
        cookie.setPath("/");
        //回写
        response.addCookie(cookie);
        //页面重定向
        response.sendRedirect("LoginTimeServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
