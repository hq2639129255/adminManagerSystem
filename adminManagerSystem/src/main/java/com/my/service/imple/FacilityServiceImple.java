package com.my.service.imple;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.QueryObj;
import com.my.dao.FacilityDao;
import com.my.dao.imple.FacilityDaoImple;
import com.my.service.FacilityService;
import com.my.utils.JDBCutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FacilityServiceImple implements FacilityService {
   private  FacilityDao dao=new FacilityDaoImple();
    @Override
    public Page<Facility> showCurrentFacility(int offset, int rowcount) {
        Connection con= null;
        Page<Facility> data=null;
        try {
            con = JDBCutil.getConnection();
        data= dao.findFacilityByPagesize(con, offset, rowcount);

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
    public boolean addFacility(Facility f) {
        boolean b = false;
        Connection con=null;

        try {
           con=JDBCutil.getConnection();
            b = dao.addFacility(con, f);
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
    public boolean updateFacility(Facility f) {
        Connection con=null;
        boolean b = false;
        try {
            con=JDBCutil.getConnection();
            b = dao.updateFacilityByid(con, f);
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
    public boolean deleteFacilityByid(int id) {
        boolean b = false;
        Connection con=null;
        try {
            con=JDBCutil.getConnection();
            b = dao.deleteFacilityByit(con, id);
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
    public  Page<Facility> showFacilityByParameter(QueryObj queryObj, int offset, int rowcount) {
        Connection con=null;
        Page<Facility> datalist=null;
        try {
            con=JDBCutil.getConnection();
            datalist = dao.findFacilityByParameter(con,queryObj,  offset, rowcount);
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
        return  datalist;
    }
}
