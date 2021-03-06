<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.flsc.model.*"%>
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
<link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/css/stylish-portfolio.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">





	<%@include file="/front-end/member_interface/headerBar.file"%>



	<%
		FlscService flscSvc = new FlscService();
		List<FlscVO> list = flscSvc.getAll();
		pageContext.setAttribute("list", list);
	%>


<style type="text/css">
body {
	background-image: url(<%=request.getContextPath()%>/front-end/img/bg004.jpg);
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}
</style>
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

		<div class="container">
			<div class="row">

				<div>
					<ul class="nav nav-tabs">
						<li class="active"><a
							href="<%=request.getContextPath()%>/front-end/member_interface/listAllFlsc.jsp"><b>班機查詢</b></a></li>
						<li><a
							href="<%=request.getContextPath()%>/front-end/path/findPath.jsp">室內地圖導引</a></li>
					</ul>
					<br>
				</div>
				</div>
				</div>
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



			<table border="0" cellpadding="0" cellspacing="0"
				style="width: 100%;">
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
				<%@ include file="schedule1.file"%>
				<c:forEach var="flscVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle'}>
						<td>${flscVO.flsc_term}</td>
						<td>${flscVO.flsc_airlinecode}</td>
						<td>${flscVO.flsc_airline_c}</td>
						<td>${flscVO.flsc_flno}</td>
						<td>${flscVO.flsc_gate}</td>
						<td>${flscVO.flsc_sdate}</td>
						<td>${flscVO.flsc_stime}</td>
						<td>${flscVO.flsc_pdate}</td>
						<td>${flscVO.flsc_ptime}</td>
						<td>${flscVO.flsc_location_code}</td>
						<td>${flscVO.flsc_location_c}</td>
						<td>${flscVO.flsc_status}</td>
						<td>${flscVO.flsc_bag}</td>
						<td>${flscVO.flsc_checkin}</td>
					</tr>
				</c:forEach>
			</table>
	<%@ include file="schedule2.file"%>

	<%@ include file="/front-end/member_interface/script.file"%>
	<script src="https://code.jquery.com/jquery.js"></script>

	<script>
		$(document).ready(function() {
			$("tr:even").css("background-color", "#CFE0E1");
			$("tr:odd").css("background-color", "#ffffff");
		});
	</script>

	<script type='text/javascript'>
		$(document).ready(function() {
			if ($("[rel=tooltip]").length) {
				$("[rel=tooltip]").tooltip();
			}
		});
	</script>



</body>

</html>