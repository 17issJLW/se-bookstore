package web.com.servlet;

import com.google.gson.Gson;
import web.com.pojo.Book;
import web.com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wan Yu on 2020/4/15
 */
public class QueryAllBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        findAllBook(req, resp);
        doGet(req,resp);
    }

    private void findAllBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            BookServiceImpl bookService = new BookServiceImpl();
            List<Book> bookList =  bookService.findAllBook();

            Map<String,Object> bookListMap = new HashMap<String, Object>();

            bookListMap.put("BookList",bookList);

            Gson gson = new Gson();
            String bookJSON = gson.toJson(bookListMap);
            response.sendError(200,bookJSON);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            response.sendError(400,"{\"message\":\"查询失败\"}");
            throw new RuntimeException(e);
        }

    }
}
