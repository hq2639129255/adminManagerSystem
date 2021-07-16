package com.my.servlet;

import com.google.gson.Gson;
import com.my.bean.*;
import com.my.exception.UserIsLock;
import com.my.exception.UserNameORpasswordException;
import com.my.service.*;
import com.my.service.imple.*;
import com.my.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ResponseUserServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String   path= request.getRequestURI();


    response.setContentType("text/html;charset=utf-8");
        String methodName=request.getRequestURI().substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
        System.out.println(methodName);
        try {
            Method method=this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
    /*===============================用户管理begin=========================================*/

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Gson gson=new Gson();
        PrintWriter out = response.getWriter();
        String usernaem=request.getParameter("username");
        String password=request.getParameter("password");
        String role=request.getParameter("role");

        UserService userService=new UserServiceImple();
     byte b=  userService.login(usernaem, MD5Util.md5(password),Integer.parseInt(role));
     if(b==0){
         try {
             throw  new UserNameORpasswordException("用户名或密码错误");
         } catch (UserNameORpasswordException e) {
             out.write(gson.toJson(new ReturnPath(false,e.getMessage(),"")));
         }
     }else if(b==1){
         try {
             throw  new UserIsLock("账号已冻结请联系管理员");
         } catch (UserIsLock userIsLock) {
             out.write(gson.toJson(new ReturnPath(false, userIsLock.getMessage(),"")));
         }
     }else {
         User user=new User(usernaem,password,Integer.parseInt(role),0);
        session.setAttribute("user",user);
         VipinfoviewService service=new VipinfoviewServiceImple();
         Vipinfo v = service.getCurentVipinfo(usernaem);
         session.setAttribute("userinfo",v);
        Cookie cookiejs=new Cookie("JSESSIONID",session.getId());
        response.addCookie(cookiejs);
         Cookie cookieqxid=new Cookie("qxid",String.valueOf(user.getAu_id()));
         cookieqxid.setMaxAge(60*60);
         response.addCookie(cookieqxid);
if("on".equals(request.getParameter("issave"))){

    Cookie cookie=new Cookie("AutoLogin",usernaem+"@"+MD5Util.md5(password)+"@"+role);
cookie.setMaxAge(60*60*24*3);
response.addCookie(cookie);
}
         if(role.equals("1")||role.equals("2")){
         ReturnPath returnPath=new ReturnPath(true,"","/admin/index.html");
        out.write(gson.toJson(returnPath));}
        else if(role.equals("3")){
             ReturnPath returnPath=new ReturnPath(true,"","/admin/employeeindex.html");
             out.write(gson.toJson(returnPath));
         }else if(role.equals("4")){
             ReturnPath returnPath=new ReturnPath(true,"","/admin/userindex.html");
             out.write(gson.toJson(returnPath));
         }
     }







    }
    //分页查询
    protected void findPageData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rowconut=request.getParameter("rowconut");
        String curentpage=request.getParameter("curentpage");
        String datatype=request.getParameter("pagetype");
        if(curentpage==null){
            curentpage="1";
        }
        if(rowconut==null){
            rowconut="10";
        }

        int pageoffset=(Integer.parseInt(curentpage)-1)*Integer.parseInt(rowconut);
        if("Facility".equals(datatype)){
        FacilityService service=new FacilityServiceImple();
        Page<Facility> data = service.showCurrentFacility(pageoffset, Integer.parseInt(rowconut));
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(data));
        }
        else if("VipCardView".equals(datatype)){
            VipCardViewService service=new VipCardViewServiceImple();
            Page<VipCardView> data = service.showCurrentVipCardView(pageoffset, Integer.parseInt(rowconut));
            Gson gson=new Gson();
            response.getWriter().write(gson.toJson(data));
        }else if("Employee".equals(datatype)){
            EmployeeService service=new EmployeeServiceImple();
            Page<Employee> data = service.showCurrentEmployee(pageoffset, Integer.parseInt(rowconut));
            Gson gson=new Gson();
            response.getWriter().write(gson.toJson(data));
        }else if("Vipinfoview".equals(datatype)){
            VipinfoviewService service=new VipinfoviewServiceImple();
            Page<Vipinfoview> data = service.showCurrentVipinfoview(pageoffset, Integer.parseInt(rowconut));
            Gson gson=new Gson();
            response.getWriter().write(gson.toJson(data));
        }else if("Instructor".equals(datatype)){
            EmployeeService service=new EmployeeServiceImple();
            Page<Instructor> data = service.showAllInstructorinfo(pageoffset, Integer.parseInt(rowconut));
            Gson gson=new Gson();
            response.getWriter().write(gson.toJson(data));
        }else if("Userinfo".equals(datatype)){
          UserService service=new UserServiceImple();
            Page<Userinfo> data = service.showCrentUserinfo(pageoffset, Integer.parseInt(rowconut));
            Gson gson=new Gson();
            response.getWriter().write(gson.toJson(data));
        }

    }
    /*==================================器材管理beggin==================================*/
    //新增器材
    protected void addFacility(HttpServletRequest request, HttpServletResponse response) throws IOException {
String Facilityname=request.getParameter("Facilityname");
String buyFacilitytime=request.getParameter("buyFacilitytime");
String Facilitrmark=request.getParameter("Facilitrmark");
if(Facilitrmark==null){
    Facilitrmark="";
}
        Facility f=new Facility();
        f.setF_name(Facilityname);
        f.setBuyTime(buyFacilitytime);
        f.setRemark(Facilitrmark);
        System.out.println(f.toString());
        FacilityService service=new FacilityServiceImple();
        boolean b = service.addFacility(f);
Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("新增成功");
        }else {
            returnPath.setInfo("新增失败");
        }


