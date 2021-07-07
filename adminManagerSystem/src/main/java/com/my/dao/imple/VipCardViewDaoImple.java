package com.my.dao.imple;

import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.VipCardView;
import com.my.bean.Vipinfo;
import com.my.dao.BaseDao;
import com.my.dao.VipCardViewDao;
import com.my.utils.JDBCutil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VipCardViewDaoImple extends BaseDao<VipCardView> implements VipCardViewDao {
    private SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public List<VipCardView> findVipCardViewAll(Connection con) {
        List<VipCardView> data=null;
String sql="SELECT  * FROM vipmanager_v ";
        try {
            ResultSet re = this.getListResultSet(con,sql);
            data= this.parserResult(re);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public boolean insert(Connection con, int cardtype, Vipinfo vipinfo) {
        boolean flag=true;

        PreparedStatement pre1=null;
        PreparedStatement pre3=null;
        PreparedStatement pre=null;
        ResultSet generatedKeys=null;
        ResultSet generatedKeys1 =null;
        try {
            String sql1="INSERT INTO `vipcard`(`t_id`,`serviceendtime`)VALUES(?,DATE_ADD(NOW(), INTERVAL 30 DAY))";
           pre = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1,cardtype);
            pre.executeUpdate();
            generatedKeys=pre.getGeneratedKeys();
            int  cardKey=0;
            while (generatedKeys.next()){
                cardKey=generatedKeys.getInt(1);
            }
            System.out.println(cardKey);
            int vipkey=0;
            String sql2="INSERT INTO `wipinfo`(`card_id`,`e_name`,`sex`,`phone`,`email`)VALUES(?,?,?,?,?)";
            pre1 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            pre1.setInt(1,cardKey);
            pre1.setString(2,vipinfo.getE_name());
            pre1.setString(3,vipinfo.getSex());
            pre1.setString(4,vipinfo.getPhone());
            pre1.setString(5,vipinfo.getEmail());
            pre1.executeUpdate();
            generatedKeys1= pre1.getGeneratedKeys();
            System.out.println(generatedKeys1);
            while (generatedKeys1.next()){
                vipkey=generatedKeys1.getInt(1);
            }
            System.out.println(vipkey);
            String sql3="INSERT INTO `renew`(`card_id`,`vipid`,`set_id`,`buytime`)VALUES(?,?,1,NOW())";

          pre3 =con.prepareStatement(sql3);
            pre3.setInt(1,cardKey);
            pre3.setInt(2,vipkey);
            pre3.executeUpdate();
        } catch (SQLException e) {

            try {
                flag=false;
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCutil.close(null,pre,generatedKeys);
            JDBCutil.close(null,pre1,generatedKeys1);
            JDBCutil.close(null,pre3,null);
        }
        return flag;
    }

    @Override
    public Page<VipCardView> findVipCardViewByPagesize(Connection con, int offset, int rowcount) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Page page = new Page();
        List<VipCardView> data = new ArrayList<VipCardView>();
        String sql = " SELECT cid,t_name,e_name,serviceendtime FROM `vipmanager_v`LIMIT ?,?";
        try {
            String sqlsun = "  SELECT COUNT(1) AS sun  FROM   vipmanager_v";
            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            ResultSet re = this.getListResultSet(con, sql, offset, rowcount);
           data= this.parserResult(re);
            page.setCurentrow(data.size());
            page.setPageData(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(page);
        return page;
    }

    public List<VipCardView> parserResult(ResultSet re) {
        List<VipCardView> data = new ArrayList<VipCardView>();
        try {

            while (re.next()) {
                VipCardView f = new VipCardView();
                f.setCid(re.getInt("cid"));
                f.setT_name(re.getString("t_name"));
                f.setE_name(re.getString("e_name"));
                f.setServiceendtime(simple.format(re.getDate("serviceendtime")));
                data.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
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
