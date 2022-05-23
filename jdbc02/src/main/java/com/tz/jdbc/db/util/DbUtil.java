/**
 *
 */
package com.tz.jdbc.db.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 封装的第一步 我们把取得的连接和关闭连接抽取出来成为一个独立的方法放入工具类中
 * 第二步：我们是否要考虑将我们的数据库连接的四要素（类型，用户名 密码 ，地址 抽取出来做成配置文件的方式）
 * @author 南天
 *
 * @Date 2017年4月18日
 */
public class DbUtil {

	/**
	 * 取得数据库的连接
	 */
	public static Connection getConnection() throws Exception{
		//得到类加载对象
		ClassLoader cl = DbUtil.class.getClassLoader();
		//通过类加载器对象得到指定的资源文件字节流
		InputStream is = cl.getResourceAsStream("jdbc.properties");
		Properties dbconfig = new Properties();
		dbconfig.load(is);

		Connection conn = null;
//		System.out.println(dbconfig.getProperty("jdbc.driver"));
//		System.out.println(dbconfig.getProperty("jdbc.url"));
//		System.out.println(dbconfig.getProperty("jdbc.user"));
//		System.out.println(dbconfig.getProperty("jdbc.password"));
		Class.forName(dbconfig.getProperty("jdbc.driver"));

		String url = dbconfig.getProperty("jdbc.url");

		String user = dbconfig.getProperty("jdbc.user");

		String pwd = dbconfig.getProperty("jdbc.password");

		conn = DriverManager.getConnection(url,user, pwd);
		System.out.println("现在已连接MySQL数据库");

		return conn;
	}

	/**
	 * 关闭数据库
	 */
	public static void closeConnection(Connection conn, PreparedStatement pre, ResultSet rs){
		try {
			if (rs != null) {
				rs.close();
			}
			if (pre != null) {
				pre.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("关闭MySQL数据库");
	}

}
