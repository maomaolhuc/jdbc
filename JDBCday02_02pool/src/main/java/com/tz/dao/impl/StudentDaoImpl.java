package com.tz.dao.impl;

import com.tz.dao.BaseDao;
import com.tz.dao.StudentDao;
import com.tz.entity.Student;
import com.tz.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StudentDao的实现
 * Java是单继承的  只能继承一个父类
 * 但是可以实现多个接口
 * 写 先继承后实现
 */
public class StudentDaoImpl extends BaseDao implements StudentDao {
    // 连接组件
    private Connection conn;
    // 得到预编译的发送Sql语句的组件
    private PreparedStatement ps;
    // 处理结果集的组件
    private ResultSet rs;

    public void addStudent(Student s) {
        String sql = "insert into student(sid,sname,pwd,sex,address) values(?,?,?,?,?)";
        Object[] obs = {s.getId(), s.getName(), s.getPwd(), s.getSex(), s.getAddress()};
        toUpdate(sql, obs);
    }

    public int deleteStudent(int id) {
        String sql = "delete from student where sid=?";
        Object[] obs = {id};
        int a = toUpdate(sql, obs);
        return a;
    }

    public Student updateStudent(Student s) {
        String sql = "update student set sname=?,pwd=?,sex=?,address=? where sid=?";
        Object[] obs = {s.getName(), s.getPwd(), s.getSex(), s.getAddress(), s.getId()};
        toUpdate(sql, obs);
        Student student = findById(s.getId());
        return student;
    }

    public Student findById(int id) {
        Student s = new Student();
        try {
            conn = DBUtil.getConn();
            String sql = "select * from student where sid=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            // 会返回一个int值 表示 你影响了 几行数据
            rs = ps.executeQuery();
            while (rs.next()) {
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setPwd(rs.getString("pwd"));
                s.setAddress(rs.getString("address"));
                s.setSex(rs.getString("sex"));
            }
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn, ps, rs);
        }
        return s;
    }

}
