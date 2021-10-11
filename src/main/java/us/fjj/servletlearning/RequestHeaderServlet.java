package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name = "RequestHeaderServlet", value = "/RequestHeaderServlet")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("test<br>");
        Enumeration<String> headers = request.getHeaderNames();
        writer.write("ContentType: "+request.getContentType()+"<br>");
        writer.write("ContentLength: "+request.getContentLength()+"<br>");
        writer.write("CharacterEncoding: "+request.getCharacterEncoding()+"<br>");
        while(headers.hasMoreElements()) {
            String key = headers.nextElement();
            System.out.println(key);
            writer.write(key+": "+request.getHeader(key)+"<br>");
        }
        writer.close();
//         * test
//         ContentType: nullContentLength: -1CharacterEncoding: UTF-8host: localhost:8080
//         connection: keep-alive
//         cache-control: max-age=0
//         sec-ch-ua: "Chromium";v="94", "Google Chrome";v="94", ";Not A Brand";v="99"
//         sec-ch-ua-mobile: ?0
//         sec-ch-ua-platform: "Windows"
//         dnt: 1
//         upgrade-insecure-requests: 1
//         user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36
//         accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*//*;q=0.8,application/signed-exchange;v=b3;q=0.9
//        sec-fetch-site: none
//        sec-fetch-mode: navigate
//        sec-fetch-user: ?1
//        sec-fetch-dest: document
//        accept-encoding: gzip, deflate, br
//        accept-language: zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7
//        cookie: JSESSIONID=6FC6BF47290A19F5271B278A297C6FDC

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
