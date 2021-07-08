package com.my.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface VipcardDao {
    boolean updateByid(Connection con,int daycount,int id) throws SQLException;
    boolean updateservicetimeByid(Connection con,int daycount,int id) throws SQLException;
}
