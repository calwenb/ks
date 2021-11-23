<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单统计</title> <%--标题--%>
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
</head>
<body>
<!-- 导航条 -->
<%@ include file="../common/background/head.jsp" %>
<!-- 中间部分（包括左边栏和员工/订单表单显示部分） -->
<div class="hrms_body" style="position:relative; top:-15px;">

    <!-- 左侧栏 -->
    <%@ include file="../common/background/leftsidebar.jsp" %>
    <div class="emp_info col-sm-10">
        <div class="panel panel-info">
            <!-- 路径导航 -->
            <div class="panel-heading">
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/OrderQueryController">订单首页</a></li>
                    <li>订单统计</li>
                </ol>
            </div>
            <%--在这里写内容--%>
            <div style="padding-left: 3%;">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <div class="page-header">
                            <h1>
                                <small>订单统计</small>
                            </h1>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 3%;">
                    <%--这里--%>
                    <div style="padding-left: -4%;padding-bottom: 10%;">
                        <h4>月份订单量</h4>
                        <p>当年总订单量: <span id="yearCount">0</span> 单</p>
                        <p>当年总营业额: <span id="yearAmount">0</span> 元</p>
                        <hr style="background-color: skyblue;">
                        <div id="year" style="width:800px;height:600px;margin:0px auto;"></div>
                        <h4>当月订单量</h4>
                        <p>当月总订单量: <span id="monthCount">0</span> 单</p>
                        <p>当年总营业额: <span id="monthAmount">0</span> 元</p>
                        <hr style="background-color: skyblue;">
                        <div id="month" style="width:800px;height:600px;margin:0px auto;"></div>
                        <script type="text/javascript">
                            var mychart = echarts.init(document.getElementById("year"));
                            var mychart2 = echarts.init(document.getElementById("month"));
                            $.ajax({
                                url: "${pageContext.request.contextPath}/OrderCountController",
                                data: {},
                                success: function (data) {
                                    console.log(data)
                                    data = JSON.parse(data)
                                    x_data = JSON.parse(data.year)
                                    month_x = JSON.parse(data.month_x)
                                    month_y = JSON.parse(data.month_y)
                                    yearAmount = data.yearAmount
                                    monthAmount = data.monthAmount
                                    $('#yearAmount').text(yearAmount);
                                    $('#monthAmount').text(monthAmount);
                                    setOption(x_data)
                                    setOption2(month_x, month_y)
                                    console.log(month_x, month_y)
                                    var count = 0
                                    for (let i = 0; i < x_data.length; i++) {
                                        count += x_data[i]
                                    }
                                    $('#yearCount').text(count);
                                    count = 0;
                                    for (let i = 0; i < month_x.length; i++) {
                                        count += month_y[i]
                                    }
                                    $('#monthCount').text(count);
                                },

                                error: function (msg) {
                                    console.log("no")
                                    console.log(msg)
                                }
                            });


                            function setOption(x_data) {
                                var open = {
                                    xAxis: {
                                        type: 'category',
                                        boundaryGap: true,
                                        data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
                                    },
                                    yAxis: {
                                        type: 'value'
                                    },
                                    series: [
                                        {
                                            name: '月份订单量',
                                            type: 'line',
                                            stack: '总量',
                                            data: x_data,
                                            itemStyle: {
                                                normal: {
                                                    label: {
                                                        show: true, //开启显示
                                                        position: 'top', //在上方显示
                                                        textStyle: { //数值样式
                                                            color: 'black',
                                                            fontSize: 16
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    ]

                                };
                                mychart.setOption(open)
                            }

                            function setOption2(x_data, y_data) {
                                var open = {
                                    xAxis: {
                                        type: 'category',
                                        boundaryGap: true,
                                        data: x_data
                                    },
                                    yAxis: {
                                        type: 'value'
                                    },
                                    series: [
                                        {
                                            name: '月份订单量',
                                            type: 'bar',
                                            barWidth: 10,
                                            stack: '总量',
                                            data: y_data,
                                            itemStyle: {
                                                normal: {
                                                    label: {
                                                        show: true, //开启显示
                                                        position: 'top', //在上方显示
                                                        textStyle: { //数值样式
                                                            color: 'black',
                                                            fontSize: 16
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    ]

                                };
                                mychart2.setOption(open)
                            }
                        </script>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>