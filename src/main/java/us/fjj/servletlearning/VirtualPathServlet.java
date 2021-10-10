package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

public class VirtualPathServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("访问的Servlet是："+this.getServletName());
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

//Tomcat中的缺省Servlet
//在Servlet安装目录里的\conf\web.xml文件中，注册了一个名称为org.apache.catalina.servlets.DefaultServlet的Servlet，并将它设置为缺省Servlet。
//Tomcat服务器中的Web应用没有缺省Servlet时，会将DefaultServlet作为其缺省Servlet。当客户端访问Tomcat服务器中某个静态HTML文件或者图片时，DefaultServlet会判断该HTML或者图片是否存在。若存在，则将数据以流的形式返回客户端，否则会报告404错误。
