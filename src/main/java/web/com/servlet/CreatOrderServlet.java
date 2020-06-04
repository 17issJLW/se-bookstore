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
public class CreatOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        createOrder(req, resp);
        doGet(req,resp);

    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            response.setContentType("text/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            String token = request.getHeader("Token");

            if(token == null || token.equals("")){
                System.out.println("请先登录");
                response.sendError(400,"{\"message\":\"未登录\"}");
                return;
            }

            String orderID = request.getParameter("OrderID");
            String userAddress = request.getParameter("UserAddress");
            String userTel = request.getParameter("UserTel");

            if(userAddress.equals("") || userTel.equals("")){
                System.out.println("请完善订单信息！");
                return;
            }

            OrderServiceImpl orderService = new OrderServiceImpl();
            Orders order = orderService.findOrderByID(orderID);

            orderService.createOrder(order, userAddress, userTel);
            System.out.println("订单生成成功");
            response.sendError(200,"{\"message\":\"订单生成成功\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(400,"{\"message\":\"订单生成失败\"}");
            System.out.println("订单生成失败");
        }
    }




}
