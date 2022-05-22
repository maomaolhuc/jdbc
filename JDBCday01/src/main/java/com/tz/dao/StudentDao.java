package com.tz.dao;

import com.tz.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 做student这个表的增删改查操作
 */
public class StudentDao {
    // Java用来连接数据库的组件
    private Connection conn;
    // Java用来发送Sql语句的组件 ，注意：这能发送静态Sql
    private Statement st;
    // Java用来处理返回的结果集的组件
    private ResultSet rs;

    // 做一个创建表
    public void createtable() throws Exception {
        // 第一步  加载驱动。
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 第二步  得到连接  DriverManager是个类
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz"
                , "root", "mysql");
        String sql = "drop table xhz";
        // 第三步  得到发送Sql语句的组件
        st = conn.createStatement();
        // 第四部 执行sql语句
        st.execute(sql);
        System.out.println("执行成功");
    }

    // 添加一个学生
    public void addStudent(Student s) throws Exception {
        // Java连接数据库的步骤
        // 第一步  加载驱动。
        Class.forName("com.mysql.jdbc.Driver");
        // 第二步  得到连接  DriverManager是个类
        /**
         * 得到Java和数据库的连接
         * 1 url
         * 2 用户名
         * 3 密码
         */
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz"
                , "root", "root123");
        // 验证一下 能不能得到连接 如果能得到连接 成功70%
        String sql = "insert into student values(" + s.getSid() + ",'" + s.getSname() + "','" + s.getPwd() + "','" + s.getSex() + "','" + s.getAddress() + "')";
        // 第三步  得到发送Sql语句的组件
        st = conn.createStatement();
        // 第四部 执行sql语句
        st.execute(sql);
        System.out.println("执行插入完毕");
    }

    // 删除一个学生
    public void deleteStudent(int sid) throws Exception {
        // 第一步  加载驱动。
        Class.forName("com.mysql.jdbc.Driver");
        // 第二部
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz"
                , "root", "root123");
        // 验证一下 能不能得到连接 如果能得到连接 成功70%
        String sql = "delete from student where sid=" + sid;
        // 第三步  得到发送Sql语句的组件
        st = conn.createStatement();
        // 第四部 执行sql语句
        st.execute(sql);
        System.out.println("执行插入完毕");
    }

    // 修改一个学生
    public void updateStudent(Student s) throws Exception {
        // 第一步  加载驱动。
        Class.forName("com.mysql.jdbc.Driver");
        // 第二部
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz"
                , "root", "root123");
        // 验证一下 能不能得到连接 如果能得到连接 成功70%
        String sql = "update student set sname='" + s.getSname() + "',pwd='" + s.getPwd() + "',sex='" + s.getSex() + "',address='" + s.getAddress() + "' where sid=" + s.getSid() + "";
        // 第三步  得到发送Sql语句的组件
        st = conn.createStatement();
        // 第四部 执行sql语句
        st.execute(sql);
        System.out.println("执行插入完毕");
    }

    // 查询  根据ID查询
    public Student findById(int id) throws Exception {
        Student s = new Student();
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz"
                , "root", "root123");
        String sql = "select * from student where sid=" + id;
        st = conn.createStatement();
        // 就是把执行查询后的结果集 放在ResultSet对象中
        rs = st.executeQuery(sql);
        while (rs.next()) {
            s.setSid(rs.getInt("sid"));
            s.setSname(rs.getString("sname"));
            s.setPwd(rs.getString("pwd"));
            s.setSex(rs.getString("sex"));
            s.setAddress(rs.getString("address"));
        }
        return s;
    }

    public List<Student> findAll() {
        List<Student> list = new ArrayList<Student>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tz"
                    , "root", "root123");
            String sql = "select * from student";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setPwd(rs.getString("pwd"));
                s.setSex(rs.getString("sex"));
                s.setAddress(rs.getString("address"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}











