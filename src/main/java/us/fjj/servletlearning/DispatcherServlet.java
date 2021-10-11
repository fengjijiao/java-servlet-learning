package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

//Web应用在处理客户端的请求时，经常需要多个Web资源共同协作才能生成响应结果。但由于Servlet对象无法直接调用其他Servlet的service方法，所以Servlet规范提供了2种解决方案：
//1.请求转发
//2.请求包含（了解）
//

//请求转发
//请求转发属于服务器行为。容器接收请求后，Servlet会先对请求做一些预处理，然后将请求传递给其他Web资源，来完成包括生成响应在内的后续工作。
//

//RequestDispatcher接口
//javax.servlet包中定义了一个RequestDispatcher接口，RequestDispatcher对象由Servlet容器创建，用于封装由路径所标识的Web资源。利用RequestDispatcher对象可以把请求转发给其他的Web资源。
//
//Servlet可以通过2种方式获得RequestDispatcher对象：
//1.调用ServletContext的getRequestDispatcher(String path)方法，参数path指定目标资源的路径，必须为绝对路径。
//2.调用ServletRequest的getRequestDispatcher(String path)方法，参数path指定目标资源的路径，可以为绝对路径，也可以为相对路径。
//注：servletContext与servletRequest：生命周期不同、作用域不同、Web应用中数量不同、实现数据共享的方式不同

//RequestDispatcher接口中提供了以下方法
//void forward(ServletRequest request, ServletResponse response)//用于将请求转发给另一个Web资源。该方法必须在响应提交给客户端之前被调用，否则将抛出IllegalStateException异常。
//void include(ServletRequest request, ServletResponse response)//用于将其他的资源作为当前响应内容包含进来

//请求转发的特点
//1.请求转发不支持跨域访问，只能跳转到当前应用中的资源
//2.请求转发后，浏览器地址栏中的URL不会发生改变，因此浏览器不知道在服务器内部发生了转发行为，更无法得知转发的次数。
//3.参与请求转发的Web资源之间共享同一request对象和response对象。
//4.由于forward()方法会先清空response缓冲区，因此只有转发到最后一个Web资源时，生成的响应才会被发送到客户端。


//request域对象
//request是Servlet的三大域对象之一，它需要与请求转发配合使用，才可以实现动态资源间的数据传递。
//在ServletRequest接口中定义了一系列操作属性的方法，如下表
//void setAttribute(String name, Object o)//将java对象与属性名绑定，并将它作为一个属性存放到request对象中。参数name为属性名，o为属性值。
//Object getAttribute(String name)
//void removeAttribute(String name)
//Enumeration getAttributeNames()
@WebServlet(name = "DispatcherServlet", value = "/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //尝试在请求转发前向response缓冲区写入内容，最后在页面查看是否展示.
        writer.write("<h1>这是转发前在响应信息内的内容！</h1>");
        request.setAttribute("webName", "iiiv");
        request.setAttribute("url", "www.iiiv.org");
        request.setAttribute("welcome", "welcome to this site.");
        //转发
        request.getRequestDispatcher("/DoServlet").forward(request, response);
//        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
