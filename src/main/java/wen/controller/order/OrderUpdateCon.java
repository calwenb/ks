package wen.controller.order;

import wen.pojo.Order;
import wen.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OrderUpdateCon")
public class OrderUpdateCon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = null;
        try {
             order = OrderService.queryOrderById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("order",order);
        request.getRequestDispatcher("/background/orderUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}