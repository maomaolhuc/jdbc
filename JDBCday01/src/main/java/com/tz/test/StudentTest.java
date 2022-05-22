package com.tz.test;

import com.tz.dao.StudentDao;
import com.tz.entity.Student;
import org.junit.Test;

import java.util.List;

/**
 * Junit4 单元测试的工具
 */

public class StudentTest {

    /**
     * 拿所有的数据
     */
    @Test
    public void testFindAll() {
        StudentDao dao = new StudentDao();
        List<Student> list = dao.findAll();
        System.out.println(list);
    }

    /**
     * 测试根据ID拿到对象
     *
     * @throws Exception
     */
    @Test
    public void testFindById() {
        StudentDao dao = new StudentDao();
        try {
            dao.deleteStudent(1002);
//			dao.updateStudent(new Student(1002,"小孩子","123","男","北京朝阳"));
//			Student s=dao.findById( 1002);
//			System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试创建表
     */
    @Test
    public void testcreatetable() {
        StudentDao dao = new StudentDao();
        try {
            dao.createtable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试能不能插入
     */
    @Test
    public void testaddStudent() {
        StudentDao dao = new StudentDao();
        try {
            dao.addStudent(new Student(1002, "小海子", "xhz", "男", "广西南宁"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//	@Before
//	public void t1(){
//		System.out.println("我出生了");
//	}
//	@Test
//	public void test(){
//		System.out.println("你好，这个美丽的世界");
//	}
//	@After
//	public void t2(){
//		System.out.println("我要飞得更高");
//	}

}
