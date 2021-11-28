<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <title>确认订单</title>
    <meta name="description"
          content="品优购-专业的综合网上购物商城,销售家电、数码通讯、电脑、家居百货、服装服饰、母婴、图书、食品等数万个品牌优质商品.便捷、诚信的服务，为您提供愉悦的网上购物体验!">
    <meta name="Keywords" content="网上购物,网上商城,手机,笔记本,电脑,MP3,CD,VCD,DV,相机,数码,配件,手表,存储卡,品优购">
    <!-- 引入facicon.ico网页图标 -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
    <!-- 引入css 初始化的css 文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 引入公共样式的css 文件 -->
    <link rel="stylesheet" href="css/common.css">
    <!-- 引入car css -->
    <link rel="stylesheet" href="css/car.css">
    <!-- 引入dialog css -->
    <link rel="stylesheet" href="css/dialog.css">
    <!-- 先引入jquery -->
    　
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- 再引入我们自己的js文件 -->
    <script src="js/car-11.3.7.js"></script>
</head>
<body bgcolor="#4dff0e">
<div style="text-align: center;font-size: 20px">
    <form action="addOrderController" method="get" enctype="multipart/form-data">
        <div id="Nav"><h1 align="center">确认订单：</h1></div>
        <hr>
        <br>
        <ul>
            <li>姓名：<input type="text" name="name" style="background-color: #dddddd"></li>
        </ul>
        <ul>
            <li>联系电话:<input type="text" name="tel" style="background-color: #dddddd"></li>
        </ul>
        <ul>
            <li>地址：<input type="text" name="address" style="background-color: #dddddd"></li>
        </ul>
        <ul>
            <li>备注：<input type="text" name="remark" value="无" style="background-color: #dddddd"></li>
        </ul>
        <div class="com_list w">
            <!-- <div class="com_list-hd">
                < img src="upload/com_hd.png" alt="">
            </div> -->
            <div class="com_list-bd">
                <ul class="clearfix">
                    <c:set var="0" value="amount"/>
                    <c:forEach var="rs" items="${sessionScope.carts}" varStatus="loop">
                        <div class="cart-item-list">
                            <div class="cart-item">
                                <div class="p-checkbox">
                                </div>
                                <div class="p-goods">
                                    <div class="p-img">
                                        <img src="${sessionScope.imgurls[loop.count-1]}" alt="" width="100px"
                                             height="100px">
                                    </div>
                                    <div class="p-msg"><p>${rs.goodsName}</p></div>
                                </div>
                                <c:set value="${amount+rs.price * rs.buyNum}" var="amount"/>
                                <div class="p-sum">${rs.price * rs.buyNum}元</div>
                            </div>
                        </div>
                    </c:forEach>
                </ul>
                <h4 style="color: red">总金额${amount}元</h4>
                <input type="hidden" name="amount" value="${amount}">
                <a href="showCart" style="font-size: 30px">取消下单</a>&nbsp;&nbsp;
                <input type="submit" value="提交订单" style="font-size: 30px">
            </div>
        </div>
    </form>
</div>

</body>
</html>
