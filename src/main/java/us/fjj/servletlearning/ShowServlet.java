package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ShowServlet", value = "/ShowServlet")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer count = (Integer) getServletContext().getAttribute("count");
        response.setContentType("text/html;charset=UTF-8");
        if (count != null) {
            response.getWriter().write("一共被访问了"+count+"次。");
        }else {
            response.getWriter().write("请先访问CountServlet!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
