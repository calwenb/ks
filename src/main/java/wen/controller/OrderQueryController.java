package wen.controller;

import wen.pojo.Order;
import wen.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/OrderQueryController")
public class OrderQueryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.setAttribute("orders", orders);
            request.setAttribute("preNum", preNum);
            request.getRequestDispatcher("/background/orderPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}