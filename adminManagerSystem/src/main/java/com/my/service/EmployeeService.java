package com.my.service;

import com.my.bean.*;
import com.my.dao.StudentinfoDao;

import java.sql.Connection;
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
    List<Instructor> findInstructorByParameter(String e_id, String e_name, String sex);
    List<Studentinfo> findStudentBye_id( int id);
    List<Studentinfo> selectStudentinfoByParameter(String  phone,String name,String sex,int e_id);
   boolean replaceCoach(int newe_id,int olde_id,int v_id);

    /**
     *
     * @return 返回所有班次信息
     */
   List<Worktime> showAllWorktime();

    /**
     *
     * @param phone
     * @return  true 手机号不存在
     */
    boolean   isEmployeeByPhone(String phone);

    /**
     * 判断邮箱号是否存在
     * true 邮箱号不存在
     * @param emil
     * @return
     */
    boolean isEmployeeByEmail(String emil);
}
