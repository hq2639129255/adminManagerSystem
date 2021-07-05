package com.my.service.imple;

import com.my.bean.User;
import com.my.dao.UserDao;
import com.my.dao.imple.UserDaoImple;
import com.my.service.UserService;
import com.my.utils.JDBCutil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImple implements UserService {
    private UserDao userdao=new UserDaoImple();
    @Override
    public byte login(String usernaem,String password,int role) {
        User user=null;
        try {
            Connection con = JDBCutil.getConnection();
    user =   userdao.findUserByUsernameAndPassword(con,usernaem,password,role);
    con.close();
    if(user==null){
        return 0;
    }
    if(user.getStatus_id()==1){
        return 1;

    }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return 2;
    }
}
