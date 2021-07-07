package com.my.bean;

public class Vipinfo {
 private   int  v_id  ;//会员id',
 private   int   card_id ;//卡id
 private  String    e_name ;//'姓名',
 private String    address; //地址,
 private  String    sex ;//'性别',
 private  String   phone ;//'手机号码',
 private  String   email;//'邮箱号'

    public Vipinfo() {
    }

    public Vipinfo(int card_id, String e_name, String address, String sex, String phone, String email) {
        this.card_id = card_id;
        this.e_name = e_name;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
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
