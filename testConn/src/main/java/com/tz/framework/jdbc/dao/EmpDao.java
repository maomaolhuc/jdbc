package com.tz.framework.jdbc.dao;

import com.tz.framework.jdbc.Dbutils;
import com.tz.framework.jdbc.bean.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
	/**
	 * 批量添加emp表数据
	 * com.tz.framework.jdbc.dao
	 * 方法名：addBatchEmp
	 * 创建人：huayan
	 * 时间：2018年6月7日-下午9:14:13
	 * @param empList
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException int[]
	 * @exception
	 * @since  1.0.0
	 */
	public int[] addBatchEmp (List<Emp> empList) throws ClassNotFoundException, SQLException {
		int[] rows = new int[empList.size()];
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		con = Dbutils.getConnection();
		String sql = "INSERT INTO T_EMP(EMPNO,ENAME,JOB,SAL,DEPTNO) VALUES(?,?,?,?,?)";
        pre = con.prepareStatement(sql)	;
        
        for(Emp emp : empList) {
        	pre.setInt(1, emp.getEmpno());
        	pre.setString(2, emp.getEname());
        	pre.setString(3, emp.getJob());
        	pre.setDouble(4, emp.getSal());
        	pre.setInt(5, emp.getDeptno());
        	pre.addBatch();   //把已经赋值完毕的sql加入到pre对象
        }
		rows = pre.executeBatch(); //一次性执行sql
		Dbutils.closeConnection(con, pre, result); 
		return rows;
	}
	/**
	 * 新增一条emp数据
	 * com.tz.framework.jdbc.dao
	 * 方法名：addEmp
	 * 创建人：huayan
	 * 时间：2018年6月5日-下午9:43:38
	 * @param emp
	 * @return int
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @exception
	 * @since  1.0.0
	 */
	public int addEmp(Emp emp) throws ClassNotFoundException, SQLException {
		int rows = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		con = Dbutils.getConnection();
		
		String sql = "INSERT INTO T_EMP(EMPNO,ENAME,JOB,SAL,DEPTNO) VALUES(?,?,?,?,?)";
        pre = con.prepareStatement(sql)	;
        pre.setInt(1, emp.getEmpno());
        pre.setString(2, emp.getEname());
        pre.setString(3, emp.getJob());
        pre.setDouble(4, emp.getSal());
        pre.setInt(5, emp.getDeptno());
        
        rows = pre.executeUpdate();
        
        Dbutils.closeConnection(con, pre, result);
        System.out.println("成功插入"+rows+"条数据");
		return rows;
	}
	
    /**
     * 删除emp表一条记录 
     * com.tz.framework.jdbc.dao
     * 方法名：delEmp
     * 创建人：huayan
     * 时间：2018年6月5日-下午10:08:19
     * @param emp
     * @return int
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @exception
     * @since  1.0.0
     */
	public boolean delEmp(int empno) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		int rows  = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		con = Dbutils.getConnection();
		String sql =  "DELETE FROM T_EMP WHERE EMPNO = ?";
		pre = con.prepareStatement(sql);
		pre.setInt(1, empno);
		rows = pre.executeUpdate();
		Dbutils.closeConnection(con, pre, result);
	    if(rows > 0)
	    	flag = true;
	    System.out.println("成功删除"+rows+"条数据");
		return flag;
	}
	/**
	 * 修改一条emp表记录
	 * com.tz.framework.jdbc.dao
	 * 方法名：updateEmp
	 * 创建人：huayan
	 * 时间：2018年6月7日-下午7:52:59
	 * @param emp
	 * @return int
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @exception
	 * @since  1.0.0
	 */
	public int updateEmp(Emp emp) throws ClassNotFoundException, SQLException {
		int rows  = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		con = Dbutils.getConnection();
		String sql =  "UPDATE T_EMP SET ENAME = ? WHERE EMPNO = ?";
		pre = con.prepareStatement(sql);
		pre.setString(1, emp.getEname());
		pre.setInt(2, emp.getEmpno());
		
		rows = pre.executeUpdate();
		Dbutils.closeConnection(con, pre, result);
	    System.out.println("成功修改"+rows+"条数据");
		return rows;
	}
	/**
	 * 根据部门编码获取员工信息
	 * com.tz.framework.jdbc.dao
	 * 方法名：getEmp
	 * 创建人：huayan
	 * 时间：2018年6月7日-下午7:53:28
	 * @param deptno 部门编码
	 * @return int
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @exception
	 * @since  1.0.0
	 */
	public List<Emp> getEmp(int deptno) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		con = Dbutils.getConnection();
		List<Emp> empList = new ArrayList<Emp>();
		String sql =  "SELECT EMPNO ,ENAME ,SAL,DEPTNO FROM T_EMP WHERE DEPTNO = ?";
		pre = con.prepareStatement(sql);
		pre.setInt(1, deptno);
		result = pre.executeQuery();
		Emp emp;
		while(result.next()) {
			emp = new Emp() ;
			emp.setEmpno(result.getInt("empno"));
			emp.setEname(result.getString("ename"));
			emp.setSal(result.getDouble("sal"));
			emp.setDeptno(result.getInt("deptno"));
			empList.add(emp);
		}
		Dbutils.closeConnection(con, pre, result);
		return empList;
	}
}
