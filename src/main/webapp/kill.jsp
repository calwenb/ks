<%@ page import="wen.pojo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
    <!-- 网站说明 -->

    <meta name="keywords" content="网上购物,网上商城,手机,笔记本,电脑,MP3,CD,VCD, DV,相机,数码,配件,手表,存储卡,京东">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="/favicon.ico">
    <!-- 引入 css 初始化文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 引入 css 公共样式文件 -->
    <link rel="stylesheet" href="css/common.css">
    <!-- 引入 css list文件 -->
    <link rel="stylesheet" href="css/list.css">
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog.css">
</head>

<body>
<!-- 快捷导航模块 -->
<section class="shortcut">
    <div class="w">
        <div class="fl">
            <ul>
                <li>
                    ${sessionScope.LOGIN_USER.userName}&nbsp;&nbsp;你好!
                </li>

                <li>&nbsp;品优购欢迎您！&nbsp;</li>
                <li>
                    <%
                        User user = (User) session.getAttribute("LOGIN_USER");
                        if (user == null) {
                            out.print("&nbsp;<a href='login.jsp' >请登录</a> " +
                                    "&nbsp;&nbsp;<a href='register.jsp' >免费注册</a>");
                        } else {
                            out.print(" &nbsp;&nbsp;<a href='OutLoginController' >退出登录</a>");
                        }
                    %>
                    &nbsp;
                </li>
            </ul>
        </div>
        <div class="fr">
            <ul>
                <li><a href="${pageContext.request.contextPath}/myOrderController">我的订单</a></li>
                <li></li>
                <li class="arrow-icon"><a href="#">我的信息</a></li>

                <li></li>
                <%--<li class="arrow-icon"><a href="#">客户服务</a></li>--%>
                <%
                    if (user != null) {
                        out.print("<li ><a onclick='onDialog();'>更改密码</a></li>");
                    }
                %>
            </ul>
        </div>
    </div>
</section>

<!-- header 头部区域 -->
<header class="w">
    <div class="logo">
        <h1>
            <a href="index.jsp" title="品优购商城">品优购商城</a>
        </h1>
    </div>
    <div class="search">
        <input type="search" name="" id="" placeholder="语言开发">
        <button>搜索</button>
    </div>

    <div class="shopcar">
        <a href="#">我的购物车</a>
        <i class="count">8</i>
    </div>

</header>

<div class="com_list w">
    <div class="com_list-bd">
        <ul class="clearfix">
            <c:choose>
                <c:when test="${SKGoodsIds==null||SKGoodsIds.size()==0||SKTime==null}">
                    <h3 style="text-align: center;">还没有商品,参加秒杀活动</h3>
                </c:when>
                <c:otherwise>
                    <h2 style="text-align: center;">秒杀时间:${SKTime}:00&nbsp;&nbsp;||&nbsp;&nbsp;当前时间:<span id="now_time">0000-00-00 00:00:00</span>
                    </h2>
                    <c:forEach var="SkGoodId" items="${SKGoodsIds}">
                        <li>
                            <a href="#">
                                <img src="images/goods/com_01.png" alt="">
                            </a>
                            <a href="">
                                <h4>iphone:${SkGoodId}号开始抢购!!!</h4>
                                    <%--<h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>--%>
                            </a>
                            <a href="" class="price">
                                ￥6088
                                <em>￥6988</em>
                            </a>
                            <div class="sold">
                                已售87%
                                <div class="cylinder">
                                </div>
                                剩余<i>29</i>件
                            </div>
                            <input type="hidden" id="SKill_id" value="${SkGoodId}"/>
                            <button onclick="start_spike();" class="buy">点击秒杀</button>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
<script>
    function start_spike() {
        var SKill_id = $('#SKill_id').val()
        $.ajax({
            url: "${pageContext.request.contextPath}/sKillController",
            type: 'post',
            data: {"goodId": SKill_id},
            success: function (msg) {
                alert(msg);
            },
            error: function (msg) {
                console.log()
                alert(msg);
            }
        })
    }

    $(function () {
        window.setInterval(
            "$('#now_time').load('info',{})",
            1000
        )
    })
</script>

</body>
</html>