response.getWriter().write(gson.toJson( returnPath));

    }
    //修改器材信息
    protected void updateFacility(HttpServletRequest request, HttpServletResponse response) throws IOException {

      String id=request.getParameter("id");
      String name = request.getParameter("name");
      String buytime=request.getParameter("buytime");
      String rmark=request.getParameter("rmark");
        Facility f=new  Facility();
        f.setId(Integer.parseInt(id));
        f.setF_name(name);
        f.setBuyTime(buytime);
        f.setRemark(rmark);
        FacilityService service=new FacilityServiceImple();
     boolean b= service.updateFacility(f);
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("修改成功");
        }else {
            returnPath.setInfo("修改失败");
        }
        response.getWriter().write(gson.toJson(returnPath));
    }
    //删除器材信息
    protected void   deleteFacilityByid(HttpServletRequest request, HttpServletResponse response) throws IOException {
String id=request.getParameter("id");
        FacilityService service=new FacilityServiceImple();
        boolean b = service.deleteFacilityByid(Integer.parseInt(id));
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
returnPath.setFlag(b);
if(b){returnPath.setInfo("删除成功");}
else{
    returnPath.setInfo("删除失败");
}
        response.getWriter().write(gson.toJson(returnPath));
    }

    protected void showSelectFacility(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String id=request.getParameter("qid");
       if("".equals(id)||id==null){
           id=Integer.toString(-1);
       }
       String name=request.getParameter("qname");
       String date=request.getParameter("qdate");
       if("".equals(name)||name==null){
           name=null;
       }
       if("".equals(date)||date==null){
           date=null;
       };
        QueryObj queryObj=new QueryObj();
        queryObj.setFacilityid(Integer.parseInt(id));
        queryObj.setFacilityname(name);
        queryObj.setFacilitydate(date);
        FacilityService service=new FacilityServiceImple();
        List<Facility> data = service.showFacilityByParameter(queryObj);
        Gson gson=new Gson();
        ReturnPath<Facility> returnPath=new ReturnPath<Facility>();
        if(data.size()==0){
            returnPath.setFlag(false);
            returnPath.setInfo("没有查询到符合条件的数据");
        }else {
            returnPath.setFlag(true);
            returnPath.setDataList(data);

        }
        response.getWriter().write(gson.toJson(returnPath));
    }

 /*==========================会员卡管理begin================================*/
 protected void showcardinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     VipCardViewService service=new VipCardViewServiceImple();
     List<VipCardView> data = service.showCardList();
     Gson gson=new Gson();
     ReturnPath<VipCardView> returnPath=new ReturnPath<VipCardView>();

     if(data.size()>0){
         returnPath.setFlag(true);
         returnPath.setDataList(data);
     }else{
         returnPath.setFlag(false);
         returnPath.setInfo("此页面暂无数据");
     }
     response.getWriter().write(gson.toJson(returnPath));
 }

