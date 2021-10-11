package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Do2Servlet", value = "/Do2Servlet")
public class Do2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        //设置是否成功标识
        Boolean IsSuccess = true;
        String code1 = (String) getServletContext().getAttribute("code");
        if (!"".equals(code) && code != null && code.equalsIgnoreCase(code1) && "admin".equals(username) && "admin".equals(password)) {
            response.sendRedirect("SuccessServlet");
        } else if (!"admin".equals(username) || !"admin".equals(password)) {
            getServletContext().setAttribute("msg", "账号或密码错误！");
            IsSuccess = false;
        } else if ("".equals(code)||code == null || !code.equalsIgnoreCase(code1)) {
            getServletContext().setAttribute("msg", "验证码输入错误！");
            IsSuccess = false;
        }
        if (!IsSuccess) {
            //设置自动跳转的时间，存储在上下文中
            getServletContext().setAttribute("time", 5);
            //向request对象中设置属性requestArr，在重定向之后取值。
            request.setAttribute("requestAttr", "重定向中使用request域对象传递的数据");
            response.sendRedirect("RefreshServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
