package com.my.dao.imple;

import com.my.bean.Setmeal;
import com.my.dao.BaseDao;
import com.my.dao.SetmealDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SetmealDaoImple extends BaseDao<Setmeal> implements SetmealDao {

    @Override
    public List<Setmeal> findAllSetmeal(Connection con) {
        String sql="SELECT  set_id as setId ,neme,servicetime FROM `setmeal`";
        List<Setmeal> data = null;
        try {
            data = this.getListInstence(con, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public int findset_idByservicetime(Connection con, int servicetime) throws SQLException {
String sql="SELECT `set_id` FROM `setmeal`  WHERE `servicetime`=?";
int setid=0;
setid=this.getValue(con,sql,servicetime);

        return setid;

    }

    @Override
    public boolean addsetmeal(Connection con, int card_id, int vipid, int buytime) throws SQLException {
        String sql="INSERT INTO `renew`(`card_id`,`vipid`,`set_id`,`buytime`)VALUES(?,?,?,NOW())";
        int row = this.update(con,sql, card_id, vipid, buytime);
        return row>0;
    }
}
