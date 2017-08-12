<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stpm.model.*,com.prod.model.*,com.prpm.model.*"%>

<%
	PrpmVO prpmVO = (PrpmVO) request.getAttribute("prpmVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>詳情修改</title>

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
<link rel="stylesheet" href="js/bootstrap-datepicker3.min.css" />

</head>

<body>

	<%@ include file="headerBar.file"%>

	<div class="container">
		<div class="row"></div>
		<div class="col-xs-12 col-sm-12" align="center">

			<br>

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
			</div>

			<div class="callout"></div>



<!-- 			<div id="page-wrapper"> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-10 col-md-offset-1"> -->
<!-- 						<h3 class="page-header">修改促銷產品</h3> -->
<!-- 					</div> -->
<!-- 					/.col-lg-12 -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div id="page-wrapper col-md-12"> -->
<!-- 				<div class="col-md-6"> -->
<!-- 					<ol class="breadcrumb"> -->
<!-- 						<li><a -->
<%-- 							href="<%=request.getContextPath()%>/front-end/store_interface/listMyAllStpm.jsp">查詢所有促銷專案</a> --%>
<!-- 						</li> -->
<!-- 						<li class="active">修改促銷產品</li> -->
<!-- 					</ol> -->
<!-- 				</div> -->

<!-- 			</div> -->

			<FORM METHOD="post" ACTION="prpm.do" name="form1">
				<table border="0">
					<tr>
						<td><input type="hidden" name="stpm_id"
							value="<%=prpmVO.getStpm_id()%>"></td>
					</tr>
					<tr>
						<td><label>促銷商品:&nbsp;&nbsp;</label><font color=red><b>＊</b></font></td>
						<td><%=prpmVO.getProd_id()%></td>
					</tr>
					<tr>
						<td><label>促銷價格:&nbsp;&nbsp;</label></td>
						<td><input type="number" name="prpm_price"
							required="required" value="<%=prpmVO.getPrpm_price()%>" /></td>
					</tr>
					<tr>
						<td><input type="hidden" name="prpm_status"
							value="<%=prpmVO.getPrpm_status()%>"></td>
					</tr>
				</table>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="prod_id" value="<%=prpmVO.getProd_id()%>">
				<input type="submit" value="送出確認">
			</FORM>
		</div>
	</div>

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
