package com.my.servlet;

import com.google.gson.Gson;
import com.my.bean.*;
import com.my.dao.VipCardViewDao;
import com.my.dao.imple.VipCardViewDaoImple;
import com.my.exception.UserIsLock;
import com.my.exception.UserNameORpasswordException;
import com.my.service.FacilityService;
import com.my.service.UserService;
import com.my.service.VipCardViewService;
import com.my.service.imple.FacilityServiceImple;
import com.my.service.imple.UserServiceImple;
import com.my.service.imple.VipCardViewServiceImple;
import com.my.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    //登录
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
        Cookie cookiejs=new Cookie("JSESSIONID",session.getId());
        response.addCookie(cookiejs);
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
            System.out.println(gson.toJson(data));
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
//新增会员卡数据

    protected void  insertCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * addvipname:$("input[name=addvipname]").val(),
                    addsex:$("[name=addsex]:selected").val(),
                    addphone:$("[name=addphone]").val(),
                    addemil:$("[name=addemil]").val(),
                    addcardtype:$("[name=addcardtype]:selected").val()
        * */
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
       String  name=request.getParameter("name");
       String type =request.getParameter("type");
        System.out.println(name);
        System.out.println(type);
    }
}
