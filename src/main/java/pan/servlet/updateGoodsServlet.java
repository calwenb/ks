package pan.servlet;

import pan.dao.GoodsDao;
import wen.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/updateGoodsServlet")
public class updateGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        System.out.println(request.getParameter("price"));
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        int pnum = Integer.parseInt(request.getParameter("num"));

        String description = request.getParameter("description");
        String size = request.getParameter("size");
        GoodsDao goodsDao = new GoodsDao();
        String sql = "UPDATE shop.`goods`  SET `goods`.name=?,`goods`.price=?,`goods`.category=?,`goods`.pnum=?,`goods`.description=?,`goods`.size=? WHERE `goods`.id=?";
        Object[] setsqls = new Object[]{name, price, category, pnum, description, size, id};
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int i = goodsDao.updateTarget(connection, sql, setsqls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("goodsshow.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }
}
