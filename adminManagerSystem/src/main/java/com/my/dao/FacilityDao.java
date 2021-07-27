package com.my.dao;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.QueryObj;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface FacilityDao {
    List<Facility> findFacilityAll(Connection con);
    Page<Facility> findFacilityByPagesize(Connection con,int offset,int rowcount);
    boolean addFacility(Connection con,Facility f) throws SQLException;
    boolean updateFacilityByid(Connection con,Facility f) throws SQLException;
    boolean deleteFacilityByit(Connection con,int id) throws SQLException;
    Page<Facility> findFacilityByParameter(Connection con,QueryObj queryObj,int offset,int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
