package com.my.dao.imple;

import com.my.bean.User;
import com.my.dao.BaseDao;
import com.my.dao.UserDao;
import com.my.utils.JDBCutil;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImple extends BaseDao<User> implements UserDao {
    @Override
    public User findUserByUsernameAndPassword(Connection con, Object... args) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        String sql=" SELECT `username` AS username,`u_password` AS `password`,`a_id` AS au_id,`sta_id` AS status_id FROM `user` WHERE username =? AND `u_password`=?AND `a_id`=?";
        User obj = this.getInstence(con,sql,args);

        return obj;
    }

    @Override
    public boolean adduser(Connection con, User u) throws SQLException {
        String sql="INSERT INTO  `user` (`username`,`a_id`,`sta_id`) VALUES(?,3,0);";
        int b = this.update(con, sql, u.getUsername());
        return b>0;
    }

    @Override
    public boolean deleteByuserName(Connection con, String userName) throws SQLException {
        String sql="DELETE  FROM `user`   WHERE `username`=?";
        int row = this.update(con, sql, userName);
        return row>0;
    }

    @Override
    public boolean updateAuthorityByUserName(Connection con,int aid, String userName) throws SQLException {
      String sql="UPDATE  `user` SET `a_id`=? WHERE `username`=?";
int row=this.update(con,sql,aid,userName);
        return row>0;
    }

    @Override
    public boolean resetPassword(Connection con, String UserName) throws SQLException {
        String sql=" UPDATE `user` SET `u_password`='8ddcff3a80f4189ca1c9d4d902c3c909' WHERE `username`=?";
        int row = this.update(con, sql, UserName);
        return row>0;
    }

    @Override
    public boolean lockUserCode(Connection con, String UserName) throws SQLException {
        String  sql="UPDATE `user` SET `sta_id`=1 WHERE `username`=?";
        int row = this.update(con,sql, UserName);
        return row>0;
    }

    @Override
    public boolean unLockUserCode(Connection con, String UserName) throws SQLException {
     String sql="UPDATE `user` SET `sta_id`=0 WHERE `username`=?";
        int row = this.update(con,sql, UserName);
        return row>0;
    }

    @Override
    public boolean managerAdduser(Connection con, User u) throws SQLException {
        String sql="INSERT INTO`user`(`username`,`u_password`,`a_id`,`sta_id`)VALUES(?,'8ddcff3a80f4189ca1c9d4d902c3c909',?,0)";
        int b = this.update(con, sql, u.getUsername(),u.getAu_id());
        return b>0;
    }


}
