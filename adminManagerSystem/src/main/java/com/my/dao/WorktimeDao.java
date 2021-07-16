package com.my.dao;

import com.my.bean.Worktime;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 考勤班次DAO
 */
public interface WorktimeDao {
    /**
     * 查询所有班次信息
     * @param con
     * @return
     */
    List<Worktime> findAllWorktime(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
