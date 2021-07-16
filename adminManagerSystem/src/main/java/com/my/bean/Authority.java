package com.my.bean;

public class Authority {
    private  int id;
    private String name;

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
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

    public Authority(String name) {

        this.name = name;
    }

    public Authority() {

    }
}
