package wen.filter;

import wen.pojo.User;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤
 * 没有登录的用户请求,跳转到 login.jsp
 * 普通用户,禁止访问
 * 放行指定url
 * whl
 */
//@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    String[] noFilURLs = {"/index.jsp", "/login.jsp", "/register.jsp", "/LoginController","/OutLoginController"};
    String[] statics = {".css", ".js", ".png"};

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String reqURI = req.getRequestURI();
        for (String noFilURL : noFilURLs) {
            noFilURL = req.getContextPath() + noFilURL;
            if (noFilURL.equals(reqURI)) {
                chain.doFilter(request, response);
                return;
            }
        }
        if (reqURI.contains(".")) {
            String str = reqURI;
            String s1 = str.substring(0, str.indexOf("."));
            String s2 = reqURI.substring(s1.length() , str.length());
            for (String s : statics) {
                if (s.equals(s2)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        User loginUser = (User) req.getSession().getAttribute("LOGIN_USER");
        if (loginUser == null) {
            System.out.println("拦截:"+req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else if (loginUser.getUserType() != 0 && loginUser.getUserType() != 1) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            chain.doFilter(request, response);
        }

    }
}
