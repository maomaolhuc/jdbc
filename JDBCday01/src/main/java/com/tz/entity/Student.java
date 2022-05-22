package com.tz.entity;

/**
 * 封装  私有的属性 共有的方法
 */
public class Student {
    private int sid;
    private String sname;
    private String pwd;
    private String sex;
    private String address;

    // alt+shift+s
    public Student() {
    }

    public Student(int sid, String sname, String pwd, String sex, String address) {
        this.sid = sid;
        this.sname = sname;
        this.pwd = pwd;
        this.sex = sex;
        this.address = address;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Student [sid=" + sid + ", sname=" + sname + ", pwd=" + pwd
                + ", sex=" + sex + ", address=" + address + "]";
    }

}
