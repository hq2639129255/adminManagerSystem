package com.my.service;

import com.my.bean.*;
import com.my.dao.SalaryDao;
import com.my.dao.StudentinfoDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    /**
     * 查询员工工资信息
     * @return
     */
    List<SalaryView> findAllSalaryView();
    List<Jobtype> initJobtypeList();
    Page<Employee> showCurrentEmployee(int offset, int rowcount);
    boolean addEmployee(Employee e,User user,double salary);
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
    /**
     * 获取当前员工班次信息
     * @param
     * @return
     */
    EmployeeCallInfo showCurentEmployeeCallinfo(String phone);

    /**
     *
     * @param curentDate
     * @param eid
     * @return  返回当前员工打卡时间信息
     */
    Checkingin showCurentEmployeeCallinfo( String curentDate, int eid);

    /**
     * 员工打上班卡
     * @param c
     * @return
     */
    boolean insertStartCallinfo(Checkingin c);

    /**
     * 下班卡
     * @return
     */
    boolean updateEndCallinfo(Checkingin c);
    boolean insertEndCallinfo(Checkingin c);
/**
 * 考勤信息
 */
List<CallCardInfo> findAllCallCardInfo(int month);
  Page<CallCardInfo>  findCallCardInfoByPagesize(int offset, int rowcount);

  List<Checkingin>  findCheckinginByEidAndMonth(int eid, int month);


    /**
     * 查询考勤信息

     * @param empON
     * @param name
     * @param month
     * @return
     */
 List<CallCardInfo>   findCallCardInfoByparameter(String empON, String name,int month);

    /**
     * 显示教练名下学员信息
      * @param phone
     * @return
     */
 List<Studentinfo> findStudentByPhone( String phone);

    List<Studentinfo> findStudentinfoByParameter(String phone, String name, String sex, String e_phone);

/**
 * 保存员工登录信息
 */
Employee findEmployeeByPhone(String phone);
    List<Checkingin> findCheckinginByE_id(int e_id,int month);
    List<StudentAppointmentForTeach> findStudentAppointmentForTeachByE_id(int eid);

    /**
     * 插入工资信息
     * @param eid

     * @param salary
     * @return
     */
   boolean insetSalary(int eid,  double salary);

    Page<SalaryView> findSalaryViewByPagesize(int offset, int rowcount);

    /**
     * 多参数查询工资信息
     * @param month
     * @param name
     * @param empno
     * @return
     */


List<SalaryView> findSalaryViewByParameter( String month, String name, int empno);

    /**
     * 更新工资信息
     * @param salary
     * @return
     */
    boolean updateSalary( Salary salary);
    boolean sendCurentMonthSalary(String month);

    /**
     * 上月工资是否发放
     * @return
     */
    boolean isSendSalary() ;

    /**
     * 员工查看工资
     * @param eid
     * @return
     */
  List<SalaryView>  findSalaryBye_id(int eid);
}
