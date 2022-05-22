package com.tz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用预编译的发送Sql语句的对象
 */
public class LoginPreparedDao {
    // 用来连接数据库的组件
    private Connection conn;
    // 预编译 的发送Sql语句的组件
    private PreparedStatement ps;
    // 处理查询出来结果集的组件
    private ResultSet rs;

    public String login(String name, String pwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz",
                    "root", "mysql");
            // 写Sql语句 就变了
            String sql = "select * from student where sname=? and pwd=?";
            ps = conn.prepareStatement(sql);
            // 如果有传递参数  写入参数
            ps.setString(1, name);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            while (rs.next()) {
                return "yes";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "no";
    }
}
















