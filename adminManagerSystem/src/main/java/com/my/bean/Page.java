package com.my.bean;

import java.util.List;

public class Page<T> {
    private int sunPage;//总页数
    private int sunrow;//总条数
    private int curentPage;//当前页
    private int curentrow;//当前页条数
    private List<T> pageData;//页面数据

    public int getSunPage() {
        return sunPage;
    }

    public void setSunPage(int sunPage) {
        this.sunPage = sunPage;
    }

    public int getSunrow() {
        return sunrow;
    }

    public void setSunrow(int sunrow) {
        this.sunrow = sunrow;
    }

    public int getCurentPage() {
        return curentPage;
    }

    public void setCurentPage(int curentPage) {
        this.curentPage = curentPage;
    }

    public int getCurentrow() {
        return curentrow;
    }

    public void setCurentrow(int curentrow) {
        this.curentrow = curentrow;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public Page() {
    }

    public Page(int sunPage, int sunrow, int curentPage, int curentrow, List<T> pageData) {
        this.sunPage = sunPage;
        this.sunrow = sunrow;
        this.curentPage = curentPage;
        this.curentrow = curentrow;
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "Page{" +
                "sunPage=" + sunPage +
                ", sunrow=" + sunrow +
                ", curentPage=" + curentPage +
                ", curentrow=" + curentrow +
                ", pageData=" + pageData +
                '}';
    }
}
