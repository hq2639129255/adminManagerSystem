package com.my.dao;

import com.my.bean.Page;
import com.my.bean.SalaryView;
import com.my.bean.Userinfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SalaryViewDao {
    List<SalaryView> findAllSalaryView(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    Page<SalaryView> findUserinfoByPagesize(Connection con, int offset, int rowcount) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException;
    List<SalaryView>findSalaryViewByParameter(Connection con, String month,String  name,int  empno) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    List<SalaryView> findAllSalaryViewByMonth(Connection con,String month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    List<SalaryView> findSalaryBye_id(Connection con,int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
