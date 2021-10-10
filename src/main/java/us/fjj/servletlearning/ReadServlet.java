package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "ReadServlet", value = "/ReadServlet")
public class ReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        //获取相对路径中的输入流对象
        InputStream ins = getServletContext().getResourceAsStream("/WEB-INF/db.properties");
        //获取输入流
        Properties pro = new Properties();
        //加载
        pro.load(ins);
        String name = pro.getProperty("name");
        String url = pro.getProperty("url");
        String desc = pro.getProperty("desc");
        writer.write("用户名："+name+"；地址："+url+"；描述："+desc+"<br>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
