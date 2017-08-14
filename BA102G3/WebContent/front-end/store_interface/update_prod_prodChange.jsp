<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*,com.prod.model.*,com.prpi.model.*"%>

<%
	ProdVO prodVO = (ProdVO) request.getAttribute("prodVO");
	pageContext.setAttribute("prodVO", prodVO);
	Integer prod_id = (Integer) prodVO.getProd_id();
	PrpiService prpiSvc = new PrpiService();
	PrpiVO prpiVO = prpiSvc.getOnePrpiByProd(prod_id);
	pageContext.setAttribute("prpiVO", prpiVO);
	session.setAttribute("prpiVO", prpiVO);
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>OneStore_order_list</title>

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
	href="<%=request.getContextPath()%>/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.item img {
	height: 300px;
	width: 100%;
}

th {
	text-align: center;
	font-size: 14px;
}
</style>
</head>

<body>


	<%@include file="headerBar.file"%>
	<!-- Header -->
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

	<div id="page-wrapper">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<h3 class="page-header">修改商品資料</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>

	<script>
		var loadFile = function(event) {
			var output = document.getElementById('output');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>


	<div class="container">
		<div class="row">
			<div class="col-md-6 col-xs-12">
				<FORM ACTION="<%=request.getContextPath()%>/front-end/store_interface/prod.do" method=post name="form1" enctype="multipart/form-data">
					<div>
							<label>圖片名稱:&nbsp;&nbsp;</label>
							<input type="TEXT" name="prpi_name" required="required"	value="${prpiVO.prpi_name}" size="20" />
							<img id="output" src="<%=request.getContextPath()%>/front-end/store_interface/DBGifReader?prod_id=${prodVO.prod_id}" height="350" width="400" />
							<input type="file" name="prpi_img" multiple accept="image/*" onchange="loadFile(event)" />
			</div>
		</div>
		
		
		
		
		
		<div class="col-md-6 col-xs-12">
			<div class="row">
				<div class="col-md-12 col-xs-6">
					<div class="row">

							<table border="0">
								<tr>
									<td><label>產品編號:&nbsp;&nbsp;</label></td>
									<td>${prodVO.prod_id}</td>
								</tr>
								<tr>
									<td><label>商家編號:&nbsp;&nbsp;</label></td>
									<td>${prodVO.store_id}</td>
								</tr>
								<tr>
									<td><label>產品名稱:&nbsp;&nbsp;</label></td>
									<td><input type="TEXT" name="prod_name" size="20"
										value="${prodVO.prod_name}" /></td>
								</tr>
								<tr>
									<td><label>產品價格:&nbsp;&nbsp;</label></td>
									<td><input type="number" name="prod_price" size="20"
										value="${prodVO.prod_price}" /></td>
								</tr>

								<tr>
									<td><label>產品種類:&nbsp;&nbsp;</label></td>
									<td><input type="TEXT" name="prod_sort" size="20"
										value="${prodVO.prod_sort}" /></td>
								</tr>
								<tr>
									<td><label>產品規格:&nbsp;&nbsp;</label></td>
									<td><input type="TEXT" name="prod_format" size="20"
										value="${prodVO.prod_format}" /></td>
								</tr>
								<tr>
									<td><label>產品品牌:&nbsp;&nbsp;</label></td>
									<td><input type="TEXT" name="prod_brand" size="20"
										value="${prodVO.prod_brand}" /></td>
								</tr>
<!-- 								<tr> -->
<!-- 									<td><label>更新時間:&nbsp;&nbsp;</label></td> -->
<%-- 									<td>${prodVO.prod_updatetime}</td> --%>
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td><label>售出數量:&nbsp;&nbsp;</label></td> -->
<%-- 									<td>${prodVO.prod_soldcount}</td> --%>
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td><label>獲得評分:&nbsp;&nbsp;</label></td> -->
<%-- 									<td>${prodVO.prod_count}</td> --%>
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td><label>評分總分:&nbsp;&nbsp;</label></td> -->
<%-- 									<td>${prodVO.prod_score}</td> --%>
<!-- 								</tr> -->
								<tr>
									<td><label>產品描述:&nbsp;&nbsp;</label></td>
									<td><textarea rows="4" cols="40" name="prod_descript"
											required="required">${prodVO.prod_descript}</textarea></td>
								</tr>
								<tr>
									<td><label>產品狀態:&nbsp;&nbsp;</label></td>

									<%!String isTrue = "selected=\"true\"";%>
									<%!String isFalse = "";%>
									<%!String valueA = null;%>
									<%!String valueB = null;%>

									<%
										if (prodVO.getProd_status() == 2) {
											valueA = isFalse;
											valueB = isTrue;
										} else if (prodVO.getProd_status() == 1) {
											valueA = isTrue;
											valueB = isFalse;
										}
									%>

									<td><select name="prod_status">
											<option value="1" <%=valueA%>>販售</option>
											<option value="2" <%=valueB%>>停售</option>
									</select>
									<td>
								</tr>
								
								<input type="hidden" name="prod_id" value="${prodVO.prod_id }">
								<input type="hidden" name="store_id" value="${prodVO.store_id}">

								<%
									java.sql.Date startdate_SQL = new java.sql.Date(System.currentTimeMillis());
								%>

								<input type="hidden" name="prod_updatetime"	value="<%=startdate_SQL%>">
								<input type="hidden" name="prod_soldcount"	value="${prodVO.prod_soldcount}">
								<input type="hidden" name="prod_count"	value="${prodVO.prod_count}">
								<input type="hidden" name="prod_score"	value="${prodVO.prod_score}">
								<input type="hidden" name="prod_status"	value="${prodVO.prod_status}">
								<td><input type="hidden" name="action" value="update_prodChange"><input type="submit" value="送出修改"></td>
							</table>
						</FORM>
					</div>
				</div>

			</div>
		</div>
	</div>
	</div>

	<br>
	<br>
	<br>
	<br>
	<br>

	<script
		src="<%=request.getContextPath()%>/front-end/js_store/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script>
		//easy-sidebar-toggle-right
		$('.easy-sidebar-toggle').click(function(e) {
			e.preventDefault();
			//$('body').toggleClass('toggled-right');
			$('body').toggleClass('toggled');
			//$('.navbar.easy-sidebar-right').removeClass('toggled-right');
			$('.navbar.easy-sidebar').removeClass('toggled');
		});
	</script>


</body>
</html>
