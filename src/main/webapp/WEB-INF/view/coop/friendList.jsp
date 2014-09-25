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
      <jsp:param name="nav" value="friend"/>
    </jsp:include>
    <div class="aui-page-panel-inner">
      <div class="aui-page-panel-nav">
        <nav class="aui-navgroup aui-navgroup-vertical">
          <div class="aui-navgroup-inner">
            <div class="aui-nav-heading">
              <strong>Friends</strong>
              <a href="coop/relationship/add" class="aui-lozenge aui-lozenge-complete fl-right">Add Friend</a>
            </div>
            <ul class="aui-nav __skate">
              <li class="${type eq 'friends' ? 'aui-nav-selected' : ''}"><a href="coop/relationship/friends">Friends
                <c:if test="${nFriends != 0}"><span class="aui-badge fl-right">${nFriends}</span></c:if></a></li>
              <li class="${type eq 'fans' ? 'aui-nav-selected' : ''}"><a href="coop/relationship/fans">Fans
                <c:if test="${nFans != 0}"><span class="aui-badge fl-right">${nFans}</span></c:if></a></li>
            </ul>
          </div>
        </nav>
      </div>
      <section class="aui-page-panel-content">
        <c:choose>
          <c:when test="${type eq 'friends'}"><h2>Friends</h2></c:when>
          <c:when test="${type eq 'fans'}"><h2>Fans</h2></c:when>
        </c:choose>
        <c:if test="${fn:length(userList) eq 0}">
          <div class="aui-message">
            <c:if test="${type eq 'friends'}">
              <p>You have no friends yet, add some!</p>
              <p><a href="coop/addFriend">Add Friend</a></p>
            </c:if>
          </div>
        </c:if>
        <c:if test="${fn:length(userList) gt 0}">
          <table class="aui aui-table-interactive centered">
            <thead>
              <tr>
                <th>#</th>
                <th>Account</th>
                <th>Last Login</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${userList}" var="user" varStatus="status">
                <tr id="user-${user.path}">
                  <td><a href="coop/u/${user.path}"><img src="image/u/${user.profile}"/></a></td>
                  <td><a href="coop/u/${user.path}">${user.account}</a>&nbsp;
                    <c:if test="${user.verified}"><span class="aui-icon aui-icon-small aui-iconfont-success verified" title="Verified User"></span></c:if>
                  </td>
                  <td><source:date date="${user.update}"/></td>
                  <td>
                    <a href="coop/message/writeMessage?receiver=${user.id}" class="aui-lozenge aui-lozenge-subtle">Send Message</a>
                    <br/>
                    <a href="javascript:removeFriend('${user.path}', ${type eq 'friends' ? 1 : 0});" class="aui-lozenge aui-lozenge-error aui-lozenge-subtle">Remove from ${type}</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </section>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />