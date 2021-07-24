package com.my.service.imple;

import com.my.bean.*;
import com.my.dao.*;
import com.my.dao.imple.*;
import com.my.service.EmployeeService;
import com.my.utils.JDBCutil;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmployeeServiceImple implements EmployeeService
{   private  SalaryViewDao salaryViewDao=new SalaryViewDaoImple();
    private SalaryDao salaryDao=new  SalaryDaoImple();
    private  StudentAppointmentForTeachDao stuappdao=new StudentAppointmentForTeachDaoImple();
    private  CallCardInfoDao callCardInfoDao=new CallCardInfoDaoImple();
   private EmployeeDao dao=new EmployeeDaoImple();
    private JobtypeDao jobdao=new JobtypeDaoImple();
    private  UserDao  userDao=new UserDaoImple();
    private CoachDao cochdao=new CoachDaoImple();
    private InstructorDao instructorDao=new InstructorDaoImple();
    private  StudentinfoDao studentinfoDao=new StudentinfoDaoImple();
    private  WorktimeDao worktimeDao=new WorktimeDaoImple();
    private  EmployeeCallInfoDao employeeCallInfoDao=new EmployeeCallInfoDaoImple();
private CheckinginDao checkinginDao=new  CheckinginDaoImple();

    @Override
    public List<SalaryView> findAllSalaryView() {
        Connection con=null;
        List<SalaryView> data=null;
        try {
            con=JDBCutil.getConnection();
            data = salaryViewDao.findAllSalaryView(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    @Override
    public List<Jobtype> initJobtypeList() {
        Connection con=null;
        List<Jobtype> data = null;
        try {
            con=JDBCutil.getConnection();
            data = jobdao.findAllJobtype(con);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**
     *
     * @param offset
     * @param rowcount
     * @return
     */
    @Override
    public Page<Employee> showCurrentEmployee(int offset, int rowcount) {
        Connection con= null;
        Page<Employee> data=null;
        try {
            con = JDBCutil.getConnection();
            data= dao.findEmployeeByPagesize(con, offset, rowcount);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    /**
     * 新增员工业务
     * 1、新增员工信息
     * 2、为员工创建一个账号
     * @param e
     * @param user
     * @return
     */
    @Override
    public boolean addEmployee(Employee e,User user,double salary) {
        boolean b = true;
        Connection con=null;


        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            b = dao.addEmployee(con, e,salary);
            userDao.adduser(con,user);

            con.commit();
        } catch (SQLException e1) {
            try {
                b=false;
                con.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            e1.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


        return b;
    }

    @Override
    public List<Employee> showEmployeeParameter(QueryEmployee e) {
        Connection con=null;
        List<Employee> datalist = null;

        try {
            con=JDBCutil.getConnection();
            datalist=dao.findEmployeeByParameter(con,e);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


        return datalist;
    }

    @Override
    public boolean updateEmployee(Employee ee) {
        Connection con=null;
        boolean b = false;
        try {
            con=JDBCutil.getConnection();
            b = dao.updateEmployee(con,ee);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return b;


    }

    @Override
    public List<Employee> showSelectEmployeey(int id) {
        List<Employee> data = null;
        Connection con=null;
        try {
           con=JDBCutil.getConnection();
            data = dao.findEmployeebyId(con, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    @Override
    public boolean deleteEmployeeByid(int eid, int sid) {
        boolean flag=true;
        Connection con=null;
        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            String phone=dao.findPhoneByid(con,eid);
            cochdao.updateCoach(con,eid,sid);
            userDao.deleteByuserName(con,phone);
            dao.deleteEmployeebyId(con,eid);
            con.commit();
        } catch (SQLException e) {
            flag=false;
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {

            try {
                con.close();
            } catch (SQLException e) {

            }
        }

        return flag;
    }

    @Override
    public  Page<Instructor> showAllInstructorinfo(int offset, int rowcount)  {
        Page<Instructor> data=null;
        Connection con=null;

        try {
            con=JDBCutil.getConnection();
            data=instructorDao.findInstructorByPagesize(con,offset,rowcount);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return data;
    }

    @Override
    public List<Instructor> findInstructorByParameter(String e_id, String e_name, String sex) {
        Connection con=null;
        List<Instructor> datalist = null;
        try {
            con=JDBCutil.getConnection();
            datalist = instructorDao.findInstructorByParameter(con,e_id,e_name,sex);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return datalist;
    }

    @Override
    public List<Studentinfo> findStudentBye_id(int id) {
        Connection con=null;

        List<Studentinfo> data = null;
        try {
            con=JDBCutil.getConnection();
            data = studentinfoDao.findStudentBye_id(con, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public List<Studentinfo> selectStudentinfoByParameter(String phone, String name, String sex, int e_id) {
        Connection con=null;
        List<Studentinfo> datalist = null;


        try {
            con=JDBCutil.getConnection();
            datalist = studentinfoDao.findStudentinfoByParameter(con,phone,name,sex,e_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return datalist;
    }

    @Override
    public boolean replaceCoach(int newe_id, int olde_id, int v_id) {
        Connection con=null;
        boolean flag= false;
        try {
            con=JDBCutil.getConnection();
            flag = cochdao.updateCoachByE_idandV_id(con,newe_id,olde_id,v_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public List<Worktime> showAllWorktime() {
        Connection con=null;
        List<Worktime> data = null;
        try {
            con=JDBCutil.getConnection();
            data = worktimeDao.findAllWorktime(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean isEmployeeByPhone(String phone) {
        Connection con=null;
        Employee data = null;
        try {
            con= JDBCutil.getConnection();
            data = dao.findEmployeeByPhone(con, phone);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return data==null;
    }

    @Override
    public boolean isEmployeeByEmail(String emil) {
        Connection con=null;
        Employee data = null;
        try {
            con= JDBCutil.getConnection();
            data = dao.findEmployeeByEmail(con,emil);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return data==null;
    }

    @Override
    public EmployeeCallInfo showCurentEmployeeCallinfo(String phone) {
        Connection con=null;
        EmployeeCallInfo data=null;
        try {
            con=JDBCutil.getConnection();
          data = employeeCallInfoDao.findEmployeeCallInfoByPhone(con, phone);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    @Override
    public Checkingin showCurentEmployeeCallinfo(String curentDate, int eid) {
        Connection con=null;
        Checkingin data = null;
        try {
            con=JDBCutil.getConnection();
            data = checkinginDao.findCheckinginByEid(con, curentDate, eid);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean insertStartCallinfo(Checkingin c) {
        Connection con=null;
        boolean flag = false;
        try {
            con=JDBCutil.getConnection();
            flag = checkinginDao.insertStartCallinfo(con, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean updateEndCallinfo(Checkingin c) {
 Connection con=null;
        boolean flag = false;
        try {
            con=JDBCutil.getConnection();
            flag = checkinginDao.updateEndCallinfo(con, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    @Override
    public boolean insertEndCallinfo(Checkingin c) {
        Connection con=null;
        boolean flag = false;
        try {
            con=JDBCutil.getConnection();
            flag = checkinginDao.insertEndCallinfo(con, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public List<CallCardInfo> findAllCallCardInfo(int month) {
        Connection con=null;
        List<CallCardInfo> data = null;
        try {
            con=JDBCutil.getConnection();
            data = callCardInfoDao.findAllCallCardInfo(con, month);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public Page<CallCardInfo> findCallCardInfoByPagesize(int offset, int rowcount) {
Connection con=null;
        Page<CallCardInfo> data = null;
        try {
            con=JDBCutil.getConnection();
            data = callCardInfoDao.findCallCardInfoByPagesize(con, offset, rowcount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public List<Checkingin> findCheckinginByEidAndMonth(int eid, int month) {
        Connection con=null;
        List<Checkingin> data = null;
        try {
            con=JDBCutil.getConnection();
            data = checkinginDao.findCheckinginByEidAndMonth(con, eid, month);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public List<CallCardInfo> findCallCardInfoByparameter( String empON, String name, int month) {
        Connection con=null;
        List<CallCardInfo> data = null;
        try {
            con=JDBCutil.getConnection();
            data = callCardInfoDao.findCallCardInfoByparameter(con, empON, name, month);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return data;
    }

    @Override
    public List<Studentinfo> findStudentByPhone(String phone) {
        Connection con=null;
        List<Studentinfo> data = null;
        try {
            con=JDBCutil.getConnection();
            data = studentinfoDao.findStudentByPhone(con, phone);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return  data;
    }

    @Override
    public List<Studentinfo> findStudentinfoByParameter(String phone, String name, String sex, String e_phone) {
        Connection con=null;
        List<Studentinfo> data = null;
        try {
            con=JDBCutil.getConnection();
            data = studentinfoDao.findStudentinfoByParameter(con, phone, name, sex, e_phone);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public Employee findEmployeeByPhone(String phone) {
        Connection con=null;
        Employee data = null;
        try {
            con=JDBCutil.getConnection();
            data = dao.findEmployeeByPhone(con, phone);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public List<Checkingin> findCheckinginByE_id(int e_id, int month) {
        Connection con=null;
        List<Checkingin> data = null;
        try {
            con=JDBCutil.getConnection();
            data = checkinginDao.findCheckinginByE_id(con, e_id, month);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<StudentAppointmentForTeach> findStudentAppointmentForTeachByE_id(int eid) {
        Connection con=null;
        List<StudentAppointmentForTeach> data = null;
        try {
            con=JDBCutil.getConnection();
            data = stuappdao.findStudentAppointmentForTeachByE_id(con, eid);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  data;
    }

    @Override
    public boolean insetSalary(int eid,  double salary) {
     Connection con=null;
        boolean flag = false;
        try {
            con=JDBCutil.getConnection();
            flag = salaryDao.insetSalary(con, eid, salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    @Override
    public Page<SalaryView> findSalaryViewByPagesize(int offset, int rowcount) {
        Connection con=null;
        Page<SalaryView> data = null;
        try {
            con=JDBCutil.getConnection();
            try {
                data =salaryViewDao.findUserinfoByPagesize(con, offset, rowcount);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    @Override
    public List<SalaryView> findSalaryViewByParameter(String month, String name, int empno) {
      Connection  con=null;
        List<SalaryView> data = null;
        try {
            con=JDBCutil.getConnection();
            data = salaryViewDao.findSalaryViewByParameter(con, month, name, empno);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public boolean updateSalary(Salary salary) {
        Connection con=null;
        boolean flag = false;
        try {
            con=JDBCutil.getConnection();
            flag = salaryDao.updateSalary(con, salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean sendCurentMonthSalary(String month) {
        Connection con=null;
        boolean flag=true;
        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            //1、发放工资 做更新处理
            boolean b = salaryDao.updateSalaryByMonth(con, month);
            if(b){
    //2、查询当月员工信息
                List<SalaryView> data = salaryViewDao.findAllSalaryViewByMonth(con, month);
                //3、插入下个月员工工资信息
                for(SalaryView sa : data){

                    salaryDao.insetNextMonthSalary(con,month,sa.getE_id(),sa.getSalary());
                }
                con.commit();
            }else {
                try {
                    flag=false;
                    con.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            flag=false;
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean isSendSalary(){
     boolean falg=false;
        SimpleDateFormat simpDate=new SimpleDateFormat("yyyy-MM-dd");
        Connection con=null;
        try {
            con=JDBCutil.getConnection();
            Calendar month=Calendar.getInstance();
            month.setTime(new Date());
            month.add(Calendar.MONTH,-1);
            List<Salary> data = salaryDao.selectSalaryByUpMonth(con, simpDate.format(month.getTime()));
            if(data!=null&&data.size()!=0){
                falg=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return falg;
    }

    @Override
    public List<SalaryView> findSalaryBye_id(int eid) {
        Connection con=null;
        List<SalaryView> data = null;
        try {
            con=JDBCutil.getConnection();
            data = salaryViewDao.findSalaryBye_id(con, eid);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }


}
