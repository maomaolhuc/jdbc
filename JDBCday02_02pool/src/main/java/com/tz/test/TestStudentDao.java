package com.tz.test;

import org.junit.Test;

import com.tz.dao.StudentDao;
import com.tz.dao.impl.StudentDaoImpl;
import com.tz.entity.Student;

public class TestStudentDao {

    @Test
    public void testadd() {
        // 向上造型
        StudentDao dao = new StudentDaoImpl();
        Student s = new Student(1010, "刘能", "男", "123", "铁岭象牙山");
        dao.addStudent(s);
    }

    @Test
    public void testdelete() {
        StudentDao dao = new StudentDaoImpl();
        int a = dao.deleteStudent(1010);
        System.out.println(a);
    }

    @Test
    public void testupdate() {
        StudentDao dao = new StudentDaoImpl();
        Student s = new Student(1010, "刘德华", "男", "123", "中国香港");
        Student st = dao.updateStudent(s);
        System.out.println(st);
    }

    @Test
    public void testfindByid() {
        StudentDao dao = new StudentDaoImpl();
        Student st = dao.findById(1010);
        System.out.println(st);
    }


}
