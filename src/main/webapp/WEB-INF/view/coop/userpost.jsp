<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/view/coop/header.jsp" />
<script src="assets/js/message.js"></script>
<section id="content" role="main" style="background: #F7F6F2;">
  <div class="aui-page-panel">
    <jsp:include page="/WEB-INF/view/coop/mainnav.jsp"/>
    <div class="aui-page-panel-inner">
      <section class="aui-page-panel-content" style="background: #F7F6F2;">
        <div class="user-page">
          <jsp:include page="/WEB-INF/view/coop/userprofile.jsp"/>
          <div class="user-content">
            <div class="aui-tabs horizontal-tabs">
              <ul class="tabs-menu">
                <li class="menu-item">
                  <a href="coop/u/${user.path}/project"><strong>Project</strong></a>
                </li>
                <li class="menu-item active-tab">
                  <a href="coop/u/${user.path}/post"><strong>Post</strong></a>
                </li>
                <li class="menu-item">
                  <a href="coop/u/${user.path}/activity"><strong>Activity</strong></a>
                </li>
              </ul>
              <div class="tabs-pane active-pane">
                <c:if test="${singlePost}">
                  <source:post post="${post}"/>
                </c:if>
                <c:if test="${not singlePost}">
                  <c:forEach items="${postList}" var="post">
                    <source:post post="${post}"/>
                  </c:forEach>
                </c:if>
              </div>
		    </div>
          </div>
          <div class="clearfix"></div>
        </div>
      </section>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />