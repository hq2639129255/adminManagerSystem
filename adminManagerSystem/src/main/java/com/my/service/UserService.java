package com.my.service;

import com.my.bean.*;
import com.my.dao.imple.AuthorityDaoImple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
   byte login(String usernaem,String password,int role);
   List<Userinfo> showAllUserinfo() throws SQLException;
    Page<Userinfo> showCrentUserinfo(int offset, int rowcount);
    List<Authority> initAuthority();
    List<Userstatus> initUserstatus();
    boolean updateAuthorityByUserName(int aid,String userName);
    boolean resetPassword(String UserName);
    boolean lockUserCode(String UserName);
    boolean unLockUserCode(String UserName);
 List<Userinfo>   findUserinfoByParameter(String userName, int aid, String vname);
boolean addUser(Employee e,User u);
}
