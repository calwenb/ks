package pan.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pan.dao.GoodsDao;
import pan.pojo.Goods;
import wen.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addGoodsServlet")
public class addGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "images/goods";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * 上传数据及保存文件
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = null;
        double price = 0;
        String category = null;
        int pnum = 0;
        String imgurl = null;
        String description = null;
        String size = null;
        ////////
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        System.out.println(ServletFileUpload.isMultipartContent(request));
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);


        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;


        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    if (item.isFormField()) {  //普通表单字段
                        String fieldName = item.getFieldName();   //表单字段的name属性值
                        if (fieldName.equals("name")) {
                            //输出表单字段的值
                            name = item.getString("UTF-8");
                            System.out.println("name   " + name);
                        }
                        if (fieldName.equals("price")) {
                            price = Double.parseDouble(item.getString("UTF-8"));
                        }
                        if (fieldName.equals("category")) {
                            category = item.getString("UTF-8");
                        }
                        if (fieldName.equals("num")) {
                            pnum = Integer.parseInt(item.getString("UTF-8"));
                        }
                        if (fieldName.equals("description")) {
                            description = item.getString("UTF-8");
                        }
                        if (fieldName.equals("size")) {
                            size = item.getString("UTF-8");
                        }
                    }
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        imgurl = UPLOAD_DIRECTORY + '/' + fileName;
                        System.out.println(imgurl);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                                "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }


        ///////////////////////

        Goods goods = new Goods(-1, name, price, category, pnum, imgurl, description, size);
        GoodsDao goodsDao = new GoodsDao();
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "insert into shop.`goods`(name,price,category,pnum,imgurl,description,size)VALUES ( ?, ?, ?, ?, ?, ?,?)";
        try {
            goodsDao.addTarget(connection, sql, goods);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("goodsshow.jsp").forward(request, response);
    }


}
