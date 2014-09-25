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
              <a href="#" class="aui-lozenge aui-lozenge-complete fl-right">Add Friend</a>
            </div>
            <ul class="aui-nav __skate">
              <li><a href="coop/relationship/friends">Friends<c:if test="${nFriends != 0}"><span class="aui-badge fl-right">${nFriends}</span></c:if></a></li>
              <li><a href="coop/relationship/fans">Fans<c:if test="${nFans != 0}"><span class="aui-badge fl-right">${nFans}</span></c:if></a></li>
            </ul>
          </div>
        </nav>
      </div>
      <section class="aui-page-panel-content">
        <h2>Add Friends</h2>
        <div id="search-user-form">
          <form action="#" method="post" class="aui top-label">
            <fieldset>
              <div class="field-group">
                <input class="text long-field" type="text" id="ajax-search-user" name="query" title="Query" placeholder="Enter Account/Email here...">
                <a href="javascript:searchUser();" class="aui-button aui-button-primary">Search</a>
              </div>
            </fieldset>
          </form>
          <div class="h-separator"></div>
          <div class="ajax-no-result">No result found.</div>
          <div class="aui-group ajax-progressing">
            
            <p style="margin-bottom: 10px;" id="loading-label">Loading...&nbsp;<img src="assets/css/wait.gif"/></p>
          </div>
          <div class="ajax-result-content">
            <table class="aui aui-table-interactive centered">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Account</th>
                  <th>Fans</th>
                  <th>Friends</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
        </div>
      </section>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />