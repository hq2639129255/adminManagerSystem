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


}
