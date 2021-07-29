package com.my.service;

import com.my.bean.*;
import com.my.dao.Make_an_appointmentDao;
import com.my.dao.imple.AppointmentUserinfoDaoImple;
import com.sun.corba.se.impl.resolver.BootstrapResolverImpl;

import java.sql.Connection;
import java.util.List;

public interface VipUserService {
    List<UserAppointmentInfo> showAppointment();
    Make_an_appointment isAppointment(String date, int v_id);
   boolean executeAppointment(int time_id, int v_id);
    boolean clearAppointment(String crentDate, int v_id);
   List<AppointmentUserinfo> findAllAppointmentUserinfoByTimeID(int timeid);
    List<Renew> findRenewByCid( int c_id);
    /**
     * 返回课程信息
     * @return
     */
    List<Course> findAllCourse();
    /**
     * 返回在职教练信息
     * @return
     */
    List<Employee> findAllTeach();

    /**
     * 插入授课课程信息
     * @param e_id
     * @param v_id
     * @param c_id
     * @return
     */
    boolean insertCoachinfo(int e_id, int v_id, int c_id);
    Page<Course> findCourseByParameter( int c_id, String cname, int pirce, int offset, int rowcount);

    boolean insertCourse(String cname,double prie);
    boolean updateCourse( Course course);
    boolean deleteCourse(int id);
    Page<Serviceinfo>  findPageByParameter(Serviceinfo serviceinfo, int offset, int rowcount);

}
