package web.com.servlet;

import web.com.pojo.Book;
import web.com.pojo.Orders;
import web.com.service.impl.BookServiceImpl;
import web.com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wan Yu on 2020/4/15
 */
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=UTF-8");
        try {

            String token = req.getHeader("Token");

            if(token == null || token.equals("")){
                System.out.println("请先登录");
                resp.sendError(400,"{\"message\":\"未登录\"}");
                return;
            }

            String bookID = req.getParameter("BookID");

            BookServiceImpl bookService = new BookServiceImpl();

            Book book = bookService.findBookByID(bookID);
            if(book == null){
                System.out.println("未查找到该书籍");
                resp.sendError(200,"{\"message\":\"未查找到该书籍\"}");
                return;
            }

            bookService.deleteBook(bookID);

            System.out.println("已删除该书籍");
            resp.sendError(200,"{\"message\":\"已删除该书籍\"}");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(400,"{\"message\":\"删除该书籍失败\"}");
            System.out.println("删除该书籍失败");
        }

        doGet(req,resp);
    }
}
