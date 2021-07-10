package com.my.dao;

import com.my.bean.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    User findUserByUsernameAndPassword(Connection con,Object...args) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException;
boolean adduser(Connection con,User u) throws SQLException;

}
