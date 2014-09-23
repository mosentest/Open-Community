<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<style type="text/css">
	.access-title {
		font-size: 32px;
	}
	#access-content {
		width: 800px;
		margin: 20px auto;
	}
</style>
<div id="content">
	<div class="sub-element">
		<div class="sub-element-info"><div class="sub-element-title">403 - Access denied</div></div>
		<div class="sub-element-content">
			<div id="access-content">
				<table class="default-table center">
					<tr>
						<td><span class="access-title">Unauthorized Access</span></td>
					</tr>
					<tr>
						<td><img src="assets/img/accessdenied.png"/></td>
					</tr>
					<tr class="separator-tr"><td></td></tr>
					<tr>
						<td></td>
					</tr>
					<tr class="separator-tr"><td></td></tr>
					<tr>
						<c:if test="${sessionScope.online}">
						<td><a class="link" href="dashboard.html">Goto gome page</a></td>
						</c:if>
						<c:if test="${not sessionScope.online}">
						<td><a class="link" href="login">Goto login Page</a></td>
						</c:if>
					</tr>
				</table>
			</div>			
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />