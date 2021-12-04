<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人登录</title>
    <!-- 引入 favicon 图标 -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
    <!-- 引入 css 初始化文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <!-- 引入 css 公共样式文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <!-- 引入 css register文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>
<header class="w">
    <a href="index.jsp"><img src="images/logo.png" alt=""></a>
</header>
<div class="registerarea w">
    <h3>用户登录
        <div class="login">还没有账号，去<a href="register.jsp" class="style_red">注册</a></div>
    </h3>
    <div class="reg-form">
        <form action="LoginController" method="post">

            <ul>
                <li><label for="tel">账号：</label><input type="tel" name="loginName" id="tel"></li>
            </ul>

            <ul>

                <li><label for="password_02">密码：</label><input type="password" name="password" id="password_02"></li>

                <li class="agree"><input type="checkbox" name="auto_login"/>自动登录</li>
                <br>

                <li><input type="submit" value="登录" class="btn"></li>
            </ul>
        </form>
    </div>
</div>

</body>

</html>