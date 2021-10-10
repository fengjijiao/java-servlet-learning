package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//初始化参数既可以在注解中配置也可在web.xml中配置。
//1.
@WebServlet(name = "InitParametersTestServlet", value = "/InitParametersTestServlet", initParams = {
        @WebInitParam(name="firstname", value = "Mike"),
        @WebInitParam(name="secondname", value = "Foll")
})
//2.在web.xml中
public class InitParametersTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
//        writer.write(""+this.getInitParameter("firstname")+" "+this.getInitParameter("secondname"));
        ServletConfig servletConfig = this.getServletConfig();
        String servletName = this.getServletName();
        writer.write("servlet name: "+servletName+"<br>");
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        //遍历集合获取初始化参数名称
        while (initParameterNames.hasMoreElements()) {
            //获取初始化参数名称
            String initParamName = initParameterNames.nextElement();
            //获取相应的初始参数值
            String initParamValue = servletConfig.getInitParameter(initParamName);
            //向页面输出
            writer.write(initParamName+":"+initParamValue+";");
        }
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
