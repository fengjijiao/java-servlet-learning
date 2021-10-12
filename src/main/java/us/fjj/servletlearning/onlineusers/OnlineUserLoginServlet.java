package us.fjj.servletlearning.onlineusers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OnlineUserLoginServlet", value = "/OnlineUserLoginServlet")
public class OnlineUserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //修改request缓冲区字符集为UTF-8
        request.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        //获取表单数据
        String username = request.getParameter("username");
        //查看当前会话是否已有账号登录
        String logined = (String) request.getSession().getAttribute("username");
        //当前会话已有账号登录
        if ("".equals(username) || username == null) {
            System.out.println("非法操作，你没有输入用户名");
            response.sendRedirect("login4.html");
        } else {
            if (!"".equals(logined) && logined != null) {
                System.out.println("你已经登录，重复登录无效，请先退出当前账号重新登录！");
                writer.write(
                        "<h1>Welcome</h1>"+
                           "<h3>你好，你已经登录了账号："+logined+"，如果需要登录其他账号，请先退出当前账号重新登录！</h3>"
                );
                //登录页面为填写内容
            } else {
                //将当前账号加入会话中
                request.getSession().setAttribute("username", username);
                writer.write(
                        "<h1>Welcome</h1>"+
                           "<h3>"+username+"，欢迎你的到来！</h3>"
                );
            }
            //从上下文中获取已经登录账号的集合
            List<String> onLineUserList = (List<String>) request.getServletContext().getAttribute("onLineUserList");
            if (onLineUserList != null) {
                //向页面输出结果
                writer.write(
                        "<h3>当前在线人数为："+onLineUserList.size()+"</h3>"
                );
                for (int i = 0; i < onLineUserList.size(); i++) {
                    writer.write(onLineUserList.get(i)+"<br>");
                }
            }
            writer.write("<a href='LogoutServlet'>退出登录</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
