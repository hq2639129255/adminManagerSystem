package com.my.service;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.QueryObj;

import java.sql.Connection;
import java.util.List;

public interface FacilityService {
    Page<Facility> showCurrentFacility(int offset, int rowcount);
    boolean addFacility(Facility f);
    boolean updateFacility(Facility f);
    boolean deleteFacilityByid(int id);
    List<Facility> showFacilityByParameter(QueryObj queryObj);


}
