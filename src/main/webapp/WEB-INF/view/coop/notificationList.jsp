<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/view/coop/header.jsp" />
<script src="assets/js/user.js"></script>
<section id="content" role="main" style="background: #f5f5f5;">
  <div class="aui-page-panel">
    <jsp:include page="/WEB-INF/view/coop/mainnav.jsp">
      <jsp:param name="nav" value="notification"/>
    </jsp:include>
    <div class="aui-page-panel-inner">
      <div class="aui-page-panel-nav">
        <nav class="aui-navgroup aui-navgroup-vertical">
          <div class="aui-navgroup-inner">
            <div class="aui-nav-heading">
              <strong>Notifications</strong>
            </div>
            <ul class="aui-nav __skate">
              <li class="${type eq 'all' ? 'aui-nav-selected' : ''}"><a href="coop/notifications/all">All
                <c:if test="${nAll != 0}"><span class="aui-badge fl-right">${nAll}</span></c:if></a></li>
              <li class="${type eq 'unread' ? 'aui-nav-selected' : ''}"><a href="coop/notifications/unread">Unread
                <c:if test="${nUnread != 0}"><span class="aui-badge fl-right">${nUnread}</span></c:if></a></li>
              <li class="${type eq 'project' ? 'aui-nav-selected' : ''}"><a href="coop/notifications/project">Project
                <c:if test="${nProject != 0}"><span class="aui-badge fl-right">${nProject}</span></c:if></a></li>
              <li class="${type eq 'at' ? 'aui-nav-selected' : ''}"><a href="coop/notifications/at">At
                <c:if test="${nAt != 0}"><span class="aui-badge fl-right">${nAt}</span></c:if></a></li>
              <li class="${type eq 'post' ? 'aui-nav-selected' : ''}"><a href="coop/notifications/post">Post
                <c:if test="${nPost != 0}"><span class="aui-badge fl-right">${nPost}</span></c:if></a></li>
              <li class="${type eq 'system' ? 'aui-nav-selected' : ''}"><a href="coop/notifications/system">System
                <c:if test="${nSystem != 0}"><span class="aui-badge fl-right">${nSystem}</span></c:if></a></li>
            </ul>
          </div>
        </nav>
      </div>
      <section class="aui-page-panel-content">
        <c:choose>
          <c:when test="${type eq 'all'}"><h2>All Notifications</h2></c:when>
          <c:when test="${type eq 'unread'}"><h2>Unread Notifications</h2></c:when>
          <c:when test="${type eq 'project'}"><h2>Project Notifications</h2></c:when>
          <c:when test="${type eq 'at'}"><h2>At Notifications</h2></c:when>
          <c:when test="${type eq 'post'}"><h2>Post Notifications</h2></c:when>
          <c:when test="${type eq 'system'}"><h2>System Notifications</h2></c:when>
        </c:choose>
        <div class="notification-l">
          <c:forEach items="${notificationList}" var="notification">
            <source:notification notification="${notification}"/>
          </c:forEach>
        </div>
      </section>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />