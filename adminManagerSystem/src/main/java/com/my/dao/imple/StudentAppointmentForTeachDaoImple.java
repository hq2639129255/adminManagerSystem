package com.my.dao.imple;

import com.my.bean.StudentAppointmentForTeach;
import com.my.dao.BaseDao;
import com.my.dao.StudentAppointmentForTeachDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentAppointmentForTeachDaoImple extends BaseDao<StudentAppointmentForTeach> implements StudentAppointmentForTeachDao {

    @Override
    public List<StudentAppointmentForTeach> findStudentAppointmentForTeachByE_id(Connection con, int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
       String sql="SELECT t_name,starttime,endtime,e_name,sex,phone FROM `coach` a " +
               "INNER  JOIN `wipinfo` b ON a.`v_id`=b.`v_id` " +
               "INNER JOIN `make_an_appointment` c ON a.`v_id`= c.v_id " +
               "INNER JOIN `timeframe` d ON c.time_id=d.t_id " +
               "WHERE e_id=? AND appointmenttime=DATE(NOW()) ";
        List<StudentAppointmentForTeach> data=this.getListInstence(con,sql,eid);
        return data;
    }
}
