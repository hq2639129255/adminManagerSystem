package com.my.dao;

import com.my.bean.CallCardInfo;
import com.my.bean.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CallCardInfoDao {
    List<CallCardInfo> findAllCallCardInfo(Connection con,int month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    List<CallCardInfo> findAllCallCardInfoAction(Connection con, int month, int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    Page<CallCardInfo> findCallCardInfoByPagesize(Connection con, int offset,int rowcount);
    List<CallCardInfo> findCallCardInfoByparameter(Connection con,String empON, String name,int month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
