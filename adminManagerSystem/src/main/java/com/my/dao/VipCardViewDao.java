package com.my.dao;

import com.my.bean.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VipCardViewDao {
    List<VipCardView> findVipCardViewAll(Connection con);
    boolean insert(Connection con, int cardtype, Vipinfo vipinfo);
    Page<VipCardView> findVipCardViewByPagesize(Connection con,int offset, int rowcount) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
    boolean updateCardType(Connection con,int type, int id) throws SQLException;
    public  Page<VipCardView> findVipCardViewByParameter(Connection con, VipCardView vipCardView, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

        }
