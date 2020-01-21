package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 *  * 将增删改查操作中重复代码提取出来
 *  
 */
public class JDBCUtils {

     //创建数据库连接池对象

    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

     //获取连接的方法


    public static Connection getConnection() throws SQLException {
         return comboPooledDataSource.getConnection();
    }

     //提供数据库连接池对象的方法


    public static DataSource getDataSource() {
         return comboPooledDataSource;
    }

     //释放资源的方法


    public static void release(ResultSet rs, Statement stmt, Connection conn) {
         if (rs != null) {
              try {
                   rs.close();
              } catch (SQLException e) {
                   e.printStackTrace();
              }
              rs = null;
         }
         release(stmt, conn);
    }



    public static void release(Statement stmt, Connection conn) {
         if (stmt != null) {
              try {
                   stmt.close();
              } catch (SQLException e) {
                   e.printStackTrace();
              }
              stmt = null;
         }
         if (conn != null) {
              try {
                   conn.close();
              } catch (SQLException e) {
                   e.printStackTrace();
              }
              conn = null;
         }
    }

}