//初始化会员卡类型
protected void initCardType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    VipCardViewService service=new VipCardViewServiceImple();
    List<CardType> data = service.initCardList();
    Gson gson=new Gson();
    response.getWriter().write(gson.toJson(data));

}
//初始化套餐类型

    protected void initSetmeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VipCardViewService service=new VipCardViewServiceImple();
        List<Setmeal> data = service.initSetmeal();
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(data));

    }



//新增会员卡数据

    protected void  insertCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("addvipname");
        String phone=request.getParameter("addphone");
        String emil=request.getParameter("addemil");
        String sex=request.getParameter("addsex");
        String cardtype=request.getParameter("addcardtype");

        Vipinfo vipinfo=new Vipinfo();
        vipinfo.setE_name(name);
        vipinfo.setPhone(phone);
        vipinfo.setEmail(emil);
        vipinfo.setSex(sex);
        VipCardViewService service=new VipCardViewServiceImple();
        boolean b = service.addcard(Integer.parseInt(cardtype), vipinfo);
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("新增成功");
        }
        Gson gson=new Gson();
      response.getWriter().write(gson.toJson(returnPath));
    }
    //修改会员卡信息
    protected void upadatevipcard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String id=request.getParameter("id");
     String  name=request.getParameter("name");
     String type =request.getParameter("type");

       VipCardView vipCardView=new VipCardView();
     vipCardView.setCid(Integer.parseInt(id));
       vipCardView.setE_name(name);
       vipCardView.setT_name(type);
        VipCardViewService service=new VipCardViewServiceImple();
        boolean b = service.updatecardinfo(vipCardView);
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        Gson gson=new Gson();
        if(b){
            returnPath.setInfo("修改成功");
        }else {
            returnPath.setInfo("修改失败");
        }
        response.getWriter().write(gson.toJson(returnPath));
    }
    //查询会员卡信息
    protected void showSelectVipcard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("qid");
        if("".equals(id)||id==null){
            id=Integer.toString(-1);
        }
        String name=request.getParameter("qname");
        String type =request.getParameter("qtype");
        if("".equals(name)||name==null){
            name=null;
        }
        if("".equals(type)|| type ==null){
            type =null;
        };
        VipCardView vipCardView=new VipCardView();
        vipCardView.setCid(Integer.parseInt(id));
        vipCardView.setE_name(name);
        vipCardView.setT_name(type);
        VipCardViewService service=new VipCardViewServiceImple ();
        List<VipCardView> data = service.showFacilityByParameter(vipCardView);
        Gson gson=new Gson();
        ReturnPath<VipCardView> returnPath=new ReturnPath<VipCardView>();
        if(data.size()==0){
            returnPath.setFlag(false);
            returnPath.setInfo("没有查询到符合条件的数据");
        }else {
            returnPath.setFlag(true);
            returnPath.setDataList(data);

        }
        response.getWriter().write(gson.toJson(returnPath));
    }
    protected void buyservice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
         SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        VipCardViewService service=new VipCardViewServiceImple();
        String id =request.getParameter("vipcardid");
        String buyserviceday=request.getParameter("service");
        Boolean b=null;
        String enddatetime=request.getParameter("servicedate");
        Date date = simple.parse(enddatetime);
        //判断服务是否过期
      //true 未过期  false:过期

           boolean flag=date.getTime()>System.currentTimeMillis();



           b = service.buyservice(flag, Integer.parseInt(buyserviceday),Integer.parseInt(id));


        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("续费成功");
        }else {
            returnPath.setInfo("操作失败");
        }
        response.getWriter().write(gson.toJson(returnPath));

    }

    //初始化职位类型

    protected void initJobtype(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service=new EmployeeServiceImple();
        List<Jobtype> data = service.initJobtypeList();
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(data));

    }
