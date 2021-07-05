package com.my.bean;

public class Userstatus {
//    sta_id TINYINT   COMMENT'状态id',
//    islock VARCHAR(8) COMMENT'状态'
    private byte id;
    private String name;

    public Userstatus() {
    }

    @Override
    public String toString() {
        return "Userstatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Userstatus(String name) {

        this.name = name;
    }
}
