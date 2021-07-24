package com.my.dao;

import com.my.bean.Studentinfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentinfoDao {
   List<Studentinfo> findStudentBye_id(Connection con, int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<Studentinfo> findStudentinfoByParameter(Connection con,String  phone,String name,String sex,int e_id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    List<Studentinfo>  findStudentByPhone(Connection con,String phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    List<Studentinfo> findStudentinfoByParameter(Connection con, String phone, String name, String sex,String e_phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
