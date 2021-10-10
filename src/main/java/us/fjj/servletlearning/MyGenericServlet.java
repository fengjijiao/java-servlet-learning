package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

//GenericServlet实现了Servlet接口，并提供除了service()方法以外的其他四个方法的简单实现。
//通过继承GenericServlet类创建Servlet，只需要重写service()方法即可，大大减少了创建Servlet的工作量。
//GenericServlet类中还提供了以下方法，用来获取Servlet的配置信息。
//String getInitParameter(String name)//返回名字为name的初始化参数的值，初始化参数在web.xml中进行配置。如果参数不存在则返回null。
//Enumeration<String> getInitParameterNames()//返回Servlet所有初始化参数的名字的枚举集合，没有初始化参数则返回一个空的集合。
//ServletContext getServletContext()//返回Servlet上下文对象的引用。
//String getServletName()//返回此Servlet实例的名称。
@WebServlet(name = "MyGenericServlet", value = "/MyGenericServlet")
public class MyGenericServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //设置字符集
        servletResponse.setContentType("text/html;charset=UTF-8");
        //使用PrintWriter.write()方法向前台页面输出内容
        PrintWriter writer = servletResponse.getWriter();
        writer.write("Welcome to MyGenericServlet Page");
        writer.close();
    }
}
