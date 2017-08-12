<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>航班時刻表</title>

<!-- Bootstrap Core CSS -->
<link href="./css/bootstrap.css" rel="stylesheet">

</head>

<body>

	<%@ include file="schedule_top_bar.file"%>

	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.flsc.model.*"%>

	<%
		FlscVO flscQuery = (FlscVO) request.getAttribute("flscQuery");
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

		<FORM METHOD="post" ACTION="flsc.do">

			<%
				java.sql.Date sdate_SQL = new java.sql.Date(System.currentTimeMillis());
			%>

			<b>航空代碼&班次編號:</b> <input type="text" name="flsc_airlinecode"
				size="20"> <input type="text" name="flsc_flno" size="20">
			<b>表定日期：</b> <input type="date" name="flsc_sdate"
				value="<%=sdate_SQL%>"> <input type="submit" value="送出">
			<input type="hidden" name="action"
				value="getOne_For_Display_inlistAllFlsc">
		</FORM>

	</div>
	<br>
	<br>

	<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
		<tr height="79" style="background-color: rgb(255, 228, 139);">
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">航廈</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">航空代號</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">航空中文</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">班次</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">登機門</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">表定日期</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">表定時間</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">預計日期</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">預計時間</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">往來地點</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">往來地點(中)</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">狀態</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">行李轉盤</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">報到櫃台</span><br />
		</tr>
		<tr align='center' valign='middle'}>
			<td>${flscQuery.flsc_term}</td>
			<td>${flscQuery.flsc_airlinecode}</td>
			<td>${flscQuery.flsc_airline_c}</td>
			<td>${flscQuery.flsc_flno}</td>
			<td>${flscQuery.flsc_gate}</td>
			<td>${flscQuery.flsc_sdate}</td>
			<td>${flscQuery.flsc_stime}</td>
			<td>${flscQuery.flsc_pdate}</td>
			<td>${flscQuery.flsc_ptime}</td>
			<td>${flscQuery.flsc_location_code}</td>
			<td>${flscQuery.flsc_location_c}</td>
			<td>${flscQuery.flsc_status}</td>
			<td>${flscQuery.flsc_bag}</td>
			<td>${flscQuery.flsc_checkin}</td>
		</tr>
	</table>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$("tr:even").css("background-color", "#CFE0E1");
			$("tr:odd").css("background-color", "#ffffff");
		});
	</script>

</body>

</html>
