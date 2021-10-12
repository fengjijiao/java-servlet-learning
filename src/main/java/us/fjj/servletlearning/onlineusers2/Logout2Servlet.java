package us.fjj.servletlearning.onlineusers2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Logout2Servlet", value = "/Logout2Servlet")
public class Logout2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        MyBindingListener loginedDemo = (MyBindingListener) request.getSession().getAttribute("onLineUserBindingListener");
//        List<String> onLineUserList = (List<String>) request.getServletContext().getAttribute("onLineUserList");
//        onLineUserList.remove(loginedDemo);
//        request.getServletContext().setAttribute("onLineUserList", onLineUserList);
        //退出登录操作，将此次session进行销毁
        //触发HttpSessionListener监听器的sessionDestroyed方法
        request.getSession().invalidate();
        //跳转回登录页面
        response.sendRedirect("login5.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
