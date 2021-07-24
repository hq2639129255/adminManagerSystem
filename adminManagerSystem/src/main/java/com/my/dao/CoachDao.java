package com.my.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface CoachDao {
    boolean updateCoach(Connection con,int eid,int sid) throws SQLException;
    boolean updateCoachByE_idandV_id(Connection con,int newe_id,int olde_id,int v_id) throws SQLException;

    /**
     * 插入课程关联信息
     * @param con
     * @param e_id
     * @param v_id
     * @param c_id
     * @return
     */
    boolean insertCoachinfo(Connection con,int e_id,int v_id,int c_id) throws SQLException;

}
