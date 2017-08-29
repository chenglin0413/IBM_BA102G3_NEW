<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.repm.model.*,com.user.model.*,com.store.model.*,com.prod.model.*"%>


<!-- click -->
<%
	if (session.getAttribute("userVO") != null) {
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		StoreService storeSvc = new StoreService();
		StoreVO storeVO = storeSvc.getOneStoreByUsed_Id(userVO.getUser_id());
		Integer store_id = storeVO.getStore_id();
		RepmService stpmSvc = new RepmService();
		List<RepmVO> list = stpmSvc.findByRestID(store_id);
		pageContext.setAttribute("list", list);
	}

	Integer rest_id = 3000001;
	RepmService stpmSvc = new RepmService();
	List<RepmVO> list = stpmSvc.findByRestID(rest_id);
	pageContext.setAttribute("list", list);

	String zero = "資訊關閉";
	String one = "資訊開啟";
%>


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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker3.min.css" />

<style type="text/css">
th {
	text-align: center;
	font-size: 20px;
}

td {
	text-align: center;
	font-size: 18px;
}
</style>

</head>

<body>

	<%@ include file="headerBar.file"%>




	<!-- Header -->
	<div class="callout"></div>

	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<h3 class="page-header">促銷專案列表</h3>
		</div>
	</div>

	<br>
	<br>
	<div class="col-md-12">

		<!-- 	<table border="1" align="center"> -->
		<!-- 		<tr height="79" style="background-color: rgb(229, 246, 253);"> -->
		<!-- 			<td height="79" -->
		<!-- 				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span -->
		<!-- 				style="color: rgb(229, 246, 253);">促銷編號</span><br /> -->
		<!-- 			<td height="79" -->
		<!-- 				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span -->
		<!-- 				style="color: rgb(229, 246, 253);">促銷名稱</span><br /> -->
		<!-- 			<td height="79" -->
		<!-- 				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span -->
		<!-- 				style="color: rgb(229, 246, 253);">促銷開始</span><br /> -->
		<!-- 			<td height="79" -->
		<!-- 				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span -->
		<!-- 				style="color: rgb(229, 246, 253);">促銷結束</span><br /> -->
		<!-- 			<td height="79" -->
		<!-- 				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span -->
		<!-- 				style="color: rgb(229, 246, 253);">目前狀態</span><br /> -->
		<!-- 			<td height="79" -->
		<!-- 				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span -->
		<!-- 				style="color: rgb(229, 246, 253);">詳情修改</span><br /> -->
		<!-- 		</tr> -->

		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>促銷編號</th>
					<th>促銷名稱</th>
					<th>促銷開始</th>
					<th>促銷結束</th>
					<th>目前狀態</th>
					<th>詳情修改</th>
				</tr>

				<c:forEach var="repmVO" items="${list}">
					<tr align='center' valign='middle'>
						<td>${repmVO.repm_id}</td>
						<td>${repmVO.repm_name}</td>
						<td>${repmVO.repm_startdate}</td>
						<td>${repmVO.repm_enddate}</td>

						<td><c:if test="${repmVO.repm_status == 0}">
								<%=zero%>
							</c:if> <c:if test="${repmVO.repm_status == 1}">
								<%=one%>
							</c:if></td>
						<td>

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/restaurant/repm.do">
								<button type="submit" class="btn btn-default btn-sm">修改專案</button>
								<input type="hidden" name="repm_id" value="${repmVO.repm_id}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<div class="callout"></div>


	<script
		src="<%=request.getContextPath()%>/front-end/js_store/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker.js"></script>

	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker.zh-TW.js"></script>

</body>

</html>