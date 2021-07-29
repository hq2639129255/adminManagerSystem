package com.my.dao.imple;

import com.my.bean.Course;
import com.my.bean.Page;
import com.my.dao.BaseDao;
import com.my.dao.CourseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseDaoImple extends BaseDao<Course> implements CourseDao {
    @Override
    public List<Course> findAllCourse(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "SELECT `id`,`c_name` cName,`price`  FROM `course`";
        List<Course> data = this.getListInstence(con, sql);
        return data;
    }


    public Page<Course> findCourseByParameter(Connection con, int c_id, String cname, int pirce, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page<Course> page = new Page<Course>();
        List<Course> datalist = null;
        if (c_id == 0 && cname == null && pirce == -1) {
            String sql = "SELECT id,c_name  cName ,price FROM `course` limit ?,?";
            datalist = this.getListInstence(con, sql,offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM `course`";
           Long rowsum = this.getValue(con, sqlsum);
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pagsun = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pagsun);
            page.setCurentPage((offset / rowcount) + 1);
            if (datalist != null) {
                page.setCurentrow(datalist.size());
            } else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);

        } else if (c_id != 0) {
            String sql = "SELECT id,c_name cName,price FROM `course`  WHERE id=? limit ?,?";
            datalist = this.getListInstence(con, sql,c_id,offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM `course` where id=?";
            Long rowsum = this.getValue(con, sqlsum,c_id);
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pagsun = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pagsun);
            page.setCurentPage((offset / rowcount) + 1);
            if (datalist != null) {
                page.setCurentrow(datalist.size());
            } else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);

        } else if (c_id == 0 && cname != null) {
            String sql = " SELECT id,c_name cName,price FROM `course`  WHERE c_name=? limit ?,?";
            datalist = this.getListInstence(con, sql,cname,offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM `course` where c_name=?";
          Long rowsum =this.getValue(con, sqlsum,cname);
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pagsun = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pagsun);
            page.setCurentPage((offset / rowcount) + 1);
            if (datalist != null) {
                page.setCurentrow(datalist.size());
            } else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);
        } else if (c_id == 0 && pirce != -1) {
            String sql = "SELECT id,c_name cName,price FROM `course`  WHERE price>=? limit ?,?";
            datalist = this.getListInstence(con, sql,pirce,offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM `course` where price>=?";
           Long rowsum = this.getValue(con, sqlsum,pirce);
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pagsun = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pagsun);
            page.setCurentPage((offset / rowcount) + 1);
            if (datalist != null) {
                page.setCurentrow(datalist.size());
            } else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);

        }
        return page;
    }

    @Override
    public boolean insertCourse(Connection con, String cname, double prie) throws SQLException {
        String sql=" INSERT  INTO  course(`c_name`,`price`) VALUES(?,?)";
     int row=   this.update(con,sql,cname,prie);
        return row>0;
    }

    @Override
    public boolean updateCourse(Connection con, Course course) throws SQLException {
        String sql="   UPDATE  course SET `c_name`=?,`price`=? WHERE  id=?";
        int row = this.update(con, sql, course.getCName(), course.getPrice(), course.getId());
        return row>0;
    }

    @Override
    public boolean deleteCourseByid(Connection con, int id) throws SQLException {
        String sql=" DELETE  FROM  course  WHERE id=?";
        int row = this.update(con, sql, id);
        return  row>0;
    }
}
