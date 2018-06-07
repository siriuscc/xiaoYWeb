<%--
  Created by IntelliJ IDEA.
  User: sirius
  Date: 2018/6/6
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
