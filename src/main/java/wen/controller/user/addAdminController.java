package wen.controller.user;

import wen.pojo.User;
import wen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addAdminController")
public class addAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer url = request.getRequestURL();
        System.out.println(url);
        String userName = request.getParameter("userName");
        String loginName = request.getParameter("loginName");
        String passWord = request.getParameter("password");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        User nowUser = (User) session.getAttribute("LOGIN_USER");
        //判断当前登录的已经是超级管理员,增加管理员操作
        if (nowUser != null && nowUser.getUserType() == 0) {
            User user = new User(-1, userName, loginName, passWord, 1, phoneNumber, email);
        } else {
            response.sendRedirect("background/main.jsp");
        }
        try {
            response.sendRedirect("background/main.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}