package com.my.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import javax.sql.DataSource;
import java.sql.*;

public class JDBCutil {
    private static DataSource dbs;
    static{
        ComboPooledDataSource c3p0dbs=new ComboPooledDataSource("fitnessdb");
        dbs=c3p0dbs;
    }
    public static Connection getConnection() throws SQLException {
        return  dbs.getConnection();
    }
    public static void close(Statement sta, PreparedStatement pre, ResultSet re){
        try {
            if(sta!=null){
                sta.close();
            }
            if(pre!=null){
                pre.close();
            }
            if(re!=null){
                re.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
