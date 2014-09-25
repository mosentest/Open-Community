<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en-us">
<head>
  <%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
  %>
  <base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="UTF-8">
  <title>Source Community</title>
  <link rel="icon" type="image/png" href="assets/img/core/favicon.png">
  <link rel="stylesheet" href="assets/css/aui.css" media="all">
  <link rel="stylesheet" href="assets/css/aui-experimental.css" media="all">
  <link rel="stylesheet" href="assets/css/aui-design.css" media="all">
  <link rel="stylesheet" href="assets/css/source.coop.css" media="all">
  <link rel="stylesheet" href="assets/css/common.css" media="all">
  <script src="assets/js/jquery-2.0.3.js"></script>
  <script src="http://code.jquery.com/jquery-migrate-1.1.1.js"></script> 
  <script src="assets/js/hunter.js"></script>
  <script src="assets/js/user.js"></script>
  <script src="assets/js/aui.js"></script>
  <script src="assets/js/common.js"></script>
  <script src="assets/js/config.js"></script>
  <script src="assets/js/aui-soy.js"></script>
  <script>
    $(function(){
		config.user.id = <security:authentication property="principal.id"/>;
		config.user.path = '<security:authentication property="principal.user.path"/>';
		config.user.profile = '<security:authentication property="principal.user.profile"/>';
	});
  </script>
</head>
<body class="aui-layout aui-theme-default" data-aui-version="5.5.1">
<header id="header" role="banner">
  <nav class="aui-header aui-dropdown2-trigger-group" role="navigation">
    <div class="aui-header-inner">
      <div class="aui-header-before">
        <a class="aui-dropdown2-trigger app-switcher-trigger" aria-owns="app-switcher" id="flatpack-app-switcher-trigger" aria-controls="app-switcher">
          <span class="aui-icon aui-icon-small aui-iconfont-appswitcher">Design/AUI</span>
        </a>
        <div class="aui-dropdown2 aui-style-default aui-dropdown2-in-header" id="app-switcher" style="left: 10px; top: 40px; display: none;" data-dropdown2-alignment="left" aria-hidden="true">
          <ul role="radiogroup">
            <li><a href="source/index.html" class="aui-dropdown2-radio interactive" role="radio" aria-checked="false">Source</a></li>
            <li><a class="aui-dropdown2-radio interactive checked" role="radio" aria-checked="true">Cooperation</a></li>
            <li><a href="#" class="aui-dropdown2-radio interactive" role="radio" aria-checked="false">Open Source</a></li>
            <li><a href="#" class="aui-dropdown2-radio interactive" role="radio" aria-checked="false">Discussion</a></li>
            <li><a href="#" class="aui-dropdown2-radio interactive" role="radio" aria-checked="false">Ask</a></li>
            <li><a href="#" class="aui-dropdown2-radio interactive" role="radio" aria-checked="false">Hunter</a></li>
          </ul>
        </div>
      </div>
      <div class="aui-header-primary">
        <h1 id="logo" class="aui-header-logo aui-header-logo-confluence"><a href="#"><span class="aui-header-logo-device">Hunter</span></a></h1>
          <ul class="aui-nav" style="width: auto;">
            <li><a href="coop">Home</a></li>
            <li><a href="coop/opensource">Open Source Projects</a></li>
            <li><a href="coop/social">Social Network</a></li>
            <li><a href="coop/documentation">Documentation</a></li>
            <li><a href="coop/feedback">Feedback</a></li>
          </ul>
      </div>
      <div class="aui-header-secondary">
        <ul class="aui-nav">
          <li>
            <form action="/foo" method="post" class="aui-quicksearch">
              <label for="quicksearchid" class="assistive">Search</label>
              <input id="quicksearchid" class="search" type="text" placeholder="Search" name="quicksearchname">
            </form>
          </li>
          <security:authorize access="isAuthenticated()">
            <li><a href="#"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></a></li>
            <li><a href="#dropdown2-header-conf-admin" aria-owns="dropdown2-header-conf-admin" aria-haspopup="true" class="aui-dropdown2-trigger" aria-controls="dropdown2-header-conf-admin">
              <div class="aui-avatar aui-avatar-small">
                <div class="aui-avatar-inner">
                  <img src="assets/img/core/default-avatar.png" alt="Username">
                </div>
              </div>
              </a>
              <div class="aui-dropdown2 aui-style-default aui-dropdown2-in-header" id="dropdown2-header-conf-admin" style="display: none; top: 40px; min-width: 160px; left: 1213px;" aria-hidden="true">
                <div class="aui-dropdown2-section">
                  <ul>
                    <li><a href="#" class="active">Update status…</a></li>
                  </ul>
                </div>
                <div class="aui-dropdown2-section">
                  <ul>
                    <li><a href="#">Personal Space</a></li>
                    <li><a href="#">Recently viewed</a></li>
                  </ul>
                </div>
                <div class="aui-dropdown2-section">
                  <ul>
                    <li><a href="#">Profile</a></li>
                    <li><a href="#">Settings</a></li>
                    <li><a href="#">Network</a></li>
                    <li><a href="#">Status updates</a></li>
                    <li><a href="#">Favourites</a></li>
                    <li><a href="#">Watches</a></li>
                    <li><a href="#">Drafts</a></li>
                    <li><a href="#">Atlassian Marketplace</a></li>
                    <li><a href="#">Extensions</a></li>
                  </ul>
                </div>
                <div class="aui-dropdown2-section">
                  <ul>
                    <li><a href="#">Log out</a></li>
                  </ul>
                </div>
              </div>
            </li>
          </security:authorize>
          <security:authorize access="isAnonymous()">
          	<li><a href="login.html">Login</a></li>
          </security:authorize>
        </ul>
      </div>
    </div>
  </nav>
</header>
<div id="response-message"></div>