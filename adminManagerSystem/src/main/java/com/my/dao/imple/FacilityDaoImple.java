package com.my.dao.imple;


import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.QueryObj;
import com.my.dao.BaseDao;
import com.my.dao.FacilityDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FacilityDaoImple extends BaseDao<Facility> implements FacilityDao {
    private SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public List<Facility> findFacilityAll(Connection con) {

        String sql = "SELECT `f_id`,`f_name`,`remark`,`buyTime` FROM `facility`";

        return null;
    }

    @Override
    public Page findFacilityByPagesize(Connection con, int offset, int rowcount) {
        Page page = new Page();
        List<Facility> data = new ArrayList<Facility>();
        String sql = "SELECT `f_id`,`f_name`,`remark`,`buyTime` FROM  `facility`  LIMIT ?,?";
        try {
            String sqlsun = "  SELECT COUNT(1) AS sun  FROM   `facility`";
            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, offset);
            pre.setInt(2, rowcount);
            ResultSet re = pre.executeQuery();
            while (re.next()) {
                Facility f = new Facility();
                f.setId(re.getInt("f_id"));
                f.setF_name(re.getString("f_name"));
                f.setRemark(re.getString("remark"));
                f.setBuyTime(simple.format(re.getDate("buyTime")));
                data.add(f);
            }
            page.setCurentrow(data.size());
            page.setPageData(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return page;
    }

    @Override
    public boolean addFacility(Connection con, Facility f) throws SQLException {
        String sql = " INSERT INTO `facility`(`f_name`,`remark`,`buyTime`) VALUES(?,?,?)";
        int b = this.update(con, sql, f.getF_name(), f.getRemark(), f.getBuyTime());
        return b > 0;
    }

    @Override
    public boolean updateFacilityByid(Connection con, Facility f) throws SQLException {
        String sql = "UPDATE `facility` SET `f_name`=?,`remark`=?,`buyTime`=? WHERE `f_id`=?";
        int b = this.update(con, sql, f.getF_name(), f.getRemark(), f.getBuyTime(), f.getId());
        return b > 0;
    }

    @Override
    public boolean deleteFacilityByit(Connection con, int id) throws SQLException {
        String sql = "DELETE FROM `facility` WHERE `f_id`=?";
        int b = this.update(con, sql, id);
        return b > 0;
    }

    @Override
    public Page<Facility> findFacilityByParameter(Connection con, QueryObj queryObj, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page page = new Page();
        List<Facility> data = new ArrayList<Facility>();

        if (queryObj.getFacilityid() == -1 && queryObj.getFacilityname() == null && queryObj.getFacilitydate() == null) {
            String sql = "SELECT `f_id`,`f_name`,`remark`,`buyTime` FROM `facility` LIMIT ?,?";

            try {
                String sqlsun = "  SELECT COUNT(1) AS sun  FROM   `facility`";
                Long rowsun = (Long) this.getValue(con, sqlsun);
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setInt(1, offset);
                pre.setInt(2, rowcount);
                ResultSet re = pre.executeQuery();
                while (re.next()) {
                    Facility f = new Facility();
                    f.setId(re.getInt("f_id"));
                    f.setF_name(re.getString("f_name"));
                    f.setRemark(re.getString("remark"));
                    f.setBuyTime(simple.format(re.getDate("buyTime")));
                    data.add(f);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (queryObj.getFacilityid() != -1) {
            String sql = "SELECT `f_id`,`f_name`,`remark`,`buyTime` FROM `facility` WHERE `f_id`=? LIMIT ?,?";

            try {
                String sqlsun = "  SELECT COUNT(1) AS sun  FROM   `facility` WHERE `f_id`=?";
                Long rowsun = (Long) this.getValue(con, sqlsun, queryObj.getFacilityid());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setInt(1, queryObj.getFacilityid());
                pre.setInt(2, offset);
                pre.setInt(3, rowcount);
                ResultSet re = pre.executeQuery();
                while (re.next()) {
                    Facility f = new Facility();
                    f.setId(re.getInt("f_id"));
                    f.setF_name(re.getString("f_name"));
                    f.setRemark(re.getString("remark"));
                    f.setBuyTime(simple.format(re.getDate("buyTime")));
                    data.add(f);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (queryObj.getFacilityid() == -1 && queryObj.getFacilityname() != null && queryObj.getFacilitydate() != null) {
            String sql = "SELECT `f_id`,`f_name`,`remark`,`buyTime` FROM `facility` WHERE DATE(`buyTime`)=STR_TO_DATE(?, '%Y-%m-%d') AND `f_name`=? LIMIT ?,?";
            try {
                String sqlsun = "SELECT COUNT(1) AS sun  FROM   `facility` where DATE(`buyTime`)=STR_TO_DATE(?, '%Y-%m-%d') AND `f_name`=?";
                Long rowsun = (Long) this.getValue(con, sqlsun, queryObj.getFacilityname(), queryObj.getFacilitydate());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setString(1, queryObj.getFacilitydate());
                pre.setString(2, queryObj.getFacilityname());
                pre.setInt(3, offset);
                pre.setInt(4, rowcount);
                ResultSet re = pre.executeQuery();
                while (re.next()) {
                    Facility f = new Facility();
                    f.setId(re.getInt("f_id"));
                    f.setF_name(re.getString("f_name"));
                    f.setRemark(re.getString("remark"));
                    f.setBuyTime(simple.format(re.getDate("buyTime")));
                    data.add(f);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (queryObj.getFacilityid() == -1 && queryObj.getFacilityname() == null && queryObj.getFacilitydate() != null) {

            String sql = "SELECT `f_id`,`f_name`,`remark`,`buyTime` FROM `facility`  WHERE  DATE(`buyTime`)=STR_TO_DATE(?, '%Y-%m-%d') LIMIT ?,?";


            try {
                String sqlsun = "SELECT COUNT(1) AS sun  FROM   `facility` WHERE  DATE(`buyTime`)=STR_TO_DATE(?, '%Y-%m-%d')";
                Long rowsun = (Long) this.getValue(con, sqlsun, queryObj.getFacilitydate());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);

                pre.setString(1, queryObj.getFacilitydate());
                pre.setInt(2, offset);
                pre.setInt(3, rowcount);
                ResultSet re = pre.executeQuery();
                while (re.next()) {
                    Facility f = new Facility();
                    f.setId(re.getInt("f_id"));
                    f.setF_name(re.getString("f_name"));
                    f.setRemark(re.getString("remark"));
                    f.setBuyTime(simple.format(re.getDate("buyTime")));
                    data.add(f);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "SELECT `f_id`,`f_name`,`remark`,`buyTime` FROM `facility`  WHERE  `f_name`=? LIMIT ?,?";
            try {
                String sqlsun = "SELECT COUNT(1) AS sun  FROM   `facility` WHERE `f_name`=?";
                Long rowsun = (Long) this.getValue(con, sqlsun, queryObj.getFacilityname());
                page.setSunrow(Integer.parseInt(rowsun.toString()));
                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setString(1, queryObj.getFacilityname());
                pre.setInt(2, offset);
                pre.setInt(3, rowcount);
                ResultSet re = pre.executeQuery();
                while (re.next()) {
                    Facility f = new Facility();
                    f.setId(re.getInt("f_id"));
                    f.setF_name(re.getString("f_name"));
                    f.setRemark(re.getString("remark"));
                    f.setBuyTime(simple.format(re.getDate("buyTime")));
                    data.add(f);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return page;

}
    public List<Facility> parserResult (ResultSet re){
        List<Facility> data = new ArrayList<>();
        try {
            while (re.next()) {
                Facility f = new Facility();
                f.setId(re.getInt("f_id"));
                f.setF_name(re.getString("f_name"));
                f.setRemark(re.getString("remark"));
                f.setBuyTime(simple.format(re.getDate("buyTime")));
                data.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    re.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

}