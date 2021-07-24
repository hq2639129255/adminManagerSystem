package com.my.dao;

import com.my.bean.Checkingin;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CheckinginDao {
    boolean addCheckingin(Connection con , Checkingin callcard) throws SQLException;
    Checkingin findCheckinginByEid(Connection con, String curentDate,int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    boolean insertStartCallinfo(Connection con,Checkingin checkingin) throws SQLException;
    boolean insertEndCallinfo(Connection con,Checkingin checkingin) throws SQLException;
    List<Checkingin> findCheckinginByEidAndMonth(Connection con, int eid, int month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    //SELECT  employee_id,c_date,c_starttime,c_endtime,w_id,callstatus,remark FROM `checkingin` WHERE MONTH(c_date)=? WHERE employee_id=?
/**
 * 下班卡
 */
    boolean updateEndCallinfo(Connection con,Checkingin c) throws SQLException;
List<Checkingin> findCheckinginByE_id(Connection con,int e_id,int month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    //SELECT c_date,c_starttime,c_endtime,callstatus,remark FROM `checkingin` WHERE  employee_id=?

}
