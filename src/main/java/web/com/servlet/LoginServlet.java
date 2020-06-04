package web.com.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import web.com.pojo.Users;
import web.com.service.UserService;
import web.com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wan Yu on 2020/4/6
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            String id = req.getParameter("ID");
            String password = req.getParameter("Password");
            UserServiceImpl userService = new UserServiceImpl();
            Users users = userService.userLogin(id, password);
            if(users == null) {
                resp.sendError(200,"{\"status\":\"0\",\"message\":\"用户名或密码不正确\"}");
                System.out.println("用户名或密码不正确");
                return;
            } else {

                //保存登录信息
                Gson gson = new Gson();
                //对象转换为JSON格式
                String userJSON = gson.toJson(users);
                JsonObject jsonObject = new JsonParser().parse(userJSON).getAsJsonObject();
                //添加Token
                jsonObject.add("Token",new Gson().toJsonTree(id));
                userJSON = gson.toJson(jsonObject);
                //状态码返回200，同时将含Token的JSON格式的用户信息返回给前端
                resp.sendError(200,userJSON);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //状态码返回400，同时将报错信息返回给前端
            resp.sendError(400,"{\"message\":\"登录失败\"}");
            throw new RuntimeException(e);
        }

        doGet(req,resp);
    }
}
