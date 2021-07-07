package com.my.dao;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.VipCardView;
import com.my.bean.Vipinfo;

import java.sql.Connection;
import java.util.List;

public interface VipCardViewDao {
    List<VipCardView> findVipCardViewAll(Connection con);
    boolean insert(Connection con, int cardtype, Vipinfo vipinfo);
    Page<VipCardView> findVipCardViewByPagesize(Connection con,int offset, int rowcount) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
}
