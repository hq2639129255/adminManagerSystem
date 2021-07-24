package com.my.service.imple;

import com.my.bean.*;
import com.my.dao.*;
import com.my.dao.imple.*;
import com.my.service.VipUserService;
import com.my.utils.JDBCutil;
import sun.misc.JavaUtilJarAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VipUserServiceImple implements VipUserService {
    private UserAppointmentInfoDao dao=new UserAppointmentInfoDaoImple();
    private Make_an_appointmentDao make_dao=new  Make_an_appointmentDaoImple();
   private AppointmentUserinfoDao appointmentUserinfoDao=  new AppointmentUserinfoDaoImple();
   private RenewDao renewDao=new RenewDaoImple();
   private CourseDao courseDao=new CourseDaoImple();
  private  EmployeeDao employeeDao=new EmployeeDaoImple();
  private CoachDao coachDao=new CoachDaoImple();

    @Override
    public List<UserAppointmentInfo> showAppointment() {
        Connection con=null;
        List<UserAppointmentInfo> data = null;
        try {
            con= JDBCutil.getConnection();
            data = dao.findAllUserAppointmentInfo(con);
            data=dao.findUserAppointmentInfoCount(con,data);
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

    /**
     * 判断当天用户是否预约
     * @param date
     * @param v_id
     * @return  true 已经预约  false
     */
    @Override
    public Make_an_appointment isAppointment(String date, int v_id) {
        Connection con=null;
        Make_an_appointment data =null;
        try {
            con=JDBCutil.getConnection();
           data = make_dao.isAppointment(con, date, v_id);
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
    public boolean executeAppointment(int time_id, int v_id) {
        Connection con=null;
        boolean flag= false;
        try {
            con=JDBCutil.getConnection();
            flag = make_dao.executeAppointment(con,time_id,v_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean clearAppointment(String crentDate, int v_id) {
        Connection con=null;
        boolean flag= false;
        try {
            con=JDBCutil.getConnection();
            flag = make_dao.clearAppointment(con,crentDate,v_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<AppointmentUserinfo> findAllAppointmentUserinfoByTimeID(int timeid) {
       Connection con=null;
        List<AppointmentUserinfo> data = null;
        try {
            con= JDBCutil.getConnection();
            data = appointmentUserinfoDao.findAllAppointmentUserinfoByTimeID(con, timeid);
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
    public List<Renew> findRenewByCid(int c_id) {
        Connection con=null;
        List<Renew> data = null;
        try {
            con=JDBCutil.getConnection();
            data = renewDao.findRenewByCid(con, c_id);
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
            }
        }
        return data;
    }

    @Override
    public List<Course> findAllCourse() {
        Connection con=null;
        List<Course> data = null;
        try {
            con=JDBCutil.getConnection();
            data = courseDao.findAllCourse(con);
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
    public List<Employee> findAllTeach() {
        Connection con=null;
        List<Employee> data = null;
        try {
            con=JDBCutil.getConnection();
            data = employeeDao.findAllTeach(con);
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
    public boolean insertCoachinfo(int e_id, int v_id, int c_id) {
        Connection con=null;
        boolean flag = false;
        try {
            con=JDBCutil.getConnection();
            flag = coachDao.insertCoachinfo(con, e_id, v_id, c_id);
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
}
