package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SecondTimeServlet", value = "/SecondTimeServlet")
public class SecondTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        //从session中获取上次访问的时间
        String value = (String) request.getSession().getAttribute("lastTime");

        //不是第一次访问(从LoginTimeSessionServlet过来)
        writer.write(
                "<h1>Welcome</h1><br>"+
                "<h3>你上次的时间是："+value+"</h3><br>"
                );


        //将当前时间写入Session域对象

        Date date = new Date();
        //时间的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化
        String sDate = sdf.format(date);
        //将当前时间赋值到session域对象中。
        request.getSession().setAttribute("lastTime", sDate);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
