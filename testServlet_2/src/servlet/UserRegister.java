package servlet;

import model.UserBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.JDBCUtils;
import utils.UserDBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UserRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String phoneNum = req.getParameter("phoneNum");
        System.out.println("account:" + account + "\npassword:" + password); // 打印出来看一看
        String result = "数据库读取异常！";
        if (account.isEmpty()){
            result = "用户名不能为空！";
        }else if (password.isEmpty()){
            result = "密码不能为空！";
        }else{
            QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
            try {
                UserBean userBean = UserDBUtils.userLogin(runner,account,new BeanHandler<UserBean>(UserBean.class));
                if (userBean != null){
                    //如果有值，说明已经有注册用户了
                    result ="对不起，已经有用户占用该名字";
                }else{
                    UserDBUtils.userRegister(runner,account,password,phoneNum);
                    result = "注册成功";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式
        PrintWriter pw = resp.getWriter(); // 获取 response 的输出流
        pw.println(result); // 通过输出流把业务逻辑的结果输出
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        doGet(req,resp);
    }
}
