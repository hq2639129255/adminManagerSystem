package com.my.dao;

import com.my.bean.Vipinfo;

import java.sql.Connection;
import java.sql.SQLException;

public interface VipinfoDao {
    boolean updateVipinfo(Connection con, String name,int  id) throws SQLException;
    int findvipIdbyCardid(Connection con,int id) throws SQLException;
    boolean updatevipinfobyid(Connection con,Vipinfo vip) throws SQLException;
    Vipinfo  findVipinfoByPhone(Connection con,String phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    Vipinfo  findVipinfoByEmail(Connection con,String email) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


}
