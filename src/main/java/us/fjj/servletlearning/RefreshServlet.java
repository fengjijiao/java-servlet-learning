package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "RefreshServlet", value = "/RefreshServlet")
public class RefreshServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object requestAttr = request.getAttribute("requestAttr");
        //获取存在上下文中的跳转时间
        int times = (int) getServletContext().getAttribute("time");
        //获取存在上下文中的错误信息
        String msg = (String) getServletContext().getAttribute("msg");
        //设置3个头信息，禁用浏览器缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("text/html;charset=UTF-8");
        String title = msg+"，即将在"+times+"秒后跳转到登录页";
        Calendar cale = Calendar.getInstance();
        //将Calendar类型转换成Date类型
        Date tasktime = cale.getTime();
        //设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化输出
        String nowTime = df.format(tasktime);
        //只要倒计时时间没有到0，就直至输出下面内容
        if (times != 0) {
            response.getWriter().write("<html>\n" +
                    "<head><title>"+title+"</title></head>\n"+
                    "<body>"+
                    "当前时间是："+nowTime+"\n" +
                    "<h1>通过requestArr传递的数据是："+requestAttr+"</h1>");
            //倒计时
            times--;
            //将倒计时的时间重新存入上下文中覆盖原来的值
            getServletContext().setAttribute("time", times);
            //通过refresh头完成页面刷新
            response.setHeader("refresh", "1;url=RefreshServlet");
        } else {
            //倒计时归零，则跳转到登录页面
            response.sendRedirect("login2.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
