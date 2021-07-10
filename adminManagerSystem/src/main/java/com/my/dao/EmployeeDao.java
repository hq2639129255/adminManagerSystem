package com.my.dao;

import com.my.bean.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findEmployeeAll(Connection con);
    Page<Employee> findEmployeeByPagesize(Connection con, int offset, int rowcount);
    boolean addEmployee(Connection con,Employee e) throws SQLException;
    List<Employee> findEmployeeByParameter(Connection con, QueryEmployee q) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
boolean updateEmployee(Connection con,Employee e) throws SQLException;
    List<Employee> findEmployeebyId(Connection con,int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
