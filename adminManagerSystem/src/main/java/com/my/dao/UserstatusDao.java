package com.my.dao;

import com.my.bean.Userstatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户状态接口  0正常 1冻结
 */
public interface UserstatusDao {
    List<Userstatus> findAllUserstatus(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
