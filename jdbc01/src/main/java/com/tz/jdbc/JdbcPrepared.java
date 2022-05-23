/**
 *
 */
package com.tz.jdbc;

import com.tz.bean.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 预 处理语句的例子
 *
 * @author 南天
 *
 * @Date 2017年4月15日
 */
public class JdbcPrepared {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Goods> list = JdbcPrepared.listGoods();

//		List<Goods> list = JdbcPrepared.listGoodsByParameter(3);

		for(Goods g : list){
			System.out.println("id : " + g.getId() + "| GoodsName : " + g.getGoodsName() +
					" | Num : " + g.getNum() +" | Remark : " + g.getRemark());
		}

//		JdbcPrepared.updateGoodsById("产地烟台1111", 7);

//		JdbcPrepared.deleteGoodsById(7);
	}

	/**
	 * 带参数的SQL删除
	 */
	public static void deleteGoodsById(int id){
		Connection conn = null;
		PreparedStatement pre = null;
		int index = 0;
		try {
			/**
			 * 1.注册驱动(java.sql.DriverManager),要求JVM查找并加载指定的类
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			/**
			 * 2. 获取数据库的连接 java.sql.Connection Ctrl + shift + o 引用包的快捷方式 url :
			 * jdbc:mysql://ip:port/daname?characterEncoding=utf8&useSSL=true
			 * characterEncoding 连接字符集 useSSL ： MySQL在高版本需要指明是否进行SSL连接
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz?characterEncoding=utf8&useSSL=true",
					"root", "mysql");
			/**
			 * 3. 创建执行句柄 java.sql.Statement
			 */
			String sql = "delete from goods where id=?";

			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);  //设置参数
			/**
			 * 4. 执行语句 java.sql.Resultset
			 */
			index = pre.executeUpdate();
			System.out.println("index : " + index);

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

	/**
	 * 带参数的SQL修改
	 */
	public static void updateGoodsById(String remark, int id){
		Connection conn = null;
		PreparedStatement pre = null;
		int index = 0;
		try {
			/**
			 * 1.注册驱动(java.sql.DriverManager),要求JVM查找并加载指定的类
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			/**
			 * 2. 获取数据库的连接 java.sql.Connection Ctrl + shift + o 引用包的快捷方式 url :
			 * jdbc:mysql://ip:port/daname?characterEncoding=utf8&useSSL=true
			 * characterEncoding 连接字符集 useSSL ： MySQL在高版本需要指明是否进行SSL连接
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz?characterEncoding=utf8&useSSL=true",
					"root", "mysql");
			/**
			 * 3. 创建执行句柄 java.sql.Statement
			 */
			String sql = "update goods set remark=? where id=?";

			pre = conn.prepareStatement(sql);
			pre.setString(1, remark);   //设置参数
			pre.setInt(2, id);
			/**
			 * 4. 执行语句 java.sql.Resultset
			 */
			index = pre.executeUpdate();
			System.out.println("index : " + index);

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

	/**
	 * 带参数的SQL查询
	 */
	public static List<Goods> listGoodsByParameter(int id){
		List<Goods> list = new ArrayList<Goods>();

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			/**
			 * 1.注册驱动(java.sql.DriverManager),要求JVM查找并加载指定的类
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			/**
			 * 2. 获取数据库的连接 java.sql.Connection Ctrl + shift + o 引用包的快捷方式 url :
			 * jdbc:mysql://ip:port/daname?characterEncoding=utf8&useSSL=true
			 * characterEncoding 连接字符集 useSSL ： MySQL在高版本需要指明是否进行SSL连接
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz?characterEncoding=utf8&useSSL=true",
					"root", "mysql");
			/**
			 * 3. 创建执行句柄 java.sql.Statement
			 */
			String sql = "select * from goods where id = ?";

			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);   //设置参数
			/**
			 * 4. 执行语句 java.sql.Resultset
			 */
			rs = pre.executeQuery();

			/**
			 * 5.处理结果
			 */
			Goods goods;
			while (rs.next()) {
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
		} finally {
			/**
			 * 6. 释放资源 逐一将上面的对象全部关闭，因为如果不关闭的话会影响数据库的性能，并且占用资源 逐一关闭的顺序 最后使用的最先关闭
			 */
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
		}

		return list;
	}

	/**
	 * 只修改为 ： PreparedStatement
	 * Ctrl + Shift + F  : 格式化
	 */
	public static List<Goods> listGoods() {
		List<Goods> list = new ArrayList<Goods>();

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			/**
			 * 1.注册驱动(java.sql.DriverManager),要求JVM查找并加载指定的类
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			/**
			 * 2. 获取数据库的连接 java.sql.Connection Ctrl + shift + o 引用包的快捷方式 url :
			 * jdbc:mysql://ip:port/daname?characterEncoding=utf8&useSSL=true
			 * characterEncoding 连接字符集 useSSL ： MySQL在高版本需要指明是否进行SSL连接
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz?characterEncoding=utf8&useSSL=true",
					"root", "mysql");
			/**
			 * 3. 创建执行句柄 java.sql.Statement
			 */
			String sql = "select * from goods";
			pre = conn.prepareStatement(sql);
			/**
			 * 4. 执行语句 java.sql.Resultset
			 */
			rs = pre.executeQuery();

			/**
			 * 5.处理结果
			 */
			Goods goods;
			while (rs.next()) {
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
		} finally {
			/**
			 * 6. 释放资源 逐一将上面的对象全部关闭，因为如果不关闭的话会影响数据库的性能，并且占用资源 逐一关闭的顺序 最后使用的最先关闭
			 */
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
		}

		return list;
	}

}
