package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

//获取ServletConfig对象一般有两种方法
@WebServlet(name = "GetServletConfigServlet", value = "/GetServletConfigServlet")
public class GetServletConfigServlet extends HttpServlet {
    //1.直接从带参数的init方法中提取
    private ServletConfig servletConfig;

    //注：在HttpServlet中本身可用第2种方式获取ServletConfig对象，对init方法的重写也不是必须的。仅作演示第1种
    @Override
    public void init(ServletConfig config) throws ServletException {
        //从带参数的init方法中，提取ServletConfig对象
        this.servletConfig = config;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //获得Servlet的名字
        writer.write(""+this.servletConfig.getServletName());
        //or
        //2.调用GenericServlet提供的getServletConfig()方法获得
//        ServletConfig servletConfig = this.getServletConfig();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
