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
                    <a class="navbar-brand" href="#"> 主面板 </a>
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
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="card card-stats">
                            <div class="card-header" data-background-color="orange">
                                <i class="material-icons">content_copy</i>
                            </div>
                            <div class="card-content">
                                <p class="category">JVM空闲内存</p>
                                <h3 class="title">${runtimeBean.free}/${runtimeBean.total}
                                    <small>MB</small>
                                </h3>
                            </div>
                            <div class="card-footer">
                                <div class="stats">

                                    <i class="material-icons text-success">info</i>
                                    <a href="javascript:void(0);">
                                        空闲物理内存：
                                        <fmt:formatNumber type="number"
                                                          value="${operatingSystemMXBean.freePhysicalMemorySize/(1024*1024)}"
                                                          maxFractionDigits="0"/>
                                        /
                                        <fmt:formatNumber type="number"
                                                          value="${operatingSystemMXBean.totalPhysicalMemorySize/(1024*1024)}"
                                                          maxFractionDigits="0"/>GB
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="card card-stats">
                            <div class="card-header" data-background-color="green">
                                <i class="material-icons">store</i>
                            </div>
                            <div class="card-content">
                                <p class="category">CPU 负载</p>
                                <h3 class="title">
                                    <fmt:formatNumber type="number" value="${operatingSystemMXBean.systemCpuLoad*100}"
                                                      maxFractionDigits="1"/>%
                                </h3>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <i class="material-icons">date_range</i> 平均系统装载
                                    ${operatingSystemMXBean.systemLoadAverage};
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="card card-stats">
                            <div class="card-header" data-background-color="red">
                                <i class="material-icons">info_outline</i>
                            </div>
                            <div class="card-content">
                                <p class="category">Issues</p>
                                <h3 class="title">0</h3>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <%--local_offer--%>
                                    <i class="material-icons">update</i> Just Updated
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="card card-stats">
                            <div class="card-header" data-background-color="blue">
                                <i class="fa fa-twitter"></i>
                            </div>
                            <div class="card-content">
                                <p class="category">当前流量</p>
                                <h3 class="title">+${sessionCount}</h3>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <i class="material-icons">update</i> Just Updated
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--<div class="row">--%>
                <%--<div class="col-md-4">--%>
                <%--<div class="card">--%>
                <%--<div class="card-header card-chart" data-background-color="green">--%>
                <%--<div class="ct-chart" id="dailySalesChart"></div>--%>
                <%--</div>--%>
                <%--<div class="card-content">--%>
                <%--<h4 class="title">Daily Sales</h4>--%>
                <%--<p class="category">--%>
                <%--<span class="text-success"><i class="fa fa-long-arrow-up"></i> 55% </span> increase in today sales.</p>--%>
                <%--</div>--%>
                <%--<div class="card-footer">--%>
                <%--<div class="stats">--%>
                <%--<i class="material-icons">access_time</i> updated 4 minutes ago--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-4">--%>
                <%--<div class="card">--%>
                <%--<div class="card-header card-chart" data-background-color="orange">--%>
                <%--<div class="ct-chart" id="emailsSubscriptionChart"></div>--%>
                <%--</div>--%>
                <%--<div class="card-content">--%>
                <%--<h4 class="title">Email Subscriptions</h4>--%>
                <%--<p class="category">Last Campaign Performance</p>--%>
                <%--</div>--%>
                <%--<div class="card-footer">--%>
                <%--<div class="stats">--%>
                <%--<i class="material-icons">access_time</i> campaign sent 2 days ago--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-4">--%>
                <%--<div class="card">--%>
                <%--<div class="card-header card-chart" data-background-color="red">--%>
                <%--<div class="ct-chart" id="completedTasksChart"></div>--%>
                <%--</div>--%>
                <%--<div class="card-content">--%>
                <%--<h4 class="title">Completed Tasks</h4>--%>
                <%--<p class="category">Last Campaign Performance</p>--%>
                <%--</div>--%>
                <%--<div class="card-footer">--%>
                <%--<div class="stats">--%>
                <%--<i class="material-icons">access_time</i> campaign sent 2 days ago--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <div class="card card-nav-tabs">
                            <div class="card-header" data-background-color="blue">
                                <div class="nav-tabs-navigation">
                                    <div class="nav-tabs-wrapper">
                                        <span class="nav-tabs-title">Tasks:</span>
                                        <ul class="nav nav-tabs" data-tabs="tabs">
                                            <li class="active">
                                                <a href="#profile" data-toggle="tab">
                                                    <i class="material-icons">bug_report</i> GC
                                                    <div class="ripple-container"></div>
                                                </a>
                                            </li>
                                            <li class="">
                                                <a href="#messages" data-toggle="tab">
                                                    <i class="material-icons">code</i> GC 区状态
                                                    <div class="ripple-container"></div>
                                                </a>
                                            </li>
                                            <li class="">
                                                <a href="#settings" data-toggle="tab">
                                                    <i class="material-icons">cloud</i> 服务器
                                                    <div class="ripple-container"></div>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="card-content">
                                <div class="tab-content">
                                    <%--GC面板--%>
                                    <div class="tab-pane active" id="profile">
                                        <table class="table">
                                            <tbody>
                                            <tr>
                                                <th>count</th>
                                                <th>time</th>
                                                <th>name</th>
                                                <th>pool</th>
                                            </tr>

                                            <c:forEach var="gc" items="${gcmBeanList}" varStatus="vs">

                                                <tr>
                                                    <td> ${gc.collectionCount}</td>
                                                    <td> ${gc.collectionTime}</td>
                                                    <td class="td-actions text-right">${gc.name} </td>
                                                    <td>
                                                        <c:forEach items="${gc.memoryPoolNames}" var="pool">
                                                            ${pool}<br/>
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                                    <%--堆，非堆 信息--%>
                                    <div class="tab-pane" id="messages">
                                        <table class="table">
                                            <tbody>
                                            <tr>
                                                <th>area</th>
                                                <th>init</th>
                                                <th>used</th>
                                                <th>max</th>
                                            </tr>
                                            <tr>
                                                <td>heapMemory</td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${memoryMXBean.heapMemoryUsage.init/(1024*1024)}"
                                                            pattern="#.00"/>MB
                                                </td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${memoryMXBean.heapMemoryUsage.used/(1024*1024)}"
                                                            pattern="#.00"/>MB
                                                </td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${memoryMXBean.heapMemoryUsage.max/(1024*1024)}"
                                                            pattern="#.00"/>MB
                                                </td>
                                            </tr>


                                            <tr>
                                                <td>nonHeapMemory</td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${memoryMXBean.nonHeapMemoryUsage.init/(1024*1024)}"
                                                            pattern="#.00"/>MB
                                                </td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${memoryMXBean.nonHeapMemoryUsage.used/(1024*1024)}"
                                                            maxFractionDigits="1"/>MB
                                                </td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${memoryMXBean.nonHeapMemoryUsage.max/(1024*1024)}"
                                                            maxFractionDigits="1"/>MB
                                                </td>
                                            </tr>


                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="tab-pane" id="settings">
                                        <table class="table">
                                            <tbody>
                                            <tr>
                                                <td>系统</td>
                                                <td>${operatingSystemMXBean.name}</td>
                                            </tr>
                                            <tr>
                                                <td>架构</td>
                                                <td>${operatingSystemMXBean.arch}</td>
                                            </tr>
                                            <tr>
                                                <td>处理器</td>
                                                <td>${operatingSystemMXBean.availableProcessors}</td>
                                            </tr>
                                            <tr>
                                                <td> 进程</td>
                                                <td> ${runtimeMXBean.name}</td>
                                            </tr>
                                            <tr>
                                                <td>虚拟机</td>
                                                <td>${runtimeMXBean.vmName}</td>
                                            </tr>
                                            <tr>
                                                <td>启动时间</td>
                                                <td>
                                                    <jsp:useBean id="date" class="java.util.Date" />
                                                    <jsp:setProperty name="date" property="time" value="${runtimeMXBean.startTime}"/>
                                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                                    value="${date}" />
                                                </td>
                                            </tr>

                                            <tr >
                                                <td>启动参数</td>
                                                <td style="font-size: 10px">
                                                    <c:forEach items="${runtimeMXBean.inputArguments}" var="arg">
                                                        ${arg}<br/>
                                                    </c:forEach>
                                                </td>
                                            </tr>


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="card">
                            <div class="card-header" data-background-color="orange">
                                <h4 class="title">内存池</h4>
                                <p class="category">
                                    当前线程数${threadMXBean.threadCount}&nbsp;&nbsp;
                                    历史最大线程：${threadMXBean.peakThreadCount} &nbsp;&nbsp;
                                    守护线程：${threadMXBean.daemonThreadCount}
                                </p>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table table-hover">
                                    <thead class="text-warning">
                                    <th>name</th>
                                    <th>init</th>
                                    <th>used</th>
                                    <th>max</th>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${memoryPoolMXBeans}" var="pool" varStatus="vs">

                                        <tr>
                                            <td>${pool.name}</td>
                                            <td>
                                                <fmt:formatNumber type="number"
                                                                  value="${pool.usage.init/(1024*1024)}"
                                                                  pattern="#.00"/>
                                            </td>
                                            <td>
                                                <fmt:formatNumber type="number"
                                                                  value="${pool.usage.used/(1024*1024)}"
                                                                  pattern="#.00"/>

                                            </td>
                                            <td>
                                                <fmt:formatNumber type="number"
                                                                  value="${pool.usage.max/(1024*1024)}"
                                                                  pattern="#.00"/>
                                            </td>
                                        </tr>

                                    </c:forEach>

                                    </tbody>
                                </table>
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
<script type="text/javascript">
    $(document).ready(function () {

        // Javascript method's body can be found in assets/js/demos.js
        demo.initDashboardPageCharts();


        $(".sidebar-wrapper ul li").removeClass("active");
        $(".sidebar-wrapper ul li:nth-child(1)").attr("class", "active");
    });

</script>


</html>