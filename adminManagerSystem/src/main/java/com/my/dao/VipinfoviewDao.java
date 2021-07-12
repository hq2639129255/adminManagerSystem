package com.my.dao;

import com.my.bean.Page;
import com.my.bean.VipCardView;
import com.my.bean.Vipinfo;
import com.my.bean.Vipinfoview;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VipinfoviewDao {
    List<Vipinfoview> findVipinfoviewAll(Connection con);
    Page<Vipinfoview> findVipinfoviewByPagesize(Connection con, int offset, int rowcount) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
    public List<Vipinfoview> findVipinfoviewByParameter(Connection con,String  phone,String type,String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
boolean addviminfo(Connection con,Vipinfo vip,int type);

}