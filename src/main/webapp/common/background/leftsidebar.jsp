<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<div class="panel-group col-sm-2" id="hrms_sidebar_left" role="tablist" aria-multiselectable="true">
    <ul class="nav nav-pills nav-stacked emp_sidebar">
        <li role="presentation" class="active">
            <a href="#" data-toggle="collapse" data-target="#collapse_emp">
                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true">&nbsp;商品管理</span>
            </a>
            <ul class="nav nav-pills nav-stacked" id="collapse_emp">
                <li role="presentation"><a href="${pageContext.request.contextPath}/OrderQueryController">商品信息</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/emp/toAddEmp">商品新增</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/emp/deleteAll">商品清零</a></li>
            </ul>
        </li>
    </ul>
    <ul class="nav nav-pills nav-stacked dept_sidebar">
        <li role="presentation" class="active">
            <a href="#" data-toggle="collapse" data-target="#collapse_dept">
                <span class="glyphicon glyphicon-th-list" aria-hidden="true">&nbsp;订单管理</span>
            </a>
            <ul class="nav nav-pills nav-stacked" id="collapse_dept">
                <li role="presentation"><a href="${pageContext.request.contextPath}/OrderQueryController">订单信息</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/background/set_kill.jsp">秒杀开启</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/background/orderCount.jsp">订单统计</a>
                </li>
            </ul>
        </li>
    </ul>
</div>
</html>
