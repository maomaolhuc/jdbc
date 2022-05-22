package com.tz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    private Connection conn;
    // statement 防止不了 Sql注入的问题.
    private Statement st;
    private ResultSet rs;

    /**
     * 写一个登陆的业务逻辑
     */
    public String login(String name, String pwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz",
                    "root", "mysql");
            st = conn.createStatement();
            String sql = "select * from student where sname='" + name + "' and pwd='" + pwd + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                // 证明有值 证明用户名和密码正确
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
            if (st != null) {
                try {
                    st.close();
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
