package com.my.dao;

import com.my.bean.Make_an_appointment;

import java.sql.Connection;
import java.sql.SQLException;

public interface Make_an_appointmentDao {
    Make_an_appointment isAppointment(Connection con, String date, int v_id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
boolean  executeAppointment(Connection con,int time_id,int v_id ) throws SQLException;
boolean clearAppointment(Connection con,String crentDate,int v_id) throws SQLException;
}
