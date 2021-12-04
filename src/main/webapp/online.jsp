<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" import="java.util.*" %>
<%@ page import="wen.pojo.User" %>
<% Vector myuser = (Vector) application.getAttribute("myuser"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>


<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="32" align="center" class="word_orange">欢迎顾客来的客服室！</td>
    </tr>
    <tr>
        <td height="23" align="center"><a href="#" onclick="set('所有人') ">所有人</a></td>
    </tr>
    <% for (int i = 0; i < myuser.size(); i++) {
        User mylist = (User) myuser.elementAt(i);%>
    <tr>
        <td height="23" align="center">
            <a href="#" onclick="set('<%=mylist.getLoginName()%>');"><%=mylist.getLoginName() %>></a></td>
    </tr>
    <%} %>
    <tr>
        <td height="30" align="center">当前在线[<font color="#FF6600">
            <%=myuser.size() %>
        </font>]人
        </td>
    </tr>
</table>

</body>
</html>