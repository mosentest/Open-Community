<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/view/coop/header.jsp" />
<script src="assets/js/message.js"></script>
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
        <h2>Write Message</h2>
        <form id="write-message-form" action="coop/message/new" method="post" class="aui top-label">
          <fieldset>
            <div class="field-group">
              <label for="receiver">Receiver</label> 
              <input class="text long-field" type="text" id="receiver" name="receiver" value="${receiver}" title="Receiver" placeholder="Enter receiver here...">
            </div>
            <div class="field-group">
              <label for="content">Message</label>
              <textarea class="textarea long-field" rows="9" cols="120" name="content" id="content" placeholder="Write message here..."></textarea>
            </div>
            <div class="buttons-container">
              <div class="buttons">
                <a href="javascript:sendMessage();" class="aui-button aui-button-primary">Send Message</a>
              </div>
            </div>
          </fieldset>
        </form>
      </section>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />