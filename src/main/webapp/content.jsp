<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<%
    if (session.getAttribute("username").equals("null")) {
        out.print("<script language='javascript'>alert('你还没有登录不能进入本聊天室');parent.location.href='login.html';</script>");
    }
    if (session.getAttribute("username").equals("request.getParameter(" + request.getParameter("tempuser") + ")")) {
        out.print("<script language='javascript'>alert('请重新选择聊天对象');</script>");
    }
    String message = request.getParameter("message");
    String select = request.getParameter("select");
    String tempuser = request.getParameter("tempuser");
    String color = request.getParameter("color");
    if (message != null && tempuser != null) {
        if (message.startsWith("<")) {
            out.print("<marquee direction='left' scrollamount='23'>" +
                    "<font color='blue'>" + "请不要输入带标记的特殊符号" + "</font>" + "</marquee>");
            return;
        } else if (message.endsWith(">")) {
            out.print("<marquee direction='left' scrollamount='25'>" +
                    "<font color='blue'>" + "请不要输入带标记的特殊符号" + "</font>" + "</marquee>");
            return;
        }
        if (application.getAttribute("message") == null) {
            application.setAttribute("message", "<br>" + "<font color='blue'>" +
                    "<strong>" + session.getAttribute("username") + "</strong>" + "</font>" +
                    "<font color='#CCOOOO'>" + select + "</font>" + "对" + "<font color='green'>" + "[" + tempuser + "]" + "</font>" + "说:" + "<font color=" + color + ">" + message);
        } else {
            application.setAttribute("message", "<br>" + "<font color='blue'>" +
                    "<strong>" + session.getAttribute("username") + "</strong>" + "</font>" +
                    "<font color='#CCOOOO'>" + select + "</font>" + "对" + "<font color='green'>" + "[" + tempuser + "]" + "</font>" + "说:" + "<font color=" + color + ">" + message +
                    "</font>" + application.getAttribute("message")
            );
        }
        out.println("<p>" + application.getAttribute("message") + "</p>");
    } else {
        if (application.getAttribute("message") == null) {
            out.println("<font color='#ccOOOO'>" + application.getAttribute("ul") +
                    "</font>" + "<font color='green'>" + "走进了客服聊天室" + "</font>");
            out.println("<br>" + "<center>" + "<font color='#aaOOOO'>" + "请客服注意聊天信息发送，谢谢" + "</font>" + "</center>");
        } else {
            out.println(application.getAttribute("message") + "<br>");
        }
    }

%>
</body>
</html>