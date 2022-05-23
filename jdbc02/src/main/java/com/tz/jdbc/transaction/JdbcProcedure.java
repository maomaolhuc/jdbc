/**
 *
 */
package com.tz.jdbc.transaction;

import java.sql.*;

/**
 * JDBC对存储过程的调用示例
 * @author 南天
 *
 * @Date 2017年4月18日
 */
public class JdbcProcedure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcProcedure.actionToProcedure(100);
	}


	public static void actionToProcedure(int num){
		Connection conn = null;
		CallableStatement pre = null;
		try {
			/**
			 * 1.注册驱动(java.sql.DriverManager),要求JVM查找并加载指定的类
			 */
			Class.forName("com.mysql.jdbc.Driver");
			/**
			 * 2. 获取数据库的连接 java.sql.Connection Ctrl + shift + o 引用包的快捷方式 url :
			 * jdbc:mysql://ip:port/daname?characterEncoding=utf8&useSSL=true
			 * characterEncoding 连接字符集 useSSL ： MySQL在高版本需要指明是否进行SSL连接
			 */
			String url = "jdbc:mysql://localhost:3306/tzjdbc?characterEncoding=utf8&useSSL=true";

			String user = "root";

			String pwd = "mysql";

			conn = DriverManager.getConnection(url,user, pwd);

			String sql = "{call p8(?, ?)}";
			//创建句柄
			pre = conn.prepareCall(sql);
			pre.setInt(1, num);
			pre.registerOutParameter(2, Types.INTEGER);
			//执行sql语句
			pre.execute();

			System.out.println("存储过程的结果是 ： " + pre.getInt(2));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/**
			 * 6. 释放资源 逐一将上面的对象全部关闭，因为如果不关闭的话会影响数据库的性能，并且占用资源 逐一关闭的顺序 最后使用的最先关闭
			 */
			try {
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
		}
	}

}
