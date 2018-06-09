<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html lang="en">

<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76"
          href="${pageContext.request.contextPath}/public/assets/img/apple-icon.png"/>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/public/assets/img/favicon.png"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Material Dashboard by Creative Tim</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>
    <!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/public/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <!--  Material Dashboard CSS    -->
    <link href="${pageContext.request.contextPath}/public/assets/css/material-dashboard.css?v=1.2.0" rel="stylesheet"/>
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/public/assets/css/demo.css" rel="stylesheet"/>
    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet'
          type='text/css'>


    <style>
        .pagination li a{
            border-radius: 0 !important;
            background-color: #fff !important;
            border: 1px solid #ddd !important;
            padding: 6px 12px !important;
            margin-left: -1px !important;
            color: #337ab7 !important;

            line-height: 1.42857143 !important;
        }

    </style>
</head>

<body>
<div class="wrapper">


    <!-- 菜单 -->
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/view/ms/common/menu.jsp"></jsp:include>


    <div class="main-panel">
        <nav class="navbar navbar-transparent navbar-absolute">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"> 任务列表 </a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="material-icons">dashboard</i>
                                <p class="hidden-lg hidden-md">Dashboard</p>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="material-icons">notifications</i>
                                <span class="notification">5</span>
                                <p class="hidden-lg hidden-md">Notifications</p>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Mike John responded to your email</a>
                                </li>
                                <li>
                                    <a href="#">You have 5 new tasks</a>
                                </li>
                                <li>
                                    <a href="#">You're now friend with Andrew</a>
                                </li>
                                <li>
                                    <a href="#">Another Notification</a>
                                </li>
                                <li>
                                    <a href="#">Another One</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="material-icons">person</i>
                                <p class="hidden-lg hidden-md">Profile</p>
                            </a>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="form-group  is-empty">
                            <input type="text" class="form-control" placeholder="Search">
                            <span class="material-input"></span>
                        </div>
                        <button type="submit" class="btn btn-white btn-round btn-just-icon">
                            <i class="material-icons">search</i>
                            <div class="ripple-container"></div>
                        </button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header" data-background-color="blue">
                                <h4 class="title">任务列表</h4>
                                <p class="category">用户一共发布了${page.totalRecord} 个任务</p>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th>标题</th>
                                    <th>内容</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>提醒时间</th>
                                    <th>删除</th>
                                    </thead>
                                    <tbody>


                                    <c:forEach items="${page.data}" var="task" varStatus="vs">
                                        <tr>
                                            <td>${task.title}</td>
                                            <td>${task.content}</td>
                                            <td>
                                                <fmt:formatDate value="${task.startTime}" type="both"/>
                                            </td>
                                            <td class="text-primary">
                                                <fmt:formatDate value="${task.endTime}" type="both"/>
                                            </td>

                                            <td>${task.delayMin} min </td>
                                            <td>
                                                <button class="btn btn-info" onclick="javascript:removeTask(${task.taskId})">删除</button>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <nav aria-label="Page navigation" style="text-align: center">
                            <ul class="pagination">

                                <c:if test="${page.pageNum+0 >1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/ms/user/tasklist.do?userId=${user.userId}&pageNum=${page.pageNum-1 }" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>

                                <c:forEach begin="${page.start }" end="${page.end }" var="v">
                                    <li><a href="${pageContext.request.contextPath}/ms/user/tasklist.do?userId=${user.userId}&pageNum=${v}">${v}</a></li>
                                </c:forEach>


                                <c:if test="${page.pageNum+0<page.totalPage}">

                                    <li>
                                        <a href="${pageContext.request.contextPath}/ms/user/tasklist.do?userId=${user.userId}&pageNum=&pageNum=${page.pageNum+1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>

                                </c:if>

                            </ul>
                        </nav>
                    </div>

                    <div class="col-md-4">
                        <div class="card card-profile">
                            <div class="card-avatar">
                                <a href="#pablo">
                                    <img class="img" src="${pageContext.request.contextPath}/public/assets/img/faces/headImg.png"/>
                                </a>
                            </div>
                            <div class="content">
                                <h6 class="category text-gray">${user.email}</h6>
                                <h4 class="card-title">${user.username}</h4>
                                <p class="card-content">
                                    ${user.phone}
                                </p>
                                <a href="javascript:removeTask()" class="btn btn-primary btn-round">删除</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 底部导航 -->
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/view/ms/common/footer.jsp"></jsp:include>


    </div>
</div>
</body>
<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/public/assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/assets/js/material.min.js" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="${pageContext.request.contextPath}/public/assets/js/chartist.min.js"></script>
<!--  Dynamic Elements plugin -->
<script src="${pageContext.request.contextPath}/public/assets/js/arrive.min.js"></script>
<!--  PerfectScrollbar Library -->
<script src="${pageContext.request.contextPath}/public/assets/js/perfect-scrollbar.jquery.min.js"></script>
<!--  Notifications Plugin    -->
<script src="${pageContext.request.contextPath}/public/assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Material Dashboard javascript methods -->
<script src="${pageContext.request.contextPath}/public/assets/js/material-dashboard.js?v=1.2.0"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="${pageContext.request.contextPath}/public/assets/js/demo.js"></script>
<script src="${pageContext.request.contextPath}/public/assets/js/jquery.ext.js"></script>



<script>

    $(document).ready(function () {

        $(".sidebar-wrapper ul li").removeClass("active");
        $(".sidebar-wrapper ul li:nth-child(3)").attr("class", "active");
    });


    function removeTask(taskId){

        var res=confirm("是否删除？");


        if(res){

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/ms/user/removeTask.do",
                data: "taskId="+taskId,
                async:false,
                success: function(msg){

                    if(parseInt(msg)>0){

                        alert("删除成功");
                        window.location.href=window.location.href+"&0.11234"
                    }else{
                        alert("删除失败");
                    }

                },
                error:function (msg) {
                    alert("删除失败");
                }
            })
        }else{
            alert("删除失败");
        }
    }


    function removeTask(){

        var userId=$.getUrlParam("userId");


        var res=confirm("是否删除用户？");
        if(res){

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/ms/user/removeUser.do",
                data: "userId="+userId,
                async:false,
                success: function(msg){

                    if(parseInt(msg)>0){

                        alert("删除成功");
                        window.location.href="${pageContext.request.contextPath}/ms/user/userlist.do";
                    }else{
                        alert("删除失败");
                    }

                },
                error:function (msg) {
                    alert("删除失败");
                }
            })

        }else{
            alert("删除失败");
        }



    }

</script>


</html>