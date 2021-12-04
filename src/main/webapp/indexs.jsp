<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>


<script language="javascript">
    function check() {
        if (forml.username.value == "") {
            alert("请输入用户名!");
            forml.username.focus();
            return false;
        }
    }
</script>
<form name="forml" method="post" action="logins.jsp" noSubmit="return check()">
    用户名:<input type="text" name="username" value="${sessionScope.LOGIN_USER.loginName}">
    <input type="submit" name="imageField" value="登录">
</form>
</body>
</html>