//新增工作人员
    protected void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String  addname=request.getParameter("addname");
        String  addsex=request.getParameter("addsex");
        String  addjob=request.getParameter("addjob");
        String  addAddrees=request.getParameter("addAddrees");
        String  addphone=request.getParameter("addphone");
        String  addemil=request.getParameter("addemil");
        String  workid=request.getParameter("addworkid");
        Employee e=new  Employee();
        User user=new User();
        user.setUsername(addphone);
        user.setAu_id(3);
        user.setStatus_id(0);

        e.setE_name(addname);
        e.setJ_id(Integer.parseInt(addjob));
        e.setEmail(addemil);
        e.setPhone(addphone);
        e.setSex(addsex);
        e.setAddress(addAddrees);
        e.setWorkId(Integer.parseInt(workid));
        EmployeeService service=new EmployeeServiceImple();
        boolean b = service.addEmployee(e,user);
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("新增成功");
        }else {
            returnPath.setInfo("新增失败");
        }
        response.getWriter().write(gson.toJson( returnPath));

    }


        protected void showEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String id=request.getParameter("qid");
            if("".equals(id)||id==null){
                id=Integer.toString(-1);
            }
            String name=request.getParameter("qname");
            String type =request.getParameter("qtype");
            QueryEmployee qe=new QueryEmployee();
            if("".equals(name)||name==null){
                name=null;
            }


            qe.setEid(Integer.parseInt(id));
            qe.setEname(name);
            qe.setJobid(Integer.parseInt(type));


            EmployeeService service=new EmployeeServiceImple ();
            List<Employee> data = service.showEmployeeParameter(qe);
            Gson gson=new Gson();
            ReturnPath<Employee> returnPath=new ReturnPath<Employee>();
            if(data==null){
                returnPath.setFlag(false);
                returnPath.setInfo("没有查询到符合条件的数据");
            }else {
                returnPath.setFlag(true);
                returnPath.setDataList(data);

            }
            response.getWriter().write(gson.toJson(returnPath));
        }
    protected void  insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("addvipname");
        String phone=request.getParameter("addphone");
        String emil=request.getParameter("addemil");
        String sex=request.getParameter("addsex");
        String cardtype=request.getParameter("addcardtype");

        Vipinfo vipinfo=new Vipinfo();
        vipinfo.setE_name(name);
        vipinfo.setPhone(phone);
        vipinfo.setEmail(emil);
        vipinfo.setSex(sex);
        VipCardViewService service=new VipCardViewServiceImple();
        boolean b = service.addcard(Integer.parseInt(cardtype), vipinfo);
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("新增成功");
        }
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(returnPath));
    }

    //修改员工信息
    protected void upadateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid=request.getParameter("eid");
        String  ename=request.getParameter("ename");
        String sex=request.getParameter("sex");
        String jobid=request.getParameter("jobid");
        String  addres=request.getParameter("addres");
        String  emil=request.getParameter("emil");
        String workid=request.getParameter("eworkid");


        Employee emp=new Employee();
        emp.setE_id(Integer.parseInt(eid));
        emp.setE_name(ename);
        emp.setSex(sex);
        emp.setAddress(addres);
        emp.setEmail(emil);
        emp.setJ_id(Integer.parseInt(jobid));
        emp.setWorkId(Integer.parseInt(workid));

        EmployeeService service=new EmployeeServiceImple();
        boolean b = service.updateEmployee(emp);
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        Gson gson=new Gson();
        if(b){
            returnPath.setInfo("修改成功");
        }else {
            returnPath.setInfo("修改失败");
        }
        response.getWriter().write(gson.toJson(returnPath));
    }
    //选择教练信息

    protected void initteach(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String id=request.getParameter("id");
        EmployeeService service=new EmployeeServiceImple();
        List<Employee> data = service.showSelectEmployeey(Integer.parseInt(id));
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(data));

    }

    protected void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid=request.getParameter("eid");//查
        String selectid=request.getParameter("selectid");
        EmployeeService empservice=new EmployeeServiceImple();
        boolean b = empservice.deleteEmployeeByid(Integer.parseInt(eid), Integer.parseInt(selectid));

