package web.com.dao;

import web.com.pojo.Users;

/**
 * Created by Wan Yu on 2020/3/11
 */
public interface UserDao {
    //用户注册
    void userRegister(Users users);
    //用户登录
    Users userLogin(String id, String password);
    //用户查询
    Users userQuery(String id);
}
