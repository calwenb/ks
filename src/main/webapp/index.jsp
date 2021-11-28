<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
    <!-- 网站说明 -->

</head>

<body>
<%@ include file="/common/index_head.jsp" %>
<!-- 商品列表 -->
<!-- com_list  commodity_list -->
<div class="com_list w">
    <!-- <div class="com_list-hd">
        < img src="upload/com_hd.png" alt="">
    </div> -->
    <div class="com_list-bd">
        <ul class="clearfix">

            <c:forEach var="Goods" items="${goodses}">
                <li>
                    <a href="#">
                        <img src="${Goods.getImgurl()}" alt="" width="300px" height="300px">
                    </a>
                    <a href="">
                        <h3>${Goods.getName()} </h3>
                    </a>
                    <a href="" class="price">
                        ￥${Goods.getPrice()}
                    </a>
                    <a href="CartAdd?id=${Goods.id}" class="buy">加入购物车</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<input type="hidden" id="first" value="${first}">

</body>
<script>
    $(function () {
        if ($('#first').val() != '1') {
            window.location.href = "queryGoodsServlet?category=t恤"
        }
    })
</script>

</html>
