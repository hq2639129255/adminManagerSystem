package com.my.dao;

import com.my.bean.Course;

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
}
