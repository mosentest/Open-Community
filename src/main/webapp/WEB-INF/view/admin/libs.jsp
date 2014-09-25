<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp">
  <jsp:param name="navigation" value="home" />
</jsp:include>
<section id="content" role="main" style="background: #f5f5f5;">
<header class="aui-page-header">
  <div class="aui-page-header">
    <div class="aui-page-header-inner">
      <h1>Jar Repository</h1>
    </div>
  </div>
</header>
<div class="aui-page-panel">
  <div class="aui-page-panel-inner">
    <aside class="aui-page-panel-sidebar sidebar-left adg-sidebar">
      <nav class="aui-navgroup aui-navgroup-vertical" style="margin-top: 0px;">
        <div class="aui-navgroup-inner stalking-position">
          <div class="aui-navgroup-primary">
            <ul class="aui-nav __skate">
               <div class="aui-nav-heading">search/tags</div>
                <c:forEach items="${tags}" var="tag">
               <li><a href="java/libs.html?tag=${tag.tag}">${tag.tag}</a></li>
               </c:forEach>
            </ul>
          </div><!--.aui-navgroup-primary  -->
        </div><!--.aui-navgroup-inner  -->
      </nav>
    </aside><!--.aui-page-panel-sidebar  -->
    <section class="aui-page-panel-content">
        <div class="sections recommend-repos">
          <h2 class="recommend-heading">
            <span class="more"><a href="#">more»</a></span>
            <span class="aui-icon aui-icon-small aui-iconfont-homepage"></span>
            latest
          </h2>
          <ul class="repo-list">
          <c:forEach items="${page.content}" var="jarFile">
              <li>
                <h3>
                  <a href="java/jar/${jarFile.name}.html" target="_blank">${jarFile.name}</a>
                  <span class="uploader">
                    <c:choose>
                      <c:when test="${!empty jarFile.uploader}">${jarFile.uploader }</c:when>
                      <c:otherwise>unknow uploader</c:otherwise>
                    </c:choose>
                  </span>
                  <p class="description">
                    <c:choose>
                      <c:when test="${!empty  jarFile.description}">${jarFile.description }</c:when>
                      <c:otherwise>unkonw description</c:otherwise>
                    </c:choose>
                  </p>
                </h3>
              </li>
            </c:forEach>
          </ul>
        </div>
    </section>
    <aside class="aui-page-panel-sidebar sidebar-right">
      <div class="aui-nav-heading">most downloaded</div>
        <!--选项卡-->
        <div class="aui-tabs horizontal-tabs" id="tabs-example1" role="appliaction">
          <ul class="tabs-menu" role="tablist">
            <!--第一个选项-->
            <li class="menu-item active-tab" role="presentation">
                <a href="#tabs-example-first" id="aui-uid-0" role="tab">
                      <strong>Today</strong>
                </a>
             </li>
             <!--第二个选项-->
             <li class="menu-item" role="presentation">
                <a href="#tabs-example-second" id="aui-uid-1" role="tab">
                      <strong>Week</strong>
                </a>
              </li>
              <!--第三个选项-->
              <li class="menu-item" role="presentation">
                <a href="#tabs-example-third" id="aui-uid-2" role="tab">
                      <strong>Month</strong>
                </a>
              </li>
          </ul>
          <!--第一个选项卡内容-->
                <div class="tabs-pane active-pane" id="tabs-example-first" role="tabpanel" aria-labelledby="aui-uid-0">
                  <h3 style="color: #f95844;">Today</h3>
                  <table class="aui">
                    <thead>
                      <tr>
                        <th id="name"> name </th>
                        <th id="download"> download</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${downloadJarFiles.content }" var="downlaodJarFile">
                      <tr>
                        <td headers="name"><a href="java/jar/${downlaodJarFile.name}.html">${downlaodJarFile.name}</a></td>
                        <td headers="download">
                        <c:choose>
                          <c:when test="${downloadJarFile.download  == null}">0</c:when>
                          <c:otherwise>${downloadJarFile.download}</c:otherwise>
                        </c:choose>
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div><!--tabs-example-first-->
          <!--第二个选项卡内容-->
                <div class="tabs-pane" id="tabs-example-second" role="tabpanel" aria-labelledby="aui-uid-1">
                  <h3 style="color: #f95844;">Week</h3>
                  <table class="aui">
                    <thead>
                      <tr>
                        <th id="name"> name </th>
                        <th id="download"> download</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${downloadJarFiles.content }" var="downlaodJarFile">
                      <tr>
                        <td headers="name"><a href="java/jar/${downlaodJarFile.name}.html">${downlaodJarFile.name}</a></td>
                        <td headers="download">
                          <c:choose>
                          <c:when test="${downloadJarFile.download  == null}">0.0</c:when>
                          <c:otherwise>${downloadJarFile.download}</c:otherwise>
                        </c:choose>
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div><!--tabs-example-second-->
          <!--第三个选项卡内容-->
                <div class="tabs-pane" id="tabs-example-third" role="tabpanel" aria-labelledby="aui-uid-2">
                  <h3 style="color: #f95844;">Month</h3>
                  <table class="aui">
                    <thead>
                      <tr>
                        <th id="name"> name </th>
                        <th id="download"> download</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${downloadJarFiles.content }" var="downlaodJarFile">
                      <tr>
                        <td headers="name"><a href="java/jar/${downlaodJarFile.name}.html">${downlaodJarFile.name}</a></td>
                        <td headers="download">
                         <c:choose>
                          <c:when test="${downloadJarFile.download  == null}">00</c:when>
                          <c:otherwise>${downloadJarFile.download}</c:otherwise>
                        </c:choose>
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div><!--tabs-example-third-->
      </div><!-- // .aui-tabs -->
    </aside>
  </div>
</div>
</section>
<jsp:include page="/WEB-INF/view/footer.jsp" />