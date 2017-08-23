<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.flsc.model.*"%>
<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.35">
<meta name="description" content="">
<meta name="author" content="">

<title>我的地圖</title>

<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

</head>

<body>

	 <%@include file="/front-end/member_interface/headerBar.file" %>

	<%

	    String user_id=request.getParameter("user_id");
	
	    String flsc_sdate1=request.getParameter("flsc_sdate1");
	    String flsc_airlinecode1=request.getParameter("flsc_airlinecode1");
	    String flsc_flno1=request.getParameter("flsc_flno1");
	
	    String flsc_sdate2=request.getParameter("flsc_sdate2");
	    String flsc_airlinecode2=request.getParameter("flsc_airlinecode2");
	    String flsc_flno2=request.getParameter("flsc_flno2");
	    
	    System.out.println("----------------------------");
	    System.out.println(user_id);
	    System.out.println(flsc_sdate1);
	    System.out.println(flsc_airlinecode1);
	    System.out.println(flsc_flno1);
	    
	    System.out.println(flsc_sdate2);
	    System.out.println(flsc_airlinecode2);
	    System.out.println(flsc_flno2);
	    System.out.println("----------------------------");
	    
		
    %>

</head>
<body bgcolor='white'>

	<div align="center">
		<br>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>錯誤: <c:forEach var="message"
					items="${errorMsgs}">
					<a>${message}</a>
				</c:forEach>

			</font>
		</c:if>
		
<div>
		
		${param.flsc_sdate1}, 抵達航班:${param.flsc_airlinecode1}${param.flsc_flno1}<br>
		${param.flsc_sdate2}, 出發航班:${param.flsc_airlinecode2}${param.flsc_flno2}<br>
		
		
		<img src="<%= request.getContextPath() %>/front-end/image/tagYellow.png" width=40><b>:轉機安檢出口</b>
		<img src="<%= request.getContextPath() %>/front-end/image/tagBlue.png" width=40><b>:商店取貨</b>
		<img src="<%= request.getContextPath() %>/front-end/image/tagGreen.png" width=40><b>:已訂位的餐廳</b>
		<img src="<%= request.getContextPath() %>/front-end/image/tagRed.png" width=40><b>:下一航班登機口</b>
		
		<br>
		
		<img id="image1" src="<%= request.getContextPath() %>/front-end/map/createMyMap.do?user_id=${param.user_id}&flsc_sdate1=${param.flsc_sdate1}&flsc_airlinecode1=${param.flsc_airlinecode1}&flsc_flno1=${param.flsc_flno1}&flsc_sdate2=${param.flsc_sdate2}&flsc_airlinecode2=${param.flsc_airlinecode2}&flsc_flno2=${param.flsc_flno2}" width=1000>

</div>

	</div>
	
	<br>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$("tr:even").css("background-color", "#CFE0E1");
			$("tr:odd").css("background-color", "#ffffff");
		});
	</script>
	
	<link rel="stylesheet" href="./leaflet.css">
	<script src="./leaflet.js"></script>
	<script src="./jquery-ui.min.js"></script>
	<script src="./imgViewer2.js"></script>

</body>


<script>

$("#image").imgViewer2();

$("#image").imgViewer2({

	  // zoom step
	  zoomStep: 0.5,

	  // the limit on the maximum zoom level of the image
	  zoomMax: undefined,

	  // is zoomable
	  zoomable: true,

	  // is draggable
	  dragable: true,

	  // callbacks
	  onClick: $.noop,
	  onReady: $.noop
	  
	});
</script>

</html>