package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

//Session是服务端会话技术，当浏览器访问Web服务器的资源时坏，服务器可以为每个用户浏览器创建一个Session对象，每个浏览器独占一个Session对象。
//由于每个浏览器独占一个Session，所以用户在访问服务器资源时，可以把数据保存在各自的Session中。当用户再次访问该服务器中的其他资源时，其他资源可以从Session中取出数据，为用户服务。

//Session的工作原理
//Session虽然属于服务端会话技术，但是它的实现离不开客户端浏览器和Cookie的支持，
//1.当客户端第一次请求会话对象时，服务器会创建一个Session对象，并将该Session对象分配一个唯一的SessionID（用来标识这个Session对象）；
//2.服务器将SessionID以Cookie（Cookie名为：JSESSIONID，值为SessionID的值）的形式发给客户端浏览器
//3.客户端浏览器再次发送HTTP请求时，会将携带SessionID的Cookie一同发送给服务器。
//4.服务器从请求中读取SessionID,然后根据SessionID找到对应的Session对象。

//注意：
//1.流程中的Cookie是容器自动生成的，它的maxAge属性取值为-1,表示仅当前浏览器有效。
//2.浏览器关闭时，对应的Session并没有失效，但此时与此Session对应的Cookie已失效，导致浏览器无法再通过Cookie获取服务器端的Session对象。
//3.同一浏览器的不同窗口共享同一Session对象，但不同浏览器窗口之间不能共享Session对象。


//Session与Cookie对比
//Session与Cookie都属于会话技术，都能帮助服务器保存和跟踪用户状态，但两者也存在差异。
//1.存储位置不同
//2.大小和数量限制不同，session的大小和数量一般不受限制。浏览器对cookie的大小和数量有限制。
//3.存放数据类型不同，Session中保存的是对象，cookie中保存的是字符串
//4.安全性不同,cookie明文传递，易于分析和欺诈，session存放于服务器端，安全性较高
//5.对服务器造成的压力不同，Session保存在服务端，每个用户独占一个Session，若并发量大，则会占用大量资源。
//6.跨域支持上不同,session不支持，cookie支持

//Session API
//通过HttpServletRequest.getSession()方法可以获得HttpSession对象。
//HttpSession session=request.getSession();

//HttpSession接口定义了一系列对Session对象操作的方法。
//long getCreationTime()//返回创建Session的时间。
//String getId()//返回获取Session的唯一ID,
//long getLastAccessedTime()//返回客户端上一次发送与此Session关联的请求的时间
//int getMaxInactiveInterval()//返回在无任何操作的情况下，Session失效的时间，以s为单位
//ServletContext getServletContext()//返回Session所属的ServletContext对象
//void invalidate()//使Session失效
//void setMaxInactiveInterval(int interval)//指定在无任何操作的情况下，Session失效的时间，以s为单位。负数表示永不失效

//设置Session过期时间
//Session对象在服务器中驻留一段时间后没有被使用，就会被销毁，这个时间就是Session的过期时间。
//Session的默认过期时间为30分钟，我们可以通过如下2种方式设置过期时间。
//1.使用<session-config>元素
//在web.xml中，使用session-config及其子元素<session-timeout>可以配置Session的默认过期时间。

//2.调用setMaxInactiveInterval()方法
//调用该方法设置过期时间，以秒为单位，零和负数表示会话永远不会过期。
//request.getSession().setMaxInactiveInterval(100);


//Session的生命周期
//Session对象创建
//Session对象在容器第一次调用request.getSession()方法时创建。
//值得注意的是，当客户端访问的Web资源是HTML，CSS，图片等静态资源时，服务器不会创建Session对象。

//Session对象销毁
//Session对象在如下3种情况下会被销毁
//1.Session过期
//2.调用session.invalidate()方法，手动销毁Session.
//3.服务器关闭或者应用被卸载

//Session域对象
//session对象是一种域对象，它可以对属性进行操作，进而实现会话中请求之间的数据通讯和数据共享。
//在javax.servlet.http.HttpSession接口中定义了一系列操作属性的方法
//void setAttribute(String name, Object o)//将一个java对象与一个属性名绑定，并将它作为一个属性存放到Session对象中。
//Object getAttribute(String name)
//void removeAttribute(String name)
//Enumeration getAttributeNames()//用于返回Session对象中的所有属性名的枚举集合。

//session,request和ServletContext合称为Servlet的三大域对象

//示例
//使用session记录上次访问时间
@WebServlet(name = "LoginTimeSessionServlet", value = "/LoginTimeSessionServlet")
public class LoginTimeSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Welcome</h1><br><h3>That is OK!</h3>");
        //记录当前时间
        Date date = new Date();
        //时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //会话创建时间
        Date creationTime = new Date(request.getSession().getCreationTime());
        //会话上次关联的时间
        Date lastAccessedTime = new Date(request.getSession().getLastAccessedTime());
        //格式化
        String sDate = sdf.format(date);
        //将当前时间赋值到session域对象中
        request.getSession().setAttribute("lastTime", sDate);
        //设置会话失效时间
        request.getSession().setMaxInactiveInterval(100);//s
        //将session中各个信息输出到页面
        writer.write(
                "<h3>当前时间:"+sDate+"</h3><b3>"+
                "<h3>当前会话的SessionID:"+request.getSession().getId()+"</h3><br>"+
                "<h3>创建此会话的时间为:"+sdf.format(creationTime)+"</h3><br>"+
                "<h3>Session的上次关联时间为:"+sdf.format(lastAccessedTime)+"</h3><br>"+
                "<h3>会话最大空闲时间间隔:"+request.getSession().getMaxInactiveInterval()+"</h3><br>"
                );
        String url = response.encodeURL("SecondTimeServlet");
        writer.write("<a href="+url+">再次访问</a>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
