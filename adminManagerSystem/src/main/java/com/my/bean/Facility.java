package com.my.bean;

public class Facility {
    /*
    * f_id INT  PRIMARY KEY AUTO_INCREMENT COMMENT'设备ID',
f_name  VARCHAR(50)  COMMENT'设备名称',
remark VARCHAR(300) COMMENT'备注',
buyTime DATETIME COMMENT'购买时间'*/
    private int id;
    private String f_name;
    private String remark;
    private String buyTime;

    public Facility() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public Facility(String f_name, String remark, String buyTime) {
        this.f_name = f_name;
        this.remark = remark;
        this.buyTime = buyTime;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", f_name='" + f_name + '\'' +
                ", remark='" + remark + '\'' +
                ", buyTime='" + buyTime + '\'' +
                '}';
    }
}
