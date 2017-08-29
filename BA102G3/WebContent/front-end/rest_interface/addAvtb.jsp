<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.avtb.model.*,com.user.model.*,com.rest.model.*"%>
<%@page import="java.sql.Timestamp"%>


<%
request.setCharacterEncoding("UTF-8");
AvtbVO avtbVO = (AvtbVO) request.getAttribute("avtbVO");
RestVO restVO = (RestVO) session.getAttribute("restVO");
%>
<!doctype html>
<html>
<head>
<title>新增可訂位人數及時段 - addAvtb.jsp</title>
<meta charset="UTF-8">	
 <!-- jQuery library -->

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />


<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/css_store/bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/css_store/stylish-portfolio.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

</head>

<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
<%@include file="headerBar.file" %>
<div class="callout"></div>

<h3>新增可訂位人數&時段</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<%
java.sql.Timestamp sql = new Timestamp(System.currentTimeMillis()); 
%>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/restaurant/avtb/avtb.do" name="form1">
<table border="0">

	
	<tr>
		<td>餐廳編號:</td>
		<td>${restVO.rest_name}</td>
	</tr>
	

	  <div class="col-xs-12 col-sm-6">
			<td>可預約時段 - 起始:</td>
			<td>
			<input type="text" name="avtb_date_s" value="<%=(avtbVO==null)? sql : avtbVO.getAvtb_date_s()%>">
			</td>
	  </div>
	  
	  <div class="col-xs-12 col-sm-6">
			<td>可預約時段 - 結束:</td>
			<td>
			<input type="text" name="avtb_date_e" value="<%=(avtbVO==null)? sql : avtbVO.getAvtb_date_e()%>">
			</td>
	  </div>
	  
	<tr>
		<td>目前訂位人數:</td>
		<td><input type="TEXT" name="avtb_reservation" size="45"
			value="<%= (avtbVO==null)? "0" : avtbVO.getAvtb_reservation()%>" readonly/></td>
	</tr>
	<tr>
		<td>最大訂位人數:</td>
		<td><input type="TEXT" name="avtb_max_reservation" size="45"
			value="<%= (avtbVO==null)? "20" : avtbVO.getAvtb_max_reservation()%>" required/></td>
	</tr>
	
</table>


<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="avtb_id" value="3500001">
<input type="hidden" name="rest_id" value="${restVO.rest_id}">
<input type="submit" value="送出新增"></FORM>

</body>

<script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->
    
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://jq-simple-dtpicker-gh-master.herokuapp.com/jquery.simple-dtpicker.js"></script>
<link type="text/css" href="http://jq-simple-dtpicker-gh-master.herokuapp.com/jquery.simple-dtpicker.css" rel="stylesheet" />


<script type="text/javascript">
		$(function(){
			$('*[name=avtb_date_s]').appendDtpicker({
				"inline": true,
				"futureOnly": true,
				"minTime":"06:00",
				"maxTime":"23:00"
			});
			$('*[name=avtb_date_e]').appendDtpicker({
				"inline": true,
				"futureOnly": true,
				"minTime":"06:00",
				"maxTime":"23:00"
			});
		});
</script>


</html>
