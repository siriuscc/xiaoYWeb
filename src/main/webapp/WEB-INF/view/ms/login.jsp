<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户登录</title>
    <!-- Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <style>


        .container {
            position: relative;
        }

        .login-box {

            float: none;
            border: 1px solid #ccc;
            border-radius: 6px;

            padding-top: 20px;
            padding-bottom: 30px;

            margin: 50px auto;

        }

        .row {
            margin: 0 auto 10px;
        }

        .login-box input {

            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }



    </style>
</head>

<body>


<div class="container">

    <div class="col-sm-4 login-box">

        <h3 class="col-sm-8 col-sm-offset-2">小Y 后台管理系统</h3>

        <div class="row"></div>

        <form action="${pageContext.request.contextPath}/ms/login.do">
            <div class="row">
                <input class="col-sm-8 col-sm-offset-2" name="name" type="text" placeholder="用户名">
            </div>

            <div class="row">
                <input class="col-sm-8 col-sm-offset-2" type="password" name="passwd" placeholder="密码">
            </div>
            <div class="row">
                <button class="btn btn-success col-sm-4 col-sm-offset-4" type="submit">登录</button>
            </div>

        </form>
    </div>
</div>


</body>


</html>