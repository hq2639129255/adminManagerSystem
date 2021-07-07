package com.my.service.imple;

import com.my.bean.*;
import com.my.dao.CardTypeDao;
import com.my.dao.VipCardViewDao;
import com.my.dao.imple.CardTypeDaoImple;
import com.my.dao.imple.VipCardViewDaoImple;
import com.my.service.VipCardViewService;
import com.my.utils.JDBCutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VipCardViewServiceImple implements VipCardViewService {
    private VipCardViewDao dao=new VipCardViewDaoImple();
    private  CardTypeDao cardTypeDao=new CardTypeDaoImple();
    @Override
    public List<VipCardView> showCardList() {
        Connection con = null;
        List<VipCardView> data=null;
        try {
            con = JDBCutil.getConnection();
            data = dao.findVipCardViewAll(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }



    @Override
    public List<CardType> initCardList() {
        Connection con=null;
        List<CardType> data = null;
        try {
            con=JDBCutil.getConnection();
            data = cardTypeDao.findAllCardType(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public boolean addcard(int cardtype, Vipinfo vip) {
        boolean b=false;
        Connection con=null;
        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            b = dao.insert(con, cardtype, vip);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return b;
    }

    @Override
    public Page<VipCardView> showCurrentVipCardView(int offset, int rowcount) {
        Connection con= null;
        Page<VipCardView> data=null;
        try {
            con = JDBCutil.getConnection();
            try {
                data= dao.findVipCardViewByPagesize(con, offset, rowcount);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }


}
