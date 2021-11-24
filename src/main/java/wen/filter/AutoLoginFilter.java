package wen.filter;

import wen.pojo.User;
import wen.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 自动登录过滤器
 * 当发现session中没有user,再从cookie中查找"AUTO_LOGIN",根据账号密码自动的登录
 */
@WebFilter(filterName = "AutoLoginFilter", urlPatterns = {"/*"})
public class AutoLoginFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute("LOGIN_USER") == null) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if ("AUTO_LOGIN".equals(cookie.getName())) {
                    String loginName = cookie.getValue().split("-")[0];
                    String password = cookie.getValue().split("-")[1];
                    req.setAttribute("loginName", loginName);
                    req.setAttribute("password", password);
                    Map<Boolean, User> msg = null;
                    try {
                        msg = UserService.login(loginName, password);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (msg.containsKey(true)) {
                        User user = msg.get(true);
                        req.getSession().setAttribute("LOGIN_USER", msg.get(true));
                        System.out.println("自动登录:" + user.getLoginName() + " " + user.getUserName() + ",登录.权限:" + user.getUserType());
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
