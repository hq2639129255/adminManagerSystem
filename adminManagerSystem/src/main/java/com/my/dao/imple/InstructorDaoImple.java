package com.my.dao.imple;

import com.my.bean.*;
import com.my.dao.BaseDao;
import com.my.dao.InstructorDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstructorDaoImple extends BaseDao<Instructor>  implements InstructorDao{
    @Override
    public List<Instructor> findStudentcount(Connection con, List<Instructor> empl) throws SQLException {
String sql="SELECT COUNT(1) FROM teach_view WHERE e_id=? GROUP BY e_id";
if(empl!=null) {
    Iterator<Instructor> itre = empl.iterator();
    if (empl.size() != 0) {
        while (itre.hasNext()) {
            Instructor ins = itre.next();
            Long conut = this.getValue(con, sql, ins.getE_id());
            if (conut != null) {
                ins.setStudentcount(Integer.parseInt(String.valueOf(conut)));
            } else {
                ins.setStudentcount(0);
            }


        }


    }
}

        return empl;
    }

    @Override
    public List<Instructor> findAllInstructor(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT `e_id`,`e_name` as name,`phone`,`sex` FROM `employee` WHERE `j_id`=2 and  `e_status`=1";
        List<Instructor> data = this.getListInstence(con, sql);
        return data;
    }

    @Override
    public Page<Instructor> findInstructorByPagesize(Connection con, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page<Instructor> page = new Page<Instructor>();
        List<Instructor> data = new ArrayList<Instructor>();
        String sql = "SELECT `e_id`,`e_name` as name,`phone`,`sex` FROM `employee` WHERE `j_id`=2 and  `e_status`=1 LIMIT ?,?";

            String sqlsun = "  SELECT COUNT(1) AS sun  FROM    `employee` WHERE `j_id`=2 and  `e_status`=1";

            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            data  = this.getListInstence(con, sql, offset, rowcount);
          data=this.findStudentcount(con,data);
            if(data!=null){
                page.setCurentrow(data.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(data);


        System.out.println(page);
        return page;
    }

    @Override
    public List<Instructor> findInstructorByParameter(Connection con, String e_id, String e_name, String sex) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Instructor> datalist = new ArrayList<Instructor>();
        if (e_id==null && e_name==null && sex== null) {
            String sql ="SELECT `e_id`,`e_name` as name,`phone`,`sex` FROM `employee` WHERE `j_id`=2 and  `e_status`=1";
            datalist= this.getListInstence(con, sql);
            datalist=this.findStudentcount(con,datalist);
        } else if (e_id != null) {
            String sql = "SELECT `e_id`,`e_name` as name,`phone`,`sex` FROM `employee` WHERE `j_id`=2 and  `e_status`=1 and `e_id`=?";
            datalist = this.getListInstence(con,sql,e_id);
            datalist=this.findStudentcount(con,datalist);
        }  else if (e_id == null && e_name != null && sex != null) {
            String sql = "SELECT `e_id`,`e_name` as name,`phone`,`sex` FROM `employee` WHERE `j_id`=2 and  `e_status`=1 and  `e_name`=? and `sex`=?";
            datalist = this.getListInstence(con,sql,e_name,sex);
            datalist=this.findStudentcount(con,datalist);
        }else if (e_id==null && e_name == null && sex != null) {
            String sql = "SELECT `e_id`,`e_name` as name,`phone`,`sex` FROM `employee` WHERE `j_id`=2 and  `e_status`=1 and  `sex`=?";
            datalist = this.getListInstence(con,sql,sex);
            datalist=this.findStudentcount(con,datalist);
        }else {
            String sql = "SELECT `e_id`,`e_name` as name,`phone`,`sex` FROM `employee` WHERE `j_id`=2 and  `e_status`=1 and  `e_name`=?";
            datalist = this.getListInstence(con,sql,e_name);
        }


        return datalist;
    }
}
