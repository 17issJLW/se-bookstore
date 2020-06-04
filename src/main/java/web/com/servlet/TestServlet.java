package web.com.servlet;

import web.com.pojo.Book;
import web.com.service.impl.BookServiceImpl;
import web.com.service.impl.OrderServiceImpl;

/**
 * Created by Wan Yu on 2020/3/18
 */

/*
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("json"));
        resp.sendError(200,"{\"message\":\"okk\"}");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println(req.getParameter("p1"));
    }
}
*/

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import java.util.List;

public class TestServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getParameter("FindByAuthor"));

        System.out.println("get");

    }





    private void findByAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        try {
            BookServiceImpl bookService = new BookServiceImpl();
            List<Book> bookList = bookService.findBookByAuthor(request.getParameter("FindByAuthor"));
            //System.out.println(bookList);
            request.setAttribute("bookList",bookList);
            request.getRequestDispatcher("test.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            throw new RuntimeException(e);
        }

    }




    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("post");

        //add(request,response);
        findByAuthor(request,response);

        doGet(request, response);
    }


}
