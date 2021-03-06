package com.my.dao.imple;

import com.my.bean.*;
import com.my.dao.BaseDao;
import com.my.dao.UserDao;
import com.my.dao.VipCardViewDao;
import com.my.utils.JDBCutil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VipCardViewDaoImple extends BaseDao<VipCardView> implements VipCardViewDao {
    private SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private UserDao userDao=new UserDaoImple();
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

    /**
     * 会员卡新增
     * 1、插入会员卡信息
     * 2、插入会员信息
     * 3、插入续费业务信息
     * 4、新增一个用户
     * @param con
     * @param cardtype
     * @param vipinfo
     * @return
     */
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
            User u=new User();
            u.setUsername(vipinfo.getPhone());
            u.setAu_id(4);
            userDao.adduser(con,u);




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
            if(data!=null){
            page.setCurentrow(data.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(page);
        return page;
    }

    @Override
    public boolean updateCardType(Connection con,int type,int id) throws SQLException {
        String sql="UPDATE `vipcard` SET `t_id`=? WHERE `card_id`=?";
        int b = this.update(con,sql,type,id);
        return b>0;
    }

    @Override
    public Page<VipCardView> findVipCardViewByParameter(Connection con, VipCardView vipCardView, int offset, int rowcount ) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page page = new Page();
        List<VipCardView> data = new ArrayList<VipCardView>();
        if (vipCardView.getCid()== -1 && vipCardView.getE_name() == null && vipCardView.getT_name() == null) {

            page= findVipCardViewByPagesize( con, offset,  rowcount);


//            String sql = "SELECT  cid,t_name,e_name,serviceendtime   FROM `vipmanager_v`";
//            ResultSet re = this.getListResultSet(con, sql);
//            datalist = this.parserResult(re);
        } else if (vipCardView.getCid() != -1) {
            String sql = " SELECT  cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE cid=? LIMIT ?,?";

            try {
                String sqlsun = "  SELECT COUNT(1) AS sun  FROM   vipmanager_v WHERE cid=?";
                Long rowsun = (Long) this.getValue(con, sqlsun,vipCardView.getCid());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                ResultSet re = this.getListResultSet(con, sql,vipCardView.getCid(), offset, rowcount);
                data= this.parserResult(re);
                if(data!=null){
                    page.setCurentrow(data.size());
                }else {
                    page.setCurentrow(0);
                }
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }

       //     String sql = " SELECT  cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE cid=?";
//            ResultSet re = this.getListResultSet(con, sql, vipCardView.getCid());
//            datalist = this.parserResult(re);
        }  else if (vipCardView.getCid() == -1 && vipCardView.getE_name() != null && vipCardView.getT_name() != null) {
            String sql = "SELECT cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE t_name=? AND e_name=? LIMIT ?,?";


            try {
                String sqlsun = "  SELECT COUNT(1) AS sun  FROM   vipmanager_v WHERE t_name=? AND e_name=?";
                Long rowsun = (Long) this.getValue(con, sqlsun,vipCardView.getT_name(), vipCardView.getE_name());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                ResultSet re = this.getListResultSet(con, sql,vipCardView.getT_name(), vipCardView.getE_name(), offset, rowcount);
                data= this.parserResult(re);
                if(data!=null){
                    page.setCurentrow(data.size());
                }else {
                    page.setCurentrow(0);
                }
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }


//            String sql = "SELECT cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE t_name=? AND e_name=?";
//            ResultSet re = this.getListResultSet(con, sql, vipCardView.getT_name(), vipCardView.getE_name());
//            datalist = this.parserResult(re);
        }else if (vipCardView.getCid() == -1 && vipCardView.getE_name() == null && vipCardView.getT_name() != null) {

            String sql = "SELECT cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE t_name=? LIMIT ?,?";
            try {
                String sqlsun = "  SELECT COUNT(1) AS sun  FROM   vipmanager_v WHERE t_name=?";
                Long rowsun = (Long) this.getValue(con, sqlsun,vipCardView.getT_name());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                ResultSet re = this.getListResultSet(con, sql,vipCardView.getT_name(), offset, rowcount);
                data= this.parserResult(re);
                if(data!=null){
                    page.setCurentrow(data.size());
                }else {
                    page.setCurentrow(0);
                }
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            String sql = "SELECT cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE t_name=?";
//            ResultSet re = this.getListResultSet(con, sql,vipCardView.getT_name());
//            datalist = this.parserResult(re);
        }else {
            String sql = "SELECT  cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE e_name LIKE CONCAT('%',?,'%') LIMIT ?,?";

            try {
                String sqlsun = "  SELECT COUNT(1) AS sun  FROM   vipmanager_v WHERE e_name LIKE CONCAT('%',?,'%')";
                Long rowsun = (Long) this.getValue(con, sqlsun,vipCardView.getE_name());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                ResultSet re = this.getListResultSet(con, sql,vipCardView.getE_name(), offset, rowcount);
                data= this.parserResult(re);
                if(data!=null){
                    page.setCurentrow(data.size());
                }else {
                    page.setCurentrow(0);
                }
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//
//            String sql = "SELECT  cid,t_name,e_name,serviceendtime   FROM `vipmanager_v` WHERE e_name LIKE CONCAT('%',?,'%');";
//            ResultSet re = this.getListResultSet(con, sql, vipCardView.getE_name());
//            datalist = this.parserResult(re);
        }


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
