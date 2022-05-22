package com.tz.dao;

import com.tz.entity.Student;

public interface StudentDao {

    void addStudent(Student s);

    void deleteStudentById(int id);
}
