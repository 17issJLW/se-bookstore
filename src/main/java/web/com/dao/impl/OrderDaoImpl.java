package web.com.dao.impl;

import web.com.dao.BookDao;
import web.com.pojo.Book;
import web.com.pojo.Orders;
import web.com.dao.OrderDao;
import web.com.pojo.Users;
import web.com.service.impl.BookServiceImpl;
import web.com.utils.JDBCUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Wan Yu on 2020/3/31
 */
public class OrderDaoImpl implements OrderDao {

    public void addOrder(Users users, Book book, int bookNum) {
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "insert into orders(ID, User_name, User_address, User_tel, User_CID, Book_id, Book_number, Status, Goods_price) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object param[] = {getTimeAsID(),users.getLogname(), "", "", users.getId(), book.getId(), bookNum, 0, book.getPrice()};
            queryRunner.update(sql, param);

        }  catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteOrder(Orders orders) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "delete from orders where ID = ?";
            queryRunner.update(sql,orders.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void changeBookNum(Orders orders, int bookNum) {
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "update orders set Book_number=? where id=?";
            queryRunner.update(sql, bookNum, orders.getId());
        }  catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void createOrder(Orders orders, String userAddress, String userTel) {
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "update orders set User_address=?, User_tel=?, Status=? where id=?";
            Object param[] = {userAddress, userTel,1,orders.getId()};
            queryRunner.update(sql, param);

        }  catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Orders> findUnfinishedOrders(String userCID) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "select * from orders where User_CID = ? and Status = 0";
            return queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class , new BasicRowProcessor(new GenerousBeanProcessor())),userCID);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Orders> findFinishedOrders(String userCID) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "select * from orders where User_CID = ? and Status = 1";
            return queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class , new BasicRowProcessor(new GenerousBeanProcessor())),userCID);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Orders> findAllUnfinishedOrders() {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "select * from orders where Status = 0";
            return queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class , new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Orders> findAllFinishedOrders() {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "select * from orders where Status = 1";
            return queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class , new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Orders findOrderByID(String id) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "select * from orders where ID = ?";
            return queryRunner.query(sql,new BeanHandler<Orders>(Orders.class, new BasicRowProcessor(new GenerousBeanProcessor())),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static long getTimeAsID() {
        Calendar c = Calendar.getInstance();
        String year=String.valueOf(c.get(Calendar.YEAR));
        String month = c.get(Calendar.MONTH)+1 > 10 ? String.valueOf(c.get(Calendar.MONTH)+1) : "0" + String.valueOf(c.get(Calendar.MONTH)+1);
        String day = c.get(Calendar.DAY_OF_MONTH) > 10 ? String.valueOf(c.get(Calendar.DAY_OF_MONTH)) : "0" + c.get(Calendar.DAY_OF_MONTH);
        String hour = c.get(Calendar.HOUR_OF_DAY) > 10 ? String.valueOf(c.get(Calendar.HOUR_OF_DAY)) : "0" + c.get(Calendar.HOUR_OF_DAY);
        String minute = c.get(Calendar.MINUTE) > 10 ? String.valueOf(c.get(Calendar.MINUTE)) : "0" + c.get(Calendar.MINUTE);
        String second = c.get(Calendar.SECOND) > 10 ? String.valueOf(c.get(Calendar.SECOND)) : "0" + c.get(Calendar.SECOND);
        return Long.parseLong(year + month + day + hour + minute + second);
    }



}
