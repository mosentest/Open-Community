<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<div class="aui-page-panel">
  <div class="aui-page-panel-inner">
    <section class="aui-page-panel-content">
      <div id="login-panel">
        <h2>Sign Up</h2>
        <form:form action="signup" method="post" modelAttribute="user" class="aui top-label">
          <fieldset>
            <div class="field-group">
              <label for="username">Account</label> 
              <input class="text long-field" type="text" id="account" name="account" title="account">
            </div>
            <div class="field-group">
              <label for="username">Email</label> 
              <input class="text long-field" type="text" id="email" name="email" title="email">
            </div>
            <div class="field-group">
              <label for="password">Password</label> 
              <input class="text long-field" type="password" id="password" name="password" title="password">
            </div>
            <div class="field-group">
              <label for="password">Conform Password</label> 
              <input class="text long-field" type="password" id="confirm" name="confirm" title="confirm">
            </div>
            <div class="field-group">
              <label for="password">Captcha</label> 
              <input type="text" name="kaptcha" value="" />
              <img src="kaptcha.jpg" id="kaptchaImage" />
            </div>
            <div class="checkbox">
              <input class="checkbox" type="checkbox" name="check" id="check">
              <label for="cbThree">I have read this Agreement and agree to the terms and conditions</label>
            </div>
            <div class="h-separator"></div>
            <div class="buttons-container">
              <div class="buttons">
                <input class="aui-button aui-button-primary" type="submit" id="submit" name="submit" value="Sign up"> 
              </div>
            </div>
          </fieldset>
        </form:form>
      </div>
    </section>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />