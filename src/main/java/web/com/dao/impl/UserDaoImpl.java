package web.com.dao.impl;

import web.com.dao.UserDao;
import web.com.pojo.Users;
import web.com.utils.JDBCUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Wan Yu on 2020/3/17
 */
public class UserDaoImpl implements UserDao {
    public void userRegister(Users users) {
        try{
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "insert into users(ID, Logname, Password, Email, Gender, Selfinfo, Admin) values(?, ?, ?, ?, ?, ?, ?)";
            Object param[] = {users.getId(),users.getLogname(),users.getPassword(),users.getEmail(),users.getGender(),users.getSelfinfo(),users.getAdmin()};
            queryRunner.update(sql, param);

        }  catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public Users userLogin(String id, String password) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "select * from users where ID = ? and Password = ?";
            Object params[] = {id, password};
            return queryRunner.query(sql, new BeanHandler<Users>(Users.class, new BasicRowProcessor(new GenerousBeanProcessor())),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Users userQuery(String id) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
            String sql = "select * from users where ID = ?";
            return queryRunner.query(sql,new BeanHandler<Users>(Users.class, new BasicRowProcessor(new GenerousBeanProcessor())),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
