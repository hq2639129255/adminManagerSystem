package com.my.bean;

public class Employee {
private int     e_id;//'员工id',
private int     j_id;// '职务id',
private  String   e_name ;//'姓名',
private String    address ;//'地址',
private String    sex ;//'性别',
private String     phone ;//'手机号码',
private String    email ;//'邮箱号'

    public Employee() {
    }

    public Employee(int j_id, String e_name, String address, String sex, String phone, String email) {
        this.j_id = j_id;
        this.e_name = e_name;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public int getJ_id() {
        return j_id;
    }

    public void setJ_id(int j_id) {
        this.j_id = j_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
