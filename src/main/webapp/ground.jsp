<%--
  Created by IntelliJ IDEA.
  User: 铭少
  Date: 2021/11/14
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品上架</title>
</head>
<body bgcolor="#7fffd4">
<form action="addGoodsServlet" method="post" enctype="multipart/form-data">
    <div id="Nav"><h1 align="center">商品上架：</h1></div>
    <hr>
    <br>
    <ul>
        <li>商品名称：<input type="text" name="name"></li>
    </ul>
    <ul>
        <li>商品价格:<input type="text" name="price"></li>
    </ul>
    <ul>
        <li>商品种类：<input type="text" name="category"></li>
    </ul>
    <ul>
        <li>商品数量：<input type="text" name="num"></li>
    </ul>
    <ul>
        <li>商品图片文件上传：<input type="file" name="uploadFile"></li>
    </ul>
    <ul>
        <li>商品描述：<input type="text" name="description"></li>
    </ul>
    <ul>
        <li>商品尺码：<input type="text" name="size"></li>
    </ul>
    <div id="submit">
        <input type="submit" value="上架">
    </div>

</form>
</body>
</html>
