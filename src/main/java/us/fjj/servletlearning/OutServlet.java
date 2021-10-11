package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

//在Servlet API中，定义了一个HttpServletResponse接口，它继承至ServletResponse接口。HttpServletResponse对象专门用来封装HTTP响应消息，简称response对象。
//Servlet容器会针对每次请求创建一个response对象，并把它作为参数传递给Servlet的service方法。Servlet处理请求后，会将响应信息封装到response对象中，并由容器解析后返回给客户端。
//由于HTTP响应消息由响应行、响应头、消息体三部分组成，所以HttpServletResponse接口定义了向客户端发送响应状态码、响应头、响应体的方法，下面我们将针对这些方法进行介绍。

//响应行的相关方法
//当Servlet返回响应消息时,需要在响应消息中设置状态码。因此，HttpServletResponse接口定义了发送状态码的方法。
//void setStatus(int status)//用来设置HTTP响应消息的状态码，并生成响应状态行。
//void sendError(int sc)//用来发送表示错误信息的状态码。

//响应头相关的方法
//HttpServletResponse接口中定义了一系列设置HTTP响应头的字段的方法。
//void addHeader(String name, String value)//用于增加响应头字段
//void setHeader(String name, String value)//用于设置响应头字段
//void addIntHeader(String name, int value)//用于增加值为int类型的响应头字段
//void setIntHeader(String name, int value)//用于设置值为int类型的响应头字段
//void setContentType(String type)//用于设置Servlet输出内容的MIME类型以及编码格式。
//void setCharacterEncoding(String charset)//用于设置输出内容使用的字符编码

//响应体相关的方法
//由于在HTTP响应消息中，大量的数据都是通过响应消息体传递的。因此ServletResponse遵循IO流传递大量数据的设计理念，在发送响应消息体时，定义了两个与输出流相关的方法。
//ServletOutputStream getOutputStream()//用于获取字节输出流对象。
//PrintWriter getWriter()//用于获取字符输出流对象。
//注意：getOutputStream()和getWrite()方法互相排斥，不可同时使用，否则会发生IllegalStateException异常。

//
@WebServlet(name = "OutServlet", value = "/OutServlet")
public class OutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        run2(request, response);
    }

    //使用字节流向页面输出
    private void run1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        OutputStream os = response.getOutputStream();
        byte[] bs = "中文welcome to OutServlet!".getBytes(StandardCharsets.UTF_8);//set UTF-8避免中文乱码
        os.write(bs);
        /**
         * 中文welcome to OutServlet!
         */
    }

    //使用字符流向页面输出
    private void run2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");//set UTF-8避免中文乱码
        response.setCharacterEncoding("UTF-8");//可去除
        response.getWriter().write("中文welcome to OutServlet!");
        //銝剜�𩥅elcome to OutServlet!
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
