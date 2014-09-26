<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<jsp:include page="/WEB-INF/view/blog/header.jsp">
  <jsp:param name="nav" value="home" />
</jsp:include>
<c:set var="url">u/${user.path}/blogs.html?<c:if test="${not empty category}">category=${category}</c:if><c:if test="${not empty month}">&month=${month}</c:if></c:set>
<div id="content">
  <div class="row">
    <div class="col-sm-offset-2 col-lg-8">
      <h2>${blogStat.title}</h2>
      <h4><small>${blogStat.description}</small></h4>
       <div class="pull-right">
         <button type="button" class="btn btn-default btn-xs" onclick="javascript:url('${url}&listMode=true');">
           <span class="glyphicon glyphicon-list-alt"></span> List
         </button>
         <button type="button" class="btn btn-default btn-xs" onclick="javascript:url('${url}');">
            <span class="glyphicon glyphicon-th-list"></span> Summary
         </button>
         <button type="button" class="btn btn-default btn-xs" onclick="javascript:url();">
           <span class="glyphicon glyphicon-globe"></span> Rss Feed
         </button>
      </div>
    </div>
  </div>
  <c:set var="url">${url}<c:if test="${not empty listMode}">&listMode=true</c:if></c:set>
  <div class="separator"></div>
  <div class="row">
    <div class="col-sm-offset-2 col-lg-2">
      <div class="panel panel-default">
        <div class="panel-heading">Blogger</div>
        <div class="panel-body">
          <a href="#" class="thumbnail">
            <img class="profile-large" src="image/u/${user.profile}" alt="...">
          </a>
          <table class="table">
            <tr class="none-border">
              <td colspan="2">Views</td>
              <td colspan="2">${blogStat.nView}</td>
            </tr>
            <tr class="none-border">
              <td colspan="2">Blogs</td>
              <td colspan="2">${blogStat.nBlog}</td>
            </tr>
            <tr class="none-border">
              <td colspan="2">Comments</td>
              <td colspan="2">${blogStat.nComment}</td>
            </tr>
            <tr class="none-border">
              <td colspan="2">Likes</td>
              <td colspan="2">${blogStat.nLike}</td>
            </tr>
            <tr class="none-border"><td colspan="4"></td></tr>
            <c:forEach items="${typeDataList}" var="typeData" varStatus="status">
              <c:if test="${status.count % 2 == 1}">
                <tr>
              </c:if>
              <td>${typeData.type.key}</td>
              <td>${typeData.nBlog}</td>
              <c:if test="${status.count % 2 == 0 or status.last}">
                <c:choose>
                  <c:when test="${status.count % 2 == 1}"><td></td><td></td></c:when>
                </c:choose>
                </tr>
              </c:if>
            </c:forEach>
          </table>
        </div>
      </div>
      <ul class="list-group">
        <li class="list-group-item">Categories</li>
        <c:forEach items="${categoryDataList}" var="category">
          <li class="list-group-item"><a href="u/${user.path}/blogs.html?category=${category.id}">${category.name}</a> (${category.nBlog})</li>
        </c:forEach>
      </ul>
      <ul class="list-group">
        <li class="list-group-item">Monthly blogs</li>
        <c:forEach items="${monthDataList}" var="month">
          <li class="list-group-item"><a href="u/${user.path}/blogs.html?month=${sf:toMonth(month.month)}">${sf:toMonth(month.month)}</a> (${month.nBlog})</li>
        </c:forEach>
      </ul>
      <ul class="list-group">
        <li class="list-group-item">Ranking</li>
      </ul>
      <div class="panel panel-default">
        <div class="panel-heading">Recent comments</div>
        <div class="panel-body"></div>
      </div>
    </div>
    <div class="col-lg-6">
      <c:if test="${blogList.totalElement == 0}">
        <div class="alert alert-info" role="alert">User doesn't have any blog.</div>
      </c:if>
      <c:if test="${blogList.totalElement > 0}">
        <c:if test="${listMode}">
          <div class="list-group">
            <c:forEach items="${blogList.content}" var="blog">
              <a href="u/${user.path}/blog/${blog.id}.html" class="list-group-item">
                <h4 class="list-group-item-heading">${blog.title}</h4>
              </a>
            </c:forEach>
          </div>
        </c:if>
        <c:if test="${empty listMode}">
          <div class="list-group">
            <c:forEach items="${blogList.content}" var="blog">
              <a href="u/${user.path}/blog/${blog.id}.html" class="list-group-item">
                <h4 class="list-group-item-heading">${blog.title}</h4>
                <p class="list-group-item-text">${blog.summary}</p>
                <p class="list-group-item-data">
                  <span class="number">${sf:toDate(blog.create)} </span><span class="tab"></span>
                  Views(<span class="number">${blog.nView}</span>)
                  Replies(<span class="number">${blog.nReply}</span>)
                  Likes(<span class="number">${blog.nLike}</span>)
                </p>
              </a>
            </c:forEach>
          </div>
        </c:if>
        <source:page page="${blogList}" url="${url}"/>
      </c:if>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />