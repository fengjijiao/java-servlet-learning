package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//Servlet Filter 又称Servlet过滤器，它是在Servlet2.3规范中定义的，能够对Servlet容器传给Web资源的request对象和response对象进行检查和修改。
//Filter不是Servlet，不能直接访问，它本身也不能生成request对象和response对象，它只能为Web资源提供以下过滤功能
//1.在Web资源被访问前，检查request对象，修改请求头和请求正文，或对请求进行预处理操作.
//2.将请求传递到下一个过滤器或目标资源
//3.在Web资源被访问后，检查response对象，修改响应头和响应正文。
//注意：过滤器并不是必须要将请求传递到下一个过滤器或目标资源，它可以自行对请求进行处理，并发送响应给客户端，也可以将请求转发或重定向到其他Web资源。

//Filter是Servlet规范中最实用的技术，通过它可以对服务器管理的所有web资源（例如：JSP、Servlet、静态HTML文件、静态图片等）进行拦截，从而实现一些特殊的功能，例如：用户的权限控制、过滤敏感词、设置统一编码格式等。


//Filter接口
//void init(FilterConfig config)//该方法用于初始化过滤器
//void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)//该方法完成实际的过滤操作，当客户端请求的URL与过滤器映射的URL匹配时，容器会先调用该方法对请求进行拦截。参数request和response表示请求和响应对象。参数chain代表当前Filter链对象，在该方法内部，调用chain.doFilter()方法，才能把请求交付给Filter链中下一个Filter或Web资源。
//void destroy()//该方法在销毁Filter对象之前，用于释放被Filter对象占用的资源。


//Filter的工作流程
//1.客户端请求访问容器内的Web资源。
//2.Servlet容器接收请求，并针对本次请求分别创建一个request对象和一个response对象。
//3.请求到达web资源前，先调用Filter的doFilter()方法，检查request对象，修改请求头和请求正文，或对请求进行预处理操作。
//4.在Filter的doFilter()方法内，调用FilterChain.doFilter()方法，将请求传递给下一个过滤器或目标资源。
//5.目标资源生成响应信息返回客户端之前，处理控制权会再次回到Filter的doFilter（）方法，执行FilterChain.doFilter()后的语句，检查response对象，修改响应头和响应正文。
//6.响应信息返回客户端。


//Filter生命周期
//Filter的生命周期分为3个部分：
//1.初始化阶段
//2.拦截和过滤阶段
//3.销毁阶段
//1.
//Servlet容器赋值加载和实例化Filter。容器启动时，读取web.xml或@WebFilter的配置信息对所有的过滤器进行加载和实例化
//加载和实例化完成之后，Servlet容器调用init()方法初始化Filter实例。在Filter的生命周期内，init()方法只执行一次
//2.
//该阶段是Filter生命周期中最重要的阶段。当客户端请求访问Web资源时，Servlet容器会根据web.xml或@WebFilter的过滤规则进行检查。当客户端请求的URL与过滤器映射匹配时，容器将对该请求的request对象、response对象、以及FilterChain对象以参数的形式传递给Filter的doFilter()方法，并调用该方法对请求/响应进行拦截和过滤。
//3.
//Filter对象创建后会驻留在内存中，直到容器关闭或应用被移除时销毁。销毁Filter对象之前，容器会先调用destroy()方法，释放过滤器占用的资源。在Filter的生命周期内，destroy()只执行一次。


//注册与映射Filter
//有2种方式
//1.通过web.xml配置
//2.通过@WebFilter注解配置

//1.通过web.xml配置
//在web.xml中,通过<filter>及其子元素注册Filter。

//2.使用@WebFilter注解进行配置
//@WebFilter注解具有如下的一些常用属性。以下所有属性均为可选属性，但value、urlPatterns、servletNames三者必须至少包含一个，且value和urlPatterns不能共存。如果同时指定，通常忽略value的值。
//filterName String //指定过滤器的注册名，等价于<filter-name>
//urlPatterns String[]//指定过滤器的URL匹配模式，等价于<url-pattern>标签
//value String[]//该属性等价于urlPatterns属性。但不能同时使用
//servletNames String[]//指定过滤器将应用于那些Servlet。取值是@WebServlet中的servletName的取值，或web.xml中<servlet-name>的取值。
//dispatcherTypes DispatcherType//指定过滤器拦截的资源被Servlet容器调用的方式。具体取值包括：ASYNC、ERROR、FORWARD、INCLUDE、REQUEST。
//initParams WebInitParam[]//指定一组过滤器初始化参数，等价于<init-param>标签
//asyncSupported boolean//声明过滤器是否支持异步操作模式，等价于<async-supported>标签
//description String//指定过滤器的描述信息，等价于<description>标签。
//displayName String//指定过滤器的显示名，等价于<display-name>标签。

/**
 * @WebFilter(
 *         dispatcherTypes = {
 *                 DispatcherType.REQUEST,
 *                 DispatcherType.FORWARD,
 *                 DispatcherType.INCLUDE,
 *                 DispatcherType.ERROR
 *         },
 *         asyncSupported = true,
 *         description = "过滤器",
 *         urlPatterns = {"/login2"},
 *         initParams = {
 *                 @WebInitParam(name="name", value="Welcome", description = "name的描述")
 *         },
 *         servletNames = {"FilterDemoServlet"},
 *         filterName = "MyFilter"
 * )
 */
//第一个FILTER过滤器
@WebFilter(
        dispatcherTypes = {
                DispatcherType.REQUEST,
                DispatcherType.FORWARD,
                DispatcherType.INCLUDE,
                DispatcherType.ERROR
        },
        urlPatterns = {"/FilterDemoServlet"}
)
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        //
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //chain.doFilter(request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<h1>hello, MyFilter</h1>");
    }
}
