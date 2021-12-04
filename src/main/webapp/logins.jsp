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
    request.setCharacterEncoding("gb2312");
    String username = request.getParameter("username");
    boolean flag = true;
    Vector temp = (Vector) application.getAttribute("myuser");
    if (application.getAttribute("myuser") == null) {
        temp = new Vector();
    }

//判断用户是否在线
    for (int i = 0; i < temp.size(); i++) {
        User tempuser = (User) temp.elementAt(i);
        if (tempuser.getLoginName().equals(username)) {
            out.println("<script language='javascript'>alert('该用户已经登录');window.location.href='index.jsp';</script>");
            flag = false;
        }
    }
    User mylist = new User();
    mylist.setLoginName(username);


    session.setAttribute("username", username);//保存当前登入的用户名
    application.setAttribute("ul", username);
    Vector myuser = (Vector) application.getAttribute("myuser");


    if (myuser == null) {  //当第一位用户登入时
        myuser = new Vector();
    }
    if (flag) {          //当输入的第一位用户不存在时,将该用户添加到在线人员列表中
        myuser.addElement(mylist);
    }
    application.setAttribute("myuser", myuser);
    response.sendRedirect("main.jsp");  //重新向页面到聊天室主页面
%>
</body>
</html>