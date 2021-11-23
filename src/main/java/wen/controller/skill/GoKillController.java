package wen.controller.skill;


import wen.service.SKillService;
import wen.utils.KillTimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/GoKillController")
public class GoKillController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List SKGoodsIds = SKillService.querySKGoods();
        Date killTime = KillTimeUtil.getKillTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (killTime!=null){
            String startTime = format.format(killTime);
            request.setAttribute("SKTime", startTime);
        }

        request.setAttribute("SKGoodsIds", SKGoodsIds);
        request.getRequestDispatcher("/kill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}