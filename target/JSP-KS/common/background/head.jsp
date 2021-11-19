<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>Head Page</title>
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog-admin.css">

</head>
<body>
<div class="hrms_brand_nav">
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapse" data-toggle="collapse"
                        data-target="#hrms-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" id="company_logo" href="${pageContext.request.contextPath}/background/main.jsp">后台管理系统</a>
            </div>
            <div class="collapse navbar-collapse" id="hrms-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/background/main.jsp">主页 <span
                                class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${sessionScope.LOGIN_USER.getUserType()==0}">
                        <li class="dropdown">
                            <a href="#">你好,超级管理员</a>
                        </li>
                        <li class="dropdown">
                            <a onclick="onAdmin();">添加管理员</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.LOGIN_USER.getUserType()==1}">
                        <li class="dropdown">
                            <a href="#">你好,管理员</a>
                        </li>
                    </c:if>
                    <li class="dropdown">
                        <a onclick="onDialog();">更改密码</a>
                    </li>
                    <li class="dropdown">
                        <a href="${pageContext.request.contextPath}/OutLoginController">账号退出</a>
                    </li>
                </ul>


            </div>
        </div>
    </nav>
</div>

<div class="dialog">
    <button class="dialog-close">X</button>
    <div class="dialog-body">
        <div class="ui-dialog-content">
            <p class="common-tips-tit">更改密码</p>
            <form action="${pageContext.request.contextPath}/UpdateUserController" method="get">
                <div class="form-group">
                    <label for="pwd">密码:</label>
                    <input type="password" class="form-control" id="pwd" name="password">
                </div>
                <input type="submit" class="btn btn-default"/>
            </form>
        </div>
    </div>
</div>
<div class="dialog-admin">
    <button class="dialog-admin-close">X</button>
    <div class="dialog-admin-body">
        <div class="ui-dialog-content">
            <p class="common-tips-tit">添加管理员</p><br>
            <form action="${pageContext.request.contextPath}/addAdminController" method="post">
                <div class="form-group">
                    用户名：<input type="text" name="userName"><br><br>
                    账号：&nbsp;&nbsp;<input type="text" name="loginName"><br><br>
                    邮箱：&nbsp;&nbsp;<input type="email" name="email"><br><br>
                    手机号：<input type="tel" name="phoneNumber" id="tel"><br><br>
                    登录密码：<input type="password" name="password" id="password_01"><br><br>
                    <input type="submit" value="增加管理员" class="btn">
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script>
    function onDialog() {
        $(".dialog").show();//显示状态
        $(".dialog-close").click(function () {
            $(".dialog").hide()
        })
    }

    function onAdmin() {
        $(".dialog-admin").show();//显示状态
        $(".dialog-admin-close").click(function () {
            $(".dialog-admin").hide()
        })
    }
</script>
</html>
