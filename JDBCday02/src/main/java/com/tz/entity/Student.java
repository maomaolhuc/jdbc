package com.tz.entity;


public class Student {
    private int id;
    private String name;
    private String sex;
    private String pwd;
    private String address;

    public Student() {
    }

    public Student(int id, String name, String sex, String pwd, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.pwd = pwd;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", sex=" + sex
                + ", pwd=" + pwd + ", address=" + address + "]";
    }

}
