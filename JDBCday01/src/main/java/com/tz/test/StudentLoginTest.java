package com.tz.test;

import com.tz.dao.LoginPreparedDao;

import java.util.Scanner;


public class StudentLoginTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请你输入你的用户名");
        String name = scan.nextLine();
        System.out.println("请你输入你的密码");
        String pwd = scan.nextLine();
//		LoginDao dao=new LoginDao();
        LoginPreparedDao dao = new LoginPreparedDao();
        String islogin = dao.login(name, pwd);
        if (islogin.equals("yes")) {
            System.out.println("用户名和密码都对，登陆成功");
        } else {
            System.out.println("用户名或者密码错误");
        }
    }
}
