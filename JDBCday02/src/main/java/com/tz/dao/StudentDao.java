package com.tz.dao;

import com.tz.entity.Student;

/**
 * 规范 方法名
 * 方法有几个，要做哪些方法
 */
public interface StudentDao {
    // 增加一个学生
    void addStudent(Student s);

    // 删除一个学生
    void deleteStudent(int id);

    // 修改一个学生
    Student updateStudent(Student s);

    // 查询学生
    Student findById(int id);
}












