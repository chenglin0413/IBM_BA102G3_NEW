<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>促銷專案列表</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/stylish-portfolio.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="js/bootstrap-datepicker3.min.css" />

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

</head>

<body>
	<br>
	<br>
	<table border="1" align="center">
		<tr height="79" style="background-color: rgb(229, 246, 253);">
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷編號</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷名稱</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷開始</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷結束</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">目前狀態</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">詳情修改</span><br />
		</tr>
		
		<jsp:useBean id="stpmSvc" scope="page" class="com.stpm.model.StpmService" />
		
		<c:forEach var="stpmVO" items="${stpmSvc.all}">
			<tr align='center' valign='middle'>
				<td>${stpmVO.stpm_id}</td>
				<td>${stpmVO.stpm_name}</td>
				<td>${stpmVO.stpm_startdate}</td>
				<td>${stpmVO.stpm_enddate}</td>
				<td>${stpmVO.stpm_status}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/store_interface/stpm.do">
						<input type="submit" value="修改"> <input type="hidden"
							name="stpm_id" value="${stpmVO.stpm_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
