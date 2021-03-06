package com.my.dao.imple;

import com.my.bean.Employee;
import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.QueryEmployee;
import com.my.dao.BaseDao;
import com.my.dao.EmployeeDao;
import com.my.dao.SalaryDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImple extends BaseDao<Employee> implements EmployeeDao {
   private SalaryDao salaryDao=new SalaryDaoImple();
    @Override
    public List<Employee> findEmployeeAll(Connection con) {
        return null;
    }

    @Override
    public Page<Employee> findEmployeeByPagesize(Connection con, int offset, int rowcount) {
        Page page = new Page();
        List<Employee> data = new ArrayList<Employee>();
        String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`workId`  FROM  `employee` where `e_status`=1  LIMIT ?,?";
        try {
            String sqlsun = "SELECT COUNT(1) AS sun  FROM   `employee` where `e_status`=1";
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
                e.setWorkId(re.getInt("workId"));
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
    public boolean addEmployee(Connection con, Employee e,double salary) throws SQLException {
        PreparedStatement pre=null;
        String sql = "INSERT INTO `employee`(`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`workId`)VALUES(?,?,?,?,?,?,?)";
     //   int b = this.update(con, sql,e.getJ_id(),e.getE_name(),e.getAddress(),e.getSex(),e.getPhone(),e.getEmail(),e.getWorkId() );
        pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pre.setInt(1,e.getJ_id());
        pre.setString(2,e.getE_name());
        pre.setString(3,e.getAddress());
        pre.setString(4,e.getSex());
        pre.setString(5,e.getPhone());
        pre.setString(6,e.getEmail());
        pre.setInt(7,e.getWorkId());
        pre.executeUpdate();
        ResultSet generatedKeys = pre.getGeneratedKeys();
        int empKey=0;
        while (generatedKeys.next()){
            empKey=generatedKeys.getInt(1);
        }
        boolean flag=false;
if(empKey!=0) {
    flag= salaryDao.insetSalary(con, empKey, salary);
}
        return flag;
    }

    @Override
    public Page<Employee> findEmployeeByParameter(Connection con, QueryEmployee q,int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page page = new Page();
        List<Employee> data = new ArrayList<Employee>();
        if (q.getEid() == -1 && q.getJobid()==0 && q.getEname() == null) {
            page= findEmployeeByPagesize( con, offset, rowcount);

        } else if (q.getEid() != -1) {
            String sql ="SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`workId` FROM `employee`  WHERE `e_id`=? and `e_status`=1 LIMIT ?,?";
            try {
                String sqlsun = "SELECT COUNT(1) AS sun  FROM   `employee` where `e_status`=1 and `e_id`=?";
                Long rowsun = (Long) this.getValue(con, sqlsun,q.getEid());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setInt(1, q.getEid());
                pre.setInt(2, offset);
                pre.setInt(3, rowcount);
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
                    e.setWorkId(re.getInt("workId"));
                    data.add(e);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }  else if (q.getEid() == -1 && q.getJobid() !=0 && q.getEname() != null) {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`workId` FROM `employee`  WHERE `j_id`=? AND `e_name`=? and `e_status`=1 LIMIT ?,?";

            try {
                String sqlsun = "SELECT COUNT(1) AS sun  FROM   `employee` where `e_status`=1 AND `j_id`=? AND `e_name`=?";
                Long rowsun = (Long) this.getValue(con, sqlsun,q.getJobid(),q.getEname());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);

                pre.setInt(1, q.getJobid());
                pre.setString(2,  q.getEname());
                pre.setInt(3, offset);
                pre.setInt(4, rowcount);
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
                    e.setWorkId(re.getInt("workId"));
                    data.add(e);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (q.getEid() == -1&& q.getJobid() ==0 && q.getEname() != null) {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`workId` FROM `employee`  WHERE `e_name`=? and `e_status`=1 LIMIT ?,?";

            try {
                String sqlsun = "SELECT COUNT(1) AS sun  FROM   `employee` where `e_status`=1 and `e_name`=?";
                Long rowsun = (Long) this.getValue(con, sqlsun,q.getEname());
                page.setSunrow(Integer.parseInt(rowsun.toString()));

                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);

                pre.setString(1,q.getEname());
                pre.setInt(2, offset);
                pre.setInt(3, rowcount);
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
                    e.setWorkId(re.getInt("workId"));
                    data.add(e);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            String sql = "SELECT  `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`workId` FROM `employee`  WHERE `j_id`=? and `e_status`=1 LIMIT ?,?";

            try {
                String sqlsun = "SELECT COUNT(1) AS sun  FROM   `employee` where `e_status`=1 and `j_id`=?";
                Long rowsun = (Long) this.getValue(con, sqlsun, q.getJobid());
                page.setSunrow(Integer.parseInt(rowsun.toString()));
                page.setCurentPage((offset / rowcount) + 1);
                page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setInt(1,q.getJobid());
                pre.setInt(2, offset);
                pre.setInt(3, rowcount);
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
                    e.setWorkId(re.getInt("workId"));
                    data.add(e);
                }
                page.setCurentrow(data.size());
                page.setPageData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return   page;
    }

    @Override
    public boolean updateEmployee(Connection con, Employee e) throws SQLException {
        String sql="UPDATE  `employee` SET `e_name`=?,`sex`=?,`j_id`=?,`address`=?,`email`=?,`workId`=? WHERE `e_id`=?";
        int b = this.update(con,sql, e.getE_name(), e.getSex(), e.getJ_id(), e.getAddress(), e.getEmail(),e.getWorkId(), e.getE_id());
        return b>0;

    }

    @Override
    public List<Employee> findEmployeebyId(Connection con, int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`workId` FROM `employee` WHERE `e_id`!=? and `j_id`=2 and `e_status`=1";
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

    @Override
    public Employee findEmployeeByPhone(Connection con,String phone) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`e_status`,`workId`  FROM `employee` WHERE `phone`=?";
        Employee  data = this.getInstence(con, sql, phone);
        return data;
    }

    @Override
    public Employee findEmployeeByEmail(Connection con, String emil) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT `e_id`,`j_id`,`e_name`,`address`,`sex`,`phone`,`email`,`e_status`,`workId`  FROM `employee` WHERE `email`=?";
        Employee  data = this.getInstence(con, sql,emil);
        return data;
    }

    @Override
    public List<Employee> findAllTeach(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT e_id,e_name FROM employee WHERE e_status=1 AND j_id=2";
        List<Employee> data = this.getListInstence(con, sql);
        return data;
    }


}
