package com.my.dao;

import com.my.bean.Salary;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SalaryDao {
    boolean insetSalary(Connection con, int eid, double salary) throws SQLException;
boolean updateSalary(Connection con, Salary salary) throws SQLException;
boolean updateSalaryByMonth(Connection con,String month) throws SQLException;
    boolean insetNextMonthSalary(Connection con,String month, int eid, BigDecimal salary) throws SQLException;
List<Salary> selectSalaryByUpMonth(Connection con,String month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
