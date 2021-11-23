package wen.controller.order;

import wen.pojo.Order;
import wen.pojo.User;
import wen.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/myOrderController")
public class myOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("LOGIN_USER");
        if (loginUser==null){
            response.sendRedirect("login.jsp");
        }
        try {
            int preNum = 1;
            if (request.getParameter("preNum") != null) {
                preNum = Integer.parseInt(request.getParameter("preNum"));
                String changePre = request.getParameter("changePre");
                if ("add".equals(changePre)) {
                    preNum++;
                }
                if ("minus".equals(changePre)) {
                    if (preNum > 1) {
                        preNum--;
                    }
                }
            }
            request.setAttribute("orders", null);
            String queryText = request.getParameter("queryText");
            ArrayList<Order> orders;
            if (queryText != null) {
                String isLik = request.getParameter("isLik");
                if ("模糊查询".equals(isLik)) {
                    //System.out.println(isLik);
                    orders = OrderService.queryLike(queryText, true, preNum);
                } else {
                    orders = OrderService.queryLike(queryText, false, preNum);
                }
            } else {
                orders = OrderService.queryAllOrders(preNum);
            }
            String loginName = loginUser.getLoginName();
            orders.forEach(System.out::print);
            ArrayList<Order> myOrders = new ArrayList<>();
            for (Order order : orders) {
                if (order.getLogin_name().equals(loginName)){
                    myOrders.add(order);
                }
            }
            request.setAttribute("orders", myOrders);
            request.setAttribute("preNum", preNum);
            request.getRequestDispatcher("/myOrderPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}