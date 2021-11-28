<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
</head>

<%@ include file="/common/index_head.jsp" %>

<div class="com_list w">
    <div class="com_list-bd">
        <ul class="clearfix">
            <c:choose>
                <c:when test="${SKGoodses==null||SKGoodses.size()==0||SKTime==null}">
                    <h3 style="text-align: center;">还没有商品,参加秒杀活动</h3>
                </c:when>
                <c:otherwise>
                    <h2 style="text-align: center;">秒杀时间:${SKTime}:00&nbsp;&nbsp;||&nbsp;&nbsp;当前时间:<span id="now_time">0000-00-00 00:00:00</span>
                    </h2>
                    <c:forEach var="Goods" items="${SKGoodses}">
                        <li>
                            <a href="#">
                                <img src="${Goods.getImgurl()}" alt="" width="300px" height="300px">
                            </a>
                            <a href="">
                                <h3>${Goods.getName()} </h3>
                            </a>
                            <a href="" class="price">
                                ￥${Goods.getPrice()}
                            </a>
                            <button onclick="start_spike(${Goods.getId()});" class="buy">点击秒杀</button>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

<script>
    function start_spike(SKill_id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/sKillController",
            type: 'post',
            data: {"goodId": SKill_id},
            success: function (msg) {
                alert(msg);
            },
            error: function (msg) {
                alert(msg);
            }
        })
    }

    $(function () {
        window.setInterval(
            "$('#now_time').load('info',{})",
            500
        )
    })
</script>
</body>
</html>