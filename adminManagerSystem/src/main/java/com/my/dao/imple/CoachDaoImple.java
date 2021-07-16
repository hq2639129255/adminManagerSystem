package com.my.dao.imple;

import com.my.bean.Coach;
import com.my.dao.BaseDao;
import com.my.dao.CoachDao;

import java.sql.Connection;
import java.sql.SQLException;

public class CoachDaoImple extends BaseDao<Coach>  implements CoachDao{
    @Override
    public boolean updateCoach(Connection con, int eid, int sid) throws SQLException {
      String sql="UPDATE     `coach` SET `e_id`=? WHERE  `e_id`=?";
     int row= this.update(con,sql,sid,eid);
        return row>0;
    }

    @Override
    public boolean updateCoachByE_idandV_id(Connection con, int newe_id, int olde_id, int v_id) throws SQLException {
    String sql="UPDATE `coach` set  e_id=? WHERE e_id=? AND v_id=?";
   int row= this.update(con,sql,newe_id,olde_id,v_id);
        return row>0;
    }
}