//        System.out.println("被删除id"+eid);
//        System.out.println("选择id"+selectid);
//        EmployeeService service=new EmployeeServiceImple();
//        List<Employee> data = service.showSelectEmployeey(Integer.parseInt(id));
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("执行成功");
        }else {
            returnPath.setInfo("操作失败");
        }
        response.getWriter().write(gson.toJson(returnPath));

    }

    protected void showSelectVipinfoview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone=request.getParameter("phone");
        if("".equals(phone)||phone==null){
            phone=null;
        }
        String vipname=request.getParameter("vipname");
        String cardtype=request.getParameter("cardtype");
        if("".equals(vipname)||vipname==null){
            vipname=null;
        }
        if("".equals(cardtype)||cardtype==null){
            cardtype=null;
        };
        System.out.println( phone);
        System.out.println( vipname);
        System.out.println(cardtype);
        VipinfoviewService service=new VipinfoviewServiceImple();
        List<Vipinfoview> data = service.findVipinfoviewByParameter(phone,cardtype,vipname);
        Gson gson=new Gson();
        ReturnPath<Vipinfoview> returnPath=new ReturnPath<Vipinfoview>();
        if(data.size()==0){
            returnPath.setFlag(false);
            returnPath.setInfo("没有查询到符合条件的数据");
        }else {
            returnPath.setFlag(true);
            returnPath.setDataList(data);

        }
        response.getWriter().write(gson.toJson(returnPath));
    }
    protected void  insertVipinfoview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("addname");
        String phone=request.getParameter("addphone");
        String emil=request.getParameter("addemil");
        String sex=request.getParameter("addsex");
        String cardtype=request.getParameter("addcardtype");
        String Addrees=request.getParameter("addAddrees");
        System.out.println("name:"+name);
        System.out.println("phone:"+phone);
        System.out.println("emil:"+emil);
        System.out.println("sex:"+sex);
        System.out.println("cardtype:"+cardtype);
        System.out.println("Addrees:"+Addrees);



        Vipinfo vipinfo=new Vipinfo();
        vipinfo.setE_name(name);
        vipinfo.setPhone(phone);
        vipinfo.setEmail(emil);
        vipinfo.setSex(sex);
        vipinfo.setAddress(Addrees);
        VipinfoviewService service=new VipinfoviewServiceImple();
        boolean b = service.addviminfo(vipinfo,Integer.parseInt(cardtype));
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("新增成功");
        }
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(returnPath));
    }
    protected void upadatevipinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vid=request.getParameter("vid");
        String  name=request.getParameter("name");
        String sex=request.getParameter("updatesex");
        String  addres=request.getParameter("uapdateAddrees");
        String  emil=request.getParameter("uapdateemil");
        Vipinfo  vip=new Vipinfo();
        vip.setV_id(Integer.parseInt(vid));
        vip.setAddress(addres);
        vip.setSex(sex);
        vip.setEmail(emil);
        vip.setE_name(name);




//        System.out.println("id:"+vid);
//        System.out.println("name:"+name);
//        System.out.println("sex:"+sex);
//        System.out.println("addres:"+addres);
//        System.out.println("emil:"+emil);




        VipinfoviewService service=new VipinfoviewServiceImple();
        boolean b = service.updatevipinfobyid(vip);
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        Gson gson=new Gson();
        if(b){
            returnPath.setInfo("修改成功");
        }else {
            returnPath.setInfo("修改失败");
        }
        response.getWriter().write(gson.toJson(returnPath));
    }
