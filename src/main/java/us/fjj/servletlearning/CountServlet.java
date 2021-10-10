package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//演示ServletContext的属性作用
@WebServlet(name = "CountServlet", value = "/CountServlet")
public class CountServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("count", 0);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        assert this.getServletContext() == super.getServletContext();//相等
        //获取count值
        Integer count = (Integer) servletContext.getAttribute("count");
        //存入到域对象中
        servletContext.setAttribute("count", ++count);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("Welcome, <a href=\"ShowServlet\">显示统计信息</a>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
