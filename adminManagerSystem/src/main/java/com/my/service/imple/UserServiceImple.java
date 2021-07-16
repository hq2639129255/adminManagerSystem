package com.my.service.imple;

import com.my.bean.*;
import com.my.dao.*;
import com.my.dao.imple.*;
import com.my.service.UserService;
import com.my.utils.JDBCutil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImple implements UserService {
    private EmployeeDao edao=new EmployeeDaoImple();
    private UserDao userdao=new UserDaoImple();
    private UserinfoDao userinfoDao=new UserinfoDaoImple();
    private AuthorityDao authorityDao=new AuthorityDaoImple();
    private UserstatusDao userstatusDao=new UserstatusDaoImple();
    @Override
    public byte login(String usernaem,String password,int role) {
        User user=null;
        try {
            Connection con = JDBCutil.getConnection();
    user =   userdao.findUserByUsernameAndPassword(con,usernaem,password,role);
    con.close();
    if(user==null){
        return 0;
    }
    if(user.getStatus_id()==1){
        return 1;

    }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public List<Userinfo> showAllUserinfo() throws SQLException {
        Connection con=null;
        List<Userinfo> data = null;
        try {
            con=JDBCutil.getConnection();
            data = userinfoDao.selectAllUserinfo(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            con.close();
        }

        return data ;
    }

    @Override
    public Page<Userinfo> showCrentUserinfo(int offset, int rowcount) {
        Connection con= null;
        Page<Userinfo> data=null;
        try {
            con = JDBCutil.getConnection();
            try {
                data= userinfoDao.findUserinfoByPagesize(con, offset, rowcount);
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
    public List<Authority> initAuthority() {
        Connection con=null;
        List<Authority> data = null;
        try {
            con=JDBCutil.getConnection();
            data = authorityDao.findAllAuthority(con);
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
    public List<Userstatus> initUserstatus() {

        Connection con=null;
        List<Userstatus> data = null;
        try {
            con=JDBCutil.getConnection();
            data = userstatusDao.findAllUserstatus(con);
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
    public boolean updateAuthorityByUserName(int aid, String userName) {
        Connection con=null;
      boolean  flag=false;
        try {
            con=JDBCutil.getConnection();
            flag= userdao.updateAuthorityByUserName(con,aid,userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  flag;
    }

    @Override
    public boolean resetPassword(String UserName) {
        Connection con=null;
        boolean flag = false;
        try {
            con=JDBCutil.getConnection();
            flag = userdao.resetPassword(con, UserName);
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
    public boolean lockUserCode(String UserName) {
        Connection con = null;
        boolean falg = false;
        try {
            con=JDBCutil.getConnection();
            falg = userdao.lockUserCode(con, UserName);
        } catch (SQLException e) {
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
    public boolean unLockUserCode(String UserName) {
        Connection con = null;
        boolean falg = false;
        try {
            con=JDBCutil.getConnection();
            falg = userdao.unLockUserCode(con, UserName);
        } catch (SQLException e) {
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
    public List<Userinfo> findUserinfoByParameter(String userName, int aid, String vname) {
    Connection con=null;
        List<Userinfo> data=null;
        try {
            con=JDBCutil.getConnection();
            data= userinfoDao.findUserinfoByParameter(con, userName, aid, vname);
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
    public boolean addUser(Employee e, User u) {
        boolean b = true;
        Connection con=null;


        try {
            con=JDBCutil.getConnection();
            con.setAutoCommit(false);
            b =  edao.addEmployee(con, e);
            userdao.managerAdduser(con,u);
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
}
