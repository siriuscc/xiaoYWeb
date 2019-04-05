<%--
  Created by IntelliJ IDEA.
  User: sirius
  Date: 2018/6/4
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="sidebar" data-color="blue" data-image="${pageContext.request.contextPath}/public/assets/img/sidebar-1.jpg">

    <div class="logo">
        <a href="http://www.creative-tim.com" class="simple-text">
            小Y后台管理
        </a>
    </div>


    <!--左边的导航栏-->
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li>
                <a href="${pageContext.request.contextPath}/ms/system/dashboard.do">
                    <i class="material-icons">dashboard</i>
                    <p>面板</p>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ms/staff/myself.do">
                    <i class="material-icons">person</i>
                    <p>我的</p>
                </a>
            </li>
            <li >
                <a href="${pageContext.request.contextPath}/ms/user/userlist.do">
                    <i class="material-icons">content_paste</i>
                    <p>用户</p>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ms/location/maps.do">
                    <i class="material-icons">location_on</i>
                    <p>定位</p>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ms/notify/notifications.do">
                    <i class="material-icons text-gray">notifications</i>
                    <p>通知</p>
                </a>
            </li>
            <li class="active-pro">
                <a href="${pageContext.request.contextPath}/ms/upgrade/up.do">
                    <i class="material-icons">unarchive</i>
                    <p>购买高级功能</p>
                </a>
            </li>
        </ul>
    </div>
</div>

