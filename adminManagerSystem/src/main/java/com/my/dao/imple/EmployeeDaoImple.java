package com.my.dao.imple;

import com.my.bean.Employee;
import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.QueryEmployee;
import com.my.dao.BaseDao;
import com.my.dao.EmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImple extends BaseDao<Employee> implements EmployeeDao {
    @Override
    public List<Employee> findEmployeeAll(Connection con) {
        return null;
    }

    @Override
    public Page<Employee> findEmployeeByPagesize(Connection con, int offset, int rowcount) {
        Page page = new Page();
        List<Employee> data = new ArrayList<Employee>();
        String sql = "SELECT `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email` FROM  `employee` where `e_status`=1  LIMIT ?,?";
        try {
            String sqlsun = "SELECT COUNT(1) AS sun  FROM   `employee`";
            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, offset);
            pre.setInt(2, rowcount);
            ResultSet re = pre.executeQuery();
            while (re.next()) {
                Employee e = new Employee();
                e.setE_id(re.getInt("e_id"));
                e.setJ_id(re.getInt("j_id"));
                e.setE_name(re.getString("e_name"));
                e.setAddress(re.getString("address"));
                e.setSex(re.getString("sex"));
                e.setPhone(re.getString("phone"));
                e.setEmail(re.getString("email"));
                data.add(e);
            }
            page.setCurentrow(data.size());
            page.setPageData(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return page;
    }

    @Override
    public boolean addEmployee(Connection con, Employee e) throws SQLException {
        String sql = "INSERT INTO `employee`(`j_id`,`e_name`,`address`,`sex`,`phone`,`email`)VALUES(?,?,?,?,?,?)";
        int b = this.update(con, sql,e.getJ_id(),e.getE_name(),e.getAddress(),e.getSex(),e.getPhone(),e.getEmail() );
        return b > 0;
    }

    @Override
    public List<Employee> findEmployeeByParameter(Connection con, QueryEmployee q) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Employee> datalist = new ArrayList<Employee>();
        if (q.getEid() == -1 && q.getJobid()==0 && q.getEname() == null) {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email` FROM `employee` where  `e_status`=1";

            datalist= this.getListInstence(con,sql);


        } else if (q.getEid() != -1) {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email` FROM `employee`  WHERE `e_id`=? and `e_status`=1";
            datalist= this.getListInstence(con,sql,q.getEid());
        }  else if (q.getEid() == -1 && q.getJobid() !=0 && q.getEname() != null) {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email` FROM `employee`  WHERE `j_id`=? AND `e_name`=? and `e_status`=1";
            datalist =   this.getListInstence(con,sql,q.getJobid(),q.getEname());

        }else if (q.getEid() == -1&& q.getJobid() ==0 && q.getEname() != null) {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email` FROM `employee`  WHERE `e_name`=? and `e_status`=1";
            datalist= this.getListInstence(con,sql,q.getEname());
        }else {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email` FROM `employee`  WHERE `j_id`=? and `e_status`=1";
            datalist = this.getListInstence(con, sql, q.getJobid());
        }
        return   datalist;
    }

    @Override
    public boolean updateEmployee(Connection con, Employee e) throws SQLException {
        String sql="UPDATE  `employee` SET `e_name`=?,`sex`=?,`j_id`=?,`address`=?,`email`=? WHERE `e_id`=?";
        int b = this.update(con,sql, e.getE_name(), e.getSex(), e.getJ_id(), e.getAddress(), e.getEmail(), e.getE_id());
        return b>0;

    }

    @Override
    public List<Employee> findEmployeebyId(Connection con, int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email` FROM `employee` WHERE `e_id`!=? and `j_id`=2 and `e_status`=1";
        List<Employee> data = this.getListInstence(con, sql, id);
        return data;
    }

    @Override
    public String findPhoneByid(Connection con, int id) throws SQLException {
        String sql="SELECT `phone` FROM `employee`WHERE  `e_id`=?";
        String phone=this.getValue(con,sql,id);
        return phone;
    }

    @Override
    public boolean deleteEmployeebyId(Connection con, int id) throws SQLException {
        String sql="UPDATE  `employee` SET `e_status`=0  WHERE `e_id`=?";
        int row = this.update(con, sql, id);
        return row>0;
    }



}
