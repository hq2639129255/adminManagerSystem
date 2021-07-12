package com.my.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface CoachDao {
    boolean updateCoach(Connection con,int eid,int sid) throws SQLException;
}
