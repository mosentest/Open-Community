<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="nav" value="code" />
</jsp:include>
<script src="assets/js/prism.js"></script>
<link href="assets/css/prism.css" type="text/css" rel="stylesheet" />
<div id="content">
  <div class="row">
    <source:breadCrumb library="${javaLibrary.name}" path="${javaFile.path}" file="${javaFile.name}"/>
  </div>
  <div class="row">
    <div class="col-lg-4">
      <div class="panel panel-default">
        <div class="panel-heading">Library structure</div>
        <div class="panel-body">
          <source:structure library="${javaLibrary}" roots="${javaLibrary.structure}" name="${javaFile.name}" ext="${javaFile.extension}"/>
        </div>
      </div>
    </div>
    <div class="col-lg-8">
      <div class="panel panel-default">
        <div class="panel-heading">
  	      <h3><a href="#">Download ${javaFile.name}.${javaFile.extension}</a></h3>
        </div>
        <div class="panel-body">
          <table class="table">
            <tr class="none-border">
              <td>Library</td>
              <td>${javaFile.library}</td>
              <td>Package</td>
              <td>${javaFile.path}</td>
            </tr>
            <tr>
              <td>Size</td>
              <td>${sf:sizeInfo(javaFile.size)}</td>
              <td>Lines</td>
              <td>${javaFile.nLine}</td>
            </tr>
            <tr>
              <td>Tags</td>
              <td colspan="3">
                <c:forEach items="${javaFile.tags}" var="tag">
                  <span class="label label-primary-inverse"><a href="source/libraries.html?tag=${tag}">${tag}</a></span>
                </c:forEach>
              </td>
            </tr>
          </table>
          <c:choose>
            <c:when test="${javaFile.extension eq 'xml' or javaFile.extension eq 'tld'}">
         	  <pre><code class="language-markup">${javaFile.content}</code></pre>
            </c:when>
            <c:otherwise>
              <pre><code class="language-java">${javaFile.content}</code></pre>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />