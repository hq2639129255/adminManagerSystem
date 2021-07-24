package com.my.bean;

import java.sql.Time;
public class EmployeeCallInfo {
private int  e_id;
private String e_name;
private String  phone;
private  int w_id;
private  String w_name;
private Time starttime;
private  Time endstime ;

    /**
     * 员工班次信息
     */
    public EmployeeCallInfo() {
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getW_name() {
        return w_name;
    }

    public void setW_name(String w_name) {
        this.w_name = w_name;
    }

    public Time getStarttime() {
        return starttime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Time getEndstime() {
        return endstime;
    }

    public void setEndstime(Time endstime) {
        this.endstime = endstime;
    }
}
