<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人注册</title>
    <!-- 引入 favicon 图标 -->
    <link rel="shortcut icon" href="/favicon.ico">
    <!-- 引入 css 初始化文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 引入 css 公共样式文件 -->
    <link rel="stylesheet" href="css/common.css">
    <!-- 引入 css register文件 -->
    <link rel="stylesheet" href="css/register.css">
</head>

<body>
<header class="w">
    <a href="index.jsp"><img src="images/logo.png" alt=""></a>
</header>
<div class="registerarea w">
    <h3>注册新用户
        <div class="login">我有账号，去<a href="login.jsp" class="style_red">登录</a></div>
    </h3>
    <div class="reg-form">
        <form action="registerController" method="post">
            <ul>
                <li><label for="tel">用户名：</label><input type="text" name="userName"></li>
            </ul>
            <ul>
                <li><label for="tel">账号：</label><input type="text" name="loginName"></li>
            </ul>
            <ul>
                <li><label for="tel">邮箱：</label><input type="email" name="email"></li>
            </ul>
            <ul>
                <li><label for="tel">手机号：</label><input type="tel" name="phoneNumber" id="tel"></li>
            </ul>
            <ul>
                <li><label for="password_01">登录密码：</label><input type="password" name="password" id="password_01"></li>

                <br>

                <li><input type="submit" value="完成注册" class="btn"></li>
            </ul>
        </form>
    </div>
</div>

</body>

</html>