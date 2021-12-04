package jiang.servlet;

import jiang.dao.GifDao;
import jiang.pojo.Gif;
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

@WebServlet("/ChaServlet")
public class ChaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GifDao gifDao = new GifDao();
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        String sql;
        Object[] setsqls = null;

        sql = "SELECT * FROM shop.`gif` ";
        setsqls = new Object[]{};

        ArrayList<Object> objects = null;
        try {
            objects = gifDao.queryAllTargets(connection, sql, Gif.class, setsqls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Gif> gifs = new ArrayList<>();
        for (Object object : objects) {
            gifs.add((Gif) object);
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("gifs", gifs);
        request.setAttribute("first", 1);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}