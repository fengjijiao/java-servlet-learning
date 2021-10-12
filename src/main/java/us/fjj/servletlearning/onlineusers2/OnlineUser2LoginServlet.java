package us.fjj.servletlearning.onlineusers2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OnlineUser2LoginServlet", value = "/OnlineUser2LoginServlet")
public class OnlineUser2LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        //获取表单数据
        String username = request.getParameter("username");
        //查看当前会话是否已有账号登录
        MyBindingListener loginedDemo = (MyBindingListener) request.getSession().getAttribute("onLineUserBindingListener");
        //当前会话已有账号登录
        if ("".equals(username) || username == null) {
            System.out.println("非法操作，你没有输入用户名！");
            response.sendRedirect("login5.html");
        } else {
            if (loginedDemo != null) {
                String logined = loginedDemo.getUsername();
                System.out.println("你已经登录过了，请先退出账号后重新登录！");
                writer.write(
                        "<h1>Welcome</h1>"+
                           "<h3>你好，你已经登录了帐户："+logined+"</h3>"+
                                "<br>如需登录其他帐户，请先退出账号后重新登录！"
                        );
                //登录页面为填写内容
            } else {
                //将当前帐户加入会话
                request.getSession().setAttribute("onLineUserBindingListener", new MyBindingListener(username));
                writer.write(
                        "<h1>Welcome</h1>"+
                           "<h3>你好，"+username+"</h3>"
                );
            }
            //向页面输出结果
            //从上下文中获取已经登录的帐户的集合
            List<String> onLineUserList = (List<String>) request.getServletContext().getAttribute("onLineUserList");
            System.out.println("当前在线用户为：");
            if (onLineUserList != null) {
                for (int i = 0; i < onLineUserList.size(); i++) {
                    System.out.println(onLineUserList.get(i));
                }
            }
            System.out.println("************************");
            writer.write("<h1>当前在线人数为："+onLineUserList.size()+"</h1>");
            for (int i = 0; i < onLineUserList.size(); i++) {
                writer.write(onLineUserList.get(i)+"<br>");
            }
            writer.write("<a href='Logout2Servlet'>退出登录</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
