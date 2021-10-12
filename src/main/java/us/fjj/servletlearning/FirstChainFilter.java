package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

public class FirstChainFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("FirstChainFilter对请求进行处理<br>");
        chain.doFilter(request, response);
        writer.write("FirstChainFilter对响应进行处理<br>");
    }
}
