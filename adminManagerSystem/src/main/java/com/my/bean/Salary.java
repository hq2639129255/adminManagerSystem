package com.my.bean;


import java.sql.Date;

public class Salary {

  private long saId;
  private Date saMonth;
  private long employeeId;
  private double payment;
  private double award;
  private double netPayroll;
  private String remark;
  private double salary;


  public long getSaId() {
    return saId;
  }

  public void setSaId(long saId) {
    this.saId = saId;
  }


  public Date getSaMonth() {
    return saMonth;
  }

  public void setSaMonth(Date saMonth) {
    this.saMonth = saMonth;
  }

  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }


  public double getPayment() {
    return payment;
  }

  public void setPayment(double payment) {
    this.payment = payment;
  }


  public double getAward() {
    return award;
  }

  public void setAward(double award) {
    this.award = award;
  }


  public double getNetPayroll() {
    return netPayroll;
  }

  public void setNetPayroll(double netPayroll) {
    this.netPayroll = netPayroll;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

}
