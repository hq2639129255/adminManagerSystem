package com.my.dao.imple;

import com.my.bean.CardType;
import com.my.dao.BaseDao;
import com.my.dao.CardTypeDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CardTypeDaoImple extends BaseDao<CardType> implements CardTypeDao {
    @Override
    public List<CardType> findAllCardType(Connection con) {
        String  sql="SELECT `t_id`,`t_name` FROM  `cardtype`";
        List<CardType> data=null;
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
        return data ;
    }
}
