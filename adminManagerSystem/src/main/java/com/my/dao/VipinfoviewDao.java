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
    public Page<Vipinfoview> findVipinfoviewByParameter(Connection con,String  phone,String type,String name, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     *
     * @param con
     * @param vip
     * @param type
     * @return true 手机号不存在
     */
    boolean addviminfo(Connection con,Vipinfo vip,int type);


}
