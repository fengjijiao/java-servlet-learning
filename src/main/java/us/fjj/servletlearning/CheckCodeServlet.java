package us.fjj.servletlearning;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

//使用java生成验证码图片
@WebServlet(name = "CheckCodeServlet", value = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 120;
        int height = 30;
        //在内存中生成图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //先获取画笔对象
        Graphics2D g = (Graphics2D) image.getGraphics();
        //设置灰色
        g.setColor(Color.GRAY);
        //画填充的矩形
        g.fillRect(0,0,width-1, height-1);
        //准备数据，随机4字符
        String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //设置颜色
        g.setColor(Color.YELLOW);
        //设置字体
        g.setFont(new Font("宋体", Font.BOLD, 20));
        StringBuilder code = new StringBuilder();
        List<String> list = new ArrayList<String>();
        Random random = new Random();
        int x = 20;
        int y = 20;
        for (int i =0;i<4;i++) {
            //获取正负30之间的角度
            int angle = random.nextInt(60) - 30;
            double radian = angle * Math.PI /180;
            g.rotate(radian, x,y);
            //获取下标
            int index = random.nextInt(words.length());
            //返回指定下标位置的字符串，随机获取下标
            char ch = words.charAt(index);
            //将字符存入字符数组
            char[] chc = {ch};
            //使用字符数组构造字符串
            String str = new String(chc);
            //将构造好的字符串添加进list集合中
            list.add(str);
            //写字符串
            g.drawString(""+ch, x,y);
            g.rotate(-radian, x,y);//画笔旋转回原位
            x+=20;
        }
        for (int i=0;i<list.size();i++) {
            code.append(list.get(i));
        }
        //将验证码存入上下文
        getServletContext().setAttribute("code", code.toString());
        //设置颜色
        g.setColor(Color.GREEN);
        int x1,x2,y1,y2;
        //画干扰线
        for (int i=0;i<4;i++) {
            x1 = random.nextInt(width);
            y1 = random.nextInt(height);
            x2 = random.nextInt(width);
            y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        //输出到客户端
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
