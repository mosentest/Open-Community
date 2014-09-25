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
  <link rel="stylesheet" href="assets/css/bootstrap.css" media="all">
  <link rel="stylesheet" href="assets/css/source.source.css" media="all">
  <link rel="stylesheet" href="assets/css/common.css" media="all">
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" media="all">
  <script src="assets/js/jquery-2.0.3.js"></script>
  <script src="http://code.jquery.com/jquery-migrate-1.1.1.js"></script> 
  <script src="assets/js/hunter.js"></script>
  <script src="assets/js/user.js"></script>
  <script src="assets/js/common.js"></script>
  <script src="assets/js/config.js"></script>
  <script src="assets/js/bootstrap.js"></script>
</head>
<body>
<header class="navbar navbar-static-top" id="top" role="banner" style="background-color:#ffffff;">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="source/index.html" class="navbar-brand">Source Repository</a>
    </div>
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
      <ul class="nav navbar-nav">
        <li class="${param.nav eq 'home' ? 'active' : ''}"><a href="source/index.html">Home</a></li>
        <li class="${param.nav eq 'category' ? 'active' : ''}"><a href="source/categories.html">Categories</a></li>
        <li class="${param.nav eq 'org.mu.community.common.tag' ? 'active' : ''}"><a href="source/tags.html">Tags</a></li>
        <li class="${param.nav eq 'library' ? 'active' : ''}"><a href="source/libraries.html">Libraries</a></li>
        <li class="${param.nav eq 'code' ? 'active' : ''}"><a href="source/code.html">Source Files</a></li>
        <li class="${param.nav eq 'doc' ? 'active' : ''}"><a href="source/documentation.html">Documentation</a></li>
        <li class="${param.nav eq 'upload' ? 'active' : ''}"><a href="source/upload.html">Upload Library</a></li>
      </ul>
      <security:authorize access="isAuthenticated()">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><img class="profile-small" src="image/u/<security:authentication property="principal.profile"/>"></a></li>
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#"><security:authentication property="principal.account"/><span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#">Resources</a></li>
              <li><a href="#">Notifications</a></li>
              <li><a href="#">Projects</a></li>
              <li class="divider"></li>
              <li><a href="#">Settings</a></li>
              <li class="divider"></li>
              <li><a href="logout.html">Log out</a></li>
            </ul>
          </li>
        </ul>
      </security:authorize>
      <security:authorize access="isAnonymous()">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="login.html">Login</a></li>
        </ul>
      </security:authorize>
    </nav>
  </div>
</header>
<div id="response-message"></div>