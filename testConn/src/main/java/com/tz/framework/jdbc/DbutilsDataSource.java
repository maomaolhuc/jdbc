package com.tz.framework.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbutilsDataSource {
	private static ComboPooledDataSource dataSource; //数据库连接池
	static {
		try {
			//得到类加载器对象
			ClassLoader cl = DbutilsDataSource.class.getClassLoader();
			//通过类加载器对象得到指定的资源文件的字节流
			InputStream is = cl.getResourceAsStream("com/tz/framework/jdbc/jdbc.properties");
			//将文件的输入流通过dbconfig的对象进行关联
			Properties dbconfig = new Properties();
			try {
				dbconfig.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//创建一个连接池对象
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(dbconfig.getProperty("jdbc.driverClass"));
			dataSource.setJdbcUrl(dbconfig.getProperty("jdbc.url"));
			dataSource.setUser(dbconfig.getProperty("jdbc.user"));
			dataSource.setPassword(dbconfig.getProperty("jdbc.password"));
            //设置初始化连接数量 			
			dataSource.setInitialPoolSize(5);
			//设置连接池每次新增连接的数量
			dataSource.setAcquireIncrement(3);
			//设置连接池最大连接数量
			dataSource.setMaxPoolSize(50);
			//设置连接池最小连接数量
			dataSource.setMinPoolSize(3);
			//设置闲置连接最大存活时间
			dataSource.setMaxIdleTime(200);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
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
		return dataSource.getConnection();
	}
	
	public static void main(String[] args) {
		try {
			getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
