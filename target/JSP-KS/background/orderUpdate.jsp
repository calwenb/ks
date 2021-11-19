<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单修改</title> <%--标题--%>
</head>
<body>
<!-- 导航条 -->
<%@ include file="../common/background/head.jsp" %>
<!-- 中间部分（包括左边栏和员工/订单表单显示部分） -->
<div class="hrms_body" style="position:relative; top:-15px;">

    <!-- 左侧栏 -->
    <%@ include file="../common/background/leftsidebar.jsp" %>
    <div class="emp_info col-sm-10">

        <div class="panel panel-info">
            <!-- 路径导航 -->
            <div class="panel-heading">
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/OrderQueryController">订单首页</a></li>
                    <li>订单修改</li>
                </ol>
            </div>
            <%--在这里写内容--%>
            <div style="padding-left: 3%;">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <div class="page-header">
                            <h1>
                                <small>订单修改</small>
                            </h1>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 3%;">
                    <%--这里--%>
                    <div style="padding-left: 4%;padding-bottom: 10%;">
                        <form action="${pageContext.request.contextPath}/OrderUpdateController" method="post">
<%--                            <% String orderId = request.getParameter("order");%>--%>
                            <input type="hidden" name="orderId" value="${order.getId()}"/><br><br><br>
                            联系人：<input type="text" name="linkman" value="${order.getLinkman()}"/><br><br><br>
                            地址：<input type="text" name="address" value="${order.getAddress ()}"/><br><br><br>
                            手机号：<input type="text" name="phonenumber" value="${order.getPhonenumber()}"/><br><br><br>
                            备注：<input type="text" name="remark" value="${order.getRemark ()}"/><br><br><br>
                            状态：<input type="text" name="status" value="${order.getStatus()}"/>(订单状态
                            0:无效订单,1:已经下单,2:已完成的订单)<br><br><br>
                            <input type="submit" value="修改">
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>