package web.com.servlet;

import web.com.pojo.Book;
import web.com.pojo.Users;
import web.com.service.impl.BookServiceImpl;
import web.com.service.impl.OrderServiceImpl;
import web.com.service.impl.UserServiceImpl;
import web.com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wan Yu on 2020/4/8
 */
public class AddOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        try {
            //获取请求头部信息中Token信息
            String token = req.getHeader("Token");
            if(token == null || token.equals("")){
                //未得到，返回状态码、当前状态和信息
                resp.sendError(200,"{\"status\":\"0\",\"message\":\"未登录\"}");
                return;
            }
            //得到进行下一步业务
            UserServiceImpl userService = new UserServiceImpl();
            Users users = userService.userQuery(token);

            if(users == null) {
                System.out.println("未查询到用户信息");
                resp.sendError(400,"{\"message\":\"未查询到用户信息\"}");

            } else {
                String bookID = req.getParameter("BookID");
                int bookNum = Integer.parseInt(req.getParameter("BookNum"));

                BookServiceImpl bookService = new BookServiceImpl();
                OrderServiceImpl orderService = new OrderServiceImpl();
                Book book = bookService.findBookByID(bookID);
                orderService.addOrder(users,book,bookNum);

                System.out.println("已添加到订单");
                resp.sendError(200,"{\"message\":\"已添加到订单\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(400,"{\"message\":\"添加到订单失败\"}");
            System.out.println("添加到订单失败");
        }

        doGet(req,resp);
    }


}
