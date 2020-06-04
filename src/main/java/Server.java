

import com.google.gson.internal.$Gson$Preconditions;
import config.ServletMappingConfig;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import utils.HttpRequestImp;
import utils.HttpResponseImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    private Integer port = 8088;

    Map<String,Object>  urlServletMapping = new HashMap<>();

    /**
    * 初始化，读取配置文件，加载servlet类和对象，放入容器
    *
    */
    void init() throws DocumentException, MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SAXReader reader = new SAXReader();
        File directory = new File("");
        // 读取web.xml，根据配置，获取servlet类名，和相应的URL
        Document document = reader.read(new File(directory.getAbsolutePath()+"/web.xml"));
        Element rootElement = document.getRootElement();
        List<Element> servletList = rootElement.elements("servlet");

        Map<String,String> servletNameMap = new HashMap<>();
        for(Element e: servletList){
            servletNameMap.put(e.element("servlet-name").getTextTrim(),
                    e.element("servlet-class").getTextTrim());
        }
        URL url = new URL( "file:"+directory.getAbsolutePath()+"/src/main/java/web/");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
        List<Element> urlList = rootElement.elements("servlet-mapping");
        // 根据获取的类的位置，使用反射加载Servlet类，并且和URL建立映射关系，存入Map中。
        for(Element e: urlList){
            String name = e.element("servlet-name").getTextTrim();
            String className = servletNameMap.get(name);
            if(className != null){
                System.out.println("load:"+ className);
                Class<?> aClass = urlClassLoader.loadClass(className);
                HttpServlet servlet = (HttpServlet) aClass.newInstance();
                urlServletMapping.put(e.element("url-pattern").getTextTrim(),servlet);
                System.out.println("load:"+ className+" at:"+ e.element("url-pattern").getTextTrim());

            }
//            System.out.println(name);
        }
        System.out.println("init server finished");

    }

    void start() throws Exception {
        /**
         * 服务器启动
         */
        System.out.println("server start! at http://localhost:8088/");
        // 监听指定端口，初始化线程池
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executor = Executors.newFixedThreadPool(30);
        while(true){
            // 通过多线程的方式，在socket建立连接后，将具体任务交给一个线程来处理
            Socket socket = serverSocket.accept();
            executor.execute(()->{
//                Thread th=Thread.currentThread();
//                System.out.println(th.getName());
                try{
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    // 读取http报文，并创建HttpRequest和HttpResponse对象
                    byte[] recv = new byte[2048];
                    int length = inputStream.read(recv);
                    if(length != -1){
                        String httpRequest = new String(recv,0,length);
                        HttpRequestImp request = new HttpRequestImp(httpRequest);
                        HttpResponseImp response = new HttpResponseImp(outputStream);
                        // 分发请求到不同Servlet
                        dispatch(request,response);

                    }
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

        }

    }
    public void dispatch(HttpRequestImp request, HttpResponseImp response) throws ClassNotFoundException, ServletException, IOException, IllegalAccessException, InstantiationException {
        /**
         * 分发请求到不同的Servlet
         */
        try {
            // 根据URL，将request和response对象交给不同的Servlet处理
            HttpServlet myservlet = (HttpServlet) urlServletMapping.get(request.getRequestURI());
            // 如果匹配到url，调用servlet的init()和service方法，处理请求
            if(myservlet!=null){
                myservlet.init();
                myservlet.service(request, response);

            }else {
                // 如果没有匹配到url，返回404
                response.sendError(404,"{\"message\":\"Not Found\"}");
            }
            // 执行response对象的sendResponse方法，将响应报文发到客户端
            response.sendResponse();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.init();
        server.start();
    }
}
