package com.my.service.imple;

import com.my.bean.*;
import com.my.dao.EmployeeDao;
import com.my.dao.JobtypeDao;
import com.my.dao.UserDao;
import com.my.dao.imple.EmployeeDaoImple;
import com.my.dao.imple.JobtypeDaoImple;
import com.my.dao.imple.UserDaoImple;
import com.my.service.EmployeeService;
import com.my.utils.JDBCutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImple implements EmployeeService {
    EmployeeDao dao=new EmployeeDaoImple();
    JobtypeDao jobdao=new JobtypeDaoImple();
    UserDao  userDao=new UserDaoImple();

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

    @Override
    public boolean addEmployee(Employee e,User user) {
        boolean b = true;
        Connection con=null;


        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            b = dao.addEmployee(con, e);
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
}
