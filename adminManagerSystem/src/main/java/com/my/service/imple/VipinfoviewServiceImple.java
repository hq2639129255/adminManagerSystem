package com.my.service.imple;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.Vipinfo;
import com.my.bean.Vipinfoview;
import com.my.dao.VipinfoDao;
import com.my.dao.VipinfoviewDao;
import com.my.dao.imple.VipinfoDaoImple;
import com.my.dao.imple.VipinfoviewDaoImple;
import com.my.service.VipinfoviewService;
import com.my.utils.JDBCutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VipinfoviewServiceImple implements VipinfoviewService {
    private VipinfoviewDao dao=new VipinfoviewDaoImple();
    private VipinfoDao vipinfoDao=new VipinfoDaoImple();
    @Override
    public Page<Vipinfoview> showCurrentVipinfoview(int offset, int rowcount) {
        Connection con= null;
        Page<Vipinfoview> data=null;

        try {
            con = JDBCutil.getConnection();

            data=dao.findVipinfoviewByPagesize(con, offset, rowcount);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
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
    public Page<Vipinfoview> findVipinfoviewByParameter(String phone, String type, String name,int offset, int rowcount) {
        Connection con=null;
        Page<Vipinfoview> datalist = null;
        try {
            con=JDBCutil.getConnection();
            datalist = dao.findVipinfoviewByParameter(con,phone,type,name, offset,  rowcount);
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
        return datalist;
    }

    @Override
    public boolean addviminfo( Vipinfo vip, int type) {
        boolean b=false;
        Connection con=null;
        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            b = dao.addviminfo(con,vip,type);
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
    public Boolean updatevipinfobyid(Vipinfo vip) {
        Connection con=null;
        boolean b = false;
        try {
            con=JDBCutil.getConnection();
            b = vipinfoDao.updatevipinfobyid(con, vip);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public Vipinfo getCurentVipinfo(String phone) {
        Connection con=null;
        Vipinfo data = null;
        try {
            con=JDBCutil.getConnection();
            data = vipinfoDao.findVipinfoByPhone(con, phone);
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
    public boolean isVipinfoByphone(String phone) {
        Vipinfo data=getCurentVipinfo(phone);

        return data==null;
    }

    @Override
    public boolean isVipinfoByEmail(String email) {
        Connection con=null;
        Vipinfo data = null;
        try {
            con=JDBCutil.getConnection();
            data = vipinfoDao.findVipinfoByEmail(con, email);
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
        return data==null;
    }
}
