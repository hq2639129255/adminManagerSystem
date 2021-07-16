package com.my.dao;

import com.my.bean.Instructor;
import com.my.bean.Make_an_appointment;
import com.my.bean.UserAppointmentInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserAppointmentInfoDao {
    List<UserAppointmentInfo> findUserAppointmentInfoCount(Connection con, List<UserAppointmentInfo> empl) throws SQLException;
    List<UserAppointmentInfo> findAllUserAppointmentInfo(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


}
