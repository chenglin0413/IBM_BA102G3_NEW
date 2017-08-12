
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stpm.model.*,com.store.model.*,com.prod.model.*"%>

<%
	StpmVO stpmVO = (StpmVO) request.getAttribute("stpmVO");
	Integer store_id = (Integer) stpmVO.getStore_id();
	StoreService storeSvc = new StoreService();
	List<ProdVO> list = storeSvc.findByAllProd(store_id);
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>促銷商品列表</title>

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
.content: {
	position: relative;
}
</style>
</head>

<body>

	<%@ include file="headerBar.file"%>
	
	<div class="callout"></div>

	<div class="container">
		<div class="row"></div>
		<div class="col-xs-12 col-sm-12" align="center">

			<br>
			
			<div class="callout"></div>
			<div align="center">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty ansMsgs}">
					<font color='blue'>※<c:forEach var="message"
							items="${ansMsgs}">
							<b>${message}</b>
						</c:forEach>
					</font>
				</c:if>
				<c:if test="${not empty errorMsgs}">
					<font color='red'>※<c:forEach var="message"
							items="${errorMsgs}">
							<b>${message}</b>
						</c:forEach>
					</font>
				</c:if>
			</div>
			
			<div class="callout"></div>

<!-- 			<div id="page-wrapper"> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-10 col-md-offset-1"> -->
<!-- 						<h3 class="page-header">新增促銷產品</h3> -->
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
<!-- 						<li class="active">新增促銷商品</li> -->
<!-- 					</ol> -->
<!-- 				</div> -->

<!-- 			</div> -->

				<FORM METHOD="post" ACTION="prpm.do" name="form1">
					<table border="0">
						<tr>
							<td><input type="hidden" name="stpmVO" value="<%=stpmVO%>"></td>
						</tr>

						<tr>
							<td><label>促銷編號:&nbsp;&nbsp;</label><font color=red><b>＊</b></font></td>
							<td><%=stpmVO.getStpm_id()%><input type="hidden"
								name="stpm_id" value="<%=stpmVO.getStpm_id()%>"></td>
						</tr>
						<tr>
							<td><label>商店代號:&nbsp;&nbsp;</label><font color=red><b>＊</b></font></td>
							<td><%=stpmVO.getStore_id()%> <input type="hidden"
								name="store_id" value="<%=stpmVO.getStore_id()%>"></td>
						</tr>
						<tr>
							<td><label>選擇商品:&nbsp;&nbsp;</label></td>
							<td><select name="prod_id">
									<c:forEach var="prodVO" items="${list}">
										<option value="${prodVO.prod_id}">${prodVO.prod_name}-原始價格:$${prodVO.prod_price}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><label>促銷價格:$&nbsp;&nbsp;</label></td>
							<td><input type="number" name="prpm_price"	required="required" size="15"></td>
						</tr>
						<tr>
							<!--促銷狀態 -->
							<td><input type="hidden" name="prpm_status" value="0">
							</td>
						</tr>
					</table>
					<input type="hidden" name="action" value="insert"> <input type="submit" value="新增促銷商品">
				</FORM>
				<br> <br>
			</div>
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