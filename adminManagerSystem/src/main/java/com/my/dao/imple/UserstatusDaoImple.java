package com.my.dao.imple;

import com.my.bean.Userstatus;
import com.my.dao.BaseDao;
import com.my.dao.UserstatusDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserstatusDaoImple extends BaseDao<Userstatus> implements UserstatusDao {
    @Override
    public List<Userstatus> findAllUserstatus(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql=" SELECT `sta_id` as id ,`islock` as `name` FROM `userstatus`";
        List<Userstatus> data = this.getListInstence(con, sql);
        return data;
    }
}
