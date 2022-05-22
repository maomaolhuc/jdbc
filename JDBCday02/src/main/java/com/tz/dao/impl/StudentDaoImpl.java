package com.tz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.tz.dao.StudentDao;
import com.tz.entity.Student;
import com.tz.util.DBUtil;
/**
 * StudentDao的实现
 */
public class StudentDaoImpl implements StudentDao{
	// 连接组件
	private Connection conn;
	// 得到预编译的发送Sql语句的组件
	private PreparedStatement ps;
	// 处理结果集的组件
	private ResultSet rs;
	public void addStudent(Student s) {
		try {
			conn=DBUtil.getConn();
			String sql="insert into student(sid,sname,pwd,sex,address) values(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setObject(3, s.getPwd());
			ps.setObject(4, s.getSex());
			ps.setObject(5, s.getAddress());
			// 会返回一个int值 表示 你影响了 几行数据
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}

	public void deleteStudent(int id) {
		try {
			conn=DBUtil.getConn();
			String sql="delete from student where sid=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			// 会返回一个int值 表示 你影响了 几行数据
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}

	public Student updateStudent(Student s) {
		Student student=new Student();
		try {
			conn=DBUtil.getConn();
			String sql="update student set sname=?,pwd=?,sex=?,address=? where sid=?";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, s.getName());
			ps.setObject(2, s.getPwd());
			ps.setObject(3, s.getSex());
			ps.setObject(4, s.getAddress());
			ps.setObject(5, s.getId());
			// 会返回一个int值 表示 你影响了 几行数据
			ps.executeUpdate();
			student=findById(s.getId());
			return student;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return student;
	}

	public Student findById(int id) {
		Student s=new Student();
		try {
			conn=DBUtil.getConn();
			String sql="select * from student where sid=?";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, id);
			// 会返回一个int值 表示 你影响了 几行数据
			rs=ps.executeQuery();
			while(rs.next()){
				s.setId(rs.getInt("sid"));
				s.setName(rs.getString("sname"));
				s.setPwd(rs.getString("pwd"));
				s.setAddress(rs.getString("address"));
				s.setSex(rs.getString("sex"));
			}
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return s;
	}

}
