<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="nav" value="home" />
</jsp:include>
<div id="content">
  <div class="row">
    <div class="col-sm-offset-3 col-lg-6">
      <form action="source/libraries.html" method="GET">
        <div class="input-group">
          <input class="form-control" id="search-library" 
       	 	name="query" title="Search" placeholder="Search for library...">
          <span class="input-group-btn">
            <button class="btn btn-primary" type="submit">Search</button>
          </span>
        </div>
      </form>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-offset-7 col-lg-2">
      <a href="source/advancedSearch.html">Advanced search</a>
      <span class="tab-long"></span>
      <a href="source/advancedSearch.html">History</a>
    </div>
  </div>
  <div class="separator-high"></div>
  <div class="row">
    <div class="col-lg-3">
      <h5>Tags</h5>
      <c:forEach items="${tags}" var="tag">
        <p><a href="source/libraries.html?tag=${tag.tag}">${tag.tag}</a></p>
      </c:forEach>
    </div>
    <div class="col-lg-6">
      <div class="panel panel-primary">
        <div class="panel-heading">Libraries</div>
        <ul class="list-group">
          <c:forEach items="${page.content}" var="library">
            <li class="list-group-item"><a href="source/library/${library.name}.html">${library.name}</a></li>
          </c:forEach>
        </ul>
      </div>
    </div>
    <div class="col-lg-3">
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