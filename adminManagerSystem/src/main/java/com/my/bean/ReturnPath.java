package com.my.bean;

import java.util.List;

public class ReturnPath <T> {
    private  boolean flag;
    private String info;
    private List<T> dataList;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String path;

    public ReturnPath() {

    }

    public ReturnPath(boolean flag, String path) {
        this.flag = flag;
        this.path = path;
    }

    public ReturnPath(boolean flag, String info, String path) {
        this.flag = flag;
        this.info = info;
        this.path = path;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ReturnPath{" +
                "flag=" + flag +
                ", path='" + path + '\'' +
                '}';
    }
}
