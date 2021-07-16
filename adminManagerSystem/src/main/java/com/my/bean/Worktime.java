package com.my.bean;


public class Worktime {

  private long wId;
  private String wName;
  private java.sql.Time starttime;
  private java.sql.Time endstime;


  public long getWId() {
    return wId;
  }

  public void setWId(long wId) {
    this.wId = wId;
  }


  public String getWName() {
    return wName;
  }

  public void setWName(String wName) {
    this.wName = wName;
  }


  public java.sql.Time getStarttime() {
    return starttime;
  }

  public void setStarttime(java.sql.Time starttime) {
    this.starttime = starttime;
  }


  public java.sql.Time getEndstime() {
    return endstime;
  }

  public void setEndstime(java.sql.Time endstime) {
    this.endstime = endstime;
  }

}
