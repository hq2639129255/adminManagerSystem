package com.my.dao.imple;

import com.my.bean.CardType;
import com.my.bean.Jobtype;
import com.my.dao.BaseDao;
import com.my.dao.JobtypeDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobtypeDaoImple extends BaseDao<Jobtype> implements JobtypeDao {
    @Override
    public List<Jobtype> findAllJobtype(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String  sql="SELECT `j_id` as jId,`j_name` as jName FROM jobtype";
        List<Jobtype> data=null;
        data = this.getListInstence(con, sql);
        return data;
    }
}
