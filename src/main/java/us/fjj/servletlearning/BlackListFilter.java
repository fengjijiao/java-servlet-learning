package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Enumeration;


//FilterConfig接口
//javax.servlet包中提供了一个FilterConfig接口，它与ServletConfig接口相似，用于在过滤器初始化期间向其传递信息。
//FilterConfig接口由容器实现，容器将它作为参数传入过滤器的init()方法中。通过filterConfig对象就可以获得Filter的初始化参数。
//在FilterConfig接口中，定义了4个方法
//String getInitParameter(String name)//根据初始化参数名name，返回对应的初始化参数值。
//Enumeration getInitParameterNames()//返回过滤器的所有初始化参数名的枚举集合。
//ServletContext getServletContext()//返回Servlet上下文对象的引用
//String getFilterName()//返回过滤器的名称

//示例
public class BlackListFilter implements Filter {
    private FilterConfig fConfig;
    public void init(FilterConfig config) throws ServletException {
        this.fConfig = config;
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(request, response);
        response.setContentType("text/html;charset=UTF-8");
        Boolean success = true;
        //获取前台登录的账号信息
        String name= request.getParameter("username");
        //获取过滤器中的初始化参数
        Enumeration<String> blackListNames = fConfig.getInitParameterNames();
        //判断前台登录账号是否为空
        if (name == null || "".equals(name)) {
            response.getWriter().write("用户名不能为空！");
        } else {
            //循环遍历黑名单
            while (blackListNames.hasMoreElements()) {
                //若登录账号是黑名单账号则不允许登录
                if (fConfig.getInitParameter(blackListNames.nextElement()).equals(name)) {
                    success = false;
                }
            }
            if (success) {
                chain.doFilter(request, response);
            } else {
                response.getWriter().write("<h1>温馨提示</h1><h3>你的账号存在风险，请重置后再试。</h3>");
            }
        }
    }
}
