<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<div id="content">
  <div class="row">
    <div class="col-sm-offset-3 col-lg-6">
      <div class="panel panel-default">
        <div class="panel-heading">Oops!</div>
        <div class="panel-body">${error}</div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />