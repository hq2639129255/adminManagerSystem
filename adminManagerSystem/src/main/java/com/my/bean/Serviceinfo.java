package com.my.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Serviceinfo {

    private  int card_id;
    private String e_name;
    private String neme;
    private java.sql.Timestamp buytime;

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

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }

    public Timestamp getBuytime() {
        return buytime;
    }

    public void setBuytime(Timestamp buytime) {
        this.buytime = buytime;
    }

    public Serviceinfo() {
    }
}
