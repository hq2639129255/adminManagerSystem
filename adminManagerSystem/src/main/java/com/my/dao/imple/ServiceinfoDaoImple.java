package com.my.dao.imple;

import com.my.bean.Page;
import com.my.bean.Serviceinfo;
import com.my.dao.BaseDao;
import com.my.dao.ServiceinfoDao;
import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ServiceinfoDaoImple extends BaseDao<Serviceinfo> implements ServiceinfoDao {
    @Override
    public Page<Serviceinfo> findPageByParameter(Connection con, Serviceinfo serviceinfo, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page<Serviceinfo> page = new Page<Serviceinfo>();
        List<Serviceinfo> data = null;
        if (serviceinfo.getCard_id() == -1 && serviceinfo.getE_name() == null && serviceinfo.getNeme() == null) {
            String sql = "SELECT card_id,e_name,neme,buytime FROM serviceinfo_view limit ?,?";
            data = this.getListInstence(con, sql, offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM serviceinfo_view";
            Long rowsum = this.getValue(con, sqlsum);
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pageSum = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pageSum);
            page.setCurentPage((offset / rowcount) + 1);
            page.setPageData(data);
            if (data == null) {
                page.setCurentrow(0);
            } else {
                page.setCurentrow(data.size());
            }
        } else if (serviceinfo.getCard_id() != -1 && serviceinfo.getE_name() == null && serviceinfo.getNeme() != null) {
            String sql = "SELECT  card_id,e_name,neme,buytime FROM serviceinfo_view  WHERE card_id=? AND neme=? limit ?,?";
            data = this.getListInstence(con, sql, serviceinfo.getCard_id(), serviceinfo.getNeme(), offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM serviceinfo_view where card_id=? AND neme=?";
            Long rowsum = this.getValue(con, sqlsum, serviceinfo.getCard_id(), serviceinfo.getNeme());
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pageSum = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pageSum);
            page.setCurentPage((offset / rowcount) + 1);
            page.setPageData(data);
            if (data == null) {
                page.setCurentrow(0);
            } else {
                page.setCurentrow(data.size());
            }
            page.setPageData(data);

        } else if (serviceinfo.getCard_id() != -1) {
            String sql = "SELECT  card_id,e_name,neme,buytime FROM serviceinfo_view  WHERE card_id=? limit ?,?";
            data = this.getListInstence(con, sql, serviceinfo.getCard_id(), offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM serviceinfo_view WHERE card_id=? ";
            Long rowsum = this.getValue(con, sqlsum, serviceinfo.getCard_id());
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pageSum = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pageSum);
            page.setCurentPage((offset / rowcount) + 1);
            page.setPageData(data);
            if (data == null) {
                page.setCurentrow(0);
            } else {
                page.setCurentrow(data.size());
            }
            page.setPageData(data);
        } else if (serviceinfo.getCard_id() == -1 && serviceinfo.getE_name() != null && serviceinfo.getNeme() != null) {
            String sql = "SELECT  card_id,e_name,neme,buytime FROM serviceinfo_view  WHERE e_name=? AND  neme=? limit ?,?";
            data = this.getListInstence(con, sql, serviceinfo.getE_name(), serviceinfo.getNeme(), offset, rowcount);
            String sqlsum = "SELECT COUNT(1) FROM serviceinfo_view WHERE e_name=? AND  neme=?";
            Long rowsum = this.getValue(con, sqlsum, serviceinfo.getE_name(), serviceinfo.getNeme());
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pageSum = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pageSum);
            page.setCurentPage((offset / rowcount) + 1);
            page.setPageData(data);
            if (data == null) {
                page.setCurentrow(0);
            } else {
                page.setCurentrow(data.size());
            }
            page.setPageData(data);
        } else if (serviceinfo.getCard_id() == -1 && serviceinfo.getE_name() != null && serviceinfo.getNeme() == null) {

            String sql = "SELECT  card_id,e_name,neme,buytime FROM serviceinfo_view  WHERE e_name=? limit ?,?";
            data = this.getListInstence(con, sql, serviceinfo.getE_name(), offset, rowcount);
            String sqlSum = "SELECT COUNT(1) FROM serviceinfo_view WHERE e_name=?";
            Long rowsum = this.getValue(con, sqlSum, serviceinfo.getE_name());
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pageSum = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pageSum);
            page.setCurentPage((offset / rowcount) + 1);
            page.setPageData(data);
            if (data == null) {
                page.setCurentrow(0);
            } else {
                page.setCurentrow(data.size());
            }
            page.setPageData(data);


        } else {

            String sql = "SELECT  card_id,e_name,neme,buytime FROM serviceinfo_view  WHERE   neme=? limit ?,?";
            data = this.getListInstence(con, sql, serviceinfo.getNeme(), offset, rowcount);
            String sqlSum = "SELECT COUNT(1) FROM serviceinfo_view WHERE neme=?";

            Long rowsum = this.getValue(con, sqlSum, serviceinfo.getNeme());
            page.setSunrow(Integer.parseInt(rowsum.toString()));
            int pageSum = (int) Math.ceil((rowsum + 0.01) / rowcount);
            page.setSunPage(pageSum);
            page.setCurentPage((offset / rowcount) + 1);
            page.setPageData(data);
            if (data == null) {
                page.setCurentrow(0);
            } else {
                page.setCurentrow(data.size());
            }
            page.setPageData(data);

        }
        return page;


    }
}
