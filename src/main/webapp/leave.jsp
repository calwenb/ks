<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ page import="java.util.Vector" %>
<%@ page import="wen.pojo.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<%
    Vector temp = (Vector) application.getAttribute("myuser");
    for (int i = 0; i < temp.size(); i++) {
        User mylist = (User) temp.elementAt(i);
        if (mylist.getLoginName().equals(session.getAttribute("username"))) {
            temp.removeElementAt(i);
            session.setAttribute("username", "null");
        }
        if (temp.size() == 0) {
            application.removeAttribute("username");
        }
    }
    response.sendRedirect("index.jsp");
%>
</body>
</html>