<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <script language="javascript" src="js/AjaxRequest.js">
    </script>
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <script language="javascript">
        window.setInterval("showOnline();", 1000);

        function showOnline() {
            var loader = new net.AjaxRequest("online.jsp?nocache=" +
                new Date().getTime(), deal_online, onerror, "GET");
        }

        function onerror() {
            alert("很抱歉，服务器出现错误，当前窗口将关闭！");
            window.opener = null;
            window.close();
        }

        function deal_online() {
            online.innerHTML = this.req.responseText;
        }


        function set(selectPerson) {
            console.log("set");
            if (selectPerson != "<%=session.getAttribute("username") %>") {
                form.tempuser.value = selectPerson;
            } else {
                alert("请重新选择客服对象!");
            }
        }

        //调用对象的函数
        window.setInterval("showContent();", 1000);

        function showContent() {
            var loader1 = new net.AjaxRequest("content.jsp?nocache=" +
                new Date().getTime(), deal_content, onerror, "GET");
        }

        function deal_content() {
            content.innerHTML = this.req.responseText;
        }

        function Exit() {
            window.location.href = "leave.jsp";
            alert("欢迎您的光临！");
        }


        window.onbeforeunload = function () {
            if (event.clientY < 0 && event.clientX > document.body.scrollWidth) {
                Exit();
            }
        }
    </script>
    <style>
        html {
            height: 50%;
        }

        body {
            background-image: url("images/bejin.jpg");
            background-position: center;
            background-size: cover;
        }

    </style>
</head>
<body>
<p align="center">
    当前时间:<span id="now_time">0000-00-00 00:00:00</span>
<h1 align="center">欢迎来到客服聊天室</h1></p>
<table width="800" height="350" border="0" align="center" cellpadding="0">
    <tr>
        <td width="165" valign="top" bgcolor="#FDF7E9" id="online" fstyle="padding:5px">在线人员列表</td>
        <td width="613" valign="top" bgcolor="#FFFFFF" id="content" style="padding:5px">聊天内容</td>
    </tr>
</table>
<form action="send.jsp" name="form" method="post" align="center" onSubmit="return check()">
    [${sessionScope.LOGIN_USER.loginName}]对
    <input name="tempuser" type="text" size="35" readonly="readonly">
    表情
    <select name="select" class="wenbenkuang">
        <option value="无表情的">无表情的</option>
        <option value="微笑着" selected>微笑着</option>
        <option value="笑呵呵地">笑呵呵地</option>
    </select>
    说:
    字体颜色:
    <select name="color" size="1" class="wenbenkuang" id="select">
        <option selected>默认颜色</option>
        <option style="color:#FFOOOO" value="#FFOOOO">红色热情</option>
        <option style="color:#OOOOFF" value="#OOOOff">蓝色开朗</option>
        <option style="color:#999999" value="999999">烟雨蒙蒙</option>
    </select><br>
    <input name="message" type="text" size="70">
    <input name="Submit2" type="submit" class="btn_blank" value="发送"></td>
    <input name="button_exit" type="button" class="btn_orange" value="退出" onClick="Exit()">
</form>
<form action="a" method="post" enctype="multipart/form-data" align="center">
    表情包图片文件上传：<input type="file" name="uploadFile">
    <input type="submit" value="上传">
</form>

<c:forEach var="gif" items="${gifs}">
    <img src="${gif.getBiaoqingbao()}" width="100px" height="100px">
</c:forEach>
<input type="hidden" id="first" value="${first}">

</body>
<script>
    $(function () {
        if ($('#first').val() != '1') {
            window.location.href = "ChaServlet"
        }
    })
</script>
<script>
    $(function () {
        window.setInterval(
            "$('#now_time').load('info',{})",
            500
        )
    })
</script>
</html>