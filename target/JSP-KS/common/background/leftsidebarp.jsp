<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<div class="panel-group col-sm-2" id="hrms_sidebar_left" role="tablist" aria-multiselectable="true">

    <ul class="nav nav-pills nav-stacked emp_sidebar">
        <li role="presentation" class="active">
            <a href="#" >
                <span class="glyphicon glyphicon-user" aria-hidden="true">&nbsp;个人管理</span>
            </a>
            <ul class="nav nav-pills nav-stacked" >
                <li role="presentation"><a href="${pageContext.request.contextPath}/emp/myMsg">个人信息</a></li>
            </ul>
            <ul class="nav nav-pills nav-stacked" >
                <li role="presentation"><a href="${pageContext.request.contextPath}/emp/toUppPwd">修改密码</a></li>
            </ul>
        </li>
    </ul><br>

    <ul class="nav nav-pills nav-stacked emp_sidebar">
        <li role="presentation" class="active">
            <a href="#" data-toggle="collapse" data-target="#collapse_emp">
                <span class="glyphicon glyphicon-user" aria-hidden="true">&nbsp;员工信息</span>
            </a>
            <ul class="nav nav-pills nav-stacked" id="collapse_emp">
                <li role="presentation"><a href="${pageContext.request.contextPath}/emp/empListp">员工信息</a></li>
            </ul>
        </li>
    </ul>

    <ul class="nav nav-pills nav-stacked dept_sidebar">
        <li role="presentation" class="active">
            <a href="#" data-toggle="collapse" data-target="#collapse_dept">
                <span class="glyphicon glyphicon-cloud" aria-hidden="true">&nbsp;订单信息</span>
            </a>
            <ul class="nav nav-pills nav-stacked" id="collapse_dept">
                <li role="presentation"><a href="${pageContext.request.contextPath}/dep/depListp">订单信息</a></li>
            </ul>
        </li>
    </ul>

    </ul>
</div>
</html>
