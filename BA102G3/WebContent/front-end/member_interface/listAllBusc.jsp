<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.busc.model.*"%>

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>客運時刻表</title>

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
		BuscService buscSvc = new BuscService();
		List<BuscVO> list = buscSvc.getAll();
		pageContext.setAttribute("list", list);
	%>

</head>
<body bgcolor='white'>

	<div align="center">
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
	</div>
	<br>

	<table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
		<tr height="79" style="background-color: rgb(255, 228, 139);">
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">終點站</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">客運業者</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">發車時間</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(19, 101, 109);"><span
				style="color: rgb(255, 228, 139);">停靠站別</span><br />
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="buscVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'}>
				<td>${buscVO.busc_end}</td>
				<td>${buscVO.busc_line}</td>
				<td>${buscVO.busc_time}</td>
				<td>${buscVO.busc_route}</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
<%@ include file="/front-end/member_interface/script.file" %>
	<script>
		$(document).ready(function() {
			$("tr:even").css("background-color", "#CFE0E1");
			$("tr:odd").css("background-color", "#ffffff");
		});
	</script>
	
</body>

</html>
