package us.fjj.servletlearning.onlineusers2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class MyBindingListener implements HttpSessionBindingListener {
    private String username;

    public MyBindingListener(String username) {
        super();
        this.username = username;
    }

    public MyBindingListener() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        //把用户名放入在线列表
        List<String> onLineUserList = (List) application.getAttribute("onLineUserList");
        //第一次使用前，需要初始化
        if (onLineUserList == null) {
            onLineUserList = new ArrayList<>();
        }
        onLineUserList.add(this.username);
        application.setAttribute("onLineUserList", onLineUserList);
        System.out.println("用户: "+ this.username + "已经成功加入在线用户列表");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        //从在线列表中删除用户名
        List<String> onLineUserList = (List<String>) application.getAttribute("onLineUserList");
        onLineUserList.remove(this.getUsername());
        application.setAttribute("onLineUserList", onLineUserList);
        System.out.println(this.getUsername()+"退出");
        System.out.println("当前在线人数："+onLineUserList.size());
    }
}
