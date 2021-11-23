<%@ page import="wen.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表-综合网购首选-正品低价、品质保障、配送及时、轻松购物！</title>
    <!-- 网站说明 -->

    <meta name="keywords" content="网上购物,网上商城,手机,笔记本,电脑,MP3,CD,VCD, DV,相机,数码,配件,手表,存储卡,京东">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="/favicon.ico">
    <!-- 引入 css 初始化文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 引入 css 公共样式文件 -->
    <link rel="stylesheet" href="css/common.css">
    <!-- 引入 css list文件 -->
    <link rel="stylesheet" href="css/list.css">
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog.css">
</head>

<body>
<!-- 快捷导航模块 -->
<section class="shortcut">
    <div class="w">
        <div class="fl">
            <ul>
                <li>
                    ${sessionScope.LOGIN_USER.userName}&nbsp;&nbsp;你好!
                </li>

                <li>&nbsp;品优购欢迎您！&nbsp;</li>
                <li>
                    <%
                        User user = (User) session.getAttribute("LOGIN_USER");
                        if (user == null) {
                            out.print("&nbsp;<a href='login.jsp' >请登录</a> " +
                                    "&nbsp;&nbsp;<a href='register.jsp' >免费注册</a>");
                        } else {
                            out.print(" &nbsp;&nbsp;<a href='OutLoginController' >退出登录</a>");
                        }
                    %>
                    &nbsp;
                </li>


            </ul>
        </div>
        <div class="fr">
            <ul>
                <li><a href="${pageContext.request.contextPath}/myOrderController">我的订单</a></li>
                <li></li>
                <li class="arrow-icon"><a href="#">我的信息</a></li>

                <li></li>
                <%--<li class="arrow-icon"><a href="#">客户服务</a></li>--%>
                <%
                    if (user != null) {
                        out.print("<li ><a onclick='onDialog();'>更改密码</a></li>");
                    }
                %>


            </ul>
        </div>
    </div>
</section>

<!-- header 头部区域 -->
<header class="w">
    <div class="logo">
        <h1>
            <a href="index.jsp" title="品优购商城">品优购商城</a>
        </h1>
    </div>
    <div class="search">
        <input type="search" name="" id="" placeholder="语言开发">
        <button>搜索</button>
    </div>

    <div class="shopcar">
        <a href="#">我的购物车</a>
        <i class="count">8</i>
    </div>
    <!-- <div class="seckill">
        <img src="images/seckill.png" alt="">
    </div> -->
</header>

<!-- nav 导航栏区域 -->
<nav>
    <div class="w">
        <div class="sk_list">
            <ul>
                <li><a href="${pageContext.request.contextPath}/GoKillController">品优秒杀</a></li>

            </ul>
        </div>
        <div class="sk_con">
            <ul>
                <li><a href="#">女装</a></li>
                <li><a href="#">女鞋</a></li>
                <li><a href="#">男装</a></li>
                <li><a href="#">男鞋</a></li>
                <li><a href="#">母婴童装</a></li>
                <li><a href="#">食品</a></li>
                <li><a href="#">智能数码</a></li>
                <li><a href="#">运动户外</a></li>
                <li><a href="#">更多分类</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- 商品列表 -->
<!-- com_list  commodity_list -->
<div class="com_list w">
    <!-- <div class="com_list-hd">
        <img src="upload/com_hd.png" alt="">
    </div> -->
    <div class="com_list-bd">
        <ul class="clearfix">
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
            <li>
                <a href="#">
                    <img src="images/goods/com_01.png" alt="">
                </a>
                <a href="">
                    <h4>Apple苹果iPhone 6s Plus（A1699）32G 金<br>色 移动联通电信4G手机</h4>
                </a>
                <a href="" class="price">
                    ￥6088
                    <em>￥6988</em>
                </a>
                <div class="sold">
                    已售87%
                    <div class="cylinder">
                    </div>
                    剩余<i>29</i>件
                </div>
                <a href="#" class="buy">立即抢购</a>
            </li>
        </ul>
    </div>
</div>

