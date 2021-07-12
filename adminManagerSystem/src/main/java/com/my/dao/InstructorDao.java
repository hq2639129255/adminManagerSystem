package com.my.dao;

import com.my.bean.Employee;
import com.my.bean.Facility;
import com.my.bean.Instructor;
import com.my.bean.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InstructorDao {
    List<Instructor> findStudentcount(Connection con, List<Instructor> empl) throws SQLException;
    List<Instructor> findAllInstructor(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    Page<Instructor> findInstructorByPagesize(Connection con, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
