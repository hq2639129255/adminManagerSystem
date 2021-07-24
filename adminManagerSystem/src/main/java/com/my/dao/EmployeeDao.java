package com.my.dao;

import com.my.bean.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findEmployeeAll(Connection con);
    Page<Employee> findEmployeeByPagesize(Connection con, int offset, int rowcount);
    boolean addEmployee(Connection con,Employee e,double salary) throws SQLException;
    List<Employee> findEmployeeByParameter(Connection con, QueryEmployee q) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
boolean updateEmployee(Connection con,Employee e) throws SQLException;
    List<Employee> findEmployeebyId(Connection con,int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
String findPhoneByid(Connection con,int id) throws SQLException;
boolean deleteEmployeebyId(Connection con,int id) throws SQLException;
Employee findEmployeeByPhone(Connection con, String phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    Employee findEmployeeByEmail(Connection con, String emil) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 查询所有在职教练
     * @param con
     * @return
     */
    List<Employee> findAllTeach(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
//SELECT e_id,e_name FROM employee WHERE e_status=1 AND j_id=2
}
