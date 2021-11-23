package wen.servlet;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("send".equals(action)) {
            this.send(request, response);
        } else if ("get".equals(action)) {
            this.get(request, response);
        }
    }

    private void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html;charset=UTF-8");    //设置响应内容类型及编码方式
        response.setHeader("cache-Control", "no-cache");    //禁止页面缓存
        PrintWriter out = response.getWriter();     //获取输出流对象

        /*****************获取聊天信息***********************/
        ServletContext application = getServletContext();  //获取application对象
        String msg = "";
        if (null != application.getAttribute("message")) {
            Vector<String> v_temp = (Vector<String>) application.getAttribute("message");
            for (int i = v_temp.size() - 1; i >= 0; i--) {
                msg = msg + "<br>" + v_temp.get(i);
            }
        } else {
            msg = "欢迎光临汽车之家！";
        }
        out.println(msg);  //输出生成后的聊天信息
        out.close();  //关闭输出流对象
    }

    private void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        ServletContext application = getServletContext();  //获取application对象
        /*****************获取聊天信息***********************/
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user"); //获取用户昵称
        String speak = request.getParameter("speak");   //获取说话内容

        Vector<String> v = null;
        String message = "[" + user + "]说:" + speak;  //组合说话内容
        if (null == application.getAttribute("message")) {
            v = new Vector<String>();
        } else {
            v = (Vector<String>) application.getAttribute("message");
        }

        v.add(message);
        application.setAttribute("message", v); //将聊天内容保存到application
        Random random = new Random();
        request.getRequestDispatcher("ChatServlet?action=get&nocache=" + random.nextInt(10000)).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}