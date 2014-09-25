<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="nav" value="org.mu.community.common.tag" />
</jsp:include>
<div id="content">
  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-primary">
        <div class="panel-heading">Tags</div>
        <div class="panel-body">
          <c:forEach items="${tagList}" var="tag" varStatus="status">
            <c:if test="${status.count % 6 == 1}">
              <div class="row">
            </c:if>
            <div class="col-lg-2">
              <div class="list-group">
                <a href="source/libraries.html?tag=${tag.tag}" class="list-group-item">
                  <h4 class="list-group-item-heading">${tag.tag}</h4>
                  <p class="list-group-item-text">${tag.description}</p>
                  <span >Tagged: </span><span class="number"> ${tag.nTag}</span><span> times</span>
                </a>
              </div>
            </div>
            <c:if test="${status.count % 6 == 0 or status.last}">
              </div>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />