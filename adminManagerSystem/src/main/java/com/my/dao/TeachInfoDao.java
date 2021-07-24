package com.my.dao;

import com.my.bean.TeachInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TeachInfoDao {
    List<TeachInfo> findTeachInfoByvipphone(Connection con,String vphone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
