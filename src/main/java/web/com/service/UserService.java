package web.com.service;

import web.com.pojo.Users;

/**
 * Created by Wan Yu on 2020/4/6
 * 用户管理
 */
public interface UserService {

    //用户注册
    void userRegister(Users users);

    //用户登录
    Users userLogin(String userName, String password);

    //用户查询
    Users userQuery(String id);

}
