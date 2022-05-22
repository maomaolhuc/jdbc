package com.tz.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBUtil负责得到数据库的连接
 * 做成一个工具类(如果是工具类 一般不会实例化new)
 * 工具方法 一般会做成静态方法
 * 我要用数据库连接池得到连接
 */
public class DBUtil {
    // 定义数据库连接池
    private static BasicDataSource ds;
    private static String driver;
    private static String url;
    private static String username;
    private static String pwd;
    private static String initialSize;
    private static String maxIdle;
    private static String minIdle;
    private static String maxActive;
    private static String maxWait;

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
            initialSize = p.getProperty("initialSize");
            maxIdle = p.getProperty("maxIdle");
            minIdle = p.getProperty("minIdle");
            maxActive = p.getProperty("maxActive");
            maxWait = p.getProperty("maxWait");

            // 需要实例化 数据库连接池 然后给他配置参数
            ds = new BasicDataSource();
            ds.setDriverClassName(driver);
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(pwd);
            ds.setInitialSize(Integer.parseInt(initialSize));
            ds.setMaxIdle(Integer.parseInt(maxIdle));
            ds.setMinIdle(Integer.parseInt(minIdle));
            ds.setMaxActive(Integer.parseInt(maxActive));
            ds.setMaxWait(Long.parseLong(maxWait));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 封装一个得到 Connection的方法
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }

    public static void main(String[] args) {
        try {
            System.out.println(getConn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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












