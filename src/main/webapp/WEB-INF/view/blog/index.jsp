<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<jsp:include page="/WEB-INF/view/blog/header.jsp">
  <jsp:param name="nav" value="home" />
</jsp:include>
<div id="content">
  <div class="row">
    <div class="col-sm-offset-3 col-lg-6">
      <form action="blog/search.html" method="GET">
        <div class="input-group">
          <input class="form-control" id="search-blog"
       	 	name="query" title="Search" placeholder="Search for blogs...">
          <span class="input-group-btn">
            <button class="btn btn-primary" type="submit">Search</button>
          </span>
        </div>
      </form>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-offset-7 col-lg-2">
      <a href="blog/advancedSearch.html">Advanced search</a>
      <span class="tab-long"></span>
      <a href="source/advancedSearch.html">History</a>
    </div>
  </div>
  <div class="separator"></div>
  <div class="row">
    <div class="col-lg-3">
      <ul class="list-group">
        <li class="list-group-item">Recent Blogs</li>
        <c:forEach items="${recentList.content}" var="blog">
          <li class="list-group-item"><a href="u/${blog.user.path}/blog/${blog.id}.html">${blog.title} </a></li>
        </c:forEach>
      </ul>
      <ul class="list-group">
        <li class="list-group-item">Top Blogger</li>
        <c:forEach items="${recentList.content}" var="blog">
          <li class="list-group-item"><a href="u/${blog.user.path}/blog/${blog.id}.html">${blog.title} </a></li>
        </c:forEach>
      </ul>
      <div class="list-group">
      </div>
    </div>
    <div class="col-lg-6">
      <div class="panel panel-default">
        <div class="panel-heading">Featured Blogs</div>
        <div class="list-group">
          <c:forEach items="${featuredList.content}" var="blog">
            <a href="u/${blog.user.path}/blog/${blog.id}.html" class="list-group-item">
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
          <div class="pull-right"><source:page page="${featuredList}" url=""/></div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
    <div class="col-lg-3">
      <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="javascript:void();" onclick="javascript:tabToggle(this, 'daily', 'blog-list');">Daily</a></li>
        <li><a href="javascript:void();" onclick="javascript:tabToggle(this, 'weekly', 'blog-list');">Weekly</a></li>
        <li><a href="javascript:void();" onclick="javascript:tabToggle(this, 'monthly', 'blog-list');">Monthly</a></li>
      </ul>
      <ul id="daily" class="list-group blog-list">
        <c:forEach items="${viewDayList}" var="blog">
          <li class="list-group-item"><a href="u/${blog.user.path}/blog/${blog.id}.html">${blog.title} </a></li>
        </c:forEach>
      </ul>
      <ul id="weekly" class="list-group blog-list" style="display:none;">
        <c:forEach items="${viewWeekList}" var="blog">
          <li class="list-group-item"><a href="u/${blog.user.path}/blog/${blog.id}.html">${blog.title} </a></li>
        </c:forEach>
      </ul>
      <ul id="monthly" class="list-group blog-list" style="display:none;">
        <c:forEach items="${viewMonthList}" var="blog">
          <li class="list-group-item"><a href="u/${blog.user.path}/blog/${blog.id}.html">${blog.title} </a></li>
        </c:forEach>
      </ul>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />