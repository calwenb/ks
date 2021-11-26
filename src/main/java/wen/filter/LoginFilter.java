package wen.filter;

import wen.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤
 * session中没有登录的用户请求,跳转到 login.jsp
 * 前台页面只需登录
 * 后台页面禁止普通用户登录
 * 放行指定url
 * whl
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    //不拦截的 URL
    String[] noFilURLs = {"/index.jsp", "/login.jsp", "/register.jsp", "/LoginController", "/OutLoginController",
            "/GoKillController", "/info", "/sKillController"};
    String[] statics = {".css", ".js", ".png", ".ttf", ".woff", ".ico"};

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
            String s2 = reqURI.substring(s1.length(), str.length());
            for (String s : statics) {
                if (s.equals(s2)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        String URI = req.getRequestURI();
        User loginUser = (User) req.getSession().getAttribute("LOGIN_USER");

        if (loginUser == null) {
            System.out.println("未登录拦截:" + req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else if (URI.contains("/background") && loginUser.getUserType() != 0 && loginUser.getUserType() != 1) {
            //拦截普通用户进入后台
            System.out.println("普通用户进入后台拦截:" + req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            chain.doFilter(request, response);
        }

    }
}
