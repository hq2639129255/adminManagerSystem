package com.my.service.imple;

import com.my.bean.*;
import com.my.dao.*;
import com.my.dao.imple.*;
import com.my.service.VipCardViewService;
import com.my.utils.JDBCutil;
import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VipCardViewServiceImple implements VipCardViewService {
    private VipCardViewDao dao=new VipCardViewDaoImple();
    private  CardTypeDao cardTypeDao=new CardTypeDaoImple();
    private VipinfoDao vipinfoDao=new VipinfoDaoImple();
    private SetmealDao setmealDao=new SetmealDaoImple();
    private  VipcardDao vipcardDao=new VipcardDaoImple();
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
                b=false;
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

    @Override
    public boolean updatecardinfo(VipCardView v) {
        boolean flag=true;
        Connection con=null;
        try {
          con = JDBCutil.getConnection();
            con.setAutoCommit(false);

           vipinfoDao.updateVipinfo(con, v.getE_name(), v.getCid());
           dao.updateCardType(con, Integer.parseInt(v.getT_name()),v.getCid());
            con.commit();
        } catch (SQLException e) {
            try {
                flag=false;
                con.rollback();
            } catch (SQLException e1) {


            }
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public List<VipCardView> showFacilityByParameter(VipCardView vipCardView) {
        List<VipCardView> data = null;
        Connection con=null;
        try {
          con=JDBCutil.getConnection();
            List<VipCardView> datalist = null;
            data = dao.findVipCardViewByParameter(con, vipCardView);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
    public List<Setmeal> initSetmeal() {
        Connection con=null;
        List<Setmeal> data = null;
        try {
            con=JDBCutil.getConnection();
            data = setmealDao.findAllSetmeal(con);
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
    public boolean buyservice(boolean flag,int daycount, int id) {
        Connection con=null;

        boolean b = true;
        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            if(flag){
                b = vipcardDao.updateservicetimeByid(con,daycount, id);
            }else {
                b = vipcardDao.updateByid(con,daycount, id);
            }
            int vipid = vipinfoDao.findvipIdbyCardid(con, id);
            int setid=setmealDao.findset_idByservicetime(con,daycount);
            setmealDao.addsetmeal(con,id,vipid,setid);
con.commit();
        } catch (SQLException e) {
            try {b=false;
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
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


}
