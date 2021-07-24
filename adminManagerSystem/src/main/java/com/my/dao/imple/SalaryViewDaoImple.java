package com.my.dao.imple;

import com.my.bean.Page;
import com.my.bean.SalaryView;
import com.my.bean.Userinfo;
import com.my.dao.BaseDao;
import com.my.dao.SalaryDao;
import com.my.dao.SalaryViewDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryViewDaoImple  extends BaseDao<SalaryView> implements SalaryViewDao{

    @Override
    public List<SalaryView> findAllSalaryView(Connection con) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT sa_month,e_id,e_name,salary,payment,award,net_payroll,phone,remark,salaryStatus FROM salary_view";
        List<SalaryView> data = this.getListInstence(con, sql);
        return data;
    }

    @Override
    public Page<SalaryView> findUserinfoByPagesize(Connection con, int offset, int rowcount) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        Page<SalaryView> page = new Page<SalaryView>();
        List<SalaryView> data = new ArrayList<SalaryView>();
        String sql ="SELECT sa_month,e_id,e_name,salary,payment,award,net_payroll,phone,remark,salaryStatus FROM salary_view LIMIT ?,?";

        String sqlsun = "SELECT COUNT(1) FROM salary_view";

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
    public List<SalaryView> findSalaryViewByParameter(Connection con, String  month, String name, int empno) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<SalaryView> datalist = new ArrayList<SalaryView>();
        if (month==null && empno==0 && name== null) {
            datalist=findAllSalaryView(con);


        } else if (empno!=0 &&month!=null ) {
            String sql = "SELECT sa_month,e_id,e_name,salary,payment,award,net_payroll,phone,remark,salaryStatus FROM salary_view WHERE e_id=? AND CONCAT(YEAR(sa_month),'-',MONTH(sa_month))=CONCAT(YEAR(?),'-',MONTH(?))";
            datalist= this.getListInstence(con,sql,empno,month,month);
        }  else if (empno!=0) {
            String sql = " SELECT sa_month,e_id,e_name,salary,payment,award,net_payroll,phone,remark,salaryStatus FROM salary_view WHERE e_id=?";
            datalist =   this.getListInstence(con,sql,empno);

        }else if (name!=null &&month!=null) {
            String sql = "SELECT sa_month,e_id,e_name,salary,payment,award,net_payroll,phone,remark,salaryStatus FROM salary_view WHERE e_name=? AND  CONCAT(YEAR(sa_month),'-',MONTH(sa_month))=CONCAT(YEAR(?),'-',MONTH(?))";
            datalist= this.getListInstence(con,sql,name,month,month);
        }else if(name!=null) {
            String sql = "SELECT sa_month,e_id,e_name,salary,payment,award,net_payroll,phone,remark,salaryStatus FROM salary_view WHERE e_name=?";
            datalist = this.getListInstence(con, sql,name);
        }else if(month!=null) {
            String sql = " SELECT sa_month,e_id,e_name,salary,payment,award,net_payroll,phone,remark,salaryStatus FROM salary_view WHERE CONCAT(YEAR(sa_month),'-',MONTH(sa_month))=CONCAT(YEAR(?),'-',MONTH(?))";
            datalist = this.getListInstence(con, sql,month,month);
        }
        return   datalist;
    }

    /**
     * 根据月份查询工资信息用户工资发放功能
     * @param con
     * @param month
     * @return
     */
    @Override
    public List<SalaryView> findAllSalaryViewByMonth(Connection con, String month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT sa_month,e_id,e_name,salary,phone FROM  `salary_view` WHERE CONCAT(YEAR(sa_month),'-',MONTH(sa_month))=CONCAT(YEAR(?),'-',MONTH(?))";
        List<SalaryView> data = this.getListInstence(con, sql, month, month);
        return data;
    }

    @Override
    public List<SalaryView> findSalaryBye_id(Connection con, int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT  sa_month,salary,payment,award,net_payroll,remark FROM  salary_view  WHERE e_id=?  AND salaryStatus='已发放'";
        List<SalaryView>  data= this.getListInstence(con,sql,eid );
        return data;
    }
}
