package web.com.servlet;

import web.com.pojo.Orders;
import web.com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wan Yu on 2020/4/8
 */
public class UpdateOrderBookNumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=UTF-8");

        try {

            String id = null;
            String cookie = req.getHeader("Cookie");
            if(cookie.contains("userID")){
                id = cookie.split("userID=")[1];
            }

            if(id == null){
                System.out.println("请先登录");
                resp.sendError(400,"{\"message\":\"未登录\"}");
                return;
            }

            String orderID = req.getParameter("OrderID");
            int bookNum = Integer.parseInt(req.getParameter("BookNum"));

            OrderServiceImpl orderService = new OrderServiceImpl();
            Orders order = orderService.findOrderByID(orderID);
            orderService.changeBookNum(order,bookNum);

            System.out.println("已更新该订单");
            resp.sendError(200,"{\"message\":\"已更新该订单\"}");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(400,"{\"message\":\"更新该订单失败\"}");
            System.out.println("更新该订单失败");
        }

        doGet(req,resp);
    }
}
