<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>后台管理系统</title> <%--标题--%>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<script>
    $(function () {
        window.location.href = "${pageContext.request.contextPath}/queryGoodsServlet"
    })
</script>
</body>
</html>