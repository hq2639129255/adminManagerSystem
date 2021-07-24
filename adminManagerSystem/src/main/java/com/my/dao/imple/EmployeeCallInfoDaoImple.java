package com.my.dao.imple;

import com.my.bean.EmployeeCallInfo;
import com.my.dao.BaseDao;
import com.my.dao.EmployeeCallInfoDao;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeCallInfoDaoImple extends BaseDao<EmployeeCallInfo> implements EmployeeCallInfoDao {
    @Override
    public EmployeeCallInfo findEmployeeCallInfoByPhone(Connection con, String phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
       String sql=" SELECT e_id,e_name,phone,w_id,w_name,starttime,endstime FROM `employee` a  INNER JOIN `worktime` b ON a.`workId`=b.`w_id`   WHERE  phone=?";
        EmployeeCallInfo data = this.getInstence(con, sql, phone);
        return data;
    }
}
