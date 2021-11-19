package wen.controller;

import wen.pojo.User;
import wen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        try {
            Map<Boolean, User> loginUser = UserService.login(loginName, password);
            if (loginUser.containsKey(true)) {
                User user = loginUser.get(true);
                request.getSession().setAttribute("LOGIN_USER", user);
                System.out.println(user.getLoginName() + " " + user.getUserName() + ",登录.权限:"+user.getUserType());
                //对应的用户角色进入前台或者后台
                if (user.getUserType() == 0 || user.getUserType() == 1) {
                    response.sendRedirect("background/main.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}