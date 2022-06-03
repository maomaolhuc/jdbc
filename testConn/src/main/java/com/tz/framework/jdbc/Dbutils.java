package com.tz.framework.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbutils {
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
		Class.forName("oracle.jdbc.driver.OracleDriver");  //加载oracle驱动程序
		            //127.0.0.1 指向本机 ，  1521：端口号    orcl:数据库的名字  
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"; 
		String user = "scott";
		String password = "tiger";
		con = DriverManager.getConnection(url,user,password);  //获取连接
		return con; 
	}
}
