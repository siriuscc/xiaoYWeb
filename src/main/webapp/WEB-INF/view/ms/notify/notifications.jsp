<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<html lang="en">

<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76"
          href="${pageContext.request.contextPath}/public/assets/img/apple-icon.png"/>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/public/assets/img/favicon.png"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>通知</title>


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
                    <a class="navbar-brand" href="#"> 通知 </a>
                </div>

                <%--工具栏--%>
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/view/ms/common/toolbar.jsp"></jsp:include>

        </nav>
        <div class="content">
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header" data-background-color="blue">
                        <h4 class="title">近期异常</h4>
                        <p class="category">只维护最近20个异常，详细异常请前往服务器
                            <a target="_blank" href="${pageContext.request.contextPath}/ms/notify/getNotify.do"> 查看</a>.
                        </p>
                    </div>
                    <div class="card-content">
                        <div class="row">
                            <div class="col-md-8">
                                <h5>近期异常...</h5>

                                <c:forEach items="${exceptionList}" var="exp">
                                    <div class="alert alert-info">
                                        <span>${exp.name} |
                                                 <fmt:formatDate value="${exp.date}" type="both"/>
                                        </span>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <br>
                        <br>
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


<script>

    $(document).ready(function () {

        $(".sidebar-wrapper ul li").removeClass("active");
        $(".sidebar-wrapper ul li:nth-child(5)").attr("class", "active");
    })
</script>


</html>