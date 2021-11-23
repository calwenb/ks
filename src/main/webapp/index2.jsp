<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <style type="text/css">
        body {
            font-size: 12px;
            margin: 0px
        }

        header {
            background-color: #E89C9D;
            height: 148px;
        }

        section {
            width: 689px;
            margin: 0 auto auto auto;
            clear: both;
        }

        #main {
            padding: 10px;
            height: 217px;
            background-color: #69966E;
        }

        footer {
            height: 56px;
            background-image: url(images/beijin.jpg);
            padding-top: 30px;
            padding-left: 50;
        }

        a {
            height: 425px;
            width: 500px;
            background-image: url(images/beijin.jpg);
        }
    </style>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div id="a">
    <header></header>
    <div id="main">
        <div id="content" style="heighe: 206px; overflow: hidden">欢迎光临汽车之家</div>
    </div>
    <footer>
        <input name="user" type="text" id="user" size="20">说 <input
            nema="speak" type="text" id="speak" size="50"> &nbsp;<input
            id="send" type="button" value="发送">
    </footer>
</div>
<script type="text/javascript">
    $(document).ready(function () {
            $("#send").click(function () {
                var user = $("#user").val();
                var speak = $("#speak").val()
                if (user == "") {
                    alert("请输入您的昵称！");
                }
                if (speak == "") {
                    $("#speak").focus();
                }
                $.post("ChatServlet?action=send", {
                    user: user,
                    speak: speak
                });

                function getContent() {
                    var time = new Date().getTime();
                    $.ajax({
                        url: 'ChatServlet',
                        type: 'get',
                        data: {
                            "action": "get",
                            "nocache": time
                        },
                        success: function (data) {
                            $("#content").html(data);
                        },
                        error: function (data) {
                            console.log(data)
                        }
                    })
                }

                //读取聊天内容
                getContent();
                window.setInterval(function () {
                    getContent();
                }, 5000);
            });
        }
    )
</script>
</body>
</html>