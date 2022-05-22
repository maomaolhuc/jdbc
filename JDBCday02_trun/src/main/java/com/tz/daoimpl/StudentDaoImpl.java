package com.tz.daoimpl;

import com.tz.dao.BaseDao;
import com.tz.dao.StudentDao;
import com.tz.entity.Student;

public class StudentDaoImpl extends BaseDao implements StudentDao {

    public void addStudent(Student s) {
        String sql = "insert into student(sid,sname,pwd,sex,address) values(?,?,?,?,?)";
        Object[] obs = {s.getId(), s.getName(), s.getPwd(), s.getSex(), s.getAddress()};
        toUpdate(sql, obs);
    }

    public void deleteStudentById(int id) {
        String sql = "delete from student where sid=?";
        Object[] obs = {id};
        toUpdate(sql, obs);
    }

}
