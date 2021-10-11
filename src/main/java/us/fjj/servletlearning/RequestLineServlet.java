package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RequestLineServlet", value = "/RequestLineServlet")
public class RequestLineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(
                "请求方式:"+request.getMethod()+"<br>"+
                "客户端的IP地址:"+request.getRemoteAddr()+"<br>"+
                "应用名称（上下文）:"+request.getContextPath()+"<br>"+
                "URI:"+request.getRequestURI()+"<br>"+
                "请求字符串:"+request.getQueryString()+"<br>"+
                "Servlet所映射的路径:"+request.getServletPath()+"<br>"+
                "客户端的完整主机名:"+request.getRemoteHost()+"<br>"
        );
        writer.close();
        //http://localhost:8080/servlet-learning/RequestLineServlet?a=1&b=2&c=3
        /**
         * 请求方式:GET
         * 客户端的IP地址:0:0:0:0:0:0:0:1
         * 应用名称（上下文）:/servlet-learning
         * URI:/servlet-learning/RequestLineServlet
         * 请求字符串:a=1&b=2&c=3
         * Servlet所映射的路径:/RequestLineServlet
         * 客户端的完整主机名:0:0:0:0:0:0:0:1
         */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
