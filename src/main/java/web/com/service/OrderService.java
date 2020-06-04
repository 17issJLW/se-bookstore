package web.com.service;

import web.com.pojo.Book;
import web.com.pojo.Orders;
import web.com.pojo.Users;

import java.util.List;

/**
 * Created by Wan Yu on 2020/3/11
 * 订单管理
 */
public interface OrderService {

    //添加书籍到订单
    void addOrder(Users users, Book book, int bookNum);

    //从订单移出书籍
    void deleteOrder(Orders orders);

    //修改书籍数量
    void changeBookNum(Orders orders, int bookNum);

    //生成订单
    void createOrder(Orders orders, String userAddress, String userTel);

    //获取指定用户未生成订单列表
    List<Orders> findUnfinishedOrders(String userCID);

    //获取指定用户已完成订单列表
    List<Orders> findFinishedOrders(String userCID);

    //获取所有未生成订单列表
    List<Orders> findAllUnfinishedOrders();

    //获取指定用户已完成订单列表
    List<Orders> findAllFinishedOrders();


    //查询订单
    Orders findOrderByID(String id);




}
