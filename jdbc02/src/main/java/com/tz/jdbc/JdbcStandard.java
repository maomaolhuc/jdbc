/**
 *
 */
package com.tz.jdbc;

import com.tz.bean.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 规范化数据库的连接
 * @author 南天
 *
 * @Date 2017年4月15日
 */
public class JdbcStandard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		JdbcStandard.queryGoods();

		List<Goods> list = JdbcStandard.listGoods();
		for(Goods g : list){
			System.out.println("id : " + g.getId() + "| GoodsName : " + g.getGoodsName() +
					" | Num : " + g.getNum() +" | Remark : " + g.getRemark());
		}
	}

	/**
	 * 查询goods
	 */
	public static List<Goods>  listGoods(){
		List<Goods> list = new ArrayList<Goods>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tzjdbc?characterEncoding=utf8&useSSL=true", "root", "mysql");
			/**
			 * 3. 创建执行句柄
			 * java.sql.Statement
			 */
			stmt = conn.createStatement();
			/**
			 * 4. 执行语句
			 * java.sql.Resultset
			 */
			rs = stmt.executeQuery("select * from goods");

			/**
			 * 5.处理结果
			 */
			Goods goods;
			while(rs.next()){
				goods = new Goods();
				goods.setId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setNum(rs.getInt(3));
				goods.setRemark(rs.getString(4));
				list.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			/**
			 * 6. 释放资源
			 * 逐一将上面的对象全部关闭，因为如果不关闭的话会影响数据库的性能，并且占用资源
			 * 逐一关闭的顺序 最后使用的最先关闭
			 */
			try {
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}


	/**
	 * 规范quick start 中的例子
	 */
	public static void queryGoods(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tzjdbc?characterEncoding=utf8&useSSL=true", "root", "mysql");
			/**
			 * 3. 创建执行句柄
			 * java.sql.Statement
			 */
			stmt = conn.createStatement();
			/**
			 * 4. 执行语句
			 * java.sql.Resultset
			 */
			rs = stmt.executeQuery("select * from goods");

			/**
			 * 5.处理结果
			 */
			while(rs.next()){
				System.out.println("id : " + rs.getInt(1) + "| GoodsName : " + rs.getString(2) +
						"|GoodsNum : " + rs.getInt(3) + " | Remark : " + rs.getString(4) );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			/**
			 * 6. 释放资源
			 * 逐一将上面的对象全部关闭，因为如果不关闭的话会影响数据库的性能，并且占用资源
			 * 逐一关闭的顺序 最后使用的最先关闭
			 */
			try {
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}


}
