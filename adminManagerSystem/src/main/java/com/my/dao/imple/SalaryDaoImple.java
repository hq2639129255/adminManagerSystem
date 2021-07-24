package com.my.dao.imple;

import com.my.bean.Salary;
import com.my.dao.BaseDao;
import com.my.dao.SalaryDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SalaryDaoImple extends BaseDao<Salary> implements SalaryDao{
    @Override
    public boolean insetSalary(Connection con, int eid, double salary) throws SQLException {
   String sql="INSERT INTO `salary`(`employee_id`,`sa_month`,`salary`,`payment`,`award`,`net_payroll`)VALUES(?,MONTH(NOW()),?,0,0,?)";
        int row = this.update(con, sql, eid, salary,salary);
        return row>0;
    }

    @Override
    public boolean updateSalary(Connection con, Salary salary) throws SQLException {
      String sql="UPDATE `salary` SET salary=?, payment=?,award=?,net_payroll=?,remark=?  WHERE employee_id=? AND CONCAT(YEAR(sa_month),'-',MONTH(sa_month))=CONCAT(YEAR(?),'-',MONTH(?))";
        int row = this.update(con, sql,salary.getSalary(), salary.getPayment(), salary.getAward(), salary.getNetPayroll(),salary.getRemark(), salary.getEmployeeId(), salary.getSaMonth(), salary.getSaMonth());
        return row>0;
    }

    @Override
    public boolean updateSalaryByMonth(Connection con,String month) throws SQLException {


        String sql="UPDATE `salary` SET  net_payroll=salary+award-payment,`salaryStatus`='已发放'  WHERE CONCAT(YEAR(sa_month),'-',MONTH(sa_month))=CONCAT(YEAR(?),'-',MONTH(?))";
        PreparedStatement per=con.prepareStatement(sql);
        per.setString(1,month);
        per.setString(2,month);
        int row=per.executeUpdate();
        return row>0;
    }

    @Override
    public boolean insetNextMonthSalary(Connection con, String month, int eid, BigDecimal salary) throws SQLException {
        SimpleDateFormat simpdate=new SimpleDateFormat("yyyy-MM-dd");
Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(simpdate.parse(month));
            calendar.add(Calendar.MONTH,1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sql="INSERT INTO  `salary`(`sa_month`,`employee_id`,`payment`,`award`,`net_payroll`,`remark`,`salary`,`salaryStatus`) VALUES(?,?,0,0,0,'',?,'未发放')";
        int row = this.update(con, sql,simpdate.format(  calendar.getTime()), eid, salary);
        return row>0;
    }

    @Override
    public List<Salary> selectSalaryByUpMonth(Connection con, String month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="  SELECT sa_id saId,employee_id employeeId,sa_month saMonth FROM `salary` WHERE   CONCAT(YEAR(sa_month),'-',MONTH(sa_month))=CONCAT(YEAR(?),'-',MONTH(?)) AND salaryStatus='已发放'";
        List<Salary> data = this.getListInstence(con, sql, month, month);
        return data;
    }

}
