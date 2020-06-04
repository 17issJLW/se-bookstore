package web.com.servlet;

import com.google.gson.Gson;
import web.com.pojo.Users;
import web.com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wan Yu on 2020/4/6
 */
public class QueryUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=UTF-8");

        try {
            UserServiceImpl userService = new UserServiceImpl();
            Users user = userService.userQuery(req.getParameter("ID"));

            Gson gson = new Gson();
            String userJSON = gson.toJson(user);
            resp.sendError(200,userJSON);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            resp.sendError(400,"{\"message\":\"查询失败\"}");
            throw new RuntimeException(e);
        }
    }
}
