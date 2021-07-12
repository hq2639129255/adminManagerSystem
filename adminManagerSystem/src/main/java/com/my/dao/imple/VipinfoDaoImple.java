package com.my.dao.imple;

import com.my.bean.Vipinfo;
import com.my.dao.BaseDao;
import com.my.dao.VipinfoDao;

import java.sql.Connection;
import java.sql.SQLException;

public class VipinfoDaoImple extends BaseDao<Vipinfo> implements VipinfoDao {
    @Override
    public boolean updateVipinfo(Connection con, String name,int  id) throws SQLException {
       String sql="UPDATE  `wipinfo` SET `e_name`=? WHERE `card_id`=?";
        int b = this.update(con,sql, name,id);
        return b>0;
    }

    @Override
    public int findvipIdbyCardid(Connection con, int id) throws SQLException {
        String sql="SELECT  `v_id` FROM  `wipinfo` WHERE  `card_id`=?";
        int b = 0;
            b = this.getValue(con,sql,id);
        return b;
    }

    @Override
    public boolean updatevipinfobyid(Connection con, Vipinfo vip) throws SQLException {
        String sql=" UPDATE `wipinfo` SET `e_name`=?,`address`=?,`sex`=?,`email`=? where  `v_id`=?";
        int row = this.update(con, sql, vip.getE_name(), vip.getAddress(), vip.getSex(), vip.getEmail(), vip.getV_id());
        return row>0;
    }
}
