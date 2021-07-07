package com.my.bean;

public class CardType {
 private int    t_id;//卡类型id
 private String    t_name ;//类型名

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public CardType() {
    }

    public CardType(int t_id, String t_name) {
        this.t_id = t_id;
        this.t_name = t_name;
    }
}
