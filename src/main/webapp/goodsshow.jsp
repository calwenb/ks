<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单查询</title> <%--标题--%>
</head>
<body>
<!-- 导航条 -->
<%@ include file="common/background/head.jsp" %>

<!-- 中间部分（包括左边栏和订单/订单表单显示部分） -->
<div style="position:relative; top:-15px;">
    当前时间:<span id="now_time">0000-00-00 00:00:00</span>
    <!-- 左侧栏 -->
    <%@ include file="common/background/leftsidebar.jsp" %>
    <div class="emp_info col-sm-10">
        <div class="panel panel-info">
            <!-- 路径导航 -->
            <div class="panel-heading">
                <ol class="breadcrumb">
                    <li>商品信息查询</li>
                </ol>
            </div>
            <%--在这里写内容--%>
            <%--orders:${orders}--%>
            <div>
                <div class="row clearfix" style="padding-left: 3%;">
                    <div class="col-md-12 column">
                    </div>
                </div>
                <div style="padding-left: 3%;">
                    <%--这里--%>
                    <!-- Table -->
                    <div>
                        <table class="table table-bordered table-hover" table-responsive id="dept_table">
                            <thead>
                            <th>商品编号</th>
                            <th>商品名字</th>
                            <th>商品价格</th>
                            <th>商品种类</th>
                            <th>库存</th>
                            <th>商品图片</th>
                            <th>商品描述</th>
                            <th>尺码</th>
                            <th>操作</th>
                            </thead>
                            <tbody>
                            <c:forEach var="goods" items="${goodses}">
                                <tr>
                                    <td>${goods.getId()}</td>
                                    <td>${goods.getName()}</td>
                                    <td>${goods.getPrice()}</td>
                                    <td>${goods.getCategory()}<br>
                                    <td>${goods.getPnum()}</td>
                                    <td><img src="${goods.getImgurl()}" width="100px" height="100px"></td>
                                    <td>${goods.getDescription()}</td>
                                    <td>${goods.getSize()}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/update.jsp?id=${goods.getId()}"
                                           role="button" class="btn btn-default">编辑</a>
                                        <a href="${pageContext.request.contextPath}/delGoodsServlet?id=${goods.getId()}"
                                           role="button" class="btn btn-warning">删除</a>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        window.setInterval(
            "$('#now_time').load('info',{})",
            500
        )
    })
</script>
</body>
</html>
