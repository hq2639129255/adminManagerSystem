package com.my.dao.imple;

import com.my.bean.AppointmentUserinfo;
import com.my.dao.AppointmentUserinfoDao;
import com.my.dao.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AppointmentUserinfoDaoImple extends BaseDao<AppointmentUserinfo> implements AppointmentUserinfoDao {
    @Override
    public List<AppointmentUserinfo> findAllAppointmentUserinfoByTimeID(Connection con, int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql=" SELECT card_id,a.v_id,e_name,sex,phone  FROM `make_an_appointment` a INNER JOIN `wipinfo` b ON a.`v_id`=b.`v_id`   WHERE `time_id`=? AND appointmenttime=DATE(NOW())";
        List<AppointmentUserinfo> data = this.getListInstence(con, sql, id);
        return data;
    }
}
