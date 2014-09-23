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
      <a href="source/index.html" class="navbar-brand">Source Repository</a>
    </div>
  </div>
</header>
<div id="response-message"></div>