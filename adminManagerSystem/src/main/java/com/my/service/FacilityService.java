package com.my.service;

import com.my.bean.Facility;
import com.my.bean.Page;

import java.sql.Connection;

public interface FacilityService {
    Page<Facility> showCurrentFacility(int offset, int rowcount);
    boolean addFacility(Facility f);


}
