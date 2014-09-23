<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="nav" value="code" />
</jsp:include>
<div id="content">
  <div class="row">
    <c:set var="url">
      source/code/codes.html?<c:if test="${not empty query}">query=${query}</c:if>
    </c:set>
    <c:if test="${libraryList.totalElement gt 0}">
      <div class="col-lg-4 pull-right"></div>
      <div class="col-lg-8 pull-right">
        <source:page page="${libraryList}" url="${url}"/>
      </div>
    </c:if>
  </div>
  <div class="row">
    <div class="col-lg-8">
      <c:if test="${libraryList.totalElement eq 0}">
        <div class="alert alert-info" role="alert">
          <strong>No result. </strong>
          <a href="#" class="alert-link">Go back to home page</a> for more libraries.
        </div>
      </c:if>
      <c:if test="${libraryList.totalElement gt 0}">
        <div class="panel panel-primary">
          <div class="panel-heading">Libraries</div>
            <ul class="list-group">
              <c:forEach items="${libraryList.content}" var="library">
                <li class="list-group-item"><a href="source/library/${library.name}.html">${library.name}</a></li>
              </c:forEach>
            </ul>
        </div>
      </c:if>
    </div>
    <div class="col-lg-4">
      <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Profile</a></li>
        <li><a href="#">Messages</a></li>
      </ul>
      <ul class="list-group">
        <c:forEach items="${page.content}" var="library" varStatus="status">
          <li class="list-group-item ${status.first ? 'group-item-first' : ''}">
            <a href="source/library/${library.name}.html">${library.name}</a>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />