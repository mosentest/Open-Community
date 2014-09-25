<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction"%>
<c:set var="selected" value="class='aui-nav-selected'"/>
<nav class="aui-navgroup aui-navgroup-horizontal">
  <div class="aui-navgroup-inner">
    <div class="aui-navgroup-primary">
      <c:set var="mp" value="${sf:maskURL(project.name)}"/>
      <ul class="aui-nav __skate">
        <li ${param.nav eq 'home' ? selected : ''}><a href="coop/u/${user.path}/p/${mp}">Home</a></li>
        <li ${param.nav eq 'task' ? selected : ''}><a href="coop/${user.path}/p/${mp}/tasks">Task</a></li>
        <li ${param.nav eq 'discussion' ? selected : ''}><a href="coop/${user.path}/p/${mp}/discussions">Discussion</a></li>
        <li ${param.nav eq 'members' ? selected : ''}><a href="coop/${user.path}/p/${mp}/members">Members</a></li>
        <li ${param.nav eq 'settings' ? selected : ''}><a href="coop/${user.path}/p/${mp}/settings">Settings</a></li>
        <li>&nbsp;</li>
        <li ${param.nav eq 'code' ? selected : ''}><a href="coop/${user.path}/p/${mp}/code">Code</a></li>
        <li ${param.nav eq 'documentation' ? selected : ''}><a href="coop/${user.path}/p/${mp}documentation">Documentation</a></li>
        <li ${param.nav eq 'quality' ? selected : ''}><a href="coop/${user.path}/p/${mp}/quality">Quality Management</a></li>
      </ul>
    </div>
    <div class="aui-navgroup-secondary">
      <ul class="aui-nav __skate">
        <li><a href="#"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></a></li>
      </ul>
    </div>
  </div>
</nav>