<%@ page import="wen.pojo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单查询</title> <%--标题--%>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="/favicon.ico">
    <!-- 引入 css 初始化文件 -->
    <!-- 引入 css 公共样式文件 -->
    <link rel="stylesheet" href="css/common.css">
    <!-- 引入 css list文件 -->
    <link rel="stylesheet" href="css/list.css">
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog.css">
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog-admin.css">
</head>
<body>
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
        <input type="search" name="" id="">
        <button>搜索</button>
    </div>

    <div class="shopcar">
        <a href="#">我的购物车</a>
        <i class="count">8</i>
    </div>
    <!-- <div class="seckill">
        <img src="images/seckill.png" alt="">
    </div> -->
</header>
<!-- 导航条 -->
<div class="com_list w">
    <!-- 中间部分（包括左边栏和订单/订单表单显示部分） -->
    <div style="position:relative; top:-15px;">
        <!-- 左侧栏 -->
        <div class="emp_info col-sm-10">
            <div class="panel panel-info">
                <!-- 路径导航 -->
                <div class="panel-heading">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/myOrderController">订单首页</a></li>
                        <li>订单查询</li>
                    </ol>
                </div>
                <%--在这里写内容--%>
                <%--orders:${orders}--%>
                <div>
                    <div class="row clearfix" style="padding-left: 3%;">
                        <div class="col-md-12 column">
                            <div class="page-header">
                                <h1>
                                    <small>订单查询</small>
                                    <small>
                                        <form action="myOrderController" method="get"
                                              style="margin-left: 25%;font-size: 20px;">
                                            查询内容:<input type="text" name="queryText"
                                                        style="background-color: snow;border: black;"/>
                                            <input type="submit" name="isLik" value="模糊查询" class="btn btn-default"/>
                                            <input type="submit" name="isLik" value="查询订单编号" class="btn btn-default"/>
                                        </form>
                                    </small>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <div style="padding-left: 3%;">
                        <%--这里--%>
                        <!-- Table -->
                        <div>
                            <table class="table table-bordered table-hover" table-responsive id="dept_table">
                                <thead>
                                <th>订单编号</th>
                                <th>商品信息</th>
                                <th>账号名</th>
                                <th>联系人</th>
                                <th>通讯地址</th>
                                <th>总金额</th>
                                <th>下单时间</th>
                                <th>备注</th>
                                <th>状态</th>
                                </thead>
                                <tbody>
                                <c:forEach var="order" items="${orders}">
                                    <tr>
                                        <td>${order.getId()}</td>
                                        <td>
                                            <c:forEach var="orderItem" items="${order.getOrderItems()}">
                                                <p>商品ID:${orderItem.getOrderId()},商品名:${orderItem.getGoodsName()}</p>
                                                <p>商品尺寸:${orderItem.getSize()},商品数量:${orderItem.getBuyNum()}</p>
                                                <br>
                                            </c:forEach>
                                        </td>
                                        <td>${order.getLogin_name()}</td>
                                        <td>${order.getLinkman()}</td>
                                        <td>地址:${order.getAddress ()}<br>
                                            电话:${order.getPhonenumber()}</td>
                                        <td>${order.getAmount()}</td>
                                        <td>${order.getTime()}</td>
                                        <td>${order.getRemark ()}</td>
                                        <td><c:choose>
                                            <c:when test="${order.getStatus()==1}">
                                                已下单
                                            </c:when>
                                            <c:when test="${order.getStatus()==2}">
                                                已完成订单
                                            </c:when>
                                            <c:otherwise>
                                                无效订单
                                            </c:otherwise>
                                        </c:choose></td>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div style="margin:0 35% 30% 35%;">
                                <a href="myOrderController?preNum=0&changePre=add" role="button"
                                   class="btn btn-default">首页</a>
                                <a href="myOrderController?preNum=${preNum}&changePre=minus" role="button"
                                   class="btn btn-default">上一页</a>
                                当前第:${preNum}页
                                <a href="myOrderController?preNum=${preNum}&changePre=add" role="button"
                                   class="btn btn-default">下一页</a>
                            </div
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
