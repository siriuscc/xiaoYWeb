<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76"
          href="${pageContext.request.contextPath}/public/assets/img/apple-icon.png"/>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/public/assets/img/favicon.png"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>用户分布</title>
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
        #container {
            height: 100%
        }
    </style>

</head>

<body>
<div class="wrapper">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/view/ms/common/menu.jsp"></jsp:include>


    <!--主面板-->
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
                    <a class="navbar-brand" href="#"> 定位 </a>
                </div>
                <%--工具栏--%>
                <jsp:include page="${pageContext.request.contextPath}/WEB-INF/view/ms/common/toolbar.jsp"></jsp:include>

            </div>
        </nav>

        <!--地图-->
        <div id="container"></div>


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
<!-- Material Dashboard javascript methods -->
<script src="${pageContext.request.contextPath}/public/assets/js/material-dashboard.js?v=1.2.0"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="${pageContext.request.contextPath}/public/assets/js/demo.js"></script>


<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=t89FC5IIympBkrLdsah2vhSRrKZTCBpb"></script>
<script type="text/javascript">


    /**
     *
     * @param x 经度
     * @param y 纬度
     * @param title 标记
     */
    function addPoint(x, y, title) {

        var label = new BMap.Label(title);

        label.setStyle({
            color: "#000",
            fontSize: "12px",
            border: 0,
            backgroundColor: "#0000"
        });

        label.setOffset(new BMap.Size(15, -5));

        var mypoint = new BMap.Point(x, y);
        var marker = new BMap.Marker(mypoint);
        marker.setLabel(label);

        window.map.addOverlay(marker);     //添加一个label
    }


    /**
     * 移动并放缩
     * @param x
     * @param y
     * @param zoom
     */
    function centerAndZoom(x, y, zoom) {
        //初始化中心点
        var point = new BMap.Point(x, y);
        map.centerAndZoom(point, zoom);

    }

    $(document).ready(function () {

        //初始化菜单选择状态
        $(".sidebar-wrapper ul li").removeClass("active");
        $(".sidebar-wrapper ul li:nth-child(4)").attr("class", "active");


        if ($('.main-panel > .content').length == 0) {
            $('.main-panel').css('height', '100%');
        }

        //初始化地图
        window.map = new BMap.Map("container");
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放


        // 添加带有定位的导航控件
        var navigationControl = new BMap.NavigationControl({
            // 靠左上角位置
            anchor: BMAP_ANCHOR_TOP_LEFT,
            // LARGE类型
            type: BMAP_NAVIGATION_CONTROL_LARGE,
            // 启用显示定位
            enableGeolocation: true
        });
        map.addControl(navigationControl);



        // //添加点
        // addPoint(116.409, 39.918, "sirius");



        $.getJSON("${pageContext.request.contextPath}/ms/location/getLastAllLocation.do", function(json){
            // console.log(json)

            var latitude=0
            var longitude=0;
            for(var i=0;i<json.length;++i){
                // console.log(json[i].userLocation.latitude);
                // console.log(json[i].userLocation.longitude);
                //
                // console.log(json[i].userLocation.locTime);
                latitude+=json[i].userLocation.latitude;

                longitude+=json[i].userLocation.longitude;

                addPoint(json[i].userLocation.longitude, json[i].userLocation.latitude, json[i].user.username);
            }

            latitude/=json.length;
            longitude/=json.length;
            centerAndZoom(longitude,latitude, 15);

        });

    });
</script>

</html>