package huang.service;

import huang.dao.CartDao;
import huang.pojo.Cart;
import pan.dao.GoodsDao;
import pan.pojo.Goods;
import wen.pojo.User;
import wen.utils.JDBCUtil;
import wen.utils.MailUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/CartAdd")
public class CartAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Goods p = null;//获取产品
        String pId = request.getParameter("id");//获取参数
        String kill = (String) request.getAttribute("kill");

        Connection con = null;//方便查询 置空
        try {
            con = JDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        实例化并且查询到产品
         */
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");//是从Session中获取名称为user的值，一般用于登录后的身份验证！
        int uid = user.getId();//用户id
        if (pId != null) {
            GoodsDao goodsDao = new GoodsDao();
            String sql = "SELECT * FROM `shop`.goods where id=?";
            Object[] setSqls = {pId};
            try {
                p = (Goods) goodsDao.queryTarget(con, sql, Goods.class, setSqls);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //通过产品表取出来
        }
        if (p == null) {
            response.getWriter().println("实际,没有该商品");
            return;
        }

        //包装一个实体类 javabean
        Cart cart = new Cart(
                0,
                p.getName(),
                p.getPrice(),
                1,
                p.getPnum(),
                p.getId(),
                uid
        );
        CartDao cartDao = new CartDao();
        String sql;
        sql = "SELECT * FROM shop.cart WHERE p_id=?";
        Object[] setsqls = {pId};
        Cart o = null;
        try {
            o = (Cart) cartDao.queryTarget(con, sql, Cart.class, setsqls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (o != null && pId.equals(Integer.toString(o.getpId())) && uid == o.getUId()) {
            o.setBuyNum(o.getBuyNum() + 1);
            sql = "DELETE FROM shop.`cart` WHERE p_id =?";
            Object[] sqlFields2 = {pId};
            try {
                cartDao.deleteTarget(con, sql, sqlFields2);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "insert INTO `cart`  VALUES (null, ?, ?, ?,?, ?,?)";
            try {
                int i = cartDao.addTarget(con, sql, o);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } else {
            sql = "insert INTO `cart`  VALUES (null, ?, ?, ?,?, ?,?)";
            try {
                int i = cartDao.addTarget(con, sql, cart);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String email = user.getEmail();
        try {
            MailUtil.sendMail(email, "zhu", "add购物车", "add成功");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if ("isKill".equals(kill)) {//如果是秒杀
            response.getWriter().println("秒杀成功!\n已加入购物车!!!");
        } else {
            response.sendRedirect("index.jsp");
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
