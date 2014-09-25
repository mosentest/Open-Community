<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/view/coop/header.jsp" />
<script src="assets/js/message.js"></script>
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
              <li><a href="coop/messages/all">All<c:if test="${nAll != 0}"><span class="aui-badge fl-right">${nAll}</span></c:if></a></li>
              <li><a href="coop/messages/unread">Unread<c:if test="${nUnread != 0}"><span class="aui-badge fl-right">${nUnread}</span></c:if></a></li>
            </ul>
          </div>
        </nav>
      </div>
      <section class="aui-page-panel-content">
        <c:set var="target" value="${conversation.user.id eq id ? conversation.target : conversation.user}"/>
        <c:set var="userProfile">
          <a href="coop"><img class="profile-default" src="image/u/<security:authentication property="principal.profile"/>"></a>
        </c:set>
        <h2>Conversation with <a href="coop/u/${target.id}">${target.account}</a></h2>
        <form id="write-message-form" action="coop/message/write" method="POST" class="aui">
          <input type="hidden" name="conversation" value="${conversation.id}"/>
          <input type="hidden" name="receiver" value="${target.id}"/>
          <div class="write-message">
            <div class="write-message-profile">${userProfile}</div>
            <div class="write-message-content">
              <textarea class="textarea long-field" rows="3" cols="80" name="content" id="message" placeholder="Write message here..."></textarea>
            </div>
            <div class="write-message-send"><a href="javascript:replyMessage();" class="aui-button aui-button-primary">Send</a></div>
            <div class="clearfix"></div>
          </div>
        </form>
        <div class="message-list">
          <c:forEach items="${conversation.messages}" var="message">
            <div class="message-i-date"><source:date date="${message.create}" useFormat="true"/></div>
     	      <c:if test="${(message.outward and conversation.user.id eq id) or (not message.outward and conversation.target.id eq id)}">
     	        <div class="message-i-l">
     	          <div class="message-i-profile">${userProfile}</div>
     	          <div class="message-i-content">${message.message}</div>
     	          <div class="clearfix"></div>
     	        </div>
     	      </c:if>
     	      <c:if test="${(message.outward and conversation.target.id eq id) or (not message.outward and conversation.user.id eq id)}">
     	        <div class="message-i-r">
     	          <div class="message-i-profile">
     	            <a href="coop/u/${target.path}"><img class="profile-default" src="image/u/${target.profile}"></a>
     	          </div>
     	          <div class="message-i-content">${message.message}</div>
     	          <div class="clearfix"></div>
     	        </div>
     	      </c:if>
          </c:forEach>
        </div>
      </section>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />