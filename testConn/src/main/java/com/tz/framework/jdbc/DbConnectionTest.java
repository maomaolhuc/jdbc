package com.tz.framework.jdbc;

import com.tz.framework.jdbc.bean.Emp;
import com.tz.framework.jdbc.dao.EmpDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * JDBC (java database connectivity 数据库连接)是一种执行sql语句的javaAPI,可以为多种关系型数据库提供统一的访问，
 * 它由一组用java语言编写的类和接口组成，JDBC提供了一种基准，据此可以构建更多的高级的工具和接口。
 * 
 * DriverManager  这个类专门管理数据库驱动程序
 * Connection 对象代表和数据库的连接，也就是在加载的driver和数据库之间建立连接
 * Statement 发送sql语句的发送器
 * ResultSet  在statement执行了sql语句之后 结果只是以结果集的方式返回  ResultSet就是包含查询结果的结果集   select
 * DbConnectionTest
 * 创建人:huayan
 * 时间：2018年6月5日-下午8:06:03
 * @version 1.0.0
 *
 */
public class DbConnectionTest {
	public static void testConnection() {
		Connection con = null;   //创建一个数据库连接
		PreparedStatement pre = null;  //创建预编译语句对象     一般情况下是用这个而不用Statement
		ResultSet result = null;   //创建一个结果集对象
		try {
			con = Dbutils.getConnection();  //打开数据库连接
			int deptno = 10;
			int empno = 7782;
			String sql = "SELECT EMPNO,ENAME,SAL FROM EMP WHERE DEPTNO = ? AND EMPNO = ?";  //预编译语句：？代表占位符
			pre = con.prepareStatement(sql);   //实例化预编译语句
			pre.setInt(1, deptno);
			pre.setInt(2, empno);
			result = pre.executeQuery();   //执行查询  把结果集返回给result   
			while(result.next()) {
				System.out.println("empno : " + result.getInt("empno") + 
						",ename : "+ result.getString("ename"));
			/*	System.out.println("empno : " + result.getInt(1) + 
						",ename : "+ result.getString(2));*/
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					Dbutils.closeConnection(con, pre, result);
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//testConnection();
		List<Emp> empList = new ArrayList<Emp>();
		EmpDao dao = new EmpDao();
		Emp emp = new Emp();
		emp.setEmpno(4);
		emp.setEname("刘智");
		emp.setSal(0.0);
		emp.setJob("java");
		emp.setDeptno(20);
		empList.add(emp);
		
		Emp emp2 = new Emp();
		emp2.setEmpno(6);
		emp2.setEname("戎装");
		emp2.setSal(0.0);
		emp2.setJob("java");
		emp2.setDeptno(20);
		empList.add(emp2);
		
	    dao.addBatchEmp(empList);
		/*for(Emp emp3 :empList) {
			dao.addEmp(emp3);
		}
		*/
		//dao.addEmp(emp);
		
		//dao.delEmp(1);
		
		//dao.updateEmp(emp);
		
		/*List<Emp> empList = dao.getEmp(10);
		for(Emp emp :empList) {
			System.out.println(emp.getEname());
		}*/
	}
}
