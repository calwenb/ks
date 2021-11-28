package pan.servlet;

import pan.dao.GoodsDao;
import pan.pojo.Goods;
import wen.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/queryGoodsServlet")
public class queryGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        GoodsDao goodsDao = new GoodsDao();
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql;
        Object[] setsqls = null;
        if (category != null) {
            sql = "SELECT * FROM shop.`goods` where category=?";
            setsqls = new Object[]{category};
        } else {
            sql = "SELECT * FROM shop.`goods` ";
            setsqls = new Object[]{};
        }
        ArrayList<Object> objects = null;
        try {
            objects = goodsDao.queryAllTargets(connection, sql, Goods.class, setsqls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Goods> goodses = new ArrayList<>();
        for (Object object : objects) {
            goodses.add((Goods) object);
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("goodses", goodses);
        if (category != null) {
            request.setAttribute("first", 1);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/goodsshow.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
