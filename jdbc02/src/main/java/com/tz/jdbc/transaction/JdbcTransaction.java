/**
 *
 */
package com.tz.jdbc.transaction;

import com.tz.jdbc.db.util.DbUtil;
import com.tz.jdbc.util.StringUtils;

import java.sql.*;

/**
 * JDBC对事务处理
 * @author 南天
 *
 * @Date 2017年4月18日
 */
public class JdbcTransaction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		JdbcTransaction.transactionJDBC(1, 20);
		try {
			Connection conn = DbUtil.getConnection();
			if(conn != null){
				System.out.println("有连接");
			}else{
				System.out.println("无连接");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 模拟下订单的过程
	 */
	public static void transactionJDBC(int goodsId, int num){
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
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

			//开始事务处理
			conn.setAutoCommit(false);

			String sqlg = "select goods_num from goods where id=?";
			pre = conn.prepareStatement(sqlg);
			pre.setInt(1, goodsId);
			rs = pre.executeQuery();
			if(rs.next()){
//				int number = rs.getInt(1);
				int number = rs.getInt("goods_num");
				if(num > number)
					throw new Exception("超出库存");
				else{
					//下订单
					String sql1 = "insert into orders(id, goods_id, order_num) values(?,?,?)";
					//减库存
					String sql2 = "update goods set goods_num=? where id=?";

					//下订单的pre
					pre = conn.prepareStatement(sql1);
					pre.setString(1, StringUtils.getUUID());
					pre.setInt(2, goodsId);
					pre.setInt(3, num);
					pre.executeUpdate();


					//减库存的pre
					pre = conn.prepareStatement(sql2);
					pre.setInt(1, number - num);
					pre.setInt(2, goodsId);
					pre.executeUpdate();
				}
			}
			//提交事务
			conn.commit();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			/**
			 * 6. 释放资源 逐一将上面的对象全部关闭，因为如果不关闭的话会影响数据库的性能，并且占用资源 逐一关闭的顺序 最后使用的最先关闭
			 */
			try {
				conn.setAutoCommit(true);
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
	}

}
