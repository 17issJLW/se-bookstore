package web.com.servlet;

import com.google.gson.Gson;
import web.com.pojo.Book;
import web.com.pojo.Orders;
import web.com.service.impl.BookServiceImpl;
import web.com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wan Yu on 2020/4/8
 */
public class QueryAllUnfinishedOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {

            resp.setContentType("text/json;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");

            OrderServiceImpl orderService = new OrderServiceImpl();
            BookServiceImpl bookService = new BookServiceImpl();

            List<Orders> orderList = orderService.findAllUnfinishedOrders();
            Map<String,Object> orderListMap = new HashMap<String, Object>();

            for(Orders order : orderList){
                Book book = bookService.findBookByID(String.valueOf(order.getBookId()));
                String id = String.valueOf(order.getId());

                Map<String,Object> detailOrderMap = new HashMap<String, Object>();

                detailOrderMap.put("Order",order);
                detailOrderMap.put("Book",book);

                orderListMap.put(id,detailOrderMap);
            }

            Gson gson = new Gson();
            String orderJSON = gson.toJson(orderListMap);

            resp.sendError(200,orderJSON);
            System.out.println("查询成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            resp.sendError(400,"{\"message\":\"查询失败\"}");
            throw new RuntimeException(e);
        }
        doGet(req,resp);
    }

}
