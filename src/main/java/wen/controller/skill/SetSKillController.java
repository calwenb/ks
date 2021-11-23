package wen.controller.skill;


import wen.service.SKillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SetSKillController")
public class SetSKillController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsId = request.getParameter("goodsId");
        String killNum = request.getParameter("killNum");
        String killTime = request.getParameter("killTime");
        String msg;
        if (killTime != null) { //设置秒杀时间开始
            msg = SKillService.SetSKillTime(killTime);
        } else {
            msg = SKillService.setKill(goodsId, killNum);
        }
        response.getWriter().println(msg);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}