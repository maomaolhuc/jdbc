package com.tz.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


/**
 * DBUtil负责得到数据库的连接
 * 做成一个工具类(如果是工具类 一般不会实例化new)
 * 工具方法 一般会做成静态方法
 */
public class DBUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String pwd;

    // static可以写静态的代码块
    static {
        // 实例化 属性集
        Properties p = new Properties();
        // 从本类的字节码文件中根据路径得到一个输入流
        InputStream is = DBUtil.class.getClassLoader()
                .getResourceAsStream("com/tz/util/db.properties");
        try {
            // 属性集 去加载输入流
            p.load(is);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            pwd = p.getProperty("pwd");
            // 记载驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 封装一个得到 Connection的方法
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, username, pwd);
    }

    // 封装一个关闭连接的方法
    public static void closeConn(Connection conn, PreparedStatement ps, ResultSet rs) {
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
}












