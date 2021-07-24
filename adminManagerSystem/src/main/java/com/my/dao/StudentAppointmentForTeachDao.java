package com.my.dao;

import com.my.bean.StudentAppointmentForTeach;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentAppointmentForTeachDao {
    List<StudentAppointmentForTeach> findStudentAppointmentForTeachByE_id(Connection con,int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


}
