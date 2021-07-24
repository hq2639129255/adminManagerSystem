package com.my.dao.imple;

import com.my.bean.Checkingin;
import com.my.dao.BaseDao;
import com.my.dao.CheckinginDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CheckinginDaoImple extends BaseDao<Checkingin> implements CheckinginDao {
    @Override
    public boolean addCheckingin(Connection con, Checkingin callcard) throws SQLException {
String sql=" INSERT INTO  `checkingin`(`employee_id`,`w_id`,`c_date`,`c_starttime`,`c_endtime`,callstatus,`remark`)VALUES(?,?,?,?,?,?,?)";
        int row = this.update(con, sql, callcard.getEmployeeId(), callcard.getWId(), callcard.getCDate(), callcard.getCStarttime(), callcard.getCEndtime(), callcard.getCallstatus(), callcard.getRemark());
        return row>0;
    }

    @Override
    public Checkingin findCheckinginByEid(Connection con, String curentDate, int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
      String sql="SELECT `id`,`employee_id` as employeeId,`c_date` as cDate,`c_starttime` as cStarttime,`c_endtime` as cEndtime,`w_id` as wId,`remark`,`callstatus`  FROM `checkingin`  WHERE `c_date`=? AND `employee_id`=?";
        Checkingin data = this.getInstence(con, sql, curentDate, eid);
        return data ;
    }

    /**
     * 插入上班卡信息
     * @param con
     * @param checkingin
     * @return
     * @throws SQLException
     */
    @Override
    public boolean insertStartCallinfo(Connection con, Checkingin checkingin) throws SQLException {
        String sql=" INSERT INTO  `checkingin`(`employee_id`,`w_id`,`c_date`,`c_starttime`,`c_endtime`,callstatus,`remark`)VALUES(?,?,?,?,NULL,?,?)";

        int row = this.update(con, sql, checkingin.getEmployeeId(), checkingin.getWId(), checkingin.getCDate(), checkingin.getCStarttime(),checkingin.getCallstatus(),checkingin.getRemark());
        return row>0;
    }

    @Override
    public boolean insertEndCallinfo(Connection con, Checkingin checkingin) throws SQLException {
        String sql=" INSERT INTO  `checkingin`(`employee_id`,`w_id`,`c_date`,`c_starttime`,`c_endtime`,callstatus,`remark`)VALUES(?,?,?,NULL,?,?,?)";

        int row = this.update(con, sql, checkingin.getEmployeeId(), checkingin.getWId(), checkingin.getCDate(),checkingin.getCEndtime(),checkingin.getCallstatus(), checkingin.getRemark());
        return row>0;

    }

    @Override
    public  List<Checkingin> findCheckinginByEidAndMonth(Connection con, int eid, int month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        String sql="SELECT  employee_id employeeId,c_date cDate,c_starttime cStarttime,c_endtime cEndtime,w_id wId,callstatus ,remark FROM `checkingin` WHERE MONTH(c_date)=? and employee_id=?";
        List<Checkingin> data = this.getListInstence(con, sql, month, eid);
        return data;
    }

    @Override
    public boolean updateEndCallinfo(Connection con,Checkingin c) throws SQLException {
        String sql="UPDATE `checkingin` SET callstatus=?,`c_endtime`=?,`remark`=REPLACE(`remark`,'未打下班卡/','')  WHERE `c_date`=? AND `employee_id`=? AND w_id=?";
       int row=this.update(con,sql,c.getCallstatus(),c.getCEndtime(),c.getCDate(),c.getEmployeeId(),c.getWId());
        return row>0;
    }

    @Override
    public List<Checkingin> findCheckinginByE_id(Connection con, int e_id, int month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
       String sql="SELECT c_date cDate,c_starttime cStarttime,c_endtime cEndtime,callstatus,remark FROM `checkingin` WHERE  employee_id=? AND MONTH(c_date)=?";
        List<Checkingin> data=this.getListInstence(con,sql,e_id,month);
        return data;
    }
}
