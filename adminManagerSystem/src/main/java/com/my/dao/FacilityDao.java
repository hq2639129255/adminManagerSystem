package com.my.dao;

import com.my.bean.Facility;
import com.my.bean.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface FacilityDao {
    List<Facility> findFacilityAll(Connection con);
    Page<Facility> findFacilityByPagesize(Connection con,int offset,int rowcount);
    boolean addFacility(Connection con,Facility f) throws SQLException;

}
