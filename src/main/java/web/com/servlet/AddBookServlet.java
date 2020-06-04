package web.com.servlet;

import web.com.pojo.Book;
import web.com.service.impl.BookServiceImpl;
import web.com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Wan Yu on 2020/4/6
 */
public class AddBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        add(req, resp);
        doGet(req,resp);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            response.setContentType("text/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            Book book = new Book();
            book.setId(Integer.parseInt(request.getParameter("id")));
            book.setName(request.getParameter("name"));
            book.setAuthor(request.getParameter("author"));
            book.setLanguage(Long.parseLong(request.getParameter("language")));
            book.setCategory(Long.parseLong(request.getParameter("category")));
            book.setCdrom(Long.parseLong(request.getParameter("cdrom")));
            book.setCommend(Long.parseLong(request.getParameter("commend")));
            book.setContent(request.getParameter("content"));
            book.setPrice(Double.parseDouble(request.getParameter("price")));
            book.setOnSaleTime(request.getParameter("onSaleTime"));
            book.setGoodPrice(Long.parseLong(request.getParameter("goodPrice")));
            book.setPublishName(request.getParameter("publishName"));
            book.setPublishAddress(request.getParameter("publishAddress"));
            book.setBookNum(Long.parseLong(request.getParameter("bookNum")));
            book.setPictureURL(request.getParameter("pictureUrl"));

            BookServiceImpl bookService = new BookServiceImpl();

            bookService.addBook(book);
            response.sendError(200,"{\"message\":\"添加成功\"}");
            System.out.println("添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(400,"{\"message\":\"添加失败\"}");
            System.out.println("添加失败");
        }
    }
}
