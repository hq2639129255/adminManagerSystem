package com.my.dao.imple;

import com.my.bean.TeachInfo;
import com.my.dao.BaseDao;
import com.my.dao.TeachInfoDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TeachInfoDaoImple extends BaseDao<TeachInfo> implements TeachInfoDao {
    @Override
    public List<TeachInfo> findTeachInfoByvipphone(Connection con, String vphone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT e_id,e_name,phone,sex,c_name FROM teach_view  WHERE  vipphone=?";
        List<TeachInfo> data = this.getListInstence(con, sql, vphone);
        return data;
    }
}
