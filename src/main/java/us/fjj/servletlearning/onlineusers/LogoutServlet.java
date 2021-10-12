package us.fjj.servletlearning.onlineusers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //退出登录操作，将此次session进行销毁
        //触发HttpSessionListener监听器的sessionDestroyed方法
        request.getSession().invalidate();
        //跳转回登录页面
        response.sendRedirect("login4.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
