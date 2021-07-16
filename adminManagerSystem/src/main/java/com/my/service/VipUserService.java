package com.my.service;

import com.my.bean.AppointmentUserinfo;
import com.my.bean.Make_an_appointment;
import com.my.bean.UserAppointmentInfo;
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

}
