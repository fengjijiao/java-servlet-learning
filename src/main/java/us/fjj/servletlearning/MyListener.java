package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


//Servlet Listener(监听器)
//监听器Listener是一个实现特定接口的java程序，这个程序专门用于监听另一个java对象的方法调用或属性改变，当被监听对象发生上述事件后，监听器某个方法将立即自动执行。
//监听器的相关概念
//事件：方法调用、属性改变、状态改变等
//事件源：被监听的对象（例如：request、session、servletContext）。
//监听器：用于监听事件源对象，事件源对象状态的变化都会触发监听器。
//注册监听器：将监听器与事件源进行绑定

//监听器的分类
//Servlet规范中定义了8个监听器接口，可以用于监听ServletContext、HttpSession和ServletRequest对象的生命周期和属性变化事件。开发Servlet监听器需要实现相应的监听器接口并重写接口中的方法。
//监听器Listener按照监听的事件划分，可以分为3类
//1.监听对象创建和销毁的监听器
//2.监听对象中属性变更的监听器
//3.监听HttpSession中的对象状态改变的监听器


//监听对象创建和销毁的监听器
//Servlet规范定义了监听ServletContext、HttpSession、HttpServletRequest这三个对象创建和销毁事件的监听器。
//事件源   监听器   监听器描述   创建和销毁方法  调用时机
//ServletContext ServletContextListener 用于监听ServletContext对象的创建与销毁过程  void contextInitialized(ServletContextEvent sce)/ void contextDestroyed(ServletContextEvent sce)   当创建ServletContext对象时/当销毁ServletContext对象时
//HttpSession  HttpSessionListener   用于监听HttpSession对象的创建与销毁过程   void sessionCreated(HttpSessionEvent se)/ void sessionDestroyed(HttpSessionEvent se)   当创建HttpSession对象时/当销毁HttpSession对象时
//ServletRequest   ServletRequestListener   用于监听ServletRequest对象的创建与销毁过程 void requestInitialized(ServletRequestEvent sre)/ void requestDestroyed(ServletRequestEvent src)  当创建ServletRequest对象时/当销毁ServletRequest对象时



//监听属性变更的监听器
//Servlet规范定义了监听ServletContext、HttpSession、HttpServletRequest这三个对象中的属性变更事件的监听器，这三个监听器分别是：ServletContextAttributeListener、HttpSessionAttributeListener、ServletRequestAttributeListener。
//这3个接口中都定义了3个方法，用来处理被监听对象中属性的增加，删除和替换事件。同一种事件在这3个接口中对应的方法名称完全相同，只是参数类型不同。
//事件源   监听器   监听器描述   方法   调用时机
//ServletContext ServletContextAttributeListener 用于监听ServletContext对象的属性的新增、移除和替换  public void attributeAdded(ServletContextAttributeEvent scae)/public void attributeRemoved(ServletContextAttributeEvent scae)/public void attributeReplaced(ServletContextAttributeEvent scae)   当ServletContext对象中新增一个属性时/当删除ServletContext对象中的一个属性时/但ServletContext对象中某个属性被替换时
//HttpSession HttpSessionAttributeListener 用于监听HttpSession对象的属性的新增、移除和替换 public void attributeAdded(HttpSessionBindingEvent hsbe)/public void attributeRemoved(HttpSessionBindingEvent hsbe)/public void attributeReplaced(HttpSessionBindingEvent hsbe) 当HttpSession对象中新增一个属性时/当删除HttpSession对象中一个属性时/当HttpSession对象中某个属性被替换时
//HttpServletRequest ServletRequestAttributeListener 用于监听HttpServletRequest对象的属性新增、移除和替换  public void attributeAdded(ServletRequestAttributeEvent srae)/public void attributeRemoved(ServletRequestAttributeEvent srae)/public void attributeReplaced(ServletRequestAttributeEvent srae) 当HttpServletRequest对象中新增一个属性时/当删除HttpServletRequest对象中的一个属性时/当HttpServletRequest对象中某个属性被替换时


//监听Session中对象状态改变的监听器
//Session中的对象可以有很多种状态：绑定到Session中、从Session中解除绑定、随Session对象持久化到存储设备中（钝化）、随Session对象从存储设备中恢复（活化）
//Servlet规范中定义了两个特殊的监听器接口，用来帮助对象了解自己在Session中的状态：HttpSessionBindingListener接口和HttpSessionActivationListener接口，实现这两个接口的类不需要进行注册。
//事件源   监听器   监听器描述   方法   调用时机
//HttpSession HttpSessionBindingListener  用于监听JavaBean对象绑定到HttpSession对象和从HttpSession对象解绑事件  void valueBound(HttpSessionBindingEvent event)/ void valueUnbound(HttpSessionBindingEvent event)  当对象被绑定（添加）到HttpSession对象中时/当对象从HttpSession对象中解除绑定(移除)时
//HttpSession HttpSessionActivationListener 用于监听HttpSession中对象活化和钝化的过程 void sessionWillPassivate(HttpSessionBindingEvent event)/void sessionDidActive(HttpSessionBindingEvent event)   当绑定到HttpSession对象中的对象将要随HttpSession对象被钝化之前/当绑定到HttpSession对象中的对象将要随HttpSession对象被活化之后


//注册监听器
//有2中方式
//1.在web.xml中注册监听器
//2.使用@WebListener注册监听器
//1.
//在web.xml中使用<listener>标签配置监听器，web容器会自动把监听器注册到事件源中。
//2.
//如下所示，实现对应的接口即可
//使用HttpSessionBindingListener和HttpSessionActivationListener时，不必进行注册，直接创建Java类实现这两个接口即可
@WebListener
public class MyListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public MyListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
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