//@Test
//    public void testemp(){
//    EmployeeServiceImple serviceImple=new EmployeeServiceImple();
//    List<Instructor> data = serviceImple.showAllInstructorinfo();
//    Gson gson=new Gson();
//    System.out.println( gson.toJson(data));
//}
protected void showSelectInstructor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String e_name =request.getParameter("e_name");
    if("".equals(e_name)|| e_name ==null){
        e_name =null;
    }
    String e_id=request.getParameter("e_id");
    String qsex=request.getParameter("qsex");
    if("".equals(e_id)||e_id==null){
        e_id=null;
    }
    if("".equals(qsex)||qsex==null){
        qsex=null;
    };
    System.out.println("name:"+e_name);
    System.out.println("id:"+ e_id);
    System.out.println("qsex:"+qsex);
    EmployeeService service=new EmployeeServiceImple();
    List<Instructor> data = service.findInstructorByParameter(e_id,e_name,qsex);
    Gson gson=new Gson();
    ReturnPath<Instructor> returnPath=new ReturnPath<Instructor>();
    if(data!=null){
    if(data.size()!=0) {
        returnPath.setFlag(true);
        returnPath.setDataList(data);

    }
    }else {
        returnPath.setFlag(false);
        returnPath.setInfo("没有查询到符合条件的数据");
    }
    response.getWriter().write(gson.toJson(returnPath));
}
    protected void showSelectStudentinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =request.getParameter("id");
        EmployeeService service=new EmployeeServiceImple();
        List<Studentinfo> data = service.findStudentBye_id(Integer.parseInt(id));
        Gson gson=new Gson();
        ReturnPath<Studentinfo> returnPath=new ReturnPath<Studentinfo>();
        if(data==null){
            returnPath.setFlag(false);
            returnPath.setInfo("该教练还没有分配学员");
        }else {
            returnPath.setFlag(true);
            returnPath.setDataList(data);

        }
        response.getWriter().write(gson.toJson(returnPath));
    }
    protected void querySelectStudentinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        phone:$("input[neme=phone]").val(),
