<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="selected" value="class='aui-nav-selected'"/>
<nav class="aui-navgroup aui-navgroup-horizontal">
  <div class="aui-navgroup-inner">
    <div class="aui-navgroup-primary">
      <ul class="aui-nav __skate">
        <li ${param.nav eq 'home' ? selected : ''}><a href="coop">Home</a></li>
        <li ${param.nav eq 'project' ? selected : ''}><a href="coop/projects">Projects</a></li>
        <li ${param.nav eq 'task' ? selected : ''}><a href="coop/tasks">Tasks</a></li>
        <li ${param.nav eq 'activitiy' ? selected : ''}><a href="coop/activities">Activities</a></li>
        <li ${param.nav eq 'friend' ? selected : ''}><a href="coop/relationship/friends">Friends</a></li>
        <li ${param.nav eq 'notification' ? selected : ''}><a href="coop/notifications/all">Notifications</a></li>
        <li ${param.nav eq 'message' ? selected : ''}><a href="coop/messages/all">Messages</a></li>
        <li ${param.nav eq 'settings' ? selected : ''}><a href="coop/settings">Settings</a></li>
      </ul>
    </div>
    <div class="aui-navgroup-secondary">
      <ul class="aui-nav __skate">
        <li><a href="#"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></a></li>
      </ul>
    </div>
  </div>
</nav>