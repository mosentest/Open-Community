<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="nav" value="code" />
</jsp:include>
<div id="content">
  <div class="row">
    <div class="col-sm-offset-3 col-lg-6">
      <form action="source/codes.html" method="GET">
        <div class="input-group">
          <input class="form-control" id="query" 
       	 	name="query" title="Search" placeholder="Search for library by Java class name...">
          <span class="input-group-btn">
            <button class="btn btn-primary" type="submit">Search</button>
          </span>
        </div>
      </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />