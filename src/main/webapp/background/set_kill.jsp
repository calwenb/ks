<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<!-- 导航条 -->
<%@ include file="../common/background/head.jsp" %>

<div style="position:relative; top:-15px;">

    <!-- 左侧栏 -->
    <%@ include file="../common/background/leftsidebar.jsp" %>
    <div class="emp_info col-sm-10">
        <div class="panel panel-info">
            <!-- 路径导航 -->
            <div class="panel-heading">
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/OrderQueryController">订单首页</a></li>
                    <li>秒杀设置</li>
                </ol>
            </div>
            <%--在这里写内容--%>
            <div>
                <div class="row clearfix" style="padding-left: 3%;">
                    <div class="col-md-12 column">
                        <div class="page-header">
                            <h1>
                                <small>秒杀设置</small>
                            </h1>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 3%;padding-bottom: 10%;">
                    <div>
                        商品ID:<input type="text" id="goods_id"/><br><br>
                        数量:<input type="text" id="kill_num"/><br><br>
                        <button onclick="set_kill();">添加</button><br><br>
                        秒杀开始时间:<input type="datetime-local" id="kill_time"/><br><br>
                        <button onclick="start_kill();">开始秒杀</button>
                        <button onclick="clear_kill();">重置秒杀</button>
                    </div>
                </div>
                <script>
                    function set_kill() {
                        var goodsId = $('#goods_id').val();
                        var killNum = $('#kill_num').val();
                        $.ajax({
                            url: "${pageContext.request.contextPath}/SetSKillController",
                            type: 'get',
                            data: {
                                'goodsId': goodsId,
                                'killNum': killNum,
                            },
                            success: function (msg) {
                                alert(msg);
                            },
                            error: function (msg) {
                                alert(msg);
                            }
                        })
                    }

                    function start_kill() {
                        var killTime = $('#kill_time').val();
                        $.ajax({
                            url: "${pageContext.request.contextPath}/SetSKillController",
                            type: 'get',
                            data: {
                                "killTime": killTime
                            },
                            success: function (msg) {
                                alert(msg);
                            },
                            error: function (msg) {
                                alert(msg);
                            }
                        })
                    }

                    function clear_kill() {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/ClearRedisController",
                            type: 'get',
                            success: function (msg) {
                                alert(msg);
                            },
                            error: function (msg) {
                                alert(msg);
                            }
                        })
                    }
                </script>
            </div>
        </div>
    </div>
</div>


</body>
</html>
