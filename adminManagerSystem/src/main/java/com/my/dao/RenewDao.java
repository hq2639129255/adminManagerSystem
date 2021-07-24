package com.my.dao;

import com.my.bean.Renew;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RenewDao {
    List<Renew> findRenewByCid(Connection con, int c_id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
