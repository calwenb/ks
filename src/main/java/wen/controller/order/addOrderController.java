package wen.controller.order;

import huang.pojo.Cart;
import wen.pojo.Order;
import wen.pojo.User;
import wen.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/addOrderController")
public class addOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String remark = request.getParameter("remark");
        double amount = Double.parseDouble(request.getParameter("amount"));
        User login_user = (User) request.getSession().getAttribute("LOGIN_USER");
        String loginName = login_user.getLoginName();
        Date time = new Date();
        Order order = new Order(-1, loginName, name, address, tel, amount, time, remark, 1);
        ArrayList<Cart> carts = (ArrayList<Cart>) request.getSession().getAttribute("carts");
        int i = 0;
        try {
            i = OrderService.addOrder(order, carts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((i == 0)) {
            request.setAttribute("msg", "下单失败!");
        } else {
            request.setAttribute("msg", "下单成功!");
        }
        request.getRequestDispatcher("showCart").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}