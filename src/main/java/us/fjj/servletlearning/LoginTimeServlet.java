package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

//会话技术
//常用的会话技术分为两种
//1.Cookie:客户端会话技术
//2.Session:服务端会话技术

//Cookie Api
//Cookie c = new Cookie("url", "www.iiiv.org")

//HttpServletResponse接口和HttpServletRequest接口也都定义了与Cookie相关的方法
//void addCookie(Cookie cookie)//用于在响应头中增加一个相应的Set-Cookie头字段。
//Cookie[] getCookies()//用于获取客户端提交的Cookie
//javax.servlet.http.Cookie类中提供了一系列获取或设置Cookie的方法。
//int getMaxAge()//用于获取指定cookie的最大有效时间。
//String getName()//用于获取cookie的名称
//String getPath()//用于获取cookie的有效路径
//boolean getSecure()//如果浏览器只通过安全协议发送Cookie，则返回true;如果浏览器可以使用任何协议发送Cookie，则返回false;
//String getValue()//用于获取cookie的值。
//int getVersion()//用于获取cookie遵守的协议版本。
//void setMaxAge(int expiry)//用于设置Cookie的最大有效时间，以秒为单位。取值为正表示Cookie在经过指定时间后过期。取值为负时表示Cookie不会被持久存储，在Web浏览器退出时删除。取值为0，表示删除该Cookie.
//void setPath(String uri)//用于指定Cookie的路径
//void setSecure(boolean flag)//用于设置浏览器是否只能使用安全协议（如HTTPS或SSL）发送Cookie。
//void setValue(String newValue)//用于设置Cookie的值。

//Cookie的使用细节
//使用Cookie开发时需要注意以下细节：
//1.一个Cookie只能标识一种信息，它至少包含一个名称（NAME）和一个值（VALUE）。
//2.如果创建了一个Cookie，并发送到浏览器，默认情况下它是一个会话级别的Cookie。用户退出浏览器时就会被删除。如果希望将Cookie存到磁盘上，则需要调用setMaxAge(int maxAge)方法设置最大有效时间，以秒为单位。
//3.使用setMaxAge(0)手动删除Cookie时，需要使用setPath方法指定Cookie的路径，且该路径必须与创建Cookie时的路径保持一致。
//

//Cookie的缺点
//Cookie虽然可以解决服务器跟踪用户状态的问题，但是它具有以下缺点：
//1.在HTTP请求中，Cookie是明文传递的，容易泄露用户信息，安全性不高。
//2.浏览器可以禁用Cookie，一旦被禁用，Cookie将无法正常工作。
//3.Cookie对象中只能设置文本（字符串）信息。
//4.客户端浏览器保存Cookie的数量和长度是有限的。

//示例
@WebServlet(name = "LoginTimeServlet", value = "/LoginTimeServlet")
public class LoginTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获取所有Cookie，判断是否是第一次访问
         * 2.如果是第一次访问，输出欢迎，记录当前时间，回写到浏览器
         * 3.如果不是第一次访问，获取时间，输出到浏览器，记录当前时间，回写浏览器
         * 记录当前时间，回写浏览器
         */
        //设置字符中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        //通过指定cookie名称来查找cookie, Cookie c = new Cookie("last", "当前时间");
        Cookie cookie = getCookieByName(cookies, "lastTime");
        //判断，如果cookie==null。说明是第一次访问
        if (cookie == null) {
            //输出欢迎，记录当前时间，回写浏览器
            response.getWriter().write("<h1>Welcome</h1>\n"+"<h3>That is ok!</h3>");
        } else {
            //获取Cookie的值，输出浏览器，记录当前时间，回写浏览器
            String value = cookie.getValue();
            //输出浏览器(cookie的值中含有” “，需要进行解码)
            response.getWriter().write("<h1>Welcome</h1>\n"+"<h3>上次访问时间："+ URLDecoder.decode(value, "UTF-8")+"</h3><br>"+
                    "<a href='RemoveCookieServlet'>清除Cookie</a>");
        }
        //记录当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String sDate = sdf.format(date);
        //回写浏览器
        //使用cookie回写（Cookie中的值包含" "，需要进行编码）
        Cookie c = new Cookie("lastTime", URLEncoder.encode(sDate, "UTF-8"));
        //设置有效时间为1天
        c.setMaxAge(60*60*24);//s
        //设置有效路径
        c.setPath("/");
        //回写
        response.addCookie(c);
    }

    Cookie getCookieByName(Cookie[] cookies, String name) {
        if (cookies == null) return null;
        for (Cookie c:
             cookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