<!-- 底部模块 -->
<!-- <footer>
    <div class="w">
        <div class="mod_service">
            <ul>
                <li>
                    <a href="#">
                        <h5></h5>
                        <div>
                            <h4>正品保障</h4>
                            <p>正品保障，提供发票</p>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <h5></h5>
                        <div>
                            <h4>极速物流</h4>
                            <p>急速物流，急速送达</p>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <h5></h5>
                        <div>
                            <h4>无忧售后</h4>
                            <p>7天无理由退换货</p>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <h5></h5>
                        <div>
                            <h4>特色服务</h4>
                            <p>私人订制家电套餐</p>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <h5></h5>
                        <div>
                            <h4>帮助中心</h4>
                            <p>您的购物指南</p>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <div class="mod_help">
            <dl>
                <dt><a href="#">服务指南</a></dt>
                <dd><a href="#">购物流程</a></dd>
                <dd><a href="#">会员介绍</a></dd>
                <dd><a href="#">生活旅行/团购</a></dd>
                <dd><a href="#">常见问题</a></dd>
                <dd><a href="#">大家电</a></dd>
                <dd><a href="#">联系客服</a></dd>
            </dl>
            <dl>
                <dt><a href="#">配送方式</a></dt>
                <dd><a href="#">上门自提</a></dd>
                <dd><a href="#">211限时达</a></dd>
                <dd><a href="#">配送服务查询</a></dd>
                <dd><a href="#">配送费收取标准</a></dd>
                <dd><a href="#">海外配送</a></dd>
            </dl>
            <dl>
                <dt><a href="#">支付方式</a></dt>
                <dd><a href="#">货到付款</a></dd>
                <dd><a href="#">在线支付</a></dd>
                <dd><a href="#">分期付款</a></dd>
                <dd><a href="#">邮局汇款</a></dd>
                <dd><a href="#">公司转账</a></dd>
            </dl>
            <dl>
                <dt><a href="#">售后服务</a></dt>
                <dd><a href="#">售后政策</a></dd>
                <dd><a href="#">价格保护</a></dd>
                <dd><a href="#">退款说明</a></dd>
                <dd><a href="#">返修/退换货</a></dd>
                <dd><a href="#">取消订单</a></dd>
            </dl>
            <dl>
                <dt><a href="#">特色服务</a></dt>
                <dd><a href="#">夺宝岛</a></dd>
                <dd><a href="#">DIY装机</a></dd>
                <dd><a href="#">延保服务</a></dd>
                <dd><a href="#">品优购E卡</a></dd>
                <dd><a href="#">品优购通信</a></dd>
            </dl>
            <dl>
                <dt><a href="#">帮助中心</a></dt>
                <dd>
                    <h5><img src="images/qr_code.png" alt=""></h5>
                </dd>
                <dd><a href="#">品优购客户端</a></dd>
            </dl>
        </div>
        <div class="mod_copyright">
            <div class="links">
                <ul>
                    <li><a href="#">关于我们</a></li>
                    <li>|</li>
                    <li><a href="#">联系我们</a></li>
                    <li>|</li>
                    <li><a href="#">联系客服</a></li>
                    <li>|</li>
                    <li><a href="#">商家入驻</a></li>
                    <li>|</li>
                    <li><a href="#">营销中心</a></li>
                    <li>|</li>
                    <li><a href="#">手机品优购</a></li>
                    <li>|</li>
                    <li><a href="#">友情链接</a></li>
                    <li>|</li>
                    <li><a href="#">销售联盟</a></li>
                    <li>|</li>
                    <li><a href="#">品优购社区</a></li>
                    <li>|</li>
                    <li><a href="#">品优购公益</a></li>
                    <li>|</li>
                    <li><a href="#">English Site</a></li>
                    <li>|</li>
                    <li><a href="#">Contact U</a></li>
                </ul>
            </div>
            <div class="copyright">
                地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096 电话：400-618-4000 传真：010-82935100 邮箱: zhanghj+itcast.cn
                <br>京ICP备08001421号京公网安备110108007702
            </div>
        </div>
    </div>
</footer> -->
<div class="dialog">
    <button class="dialog-close">X</button>
    <div class="dialog-body">
        <div class="ui-dialog-content">
            <p class="common-tips-tit">更改密码</p>
            <form action="${pageContext.request.contextPath}/UpdateUserController" method="get">
                <div class="form-group">
                    <label for="pwd">密码:</label>
                    <input type="password" class="form-control" id="pwd" name="password">
                </div>
                <input type="submit" class="btn btn-default"/>
            </form>
        </div>
    </div>
</div>

</body>
<script>
    function onDialog() {
        $(".dialog").show();//隐藏状态
        $(".dialog-close").click(function () {
            $(".dialog").hide()
        })
    }
</script>
</html>