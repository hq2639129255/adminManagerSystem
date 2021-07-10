package com.my.dao;

import com.my.bean.CardType;
import com.my.bean.Jobtype;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface JobtypeDao {
    List<Jobtype> findAllJobtype(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
