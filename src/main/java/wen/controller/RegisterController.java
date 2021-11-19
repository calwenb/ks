package wen.controller;

import wen.pojo.User;
import wen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerController")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String loginName = request.getParameter("loginName");
        String passWord = request.getParameter("password");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        String email = request.getParameter("email");
        User user = new User(-1, userName, loginName, passWord, 2, phoneNumber, email);
        try {
            if (UserService.addUser(user) == 1) {
                response.getWriter().println("注册成功! 正在跳转");
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("register.jsp");
            e.printStackTrace();
        }
    }
}