package com.my.service.imple;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.dao.FacilityDao;
import com.my.dao.imple.FacilityDaoImple;
import com.my.service.FacilityService;
import com.my.utils.JDBCutil;

import java.sql.Connection;
import java.sql.SQLException;

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
}
