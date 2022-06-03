package com.tz.framework.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbutilsPro {
	/**
	 * 关闭数据库连接
	 * com.tz.framework.jdbc
	 * 方法名：closeConnection
	 * 创建人：huayan
	 * 时间：2018年6月5日-下午9:06:47
	 * @param con
	 * @param pre
	 * @param result
	 * @throws SQLException void
	 * @exception
	 * @since  1.0.0
	 */
	public static void closeConnection(Connection con, PreparedStatement pre, ResultSet result) throws SQLException {
		if (result != null)
		    result.close();
		if(pre != null)
			pre.close();
		if(con != null)
			con.close();
	}
	/**
	 * 获取数据库连接
	 * com.tz.framework.jdbc
	 * 方法名：getConnection
	 * 创建人：huayan
	 * 时间：2018年6月5日-下午9:07:32
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException Connection
	 * @exception
	 * @since  1.0.0
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con;
		System.out.println("尝试连接Oracle数据库");
		//得到我们的类加载器对象
		ClassLoader cl = DbutilsPro.class.getClassLoader();
		//通过类加载器对象得到指定的资源文件的字节流
		InputStream is = cl.getResourceAsStream("com/tz/framework/jdbc/jdbc.properties");
		Properties dbconfig = new Properties();
		try {
			dbconfig.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Class.forName(dbconfig.getProperty("jdbc.driverClass"));
		con = DriverManager.getConnection(dbconfig.getProperty("jdbc.url"),
				dbconfig.getProperty("jdbc.user"),dbconfig.getProperty("jdbc.password"));
		System.out.println("Oracle数据库连接成功");
		return con; 
	}
	
	public static void main(String[] args) {
		try {
			getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
