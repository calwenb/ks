package wen.controller.order;

import wen.pojo.User;
import wen.service.OrderService;
import wen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SenMailController")
public class SenMailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String orderLoginName = request.getParameter("orderLoginName");
        User user = null;
        try {
            user = UserService.queryUserByLName(orderLoginName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            OrderService.sendMail(orderId, user.getEmail());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        response.sendRedirect("OrderQueryController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        this.doGet(request, response);
    }
}