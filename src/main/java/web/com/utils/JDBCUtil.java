package web.com.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {

    private static DataSource ds = new ComboPooledDataSource();

    public static DataSource getDataSource(){
        return ds;
    }

    public Connection getConnection(){
        try {
            return ds.getConnection();
        }catch (SQLException e){
            throw new RuntimeException("数据库连接失败" + e);
        }
    }

    public void close(ResultSet rs, Statement statement, Connection conn) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

