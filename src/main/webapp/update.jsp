<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>商品下架</title>
</head>
<body bgcolor="#f0f8ff">
<form action="updateGoodsServlet" method="post">
    <div id="Nav"><h1 align="center">商品信息修改：</h1></div>
    <hr>
    ${id}
    <input type="hidden" name="id" value="${param.id}">
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
        <li>商品描述：<input type="text" name="description"></li>
    </ul>
    <ul>
        <li>商品尺码：<input type="text" name="size"></li>
    </ul>
    <div id="submit">
        <input type="submit" value="修改">
        <a href="queryGoodsServlet">返回</a>

    </div>
</form>
</body>
</html>
