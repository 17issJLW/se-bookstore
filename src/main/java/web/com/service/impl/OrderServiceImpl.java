package web.com.service.impl;

import web.com.dao.BookDao;
import web.com.dao.OrderDao;
import web.com.dao.UserDao;
import web.com.pojo.*;
import web.com.service.OrderService;
import web.com.utils.DaoFactory;
import java.util.Calendar;

import java.util.List;
import java.util.Map;

/**
 * Created by Wan Yu on 2020/3/11
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = DaoFactory.getInstance().createDao("com.dao.impl.OrderDaoImpl",OrderDao.class);


    public void addOrder(Users users, Book book, int bookNum) {
        orderDao.addOrder(users, book, bookNum);
    }

    public void deleteOrder(Orders orders) {
        orderDao.deleteOrder(orders);
    }

    public void createOrder(Orders orders, String userAddress, String userTel) {
        orderDao.createOrder(orders, userAddress, userTel);
    }

    public void changeBookNum(Orders orders, int bookNum) {
        orderDao.changeBookNum(orders, bookNum);
    }

    public List<Orders> findFinishedOrders(String userCID) {
        return orderDao.findFinishedOrders(userCID);
    }

    public List<Orders> findUnfinishedOrders(String userCID) {
        return orderDao.findUnfinishedOrders(userCID);
    }

    public List<Orders> findAllFinishedOrders() {
        return orderDao.findAllFinishedOrders();
    }

    public List<Orders> findAllUnfinishedOrders() {
        return orderDao.findAllUnfinishedOrders();
    }

    public Orders findOrderByID(String id) {
        return orderDao.findOrderByID(id);
    }
}
