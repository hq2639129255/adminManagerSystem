package com.my.service;

import com.my.bean.*;
import com.my.dao.imple.AuthorityDaoImple;
import com.my.dao.imple.CourseDaoImple;

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

    Page<Userinfo>   findUserinfoByParameter(String userName, int aid, String vname,int offset, int rowcount);
boolean addUser(Employee e,User u,double salary);

    /**
     *
     * @param userName
     * @param password
     * @param role
     * @return true 旧密码错误
     */
    boolean isOkOldPassword(String userName,String password,int  role);

    boolean updatePassword(String newpasswrod,String username,String oldpassword);

    /**
     * 会员登录系统后显示教练信息
     * @param vphone
     * @return
     */
    List<TeachInfo> findTeachInfoByvipphone(String  vphone);





}
