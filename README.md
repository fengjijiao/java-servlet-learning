# java-servlet-learning
 
# Servlet三种创建方法
## 1.实现Servlet接口，重写全部方法。
## 2.继承GenericServlet抽象类，重写service()方法
## 3.继承HttpServlet抽象类，重写doGet()或doPost()方法。
# Servlet配置
## 1.通过web.xml配置
## 2.通过@WebServlet注解
## 两者优缺点
@WebServlet  
优点：直接在Servlet类中使用，代码量少，配置简单。每个类只关注自身业务逻辑，与其他Servlet类互不干扰，适合多人同时开发。  
缺点：Servlet较多时，每个Servlet的配置分布在各自的类中，不便于查找和修改。  

web.xml  
优点：几种管理Servlet的配置，便于查找和修改。  
缺点：代码较繁琐，可读性不强，不易于理解。  

# load-on-startup元素：控制Servlet启动优先级
load-on-startup是web.xml中的一个节点，是Servlet元素的子元素，用来标记Servlet容器启动时是否初始化当前Servlet，以及当前Servlet的初始化顺序。  
其值小于0或者没有指定表示在该Servlet首次被请求时才会被加载。  
大于或等于0时，表示容器在启动时就加载并初始化该Servlet，取值越小，优先级越高。  
取值相同时，容器就会自行选择顺序进行加载。  
与@WebServlet注解的loadOnStartup属性相对应。

# 虚拟路径映射
## 单一映射
1. 使用web.xml实现单一映射 
2. 使用@WebServlet实现单一映射

## 多重映射
1. 配置多个<servlet-mapping>元素
2. 配置多个<url-pattern>子元素
3. 在@WebServlet的urlPatterns属性中使用字符串数组

# 虚拟路径匹配规则
## 1.完全路径匹配
以/开始，不能包含通配符*。必须完全匹配。
例如: /myServlet
## 2.目录匹配
以/开头，并以/* 结尾的字符串。用于路径匹配
例如: /user/* 
## 3.扩展名匹配
以通配符* .开头的字符串。用于扩展名匹配
例如: * .do

## 4.缺省匹配
映射路径为/，表示这个Servlet为当前应用的缺省Servlet或默认Servlet。默认处理无法匹配到虚拟路径的请求。

# ServletConfig接口详情
## 获取方法(2种)
1. 直接从带参的init()方法中获取
2. 调用GenericServlet提供的getServletConfig()方法获得

# ServletContext接口详情
## 获取方法(4种)
1. 通过GenericServlet提供的getServletContext()方法
2. 通过ServletConfig提供的getServletContext()方法
3. 通过HttpSession提供的getServletContext()方法
4. 通过HttpServletRequest提供的getServletContext()方法

# HttpServletRequest接口

# Servlet请求转发
forward()和include()方法

# HttpServletResponse接口

# Servlet重定向
sendRedirect()方法

# Servlet Cookie

# Servlet Session

# Servlet Filter

# 过滤器链

# FilterConfig接口
与ServletConfig接口，用于在过滤器初始化期间向其传递信息。

# Servlet Listener(监听器)
