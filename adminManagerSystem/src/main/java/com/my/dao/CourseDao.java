package com.my.dao;

import com.my.bean.Course;
import com.my.bean.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    /** SELECT `id`,`c_name`,`price`  FROM `course`
     * 查询所有课程信息
     * @param con
     * @return
     */
    List<Course> findAllCourse(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    Page<Course> findCourseByParameter(Connection con, int c_id, String cname, int pirce, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
boolean insertCourse(Connection con,String cname,double prie) throws SQLException;
boolean updateCourse(Connection con,Course course) throws SQLException;
boolean deleteCourseByid(Connection con,int id) throws SQLException;
}
