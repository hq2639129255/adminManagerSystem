package com.my.bean;

import java.sql.Date;

public class CallCardInfo {
    private  int  eid;
    private  int  wid;
    private  String name;
    private Date date;
    private  String phone;
    private String status;

    @Override
    public String toString() {
        return "CallCardInfo{" +
                "eid=" + eid +
                ", wid=" + wid +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CallCardInfo() {
    }
}
