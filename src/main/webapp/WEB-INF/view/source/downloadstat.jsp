<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/source/header.jsp">
  <jsp:param name="nav" value="home" />
</jsp:include>
<script src="assets/js/highchart.min.js"></script>
<script>
$(function () {
	var labels = [];
	<%
    	java.util.List<String> labels = (java.util.List<String>) request.getAttribute("labels");
    	String previous = null;
    	for (String label : labels) {
    		if (label != null) {
    			previous = label;
    		}
    		out.println("labels.push('" + previous + "');");
    	}
	%>
	var data = [];
	<c:forEach items="${values}" var="value">
	data.push(parseInt("${value}"));
	</c:forEach>
	var interval = data.length > 10 ? 2 : 1;
	interval = data.length > 50 ? 15 : interval;
	var chart = {
		type: 'spline',
		title: {
			text: '${title}',
			x: -20 
		},
		subtitle: {
			text: '${subTitle}',
			x: -20
		},
		xAxis: {
			categories: labels,
			tickInterval: interval,
			labels: {
				step: data.length < 10 ? 1 : 2
			}
		},
		yAxis: {
			title: {
				text: 'Downloads'
			}
		},
		series: [{
			name: '${file}',
			data: data
		}]
	};
	$('#container').highcharts(chart);
});
 </script>
<div id="content">
  <a href="source/library/${javaLibrary.name}.html">
    <span class="glyphicon glyphicon-arrow-left"></span>
    <span>Go back to library</span>
  </a>
  <div class="panel panel-default">
    <div class="panel-heading">Statistics</div>
    <div class="panel-body">
      <ul class="nav nav-tabs" role="tablist">
        <li class="${type eq 'week' ? 'active' : ''}"><a href="statistics/download.html?file=${javaLibrary.id}&type=week">Last Week</a></li>
        <li class="${type eq 'month' ? 'active' : ''}"><a href="statistics/download.html?file=${javaLibrary.id}&type=month">Last Month</a></li>
        <li class="${type eq 'year' ? 'active' : ''}"><a href="statistics/download.html?file=${javaLibrary.id}&type=year">Last Year</a></li>
      </ul>
      <div id="container" style="height: 500px; margin:0px auto;"></div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />