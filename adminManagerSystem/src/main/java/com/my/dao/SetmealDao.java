package com.my.dao;

import com.my.bean.Setmeal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SetmealDao {
    List<Setmeal> findAllSetmeal(Connection con);
    int findset_idByservicetime(Connection con,int servicetime) throws SQLException;
    boolean addsetmeal(Connection con,int card_id,int  vipid,int buytime ) throws SQLException;
}
