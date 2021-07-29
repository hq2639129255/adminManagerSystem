package com.my.dao.imple;

import com.my.bean.Employee;
import com.my.bean.Instructor;
import com.my.bean.Page;
import com.my.bean.Userinfo;
import com.my.dao.BaseDao;
import com.my.dao.UserinfoDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserinfoDaoImple extends BaseDao<Userinfo> implements UserinfoDao{
    @Override
    public List<Userinfo> selectAllUserinfo(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql=" SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                "     INNER JOIN (\n" +
                "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                "     UNION ALL\n" +
                "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`";
        List<Userinfo> data = this.getListInstence(con, sql);
        return data ;
    }

    @Override
    public Page<Userinfo> findUserinfoByPagesize(Connection con, int offset, int rowcount) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        Page<Userinfo> page = new Page<Userinfo>();
        List<Userinfo> data = new ArrayList<Userinfo>();
        String sql = " SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                "     INNER JOIN (\n" +
                "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                "     UNION ALL\n" +
                "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone` LIMIT ?,?";

        String sqlsun = "  SELECT COUNT(1) AS sun  FROM (  SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                "     INNER JOIN (\n" +
                "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                "     UNION ALL\n" +
                "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`) AS b";

        Long rowsun = (Long) this.getValue(con, sqlsun);
        page.setSunrow(Integer.parseInt(rowsun.toString()));

        page.setCurentPage((offset / rowcount) + 1);
        page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
        data  = this.getListInstence(con, sql, offset, rowcount);
        if(data!=null){
            page.setCurentrow(data.size());
        }else {
            page.setCurentrow(0);
        }
        page.setPageData(data);


        System.out.println(page);
        return page;
    }

    @Override
    public Page<Userinfo> findUserinfoByParameter(Connection con, String userName, int aid, String vname, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page<Userinfo> page = new Page<Userinfo>();
        List<Userinfo> datalist = new ArrayList<Userinfo>();
        if (userName==null && aid==0 && vname== null) {
            page=    findUserinfoByPagesize(con, offset, rowcount);
//            datalist=selectAllUserinfo(con);


        } else if (userName!=null) {
            String sql = "SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
                    "    WHERE   username=? LIMIT ?,?";


            String sqlsun = "  SELECT COUNT(1) AS sun  FROM (  SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`) AS b   WHERE   username=?";

            Long rowsun = (Long) this.getValue(con, sqlsun,userName);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            datalist  = this.getListInstence(con, sql,userName, offset, rowcount);
            if( datalist!=null){
                page.setCurentrow( datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData( datalist);

//            String sql = "SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
//                    "     INNER JOIN (\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
//                    "     UNION ALL\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
//                    "    WHERE   username=?";
//            datalist= this.getListInstence(con,sql,userName);
        }  else if (userName==null && aid !=0 && vname != null) {

            String sql = "    SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
                    "    WHERE   e_name=? AND  a_id=? LIMIT ?,?";


            String sqlsun = "  SELECT COUNT(1) AS sun  FROM (  SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`) AS b   WHERE   e_name=? AND  a_id=?";

            Long rowsun = (Long) this.getValue(con, sqlsun,vname,aid);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            datalist  = this.getListInstence(con, sql,vname,aid, offset, rowcount);
            if(datalist!=null){
                page.setCurentrow(datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);

//            String sql = "    SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
//                    "     INNER JOIN (\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
//                    "     UNION ALL\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
//                    "    WHERE   e_name=? AND  a_id=?";
//            datalist =   this.getListInstence(con,sql,vname,aid);

        }else if (userName==null && aid==0 && vname!= null) {
            String sql = "SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
                    "    WHERE   e_name=? LIMIT ?,?";





            String sqlsun = "  SELECT COUNT(1) AS sun  FROM (  SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`) AS b WHERE   e_name=?";

            Long rowsun = (Long) this.getValue(con, sqlsun,vname);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            datalist = this.getListInstence(con, sql,vname,offset, rowcount);
            if(datalist!=null){
                page.setCurentrow(datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);









//            String sql = "SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
//                    "     INNER JOIN (\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
//                    "     UNION ALL\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
//                    "    WHERE   e_name=?";
//            datalist= this.getListInstence(con,sql,vname);
        }else {

            String sql = "SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
                    "    WHERE   a_id=? LIMIT ?,?";



            String sqlsun = "  SELECT COUNT(1) AS sun  FROM (  SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
                    "     INNER JOIN (\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
                    "     UNION ALL\n" +
                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`) AS b WHERE   a_id=?";

            Long rowsun = (Long) this.getValue(con, sqlsun, aid);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            datalist  = this.getListInstence(con, sql, aid, offset, rowcount);
            if(datalist!=null){
                page.setCurentrow(datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);












//            String sql = "SELECT e_name,username,a_id,sta_id,email FROM `user` AS  a     \n" +
//                    "     INNER JOIN (\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `employee`\n" +
//                    "     UNION ALL\n" +
//                    "    SELECT `e_name`,`phone`,`email` FROM `wipinfo`) AS b ON a.`username`=b.`phone`\n" +
//                    "    WHERE   a_id=?";
//            datalist = this.getListInstence(con, sql, aid);
        }
        return   page;
    }
}
