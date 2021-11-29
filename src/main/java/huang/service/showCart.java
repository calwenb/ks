package huang.service;

import huang.dao.CartDao;
import huang.pojo.Cart;
import pan.dao.GoodsDao;
import pan.pojo.Goods;
import wen.pojo.User;
import wen.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/showCart")
public class showCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");
        int uid = user.getId();
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CartDao cartDao = new CartDao();
        String sql = "SELECT * FROM shop.cart where u_id=?";
        Object[] setsqls = {uid};
        ArrayList<Object> objects = null;
        try {
            objects = cartDao.queryAllTargets(connection, sql, Cart.class, setsqls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Cart> carts = new ArrayList<>();
        ArrayList<String> imgurls = new ArrayList<>();
        for (Object object : objects) {
            Cart cart = (Cart) object;
            int pid = cart.getpId();
            sql = "SELECT * FROM shop.`goods` WHERE id=?";
            setsqls = new Object[]{pid};
            GoodsDao goodsDao = new GoodsDao();
            Goods goods = null;
            try {
                goods = (Goods) goodsDao.queryTarget(connection, sql, Goods.class, setsqls);
            } catch (Exception e) {
                e.printStackTrace();
            }
            imgurls.add(goods.getImgurl());
            carts.add((Cart) object);
        }

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        session.setAttribute("carts", carts);
        session.setAttribute("imgurls", imgurls);
        request.getRequestDispatcher("cart.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
