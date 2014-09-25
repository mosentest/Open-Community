<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction"%>
<jsp:include page="/WEB-INF/view/coop/header.jsp" />
<section id="content" role="main" style="background: #f5f5f5;">
  <div class="aui-page-panel">
    <jsp:include page="/WEB-INF/view/coop/mainnav.jsp">
      <jsp:param name="nav" value="home"/>
    </jsp:include>
    <div class="aui-page-panel-inner">
      <section class="aui-page-panel-content">
        <div class="block">
          <div class="block-header">
            <span>Projects</span>
          </div>
          <div class="block-content">
            <c:forEach items="${projectList}" var="project">
              <div class="project">
                <a href="coop/u/${project.manager.path}/p/${sf:maskURL(project.name)}">
                  <img src="image/p/${project.imagePath}" class="project-image"/>
                  <span class="project-name">${project.name}</span>
                </a>
              </div>
            </c:forEach>
            <div class="clearfix"></div>
          </div>
        </div>
        <div class="block">
          <div class="block-header">
            <span>Activities</span>
          </div>
          <div class="block-content"></div>
        </div>
      </section>
      <aside class="aui-page-panel-sidebar">
        <div class="block">
          <div class="block-header">
            <span>Post</span>
          </div>
          <div class="block-content">
            <div class="write-post">
              <div class="write-post-profile">
                <img class="profile-default" src="image/u/<security:authentication property="principal.user.profile"/>"/>
              </div>
              <div class="write-post-content"> 
                <textarea class="textarea long-field" rows="3" name="content" id="post" placeholder="Write message here..."></textarea>
              </div>
              <div class="write-post-link">
                <a href="javascript:addLink();" class="aui-icon aui-icon-small aui-iconfont-link" title="Add Link"></a>
                <input type="file" class="hidden-upload" id="upload"/><a href="javascript:addImage();" class="aui-icon aui-icon-small aui-iconfont-image" title="Add Image"></a>
                <a href="javascript:post();" class="aui-lozenge aui-lozenge-complete aui-lozenge-subtle">Submit</a>
              </div>
              <div class="clearfix"></div>
            </div>
            <c:forEach items="${postList}" var="post">
              <source:post post="${post}"/>
            </c:forEach>
            <div class="text-center">
              <a href="javascript:showPosts();" id="show-post">Show more</a>
            </div>
          </div>
        </div>
      </aside>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />