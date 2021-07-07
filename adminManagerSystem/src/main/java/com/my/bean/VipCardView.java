package com.my.bean;

public class VipCardView {
    private int cid;
    private String t_name;
    private String e_name;
    private String serviceendtime;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getServiceendtime() {
        return serviceendtime;
    }

    public void setServiceendtime(String serviceendtime) {
        this.serviceendtime = serviceendtime;
    }

    public VipCardView() {
    }

    @Override
    public String toString() {
        return "VipCardView{" +
                "cid=" + cid +
                ", t_name='" + t_name + '\'' +
                ", e_name='" + e_name + '\'' +
                ", serviceendtime='" + serviceendtime + '\'' +
                '}';
    }
}
