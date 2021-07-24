package com.my.bean;


public class Checkingin {

  private long id;
  private long employeeId;
  private java.sql.Date cDate;
  private java.sql.Time cStarttime;
  private java.sql.Time cEndtime;
  private long wId;
  private String remark;
  private String callstatus;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }


  public java.sql.Date getCDate() {
    return cDate;
  }

  public void setCDate(java.sql.Date cDate) {
    this.cDate = cDate;
  }


  public java.sql.Time getCStarttime() {
    return cStarttime;
  }

  public void setCStarttime(java.sql.Time cStarttime) {
    this.cStarttime = cStarttime;
  }


  public java.sql.Time getCEndtime() {
    return cEndtime;
  }

  public void setCEndtime(java.sql.Time cEndtime) {
    this.cEndtime = cEndtime;
  }


  public long getWId() {
    return wId;
  }

  public void setWId(long wId) {
    this.wId = wId;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getCallstatus() {
    return callstatus;
  }

  public void setCallstatus(String callstatus) {
    this.callstatus = callstatus;
  }

}
