package com.my.dao.imple;

import com.my.bean.Renew;
import com.my.dao.BaseDao;
import com.my.dao.RenewDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RenewDaoImple extends BaseDao<Renew> implements RenewDao {
    @Override
    public List<Renew> findRenewByCid(Connection con, int c_id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
       String sql="SELECT card_id as cardId,set_id setId,buytime  FROM `renew` WHERE card_id=?";
        List<Renew> data = this.getListInstence(con, sql, c_id);
        return data;
    }
}
