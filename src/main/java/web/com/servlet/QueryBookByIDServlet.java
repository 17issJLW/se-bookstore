package web.com.servlet;

import com.google.gson.Gson;
import web.com.pojo.Book;
import web.com.service.impl.BookServiceImpl;
import web.com.service.impl.OrderServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Wan Yu on 2020/4/6
 */
public class QueryBookByIDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        findByID(req, resp);
        doGet(req,resp);
    }

    private void findByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");


            BookServiceImpl bookService = new BookServiceImpl();
            Book book =  bookService.findBookByID(request.getParameter("ID"));

            Gson  gson = new Gson();
            String bookJSON = gson.toJson(book);
            if(bookJSON == null){
                response.sendError(400,"{\"message\":\"未查询到内容\"}");
            } else {
                response.sendError(200,bookJSON);
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(400,"{\"message\":\"查询失败\"}");
            System.out.println("查询失败");
            throw new RuntimeException(e);
        }

    }
}
