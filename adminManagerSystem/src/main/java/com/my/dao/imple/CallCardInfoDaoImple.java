package com.my.dao.imple;

import com.my.bean.CallCardInfo;
import com.my.bean.Page;
import com.my.bean.Userinfo;
import com.my.dao.BaseDao;
import com.my.dao.CallCardInfoDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CallCardInfoDaoImple extends BaseDao<CallCardInfo> implements CallCardInfoDao
{
    @Override
    public List<CallCardInfo> findAllCallCardInfo(Connection con, int month) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql="SELECT  e_id AS eid,`workId` AS wid,e_name AS `name` ,phone  FROM  `employee` a\n" +
                "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3";
        List<CallCardInfo> data = this.getListInstence(con, sql);
        Iterator<CallCardInfo> it=data.iterator();
        while (it.hasNext()) {
            CallCardInfo call = it.next();
            List<CallCardInfo> actionCall = findAllCallCardInfoAction(con, month, call.getEid());
            if (actionCall != null){
                for (CallCardInfo c : actionCall) {
                    if (!"正常".equals(c.getStatus())) {
                        call.setStatus("异常");
                        break;
                    } else {
                        call.setStatus("正常");
                    }

                }

        }else{
                call.setStatus("异常");
            }
        }

        return data;
    }

    @Override
    public List<CallCardInfo> findAllCallCardInfoAction(Connection con, int month, int eid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
      String sql="    SELECT b.e_id as eid,w_id as wid,e_name as name,c_date as date,phone,callstatus as status  FROM`checkingin` a \n" +
              "  INNER JOIN `employee` b ON a.`employee_id`=b.`e_id`  WHERE b.e_id=? AND MONTH(c_date)=?";

        List<CallCardInfo> data = this.getListInstence(con,sql, eid, month);
        return data;
    }

    public Page<CallCardInfo> findCallCardInfoByPagesize(Connection con, int offset, int rowcount)  {
        Page<CallCardInfo> page = new Page<CallCardInfo>();
        List<CallCardInfo> data = new ArrayList<CallCardInfo>();
        String sql ="SELECT  e_id AS eid,`workId` AS wid,e_name AS `name` ,phone  FROM  `employee` a\n" +
                "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3 LIMIT ?,?";
        try {
            data = this.getListInstence(con, sql,offset, rowcount);
            Iterator<CallCardInfo> it=data.iterator();
            while (it.hasNext()) {
                CallCardInfo call = it.next();
                List<CallCardInfo> actionCall = findAllCallCardInfoAction(con, Calendar.getInstance().get(Calendar.MONTH)+1, call.getEid());
                if (actionCall != null){
                    for (CallCardInfo c : actionCall) {
                        if (!"正常".equals(c.getStatus())) {
                            call.setStatus("异常");
                            break;
                        } else {
                            call.setStatus("正常");
                        }

                    }

                }else{
                    call.setStatus("异常");
                }
                System.out.println(call.toString());
            }
            String sqlsun="SELECT  COUNT(1)  FROM  `employee` a\n" +
                    "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3";


            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            if(data!=null){
                page.setCurentrow(data.size());
            }else {
                page.setCurentrow(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        page.setPageData(data);
        System.out.println(page);
        return page;
    }
    public Page<CallCardInfo> findCallCardInfoByparameter(Connection con,String empON, String name,int month, int offset, int rowcount) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Page<CallCardInfo> page = new Page<CallCardInfo>();
        List<CallCardInfo> datalist = new ArrayList<CallCardInfo>();
        if (empON==null && name==null && month==0) {

            page= findCallCardInfoByPagesize( con,offset, rowcount);





        }else if(empON!=null&& month!=0){




            String sql =" SELECT  e_id AS eid,`workId` AS wid,e_name AS `name` ,phone  FROM  `employee` a\n" +
                    "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3  AND e_id=? LIMIT ?,?";
            datalist= this.getListInstence(con,sql,empON,offset, rowcount);

if(datalist!=null){
            Iterator<CallCardInfo> it=datalist.iterator();
            while (it.hasNext()) {
                CallCardInfo call = it.next();
                List<CallCardInfo> actionCall = findAllCallCardInfoAction(con, month, call.getEid());
                if (actionCall != null){
                    for (CallCardInfo c : actionCall) {
                        if (!"正常".equals(c.getStatus())) {
                            call.setStatus("异常");
                            break;
                        } else {
                            call.setStatus("正常");
                        }

                    }

                }else{
                    call.setStatus("异常");
                }
                System.out.println(call.toString());
            }

    String sqlsun="SELECT  COUNT(1)  FROM  `employee` a\n" +
            "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3 and e_id=?";


    Long rowsun = (Long) this.getValue(con, sqlsun,empON);
    page.setSunrow(Integer.parseInt(rowsun.toString()));

    page.setCurentPage((offset / rowcount) + 1);
    page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
    if(datalist!=null){
        page.setCurentrow(datalist.size());
    }else {
        page.setCurentrow(0);
    }
    page.setPageData(datalist);



}

        } else if (empON!=null) {
            String sql =" SELECT  e_id AS eid,`workId` AS wid,e_name AS `name` ,phone  FROM  `employee` a\n" +
                    "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3  AND e_id=? LIMIT ?,?";
            datalist= this.getListInstence(con,sql,empON,offset, rowcount);
            if(datalist!=null){

            Iterator<CallCardInfo> it=datalist.iterator();
            while (it.hasNext()) {
                CallCardInfo call = it.next();
                List<CallCardInfo> actionCall = findAllCallCardInfoAction(con, Calendar.getInstance().get(Calendar.MONTH)+1, call.getEid());
                if (actionCall != null){
                    for (CallCardInfo c : actionCall) {
                        if (!"正常".equals(c.getStatus())) {
                            call.setStatus("异常");
                            break;
                        } else {
                            call.setStatus("正常");
                        }

                    }


                }else{
                    call.setStatus("异常");
                }
                System.out.println(call.toString());
            }}
            String sqlsun="SELECT  COUNT(1)  FROM  `employee` a\n" +
                    "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3 AND e_id=?";


            Long rowsun = (Long) this.getValue(con, sqlsun,empON);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            if(datalist!=null){
                page.setCurentrow(datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);





        }  else if (empON==null && name!=null && month!=0) {
            String sql ="SELECT  e_id AS eid,`workId` AS wid,e_name AS `name` ,phone  FROM  `employee` a"+
           " INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3  AND  e_name=?  LIMIT ?,?";
            datalist =this.getListInstence(con,sql,name,offset, rowcount);
            if(datalist!=null) {
                Iterator<CallCardInfo> it = datalist.iterator();
                while (it.hasNext()) {
                    CallCardInfo call = it.next();
                    List<CallCardInfo> actionCall = findAllCallCardInfoAction(con, month, call.getEid());
                    if (actionCall != null) {
                        for (CallCardInfo c : actionCall) {
                            if (!"正常".equals(c.getStatus())) {
                                call.setStatus("异常");
                                break;
                            } else {
                                call.setStatus("正常");
                            }

                        }

                    } else {
                        call.setStatus("异常");
                    }
                    System.out.println(call.toString());
                }
            }
            String sqlsun="SELECT  COUNT(1)  FROM  `employee` a\n" +
                    "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3 AND e_name=?";


            Long rowsun = (Long) this.getValue(con, sqlsun,name);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            if(datalist!=null){
                page.setCurentrow(datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);


        }else if (empON==null && name!=null&&month==0) {
            String sql ="SELECT  e_id AS eid,`workId` AS wid,e_name AS `name` ,phone  FROM  `employee` a"+
            " INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3  AND  e_name=? LIMIT ?,?";
            datalist= this.getListInstence(con,sql,name,offset, rowcount);
            Iterator<CallCardInfo> it=datalist.iterator();
            if(datalist!=null) {
                while (it.hasNext()) {
                    CallCardInfo call = it.next();
                    List<CallCardInfo> actionCall = findAllCallCardInfoAction(con, Calendar.getInstance().get(Calendar.MONTH) + 1, call.getEid());
                    if (actionCall != null) {
                        for (CallCardInfo c : actionCall) {
                            if (!"正常".equals(c.getStatus())) {
                                call.setStatus("异常");
                                break;
                            } else {
                                call.setStatus("正常");
                            }

                        }

                    } else {
                        call.setStatus("异常");
                    }
                    System.out.println(call.toString());
                }
            }
            String sqlsun="SELECT  COUNT(1)  FROM  `employee` a\n" +
                    "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3";


            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            if(datalist!=null){
                page.setCurentrow(datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);








        }else {
            String sql ="SELECT  e_id AS eid,`workId` AS wid,e_name AS `name` ,phone  FROM  `employee` a\n" +
            "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3 LIMIT ?,?";
            datalist = this.getListInstence(con, sql,offset, rowcount);
            Iterator<CallCardInfo> it=datalist.iterator();
            if(datalist!=null) {
                while (it.hasNext()) {
                    CallCardInfo call = it.next();
                    List<CallCardInfo> actionCall = findAllCallCardInfoAction(con, month, call.getEid());
                    if (actionCall != null) {
                        for (CallCardInfo c : actionCall) {
                            if (!"正常".equals(c.getStatus())) {
                                call.setStatus("异常");
                                break;
                            } else {
                                call.setStatus("正常");
                            }

                        }

                    } else {
                        call.setStatus("异常");
                    }
                    System.out.println(call.toString());
                }
            }
            String sqlsun="SELECT  COUNT(1)  FROM  `employee` a\n" +
                    "INNER JOIN `user` b ON a.phone=b.`username`  WHERE a_id=3";


            Long rowsun = (Long) this.getValue(con, sqlsun);
            page.setSunrow(Integer.parseInt(rowsun.toString()));

            page.setCurentPage((offset / rowcount) + 1);
            page.setSunPage((int) Math.ceil((rowsun + 0.01) / rowcount));
            if(datalist!=null){
                page.setCurentrow(datalist.size());
            }else {
                page.setCurentrow(0);
            }
            page.setPageData(datalist);

        }
        return    page;
    }
}
