package com.my.dao;

import com.my.bean.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    User findUserByUsernameAndPassword(Connection con,Object...args) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException;
boolean adduser(Connection con,User u) throws SQLException;
boolean deleteByuserName(Connection con,String userName) throws SQLException;
boolean updateAuthorityByUserName(Connection con,int aid,String userName) throws SQLException;
boolean resetPassword(Connection con,String UserName) throws SQLException;
boolean lockUserCode(Connection con,String UserName) throws SQLException;
boolean unLockUserCode(Connection con,String UserName) throws SQLException;
boolean managerAdduser(Connection con,User u) throws SQLException;


}
