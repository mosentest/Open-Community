<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<div id="content">
  <div class="row">
    <div class="col-sm-offset-3 col-lg-6 ">
      <div class="panel panel-primary">        
        <div class="panel-heading">Login</div>
        <div class="panel-body">
          <form class="form-horizontal" role="form" action="<c:url value='j_spring_security_check'/>" method="POST">
            <div class="form-group">
              <label class="col-sm-3 control-label" for="username">Account/Email</label>
              <div class="col-sm-7">
                <input class="form-control" type="text" id="username" name="j_username">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label" for="project">Password</label>
              <div class="col-sm-7">
                <input class="form-control" type="password" id="password" name="j_password">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-3 col-sm-6">
                <div class="checkbox">
                  <label>
                    <input type="checkbox" name="rememberme"> Remember me on this computer
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-primary">Log in</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />