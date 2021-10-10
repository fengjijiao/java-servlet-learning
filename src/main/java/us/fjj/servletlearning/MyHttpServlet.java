package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

//HttpServlet继承了GenericServlet抽象类，用于开发基于HTTP协议的Servlet程序。由于Servlet主要用来处理HTTP的请求和响应，所以通常情况下，编写的Servlet类都继承自HttpServlet。
//在HTTP/1.1协议中共定义了7种请求方式，即：GET、POST、HEAD、PUT、DELETE、TRACE和OPTIONS。
//HttpServlet针对这7种请求方式分别定义了7种方法，即：doGet(),doPost(),doHead(),doPut(),doDelete(),doTrace()和doOptions()。
//HttpServlet重写了service()方法，该方法会先获取客户端的请求方式，然后根据请求方式调用对应的doXXX()方法。

//示例
//由于我们使用的请求方式主要是GET和POST，所以通过继承HttpServlet类创建Servlet时，只需要重写doGet()或doPost()。
@WebServlet(name = "MyHttpServlet", value = "/MyHttpServlet")
public class MyHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("Welcome to MyHttpServlet Page!");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
//当Servlet容器接收到请求后，容器会将请求的URL减去当前应用的上下文路径，使用剩余的字符串作为映射URL与Servlet虚拟路径进行匹配，匹配成功后将请求交给相应的Servlet进行处理。
//以servletDemo为例，若URL为http://localhost:8080/servletDemo/myServlet，其应用上下文是servletDemo，容器会将http://localhost:8080/servletDemo去掉，使用剩余的/myServlet与Servlet虚拟路径进行匹配。

//匹配规则
//Servlet虚拟路径匹配规则有以下4种：
//1.完全路径匹配
//以/开头，不能包含通配符*。
// /myServlet
//2.目录匹配
//以/开头，并以/*结尾的字符串，用于匹配路径。
// /user/*
// /*
//3.扩展名匹配
//以通配符*.开头的字符串。用于扩展名匹配
// *.do
// *.action
// *.jsp
//4.缺省匹配（默认匹配）
// 表示这个Servlet为当前应用的缺省Servlet或默认Servlet，默认处理无法匹配到虚拟路径的请求。

//特别注意：目录匹配和扩展名匹配不能混合使用，即/rest/*.do这种写法是不正确的。

//匹配优先级
//全路径匹配（精确匹配）>目录匹配>扩展名匹配>缺省匹配（默认匹配）。
//Servlet容器会从优先级高的虚拟路径开始匹配，匹配成功后会立即将请求交给相应的Servlet进行处理，不会再关注其他虚拟路径是否匹配成功。
