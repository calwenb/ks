package wen.controller.skill;

import wen.pojo.User;
import wen.service.SKillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/sKillController")
public class SKillController extends HttpServlet {
    public String setUserId() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");
        String loginName = user.getLoginName();
        String goodId = request.getParameter("goodId");
        String msg = SKillService.doSKill(loginName, goodId);
        if (msg.indexOf("成功") != -1) {
            //加入购物车
            request.setAttribute("id", goodId);
            request.setAttribute("kill", "isKill");
            request.getRequestDispatcher("CartAdd?id=" + goodId).forward(request, response);
        }

        response.getWriter().println("用户:" + loginName + msg);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}