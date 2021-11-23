package wen.controller.order;

import wen.dao.OrderDao;
import wen.service.OrderItemService;
import wen.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delOrderController")
public class DelOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        try {
            OrderItemService.delOrderItem(orderId);
            OrderService.delOrder(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("OrderQueryController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}