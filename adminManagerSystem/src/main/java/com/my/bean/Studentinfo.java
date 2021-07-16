package com.my.bean;

import java.awt.*;

public class Studentinfo {
   private int e_id;
   private int v_id;
   private  int card_id;
   private String vipname;
   private String c_name;
   private String vipphone;
   private  String vipsex;

    public Studentinfo() {
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
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
        return vipname;
    }

    public void setE_name(String vipname) {
        this.vipname = vipname;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getVipphone() {
        return vipphone;
    }

    public void setVipphone(String vipphone) {
        this.vipphone = vipphone;
    }

    public String getVipsex() {
        return vipsex;
    }

    public void setVipsex(String vipsex) {
        this.vipsex = vipsex;
    }
}
