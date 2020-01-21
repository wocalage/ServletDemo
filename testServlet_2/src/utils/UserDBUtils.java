package utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDBUtils {
    public static void userRegister(QueryRunner runner, String userName, String pwd, String phoneNum) throws SQLException {
        String sql = "INSERT INTO user (userName,userPwd,userPhone) VALUES (?,?,?);";
        Object[] params = {userName,pwd,phoneNum};
        runner.update(sql,params);
    }

    public static void userDelete(QueryRunner runner,String userName) throws SQLException {
        String sql = "DELETE FROM user WHERE userName = ?";
        runner.update(sql,userName);
    }

    public static void userUpdate(QueryRunner runner,String userName,String userPwd,String phoneNum) throws SQLException {
        String sql = "UPDATE user SET userPwd = ?,userPhone = ? WHERE userName = ?";
        Object[] params = {userPwd,phoneNum,userName};
        runner.update(sql,params);
    }

    public static <T> T userLogin(QueryRunner runner, String userName, BeanHandler<T> rsh) throws SQLException {
        String sql = "SELECT * from user WHERE userName = ?";
        Object[] params = {userName};
        T t = runner.query(sql,rsh,params);
        return t;
    }

}
