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
    <source:breadCrumb library="${javaLibrary.name}" path="${directory}"/>
  </div>
  <div class="row">
    <div class="col-lg-4">
      <div class="panel panel-default">
        <div class="panel-heading">Library structure</div>
        <div class="panel-body">
          <source:structure library="${javaLibrary}" roots="${javaLibrary.structure}" name="${directory}" ext=""/>
        </div>
      </div>
    </div>
    <div class="col-lg-8">
      <div class="panel panel-default">
        <div class="panel-heading">
  	      <h3><a href="#">Download all files</a></h3>
        </div>
        <div class="panel-body">
          <table class="table">
            <tr class="none-border">
              <td>Library</td>
              <td>${javaLibrary.name}</td>
              <td>Package</td>
              <td>${directory}</td>
            </tr>
            <tr>
              <td>Size</td>
              <td>${sf:sizeInfo(libraryDir.size)}</td>
              <td>Files</td>
              <td>${libraryDir.nFile}</td>
            </tr>
          </table>
          <pre class="language-">
            <source:structure library="${javaLibrary}" roots="${libraryDir.files}" expand="true"/>
          </pre>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />