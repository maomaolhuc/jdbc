package com.tz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 我的第一个JDBC程序
 * @author 南天    9:45再讲
 *
 * @Date 2017年4月15日
 */
public class JdbcSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			/**
			 * 1.注册驱动(java.sql.DriverManager),要求JVM查找并加载指定的类
			 */
			Class.forName("com.mysql.jdbc.Driver");
			/**
			 * 2. 获取数据库的连接
			 * java.sql.Connection
			 * Ctrl + shift + o   引用包的快捷方式
			 * url : jdbc:mysql://ip:port/daname?characterEncoding=utf8&useSSL=true
			 * characterEncoding 连接字符集
			 * useSSL ： MySQL在高版本需要指明是否进行SSL连接
			 */
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tzjdbc?characterEncoding=utf8&useSSL=true", "root", "mysql");
			/**
			 * 3. 创建执行句柄
			 * java.sql.Statement
			 */
			Statement stmt = conn.createStatement();
			/**
			 * 4. 执行语句
			 * java.sql.Resultset
			 */
			ResultSet rs = stmt.executeQuery("select * from goods");

			/**
			 * 5.处理结果
			 */
			while(rs.next()){
				System.out.println("id : " + rs.getInt(1) + "| GoodsName : " + rs.getString(2) +
						"|GoodsNum : " + rs.getInt(3) + " | Remark : " + rs.getString(4) );
			}

			/**
			 * 6.释放资源
			 */
			rs.close();
			stmt.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
