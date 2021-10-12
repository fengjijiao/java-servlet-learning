package us.fjj.servletlearning.onlineusers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    public MySessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        List<String> onLineUserList = (List<String>) application.getAttribute("onLineUserList");
        //取得登录的用户名
        String username = (String) session.getAttribute("username");
        if (!"".equals(username) && username != null && onLineUserList != null && onLineUserList.size() > 0) {
            //从在线列表中删除用户名
            onLineUserList.remove(username);
            System.out.println(username+"已经退出了！");
            System.out.println("当前在线人数为："+onLineUserList.size());
        } else {
            System.out.println("会话已经销毁!");
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
        System.out.println("HttpSessionAttributeListener   attributeAdded()方法开始工作");
        //从上下文中获取已经登录账号的集合
        List<String> onLineUserList = (List<String>) sbe.getSession().getServletContext().getAttribute("onLineUserList");
        //在上下文中没有登录用户
        if (onLineUserList == null || onLineUserList.size() == 0) {
            onLineUserList = new ArrayList<String>();
        }
        String username = (String) sbe.getSession().getAttribute("username");
        //向已登录集合中添加当前账号
        onLineUserList.add(username);
        System.out.println("用户："+username+"成功加入在线用户列表");
        for (int i = 0; i < onLineUserList.size(); i++) {
            System.out.println(onLineUserList.get(i));
        }
        sbe.getSession().getServletContext().setAttribute("onLineUserList", onLineUserList);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
