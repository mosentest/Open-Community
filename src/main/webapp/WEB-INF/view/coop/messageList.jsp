<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/view/coop/header.jsp" />
<c:set var="id"><security:authentication property="principal.id"/></c:set>
<section id="content" role="main" style="background: #f5f5f5;">
  <div class="aui-page-panel">
    <jsp:include page="/WEB-INF/view/coop/mainnav.jsp">
      <jsp:param name="nav" value="message"/>
    </jsp:include>
    <div class="aui-page-panel-inner">
      <div class="aui-page-panel-nav">
        <nav class="aui-navgroup aui-navgroup-vertical">
          <div class="aui-navgroup-inner">
            <div class="aui-nav-heading">
              <strong>Messages</strong>
              <a href="coop/message/writeMessage" class="aui-lozenge aui-lozenge-complete fl-right">Write</a>
            </div>
            <ul class="aui-nav __skate">
              <li class="${type eq 'all' ? 'aui-nav-selected' : ''}"><a href="coop/messages/all">All
                <c:if test="${nAll != 0}"><span class="aui-badge fl-right">${nAll}</span></c:if></a></li>
              <li class="${type eq 'unread' ? 'aui-nav-selected' : ''}"><a href="coop/messages/unread">Unread
                <c:if test="${nUnread != 0}"><span class="aui-badge fl-right">${nUnread}</span></c:if></a></li>
            </ul>
          </div>
        </nav>
      </div>
      <section class="aui-page-panel-content">
        <c:choose>
          <c:when test="${type eq 'all'}"><h2>All Conversations</h2></c:when>
          <c:when test="${type eq 'unread'}"><h2>Unread Conversations</h2></c:when>
        </c:choose>
        <div class="conversation-list">
          <c:set var="outgoing"><span class="aui-icon aui-icon-small aui-iconfont-up" class="outgoing"></span></c:set>
          <c:set var="incoming"><span class="aui-icon aui-icon-small aui-iconfont-down" class="incoming"></span></c:set>
          <source:page page="${messageList}" url="coop/messages/${type}/[pn]"/>
          <c:forEach items="${messageList.content}" var="conversation">
            <c:set var="target" value="${conversation.user.id eq id ? conversation.target : conversation.user}"/>
            <div class="conv-i" id="conversation-${conversation}" onclick="url('coop/message/${conversation.id}')">
              <div class="conv-i-profile"><img class="profile-default" src="image/u/default.png"/></div>
              <div class="conv-i-content">
                <span class="conv-i-account"><a href="coop/u/${target.path}">${target.account}</a></span>
                <span class="conv-i-info">
                  <span class="aui-icon aui-icon-small aui-iconfont-comment"></span><span class="icon-link-text">${conversation.nMessage}</span>
                  <span class="aui-icon aui-icon-small aui-iconfont-time"></span><span class="icon-link-text"><source:date date="${conversation.update}"/></span>
                </span>
                <div class="clearfix"></div>
             	  <div class="conv-i-message">
             	    <c:choose>
             	      <c:when test="${conversation.user.id eq id and conversation.recentMessage.outward}">${outgoing}</c:when>
             	      <c:when test="${conversation.user.id eq id and not conversation.recentMessage.outward}">${incoming}</c:when>
             	      <c:when test="${conversation.target.id eq id and conversation.recentMessage.outward}">${incoming}</c:when>
             	      <c:when test="${conversation.target.id eq id and not conversation.recentMessage.outward}">${outgoing}</c:when>
             	    </c:choose>
                  <span class="icon-link-text">${conversation.recentMessage.message}</span>
                </div>
              </div>
              <div class="clearfix"></div>
            </div>
          </c:forEach>
        </div>
      </section>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />