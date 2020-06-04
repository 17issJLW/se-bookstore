package web.com.servlet;

import com.google.gson.Gson;
import web.com.pojo.Book;
import web.com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Wan Yu on 2020/4/15
 */
public class GetBookRandomServlet extends HttpServlet {

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

            BookServiceImpl bookService = new BookServiceImpl();

            List<Book> bookList = bookService.getBookRandom();

            if(bookList == null) {
                resp.sendError(400,"{\"message\":\"未获取到书籍\"}");
                System.out.println("未获取到书籍");
                return;
            }

            Collections.shuffle(bookList);
            Map<String,Object> bookListMap = new HashMap<String, Object>();
            bookListMap.put("BookList",bookList);

            Gson gson = new Gson();
            String bookJSON = gson.toJson(bookListMap);
            resp.sendError(200,bookJSON);

            System.out.println("获取成功");

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("获取失败");
            resp.sendError(400,"{\"message\":\"获取失败\"}");
            throw new RuntimeException(e);
        }

        doGet(req, resp);
    }


}
