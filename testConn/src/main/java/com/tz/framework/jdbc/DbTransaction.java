package com.tz.framework.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class DbTransaction {
	/**
	 * oracle事物处理的示例代码
	 * com.tz.framework.jdbc
	 * 方法名：testTransaction
	 * 创建人：huayan
	 * 时间：2018年6月9日-下午8:14:34 void
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @exception
	 * @since  1.0.0
	 */
	public static void testTransaction() {
		Connection con = null;   //创建一个数据库连接
		PreparedStatement pre = null;  //创建预编译语句对象     一般情况下是用这个而不用Statement
		ResultSet result = null;   //创建一个结果集对象
		Savepoint sp = null;
		
		try {
			con = Dbutils.getConnection();
			//设置事物非自动提交
			con.setAutoCommit(false);
			String sql1 ="INSERT INTO T_EMP (EMPNO,ENAME,JOB,DEPTNO) VALUES (6,'飞的更高2','java',30)";
			String sql2 ="INSERT INTO T_EMP (EMPNO,ENAME,JOB,DEPTNO) VALUES (7,'陈彦彦2','java',30)";
			
			pre = con.prepareStatement(sql1);
			pre.executeUpdate();
			
			sp = con.setSavepoint();  //设置事物保存点
			int a = 1/0;
			
			pre = con.prepareStatement(sql2);
			pre.executeUpdate();
			con.commit();
		} catch (Exception e) {
			try {
				//如果出现异常 事物回滚
				//con.rollback();
				con.rollback(sp);  //回滚到指定位置
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//把事物非自动提交更改过来
				con.setAutoCommit(true);
				Dbutils.closeConnection(con, pre, result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		testTransaction();
	}
}