//                vipname:$("input[name=vipname]").val(),
//                vipsex:$("#vipsex>option:selected").val(),
//                e_id:getCookie("id")
        String e_id=request.getParameter("e_id");
        String phone=request.getParameter("phone");
        if("".equals(phone)||phone==null){
            phone=null;
        }
        String vipname=request.getParameter("vipname");
        String vipsex=request.getParameter("vipsex");
        if("".equals(vipname)||vipname==null){
            vipname=null;
        }
        if("".equals(vipsex)||vipsex==null){
            vipsex=null;
        };

        System.out.println( phone);
        System.out.println( vipname);
        System.out.println(vipsex);
        System.out.println(e_id);
        EmployeeService service=new EmployeeServiceImple();
        List<Studentinfo> data = service.selectStudentinfoByParameter(phone,vipname,vipsex,Integer.parseInt(e_id));
        Gson gson=new Gson();
        ReturnPath<Studentinfo> returnPath=new ReturnPath<Studentinfo>();
        if(data==null){
            returnPath.setFlag(false);
            returnPath.setInfo("没有查询到符合条件的数据");
        }else {
            returnPath.setFlag(true);
            returnPath.setDataList(data);

        }
        response.getWriter().write(gson.toJson(returnPath));
    }

    protected void replaceCoach(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newe_id=request.getParameter("newe_id");
        String olde_id=request.getParameter("olde_id");
        String v_id =request.getParameter("v_id");
        System.out.println(newe_id);
        System.out.println(olde_id);
        System.out.println(v_id);
        EmployeeService service =new EmployeeServiceImple();
ReturnPath returnPath=new ReturnPath();
        boolean   flag=service.replaceCoach(Integer.parseInt(newe_id),Integer.parseInt(olde_id),Integer.parseInt(v_id));

        returnPath.setFlag(flag);
        if(flag){
            returnPath.setInfo("更换教练操作成功");
        }else {
            returnPath.setInfo("操作失败");
        }

        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(returnPath));
    }


    protected void initUserAppointmentInfoDao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VipUserService service=new VipUserServiceImple();
        List<UserAppointmentInfo> data = service.showAppointment();
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(data));
    }
    protected void  isAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String date=request.getParameter("date");
        HttpSession session = request.getSession();
        Vipinfo vipinfo=(Vipinfo) session.getAttribute("userinfo");

        VipUserService service=new VipUserServiceImple();
        Make_an_appointment data = service.isAppointment(date, vipinfo.getV_id());
        Gson gson=new Gson();
        ReturnPath<Make_an_appointment> returnPath=new ReturnPath<Make_an_appointment>();
        returnPath.setFlag(data!=null);
        if(data!=null){
            returnPath.setInfo("你已经预约过了不能再预约");
           returnPath.setData(data);
        }
        response.getWriter().write(gson.toJson(returnPath));
    }


    protected void executeAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VipUserService service=new VipUserServiceImple();
        String timeid=request.getParameter("timeid");
        HttpSession session = request.getSession();
        Vipinfo vipinfo=(Vipinfo) session.getAttribute("userinfo");
    boolean flag= service.executeAppointment(Integer.parseInt(timeid),vipinfo.getV_id());
      ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(flag);
        Gson gson=new Gson();
        if(flag){
            returnPath.setInfo("预约成功");
        }else {
            returnPath.setInfo("预约失败");
        }
        response.getWriter().write(gson.toJson(returnPath));
    }

    /**
     * 取消预约
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clearAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VipUserService service=new VipUserServiceImple();
        String crentdate=request.getParameter("crentdate");
        HttpSession session = request.getSession();
        Vipinfo vipinfo=(Vipinfo) session.getAttribute("userinfo");
        boolean flag= service.clearAppointment(crentdate,vipinfo.getV_id());
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(flag);
        Gson gson=new Gson();
        if(flag){
            returnPath.setInfo("取消预约成功");
        }else {
            returnPath.setInfo("取消预约失败");
        }
        response.getWriter().write(gson.toJson(returnPath));
    }
    protected void findCurentDateAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VipUserService service=new VipUserServiceImple();
        String Timeid=request.getParameter("Timeid");
        List<AppointmentUserinfo> data = service.findAllAppointmentUserinfoByTimeID(Integer.parseInt(Timeid));
        ReturnPath<AppointmentUserinfo> returnPath=new ReturnPath();
       if(data==null){
           returnPath.setFlag(false);
           returnPath.setInfo("此时段还没有会员预约");
       }else {
           returnPath.setFlag(true);
           returnPath.setDataList(data);

       }
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(returnPath));
    }
    protected void mangerClearAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VipUserService service=new VipUserServiceImple();
        String crentdate=request.getParameter("crentdate");
       String v_id=request.getParameter("vid");
        boolean flag= service.clearAppointment(crentdate,Integer.parseInt(v_id));
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(flag);
        Gson gson=new Gson();
        if(flag){
            returnPath.setInfo("取消预约成功");
        }else {
            returnPath.setInfo("取消预约失败");
        }
        response.getWriter().write(gson.toJson(returnPath));
    }


    protected void ManagerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService service=new UserServiceImple();
        List<Userinfo> data = service.showAllUserinfo();
        Gson gson=new Gson();
        ReturnPath<Userinfo> returnPath=new ReturnPath<>();
        if(data!=null){
            returnPath.setFlag(true);
            returnPath.setDataList(data);
        }else {
            returnPath.setFlag(false);
            returnPath.setInfo("还没有账号信息");
        }
response.getWriter().write(gson.toJson(returnPath));
    }

    protected void initAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService service=new UserServiceImple();
        List<Authority> data = service.initAuthority();
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(data));
    }

    protected void initUserstatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService service=new UserServiceImple();
        List<Userstatus> data = service.initUserstatus();
        Gson gson=new Gson();
        HttpSession session = request.getSession();
        User u= (User) session.getAttribute("user");
        System.out.println(u.toString());

        response.getWriter().write(gson.toJson(data));
    }
    protected void updateAuthorityByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService service=new UserServiceImple();
        String aid=request.getParameter("aid");
        String userName=request.getParameter("userName");
  boolean flag=  service.updateAuthorityByUserName(Integer.parseInt(aid),userName);
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(flag);
        if(flag){
            returnPath.setInfo("执行成功");
        }else{
            returnPath.setInfo("操作失败");        }
        response.getWriter().write(gson.toJson(returnPath));
    }

    protected void  resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService service=new UserServiceImple();
        String userName=request.getParameter("userName");

        boolean flag=  service.resetPassword(userName);
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(flag);
        if(flag){
            returnPath.setInfo("重置密码成功");
        }else{
            returnPath.setInfo("重置密码失败");        }
        response.getWriter().write(gson.toJson(returnPath));
    }

    protected void  lockUserCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService service=new UserServiceImple();
        String userName=request.getParameter("userName");

        boolean flag=  service.lockUserCode(userName);
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(flag);
        if(flag){
            returnPath.setInfo("成功冻结账号");
        }else{
            returnPath.setInfo("操作失败");        }
        response.getWriter().write(gson.toJson(returnPath));
    }

    protected void  unLockUserCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserService service=new UserServiceImple();
        String userName=request.getParameter("userName");

        boolean flag=  service.unLockUserCode(userName);
        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(flag);
        if(flag){
            returnPath.setInfo("解冻成功");
        }else{
            returnPath.setInfo("操作失败");        }
        response.getWriter().write(gson.toJson(returnPath));
    }


    protected void findUserinfoByParameter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String vname=request.getParameter("vname");
        String aid=request.getParameter("aid");
        if("".equals(userName)||userName==null){
            userName=null;
        }

        if("".equals(vname)||vname==null){
            vname=null;
        }
        if("".equals(aid)||aid==null){
            aid="0";
        };

        System.out.println("userName"+userName);

        System.out.println("vname"+vname);
        System.out.println("aid"+aid);

        UserService service=new UserServiceImple();
        List<Userinfo> data = service.findUserinfoByParameter(userName, Integer.parseInt(aid), vname);
        Gson gson=new Gson();
        ReturnPath<Userinfo> returnPath=new ReturnPath<Userinfo>();
        if(data==null){
            returnPath.setFlag(false);
            returnPath.setInfo("没有查询到符合条件的数据");
        }else {
            returnPath.setFlag(true);
            returnPath.setDataList(data);

        }
        response.getWriter().write(gson.toJson(returnPath));
    }




    protected void  addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String  addname=request.getParameter("addname");
        String  addsex=request.getParameter("addsex");
        String  addjob=request.getParameter("addjob");
        String  addAddrees=request.getParameter("addAddrees");
        String  addphone=request.getParameter("addphone");
        String  addemil=request.getParameter("addemil");
        String addqx=request.getParameter("addqx");
        Employee e=new  Employee();
        User user=new User();
        user.setUsername(addphone);
        user.setAu_id(Integer.parseInt(addqx));
        user.setStatus_id(0);

        e.setE_name(addname);
        e.setJ_id(Integer.parseInt(addjob));
        e.setEmail(addemil);
        e.setPhone(addphone);
        e.setSex(addsex);
        e.setAddress(addAddrees);
       UserService service=new UserServiceImple();
        boolean b = service.addUser(e,user);

        Gson gson=new Gson();
        ReturnPath returnPath=new ReturnPath();
        returnPath.setFlag(b);
        if(b){
            returnPath.setInfo("新增成功");
        }else {
            returnPath.setInfo("新增失败");
        }
        response.getWriter().write(gson.toJson( returnPath));
    }

    /**
     * 初始化页面班次下拉列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    protected void initWorktime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
       EmployeeService service=new EmployeeServiceImple();
        List<Worktime> data = service.showAllWorktime();
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(data));
    }

    /**
     * 验证手机号是否重复
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    protected void isEmployeeByPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        EmployeeService service=new EmployeeServiceImple();
        String phone=request.getParameter("phone");
      boolean  flag = service.isEmployeeByPhone(phone);

        response.getWriter().write(new Boolean(flag).toString());
    }



    protected void isEmployeeByEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        EmployeeService service=new EmployeeServiceImple();
        String email=request.getParameter("phone");
        boolean  flag = service.isEmployeeByEmail(email);
        response.getWriter().write(new Boolean(flag).toString());
    }
    protected void isVipinfoByphone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        VipinfoviewService service=new VipinfoviewServiceImple();
        String email=request.getParameter("phone");
        boolean  flag = service.isVipinfoByphone(email);
        response.getWriter().write(new Boolean(flag).toString());
    }

    protected void isVipinfoByEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        VipinfoviewService service=new VipinfoviewServiceImple();
        String email=request.getParameter("Email");
        boolean  flag = service.isVipinfoByEmail(email);
        response.getWriter().write(new Boolean(flag).toString());
    }

}
