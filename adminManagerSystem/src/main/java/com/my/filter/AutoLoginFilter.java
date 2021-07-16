package com.my.filter;

import com.my.bean.User;
import com.my.bean.Vipinfo;
import com.my.service.UserService;
import com.my.service.VipinfoviewService;
import com.my.service.imple.UserServiceImple;
import com.my.service.imple.VipinfoviewServiceImple;
import sun.awt.geom.AreaOp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.annotation.Repeatable;


public class AutoLoginFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        boolean flag=false;


        String request_uri = request.getRequestURI();
        String ctxPath= request.getContextPath();
        String uri= request_uri.substring(ctxPath.length());








        if(request_uri.endsWith("/login.html")||request_uri.contains("/admin/login.do")){
            chain.doFilter(request, response);
            return;
        }
        else if (session!=null) {
            if(session.getAttribute("user") != null){
            chain.doFilter(request, response);
            return;}
        }
        else {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; cookies != null && i < cookies.length; i++) {
                if ("AutoLogin".equals(cookies[i].getName())) {
                    flag=true;
                    String[] userinfo = cookies[i].getValue().split("@");
                    UserService userService = new UserServiceImple();
                    byte data = userService.login(userinfo[0], userinfo[1], Integer.parseInt(userinfo[2]));
                    if (data == 2) {
                        session=request.getSession();
                        VipinfoviewService service=new VipinfoviewServiceImple();
                        Vipinfo v = service.getCurentVipinfo(userinfo[0]);
                        session.setAttribute("userinfo",v);
                        Cookie cookiejs=new Cookie("JSESSIONID",session.getId());
                        response.addCookie(cookiejs);
                        session.setAttribute("user",new User(userinfo[0],userinfo[1],Integer.parseInt(userinfo[2]),0));
                      Cookie cookieqxid=new Cookie("qxid",userinfo[2]);
                        cookieqxid.setMaxAge(60*60);
                      response.addCookie(cookieqxid);
                        chain.doFilter(request, response);
                    } else {
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                        response.sendRedirect("/admin/login.html");
                        return;

                    }
                   break;
                }
            }

            //        这里我增加了url判断，对静态文件直接放行//request_uri.toString().contains("css") ||request_uri.toString().contains("font")|| request_uri.toString().contains("js") || request_uri.toString().contains(".png")||request_uri.toString().contains(".jpg")|| request_uri.toString().contains(".do")
            if(request_uri.toString().contains("assets")||request_uri.toString().contains("css")|| request_uri.toString().contains("img")||request_uri.toString().contains("js")|| request_uri.toString().contains(".do")|| request_uri.toString().contains(".php")||request_uri.toString().contains("assets")||request_uri.toString().contains("Content")){
                //如果发现是css或者js文件，直接放行
                chain.doFilter(request, response);
            }
            if(!flag){
                response.sendRedirect("/admin/login.html");
                return;
            }
        }


        chain.doFilter(request,response);





    }

    public void init(FilterConfig config) throws ServletException {

    }

}
