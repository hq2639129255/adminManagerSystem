package com.my.dao.imple;

import com.my.bean.Worktime;
import com.my.dao.BaseDao;
import com.my.dao.WorktimeDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WorktimeDaoImple extends BaseDao<Worktime> implements WorktimeDao {
    @Override
    public List<Worktime> findAllWorktime(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
      String sql="SELECT `w_id` as wId,`w_name` as wName,`starttime`,`endstime` FROM `worktime`";
        List<Worktime> data=this.getListInstence(con,sql);
        return data;
    }


}
