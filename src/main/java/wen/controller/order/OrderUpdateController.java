package wen.controller.order;

import wen.pojo.Order;
import wen.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OrderUpdateController")
public class OrderUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("orderId"));
        String linkman = request.getParameter("linkman");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String remark = request.getParameter("remark");
        int status = Integer.parseInt(request.getParameter("status"));
        try {
            Order order = OrderService.queryOrderById(id);
            order.setLinkman(linkman);
            order.setAddress(address);
            order.setPhonenumber(phonenumber);
            order.setRemark(remark);
            order.setStatus(status);
            OrderService.updateOrder(order);
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