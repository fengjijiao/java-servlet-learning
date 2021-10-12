package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//FilterChain过滤器链
//在Web应用中，可以部署多个Filter，若这些Filter都拦截同一目标资源，则它们就组成了一个Filter链。过滤器链中的每个过滤器负责特定的操作和任务，客户端的请求在这些过滤器之间传递，直到传递给目标资源。
//

//FilterChain接口
//javax.servlet包中提供了一个FilterChain接口，该接口由容器实现。容器将其实例化对象作为参数传入Filter对象的doFilter()方法中。Filter对象可以使用FilterChain对象调用链中下一个Filter的doFilter()方法，若该Filter是链中最后一个过滤器，则调用目标资源的service()方法。FilterChain接口中只有一个方法。
//void doFilter(ServletRequest request, ServletResponse response)//使用该方法可以调用过滤器链中的下一个Filter的doFilter（）方法，若该Filter是链中的最后一个过滤器，则调用目标资源的service()方法。
//注：在Filter.doFilter()方法中调用FilterChain.doFilter()方法的语句前后增加某些程序代码，就可以在Servlet进行响应的前后实现某些特殊功能，例如：权限控制、过滤敏感词、设置同一编码格式等。


//Filter链的拦截过程
//Filter链的拦截过程如下
//请求资源时，过滤器链中的过滤器依次对请求进行处理，并将请求传递给下一个过滤器，直到最后将请求传递给目标资源。发送响应信息时，则按照相反的顺序，对响应进行处理，直到将响应返回给客户端。
//过滤器并不是必须要将请求传递给下一个过滤器或目标资源，它可以自行对请求进行处理，并发送响应给客户端，也可以将请求转发给其他的目标资源
//注：过滤器链中任何一个Filter没有调用FilterChain.doFilter()方法，请求都不会到达目的资源。


//Filter链中Filter的执行顺序
//通过web.xml配置的Filter过滤器，执行顺序由<filter-mapping>标签的配置顺序决定。<filter-mapping>靠前，则Filter先执行，靠后则后执行。通过修改<filter-mapping>的顺序便可以修改Filter的执行顺序。
//通过@WebFilter注解配置的Filter过滤器，无法进行排序，若需要对Filter过滤器进行排序，建议使用web.xml进行配置。
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<h1>LoginServlet Context</h1>");
        /**
         * FirstChainFilter对请求进行处理
         * SecondChainFilter对请求进行处理
         * LoginServlet Context
         * SecondChainFilter对响应进行处理
         * FirstChainFilter对响应进行处理
         */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
