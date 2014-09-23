<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="navigation" value="home" />
</jsp:include>
<div class="container">
  <div class="panel panel-primary">
    <div class="panel-heading">Search for Java Libraries</div>
    <div class="panel-body">
      <form class="form-horizontal" role="form" action="source/libraries.html" method="GET">
        <div class="form-group">
          <label class="col-sm-2 control-label" for="name">Library name</label>
          <div class="col-sm-8">
            <input class="form-control" type="text" id="name" name="name" title="Library name">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label" for="project">Related Project</label>
          <div class="col-sm-8">
            <input class="form-control" type="text" id="project" name="project" title="Related project">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label" for="version">Library version</label>
          <div class="col-sm-8">
            <input class="form-control" type="text" id="version" name="version" title="Version">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label" for="category">Categories</label>
          <div class="col-sm-8">
            <input class="form-control" type="text" id="category" name="category" title="Categories">
            <span class="help-block">Separate with semicolons, example: java;jpa</span>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label" for="tag">Tags</label>
          <div class="col-sm-8">
            <input class="form-control" type="text" id="tag" name="tag" title="Tags">
            <span class="help-block">Separate with semicolons, example: java;jpa</span>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
              <label>
                <input type="checkbox" name="verified"> Verified Library
              </label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Search</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />