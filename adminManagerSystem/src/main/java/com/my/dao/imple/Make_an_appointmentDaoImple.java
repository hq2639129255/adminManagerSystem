package com.my.dao.imple;

import com.my.bean.Make_an_appointment;
import com.my.dao.BaseDao;
import com.my.dao.Make_an_appointmentDao;

import java.sql.Connection;
import java.sql.SQLException;

public class Make_an_appointmentDaoImple extends BaseDao<Make_an_appointment> implements Make_an_appointmentDao {
    @Override
    public Make_an_appointment isAppointment(Connection con, String date, int v_id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql=" SELECT `m_id` ,`appointmenttime`,`time_id`,`v_id` FROM make_an_appointment WHERE appointmenttime=? AND v_id=?";
        Make_an_appointment data = this.getInstence(con,sql, date, v_id);
        return data;
    }

    @Override
    public boolean executeAppointment(Connection con, int time_id, int v_id) throws SQLException {
        String sql="INSERT INTO `make_an_appointment`(`appointmenttime`,`time_id`,`v_id`) VALUES(NOW(),?,?)";
        int row = this.update(con, sql, time_id, v_id);
        return row>0;
    }

    @Override
    public boolean clearAppointment(Connection con, String crentDate, int v_id) throws SQLException {
       String sql="DELETE FROM `make_an_appointment` WHERE `appointmenttime`=? AND `v_id`=?";
       int row=this.update(con,sql,crentDate,v_id);
        return row>0;
    }
}
