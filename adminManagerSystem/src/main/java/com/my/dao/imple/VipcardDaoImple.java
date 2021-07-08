package com.my.dao.imple;

import com.my.bean.Vipcard;
import com.my.dao.BaseDao;
import com.my.dao.VipcardDao;

import java.sql.Connection;
import java.sql.SQLException;

public class VipcardDaoImple extends BaseDao<Vipcard> implements VipcardDao {
    @Override
    public boolean updateByid(Connection con, int daycount, int id) throws SQLException {
        String sql="UPDATE  `vipcard` SET `serviceendtime`=DATE_ADD(NOW(), INTERVAL ? DAY) WHERE card_id=?";
        int row = this.update(con, sql, daycount, id);
        return row>0;
    }

    @Override
    public boolean updateservicetimeByid(Connection con, int daycount, int id) throws SQLException {
        String sql="UPDATE  `vipcard` SET `serviceendtime`=DATE_ADD(`serviceendtime`, INTERVAL ? DAY) WHERE card_id=?";
        int row = this.update(con, sql, daycount, id);
        return row>0;

    }
}
