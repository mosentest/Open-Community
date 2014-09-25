<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<div class="user-profile">
  <div class="user-basic">
    <img class="profile-large" src="image/u/${user.profile}"/>
    <br/>
    <span class="user-name user-div">
      ${user.account}
      <c:if test="${user.verified}"><span class="aui-icon aui-icon-small aui-iconfont-success verified" title="Verified User"></span></c:if>
    </span>
  </div>
  <div class="user-action user-div">
    <c:if test="true">
      <a href="coop/relationship/add" class="aui-lozenge aui-lozenge-success aui-lozenge-subtle">Add Friend</a>
    </c:if>
    <c:if test="false">
      <a href="coop/relationship/remove" class="aui-lozenge aui-lozenge-error aui-lozenge-subtle">Remove Friend</a>
    </c:if>
    <a href="coop/message/writeMessage?receiver=${user.id}" class="aui-lozenge aui-lozenge-complete aui-lozenge-subtle">Send Message</a>
  </div>
  <div class="border-top"></div>
  <div class="user-friend user-div">
    <span class="number">${user.nFriends}</span>
    <span class="info-text">Following / </span>
    <span class="number">${user.nFans}</span>
    <span class="info-text">Fans</span>
  </div>
  <div class="border-top"></div>
  <div class="user-info user-div">
    <div class="info-text">Joined : <source:date date="${user.create}"/></div>
    <div class="info-text">Last Login : <source:date date="${user.update}"/></div>
    <div class="info-text">Path : ${user.path}</div>
  </div>
  <div class="border-top">
  </div>
  <div class="user-detail">
    <div class="info-text user-div">Gender : ${user.detail.gender ? 'Male' : 'Female'}</div>
    <div class="border-top"></div>
    <div class="info-text user-div">
      Company : ${user.detail.company}
      <c:if test="${user.detail.companyVerified}"><span class="aui-icon aui-icon-small aui-iconfont-success verified" title="Verified Company"></span></c:if>
    </div>
    <div class="border-top"></div>
    <div class="info-text user-div">Role : ${user.detail.role}</div>
    <div class="border-top"></div>
    <div class="info-text user-div">Location : ${user.detail.country} ${user.detail.city}</div>
    <div class="border-top"></div>
    <div class="info-text user-div">Tags : ${user.detail.tag}</div>
    <div class="border-top"></div>
  </div>
</div>