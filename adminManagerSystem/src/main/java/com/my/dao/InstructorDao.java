package com.my.dao;

import com.my.bean.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InstructorDao {
    List<Instructor> findStudentcount(Connection con, List<Instructor> empl) throws SQLException;
    List<Instructor> findAllInstructor(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    Page<Instructor> findInstructorByPagesize(Connection con, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<Instructor> findInstructorByParameter(Connection con, String e_id, String e_name, String sex) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
