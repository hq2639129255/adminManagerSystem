package com.my.dao;

import com.my.bean.AppointmentUserinfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AppointmentUserinfoDao {
   List<AppointmentUserinfo> findAllAppointmentUserinfoByTimeID(Connection con,int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
