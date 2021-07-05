package com.my.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/*但是当我使用filter拦截/*的url时，jsp文件文件是使用link外部导入css文件的方式去设置页面的格式。
但是当我运行程序之后并没有出现CSS写好的样式。出现Resource interpreted as

 Stylesheet but transferred with MIME type text/html*/

public class Encoding implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;

       //使用这句代码个样式文件就拿不到 response.setContentType("text/html;charset=UTF-8");
       request.setCharacterEncoding("utf-8");
        CharacterRequest characterRequest=new CharacterRequest(request);
        chain.doFilter(characterRequest, response);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
class CharacterRequest extends HttpServletRequestWrapper{
private HttpServletRequest request;
    public CharacterRequest(HttpServletRequest request) {
        super(request);
        this.request=request;
    }

    @Override
    public String getParameter(String name) {
        String value=super.getParameter(name);
        if(value==null){
            return null;
        }
        if("get".equalsIgnoreCase(request.getMethod())){
            try {
                return  new String(value.getBytes("iso-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return super.getParameter(name);
    }
}