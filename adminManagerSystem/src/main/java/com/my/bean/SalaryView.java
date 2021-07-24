package com.my.bean;

import java.math.BigDecimal;
import java.sql.Date;

public class SalaryView {
  private Date sa_month;
  private  int e_id;
  private  String e_name;
  private  BigDecimal salary;
  private  BigDecimal payment;
  private  BigDecimal award;
  private  BigDecimal net_payroll;
  private  String phone;
  private  String remark;
  private  String salaryStatus;

    public Date getSa_month() {
        return sa_month;
    }

    public void setSa_month(Date sa_month) {
        this.sa_month = sa_month;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getAward() {
        return award;
    }

    public void setAward(BigDecimal award) {
        this.award = award;
    }

    public BigDecimal getNet_payroll() {
        return net_payroll;
    }

    public void setNet_payroll(BigDecimal net_payroll) {
        this.net_payroll = net_payroll;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    public SalaryView() {

    }
}
