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
        /*try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.print  StackTrace();
        }*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");
        if (user == null) {
            response.getWriter().println("还未登录,请登录!");
        } else {
            String loginName = user.getLoginName();
            String goodId = request.getParameter("goodId");
            String msg = SKillService.doSKill(loginName, goodId);
            if (msg.indexOf("成功") != -1) {
                System.out.println("加入购物车");
            }
            //System.out.println("user:" + loginName + msg);
            response.getWriter().println("user:" + loginName + msg);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}