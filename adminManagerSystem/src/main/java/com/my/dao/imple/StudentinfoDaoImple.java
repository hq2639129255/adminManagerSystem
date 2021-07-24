package com.my.dao.imple;

import com.my.bean.Instructor;
import com.my.bean.Studentinfo;
import com.my.dao.BaseDao;
import com.my.dao.StudentinfoDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentinfoDaoImple extends BaseDao<Studentinfo> implements StudentinfoDao {
    @Override
    public List<Studentinfo> findStudentBye_id(Connection con, int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        String sql="SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE  e_id=?";
        List<Studentinfo> data=  this.getListInstence(con,sql,id);
        return data;
    }

    @Override
    public List<Studentinfo> findStudentinfoByParameter(Connection con, String phone, String name, String sex,int e_id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Studentinfo> datalist = new ArrayList<Studentinfo>();
        if (phone==null && name==null && sex== null) {
            String sql ="SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE e_id=?";
            datalist= this.getListInstence(con, sql,e_id);
        } else if (phone != null) {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE e_id=? and vipphone=?";
            datalist = this.getListInstence(con, sql,e_id,phone);

        }  else if (phone == null && name!= null && sex != null) {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE e_id=? and vipsex=? and vipname=?";
            datalist = this.getListInstence(con, sql,e_id,sex,name);
        }else if (phone==null && sex == null && name != null) {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE e_id=? and vipname=?";
            datalist = this.getListInstence(con, sql,e_id,name);
        }else {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE e_id=? and vipsex=? ";
            datalist = this.getListInstence(con, sql,e_id,sex);
        }


        return datalist;
    }

    @Override
    public List<Studentinfo> findStudentByPhone(Connection con, String phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        String sql="SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE  phone=?";
        List<Studentinfo> data=  this.getListInstence(con,sql,phone);
        return data;
    }

    @Override
    public List<Studentinfo> findStudentinfoByParameter(Connection con, String phone, String name, String sex, String e_phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Studentinfo> datalist = new ArrayList<Studentinfo>();
        if (phone==null && name==null && sex== null) {
            String sql ="SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE phone=?";
            datalist= this.getListInstence(con, sql,e_phone);
        } else if (phone != null) {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE phone=? and vipphone=?";
            datalist = this.getListInstence(con, sql,e_phone,phone);

        }  else if (phone == null && name!= null && sex != null) {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE phone=? and vipsex=? and vipname=?";
            datalist = this.getListInstence(con, sql,e_phone,sex,name);
        }else if (phone==null && sex == null && name != null) {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE phone=? and vipname=?";
            datalist = this.getListInstence(con, sql,e_phone,name);
        }else {
            String sql = "SELECT  e_id,v_id,card_id,vipname,c_name,vipphone,vipsex FROM teach_view WHERE phone=? and vipsex=? ";
            datalist = this.getListInstence(con, sql,e_phone,sex);
        }


        return datalist;
    }
}
