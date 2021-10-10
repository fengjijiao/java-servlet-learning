package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//Web应用中的所有Servlet共享同一个ServletContext对象，不同Servlet之间可用通过ServletContext对象实现数据通信，因此ServletContext对象也被称作Context域对象。
//域对象是服务器在内存上创建的存储空间，该空间用于不同动态资源（例如：Servlet、JSP）之间传递与共享数据。


//获取ServletContext对象
//共4中方法
//1.通过GenericServlet提供的getServletContext()方法
//ServletContext servletContext = this.getServletContext();
//2.通过ServletConfig提供的getServletContext()方法
//ServletContext servletContext = this.getServletConfig().getServletContext();
//3.通过HttpSession提供的getServletContext()方法
//ServletContext servletContext = req.getSession().getServletContext();
//4.通过httpServletRequest提供的getServletContext()方法
//ServletContext servletContext = req.getServletContext();
//注：后两种方法了解即可

//ServletContext的应用
//ServletContext接口定义了一组方法，Servlet可以使用这些方法与容器进行通信。
//ServletContext的应用主要有以下3个：
//1.获取上下文初始化参数
//2.实现Servlet之间的数据通信
//3.读取Web应用下的资源文件

//1.获取上下文初始化参数
//使用ServletContext对象获取Web应用的上下文初始化参数，分为2步：
//(1)设置上下文初始化参数
//(2)调用接口中的方法获取初始化参数
//
//(1)设置上下文初始化参数
//通过web.xml中的<context-param>元素可以为Web应用设置一些全局的初始化参数，这些参数被称为上下文初始化参数。
//与Servlet的初始化参数不同，应用中的所有Servlet都共享同一个上下文初始化参数。在Web应用的整个生命周期中，上下文初始化参数会一直存在，并且可以随时被任意一个Servlet访问。
//见web.xml
//(2)调用接口中的方法获取初始化参数
//Servlet容器启动时，会为容器内每个Web应用创建一个ServletContext对象，并将<context-param>元素的上下文初始化参数以键值对的形式存入该对象中，因此我们可以通过ServletContext的相关方法获取到这些初始化参数。


//2.实现数据通讯
//在Servlet中，调用ServiceContext接口的setAttribute()方法可以创建一些属性，这些属性被存放在ServletContext对象中。应用中所有Servlet都可以对这些属性进行访问和操作，通过它们可以实现应用内不同Servlet之间的数据通讯。
//
//数据通讯的相关方法
//void setAttribute(String name, Object object)//把一个Java对象与一个属性名绑定，并将它作为一个属性存放到ServletContext中。
//void removeAttribute(String name)//从ServletContext中移除属性名为name的属性
//Object getAttribute(String name)//根据指定的属性名name，返回ServletContext中对应的属性值。


//ServletContext属性与上下文初始化参数对比
//a.创建方式：
//ServletContext的属性通过调用ServletContext接口的setAttribute()方法创建。
//上下文初始化参数通过web.xml使用<context-param>元素配置。
//b.可进行的操作
//ServletContext的属性可以通过ServletContext接口的方法进行读取、新增、修改、移除等操作。
//上下文初始化参数在容器启动后只能被读取。
//c.生命周期\
//ServletContext中属性的生命周期从创建开始，到该属性被移除（remove）或者关闭结束。
//上下文初始化参数的生命周期，从容器启动开始，到Web应用被卸载或者容器关闭结束
//d.作用
//使用ServletContext中的属性可以实现Servlet之间的数据通讯
//使用上下文初始化参数无法实现数据通讯。

//示例
//见CountServlet和ShowServlet


//3.读取Web应用下的资源文件
//在实际开发中，有时会需要读取Web应用中的一些资源文件，如配置文件和日志文件等。为此，ServletContext接口定义了一些读取Web资源的方法。
//Set getResourcePaths(String path)//返回一个Set集合，该集合中包含资源目录中的子目录和文件的名称。
//String getRealPath(String path)//返回资源文件的真实路径（文件的绝对路径）。
//URL getResource(String path)//返回映射到资源文件的URL对象。
//InputStream getResourceAsStream(String path)//返回映射到资源文件的InputStream输入流对象。
//注：上表中参数path代表资源文件的虚拟路径，它以正斜线/开始，/表示当前Web应用的根目录。

//例3
//如何使用ServletContext对象读取资源文件?
//在servletDemo的src目录中，创建一个名称为db.properties的文件，文件中输入如下所示的配置信息。
//name=哈哈
//url=www.fjj.com
//desc=哈哈，欢迎你
//创建一个名称为ReadServlet的Servlet类。

@WebServlet(name = "ReadContextServlet", value = "/ReadContextServlet", initParams = {
        @WebInitParam(name="server", value = "tomcat"),
        @WebInitParam(name="version", value = "10.0.12")
})
public class ReadContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        ServletContext servletContext = this.getServletContext();
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String initParamName = initParameterNames.nextElement();
            String initParamValue = servletContext.getInitParameter(initParamName);
            writer.write(initParamName+" "+initParamValue+"<br>");
        }
        writer.close();
        /**
         * appname Fox
         * appurl https://www.fjj.com
         */
        //输出中并不包含server和version等initparams（初始化参数），因为：initparams被放在ServletConfig中，而ServletContext虽然包含的方法与ServletConfig相同，但是仅包含上下文(全局)初始化参数。
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
