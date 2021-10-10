package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

//通过实现Servlet接口创建Servlet
@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet implements Servlet {
    //Servlet实例被创建后，调用init()方法进行初始化，该方法只能被调用一次
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    //返回ServletConfig对象，该对象包含了Servlet的初始化参数
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //每次请求，都会调用一次service()方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //设置字符集
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.write("Welcome to MyServlet Page!");
        writer.close();
    }

    //返回关于Servlet的信息，例如：作者、版本、版权等
    @Override
    public String getServletInfo() {
        return null;
    }

    //Servlet被销毁时调用
    @Override
    public void destroy() {

    }
}