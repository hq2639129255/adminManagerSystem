package com.my.dao;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.QueryObj;
import com.my.bean.Userinfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserinfoDao {
    List<Userinfo> selectAllUserinfo(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public Page<Userinfo>  findUserinfoByPagesize(Connection con, int offset, int rowcount) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException;
    public Page<Userinfo> findUserinfoByParameter(Connection con,String userName,int aid,String vname, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;



}
