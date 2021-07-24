package com.my.dao;

import com.my.bean.EmployeeCallInfo;

import java.sql.Connection;
import java.sql.SQLException;

public interface EmployeeCallInfoDao {
    /**
     * 查询当前员工班次信息
     */
    EmployeeCallInfo findEmployeeCallInfoByPhone(Connection con,String phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
