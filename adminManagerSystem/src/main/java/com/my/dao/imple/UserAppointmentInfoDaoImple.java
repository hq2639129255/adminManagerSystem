package com.my.dao.imple;

import com.my.bean.Instructor;
import com.my.bean.Make_an_appointment;
import com.my.bean.UserAppointmentInfo;
import com.my.dao.BaseDao;
import com.my.dao.UserAppointmentInfoDao;
import com.my.utils.JDBCutil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;

public class UserAppointmentInfoDaoImple extends BaseDao<UserAppointmentInfo> implements UserAppointmentInfoDao {
    @Override
    public List<UserAppointmentInfo> findUserAppointmentInfoCount(Connection con, List<UserAppointmentInfo> empl) throws SQLException {
        String sql="SELECT  COUNT(1) FROM make_an_appointment  WHERE time_id=? AND appointmenttime =DATE(NOW()) GROUP BY  time_id  ";
        Iterator<UserAppointmentInfo> itre= empl.iterator();
        if(empl.size()!=0){
            while (itre.hasNext()){
                UserAppointmentInfo ins=itre.next();
                Long conut=this.getValue(con,sql,ins.getId());
                if(conut!=null){
                    ins.setCount(Integer.parseInt(String.valueOf(conut)));}else {
                    ins.setCount(0);
                }


            }


        }


        return empl;
    }

    @Override
    public List<UserAppointmentInfo> findAllUserAppointmentInfo(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql=" SELECT t_id as id,t_name as timeName,starttime as startTime,endtime as endTime FROM `timeframe`";
        List<UserAppointmentInfo> datalist=this.getListInstence(con,sql);
        return datalist;
    }



}
