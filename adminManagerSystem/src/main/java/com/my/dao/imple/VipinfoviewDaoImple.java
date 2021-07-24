package com.my.dao.imple;

import com.my.bean.*;
import com.my.dao.BaseDao;
import com.my.dao.UserDao;
import com.my.dao.VipinfoviewDao;
import com.my.utils.JDBCutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VipinfoviewDaoImple extends BaseDao<Vipinfoview> implements VipinfoviewDao {
    private UserDao userDao=new UserDaoImple();
    @Override
    public List<Vipinfoview> findVipinfoviewAll(Connection con) {
        return null;
    }

    @Override
    public Page<Vipinfoview> findVipinfoviewByPagesize(Connection con, int offset, int rowcount) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Page page = new Page();
        List<Vipinfoview> data=null;
        String sql = "SELECT v_id ,card_id,e_name,address,sex,t_name,phone,email FROM vipinfo_view LIMIT ?,?";
        try {
            String sqlsun = "  SELECT COUNT(1) AS sun  FROM   vipinfo_view";
            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            data = this.getListInstence(con, sql, offset, rowcount);
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
    public List<Vipinfoview> findVipinfoviewByParameter(Connection con, String phone, String type, String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Vipinfoview> datalist = new ArrayList<Vipinfoview>();
        if (phone==null && type==null && name== null) {
            String sql ="SELECT v_id ,card_id,e_name,address,sex,t_name,phone,email FROM vipinfo_view";
            datalist= this.getListInstence(con,sql);
        } else if (phone != null) {
            String sql = "SELECT v_id ,card_id,e_name,address,sex,t_name,phone,email FROM vipinfo_view WHERE phone LIKE CONCAT('%',?,'%')";
            datalist = this.getListInstence(con,sql,phone);
        }  else if (phone == null && type != null && name != null) {
            String sql = "SELECT v_id ,card_id,e_name,address,sex,t_name,phone,email FROM vipinfo_view WHERE  t_name=? AND e_name=?";
            datalist = this.getListInstence(con,sql,type,name);
        }else if (phone==null && type == null && name != null) {
            String sql = "SELECT v_id ,card_id,e_name,address,sex,t_name,phone,email FROM vipinfo_view WHERE  e_name=?";
            datalist = this.getListInstence(con,sql,name);
        }else {
            String sql = "SELECT v_id ,card_id,e_name,address,sex,t_name,phone,email  FROM vipinfo_view WHERE  t_name=?";
            datalist = this.getListInstence(con,sql,type);
        }


        return datalist;
    }

    /**
     * 新增会员
     * 1、插入一条卡信息
     * 2、插入会员信息
     * 3、插入业务续费信息
     * 4、新增一个会员账号
     * @param con
     * @param vip
     * @param type
     * @return
     */
    @Override
    public boolean addviminfo(Connection con, Vipinfo vip, int type) {
        boolean flag=true;

        PreparedStatement pre1=null;
        PreparedStatement pre3=null;
        PreparedStatement pre=null;
        ResultSet generatedKeys=null;
        ResultSet generatedKeys1 =null;
        try {
            String sql1="INSERT INTO `vipcard`(`t_id`,`serviceendtime`)VALUES(?,DATE_ADD(NOW(), INTERVAL 30 DAY))";
            pre = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, type);
            pre.executeUpdate();
            generatedKeys=pre.getGeneratedKeys();
            int  cardKey=0;
            while (generatedKeys.next()){
                cardKey=generatedKeys.getInt(1);
            }
            System.out.println(cardKey);
            int vipkey=0;
            String sql2=" INSERT INTO `wipinfo`(`card_id`,`e_name`,`sex`,`address`,`phone`,`email`)VALUES(?,?,?,?,?,?)";
            pre1 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            pre1.setInt(1,cardKey);
            pre1.setString(2,vip.getE_name());
            pre1.setString(3,vip.getSex());
            pre1.setString(4,vip.getAddress());
            pre1.setString(5,vip.getPhone());
            pre1.setString(6,vip.getEmail());
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
            u.setAu_id(4);
            u.setUsername(vip.getPhone());
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
}
