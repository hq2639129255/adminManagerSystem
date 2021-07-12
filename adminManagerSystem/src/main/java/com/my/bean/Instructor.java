package com.my.bean;

public class Instructor {
    private int e_id;
    private String  name;
    private String phone;
    private  String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private int studentcount;

    public Instructor() {
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStudentcount() {
        return studentcount;
    }

    public void setStudentcount(int studentcount) {
        this.studentcount = studentcount;
    }
}
