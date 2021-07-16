package com.my.dao;

import com.my.bean.Authority;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AuthorityDao {
    List<Authority> findAllAuthority(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
