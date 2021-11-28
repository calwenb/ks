<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>我的购物车-品优购</title>
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

<body>
<%@ include file="/common/index_head.jsp" %>

<div class="c-container">
    <div class="w">
        <h3>${requestScope.msg}</h3>
        <div class="cart-filter-bar">
            <em>全部商品</em>
        </div>
        <!-- 购物车主要核心区域 -->
        <div class="cart-warp">
            <!-- 头部模块 -->
            <div class="cart-thead">
                <div class="t-checkbox">
                </div>
                <div class="t-goods">商品</div>
                <div class="t-price">单价</div>
                <div class="t-num">数量</div>
                <div class="t-sum">小计</div>
                <div class="t-action">操作</div>
            </div>
            <c:set var="0" value="amount"/>
            <c:forEach var="rs" items="${sessionScope.carts}" varStatus="loop">

            <div class="cart-item-list">
                <div class="cart-item">
                    <div class="p-checkbox">
                    </div>
                    <div class="p-goods">
                        <div class="p-img">
                            <img src="${sessionScope.imgurls[loop.count-1]}" alt="" width="120px" height="120px">
                        </div>
                        <div class="p-msg"><p>${rs.goodsName}</p></div>
                    </div>
                    <div class="p-price">${rs.price}</div>
                    <div class="p-num">
                        <div class="quantity-form">
                                ${rs.buyNum}
                        </div>
                    </div>
                    <c:set value="${amount+rs.price * rs.buyNum}" var="amount"/>
                    <div class="p-sum">${rs.price * rs.buyNum}</div>
                    <div class="p-action"><a href="delCart?pId=${rs.pId}">删除</a></div>
                </div>
            </div>
            </c:forEach>
            <h4 style="color: red">总金额${amount}元</h4>
            <div class="btn-area" style="width: 150px;">
                <a href="order.jsp">去结算</a>|
                <a href="index.jsp"> 继续购买</a>
            </div>
</body>

</html>
