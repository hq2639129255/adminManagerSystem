package com.my.dao.imple;

import com.my.bean.Authority;
import com.my.bean.Userstatus;
import com.my.dao.AuthorityDao;
import com.my.dao.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AuthorityDaoImple extends BaseDao<Authority>  implements AuthorityDao {
    @Override
    public List<Authority> findAllAuthority(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql=" SELECT `a_id` as id,`a_name`as name FROM `authority`";
        List<Authority> data = this.getListInstence(con, sql);
        return data;

    }
}
