package com.my.service;

import com.my.bean.*;

import java.util.List;

public interface EmployeeService {
    List<Jobtype> initJobtypeList();
    Page<Employee> showCurrentEmployee(int offset, int rowcount);
    boolean addEmployee(Employee e,User user);
    List<Employee> showEmployeeParameter(QueryEmployee e);
    boolean updateEmployee(Employee e);
    List<Employee> showSelectEmployeey(int id);
    boolean deleteEmployeeByid(int eid,int sid);
    Page<Instructor> showAllInstructorinfo(int offset, int rowcount);

}
