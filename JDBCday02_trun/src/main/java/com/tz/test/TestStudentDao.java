package com.tz.test;

import com.tz.dao.StudentDao;
import com.tz.daoimpl.StudentDaoImpl;
import com.tz.entity.Student;
import com.tz.util.DBUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 问？为什么Java提交过去的Sql语句 就直接能保存 Commit。
 * JDBC默认就是提交事务的。
 */
public class TestStudentDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Test
    public void testaddStudent() {
        StudentDao dao = new StudentDaoImpl();
        Student s = new Student(1110, "小海子", "男", "tt123", "广西玉林");
        dao.addStudent(s);
    }

    @Test
    public void testdeleteById() {
        StudentDao dao = new StudentDaoImpl();
        dao.deleteStudentById(1110);
    }

    /**
     * 演示  事务
     * 现在这种情况需要我们自己控制事物
     * 我们就是要它要删除就一起删除，要失败就一起失败
     */
    @Test
    public void testtrun() {
        try {
            conn = DBUtil.getConn();
            // 设置  关闭事物自动提交
            conn.setAutoCommit(false);
            // 我们的事物 conn 连接对象在控制 并且是默认提交的
            String sql = "delete from student where sid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1002);
            ps.executeUpdate();
            String sqlnew = "delete from tttt where tid=?";
            ps = conn.prepareStatement(sqlnew);
            ps.setInt(1, 11);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                // 如果某一个报错 回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 设置事物继续自动提交
            if (conn != null) {
                try {
                    // JDBC的事物设置为自动提交
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
