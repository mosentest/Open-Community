<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="source" uri="http://www.source.com/lib" %>
<%@ taglib prefix="sf" uri="http://www.source.com/sourcefunction" %>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="nav" value="library" />
</jsp:include>
<div id="content">
  <div class="row">
    <div class="col-lg-8">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3>${javaLibrary.name}</h3>
          <a href="file/download.html?file=${javaLibrary.id}">Download ${javaLibrary.name}</a>
        </div>
        <div class="panel-body">
          <table class="table info">
            <tr class="none-border">
              <td colspan="4">Library Information</td>
            </tr>
            <tr>
              <td>Category</td>
              <td colspan="3">
                <c:forEach items="${javaLibrary.categories}" var="category">
                  <span class="label label-default-inverse">
                    <a href="source/libraries.html?category=${category}">${category}</a>
                  </span>
                </c:forEach>
              </td>
            </tr>
            <tr>
              <td>Description</td>
              <td colspan="3"><small>${javaLibrary.description}</small></td>
            </tr>
            <tr>
              <td>Git Repository</td>
              <td><a href="https://github.com/Zimuuu/Code">https://github.com/Zimuuu/Code</a></td>
              <td>Git Owner</td>
              <td>Spring framework</td>
            </tr>
            <tr>
              <td>File Size</td>
              <td>${sf:sizeInfo(javaLibrary.size)}</td>
              <td>Files</td>
              <td>${javaLibrary.nFile}</td>
            </tr>
            <tr>
              <td>Uploaded</td>
              <td></td>
              <td>MD5 Checksum</td>
              <td>${javaLibrary.md5}</td>
            </tr>
            <tr>
              <td>Downloads Total</td>
              <td>${javaLibrary.nDownload}</td>
              <td colspan="2">
                <a href="statistics/download.html?file=${javaLibrary.id}&type=week">See download statistics</a>
              </td>
            </tr>
            <tr>
              <td>Downloads</td>
              <td colspan="3">
                <c:if test="${javaLibrary.source != null}">Source: 
                  <a href="file/download.html?file=${javaLibrary.source}&jtype=src">${javaLibrary.source}</a><br/>
                </c:if>
                <c:if test="${javaLibrary.javadoc != null}">JavaDoc: 
                  <a href="file/download.html?file=${javaLibrary.javadoc}.jar&jtype=doc">${javaLibrary.javadoc}</a>
                </c:if>
              </td>
            </tr>
            <tr>
              <td>Tags</td>
              <td colspan="3">
                <c:forEach items="${javaLibrary.tags}" var="tag">
                  <span class="label label-primary-inverse">
                    <a href="source/libraries.html?tag=${tag}">${tag}</a>
                  </span>
                </c:forEach>
              </td>
            </tr>
          </table>
          <source:javaLib javaLibrary="${javaLibrary}"/>
        </div>
      </div>
    </div>
    <div class="col-lg-4">
      <div class="panel panel-default">
        <div class="panel-heading">Project Information</div>
        <div class="panel-body">
          <table class="table">
            <tr class="none-border">
              <td>Project</td>
              <td>Source Community</td>
            </tr>
            <tr>
              <td>Project Homepage</td>
              <td><a href="https://github.com/Zimuuu/Code">https://github.com/Zimuuu/Code</a></td>
            </tr>
            <tr>
              <td>Version</td>
              <td>0.0.1-SNAPSHOT</td>
            </tr>
            <tr>
              <td>Maven Repository</td>
               <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />