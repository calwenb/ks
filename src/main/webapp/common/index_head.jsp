<%@ page import="wen.pojo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>Title</title>
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
                <li><a href="myOrderController">我的订单</a></li>
                <li></li>
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


    <div class="shopcar">
        <a href="showCart">我的购物车</a>
    </div>

</header>

<!-- nav 导航栏区域 -->
<nav>
    <div class="w">
        <div class="sk_list">
            <ul>
                <li><a href="queryGoodsServlet?category=t恤">T恤</a></li>

            </ul>
        </div>
        <div class="sk_con">
            <ul>
                <li><a href="queryGoodsServlet?category=卫衣">卫衣</a></li>
                <li><a href="queryGoodsServlet?category=衬衫">衬衫</a></li>
                <li><a href="queryGoodsServlet?category=裤子">裤子</a></li>
                <li><a href="queryGoodsServlet?category=外套">外套</a></li>
                <li><a href="${pageContext.request.contextPath}/GoKillController">品优秒杀</a></li>
                <br>
            </ul>
        </div>
    </div>
</nav>
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
</body>
<script>
    function onDialog() {
        $(".dialog").show();//隐藏状态
        $(".dialog-close").click(function () {
            $(".dialog").hide()
        })
    }
</script>

