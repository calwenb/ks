<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="wen.pojo.User" %>
<html>
<head>
    <title>订单查询</title> <%--标题--%>
</head>
<body>
<%@ include file="/common/index_head.jsp" %>

<!-- 导航条 -->
<div class="com_list w">
    <!-- 中间部分（包括左边栏和订单/订单表单显示部分） -->
    <div style="position:relative; top:-15px;">
        <!-- 左侧栏 -->
        <div class="col-sm-10" style="width: 120%;margin-left: -5%;width: 120%;margin-top: 3%;">
            <div class="panel panel-danger">
                <!-- 路径导航 -->
                <div class="panel-heading">
                    <ol class="breadcrumb">
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
                        <h3 style="text-align: center;color: red;">${requestScope.msg}</h3>
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
                                <th>操作</th>
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
                                        <c:choose>
                                            <c:when test="${order.getStatus()==1}">
                                                <td>已下单</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/OrderFinishCon?orderId=${order.getId()}"
                                                       role="button" class="btn btn-default">确认收货
                                                    </a>
                                                </td>
                                            </c:when>
                                            <c:when test="${order.getStatus()==2}">
                                                <td>已完成订单</td>
                                                <td></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>无效订单</td>
                                                <td></td>
                                            </c:otherwise>
                                        </c:choose>
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
