package web.com.service.impl;

import web.com.pojo.Users;
import web.com.service.UserService;

import web.com.dao.UserDao;
import web.com.utils.DaoFactory;

/**
 * Created by Wan Yu on 2020/4/6
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = DaoFactory.getInstance().createDao("com.dao.impl.UserDaoImpl",UserDao.class);

    public void userRegister(Users users) {
        userDao.userRegister(users);
    }

    public Users userLogin(String id, String password) {
        return userDao.userLogin(id, password);
    }

    public Users userQuery(String id) {
        return userDao.userQuery(id);
    }
}
