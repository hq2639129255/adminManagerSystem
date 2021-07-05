package com.my.servlet;

import com.google.gson.Gson;
import com.my.bean.Facility;
import com.my.bean.Page;
import com.my.bean.ReturnPath;
import com.my.bean.User;
import com.my.exception.UserIsLock;
import com.my.exception.UserNameORpasswordException;
import com.my.service.FacilityService;
import com.my.service.UserService;
import com.my.service.imple.FacilityServiceImple;
import com.my.service.imple.UserServiceImple;
import com.my.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


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
    protected void findPageData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rowconut=request.getParameter("rowconut");
        String curentpage=request.getParameter("curentpage");
        String datatype=request.getParameter("type");
        if(curentpage==null){
            curentpage="1";
        }
        if(rowconut==null){
            rowconut="10";
        }

        int pageoffset=(Integer.parseInt(curentpage)-1)*Integer.parseInt(rowconut);

        FacilityService service=new FacilityServiceImple();
        Page<Facility> data = service.showCurrentFacility(pageoffset, Integer.parseInt(rowconut));
        Gson gson=new Gson();
       response.getWriter().write(gson.toJson(data));
    }
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

}
