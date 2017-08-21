<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>促銷資訊</title>

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

<style type="text/css">
/* .item img { */
/* 	height: 250px; */
/* 	width: 100%; */
/* } */

.content: {
	position: relative;
}

h1 {
	text-align: center;
	font-weight: bold;
}

p {
	text-align: center;
}

.item img {
	margin-bottom: 20px;
	height: 250px;
	width: 100%;
}
</style>
</head>

<body>

	<%@include file="/front-end/member_interface/headerBar.file"%>



<!-- 	<div class="container"> -->
<!-- 		<div class="row"> -->
<!-- 			<br> -->

<!-- 			<div class="col-xs-12 col-md-6"> -->
<!-- 				<a -->
<%-- 					href="<%=request.getContextPath()%>/front-end/member_interface/listAllStpm.jsp"> --%>
<!-- 					<div class="item"> -->
<!-- 						<img -->
<%-- 							src="<%=request.getContextPath()%>/front-end/img/shopping.jpg"> --%>
<!-- 						<p>商品促銷</p> -->
<!-- 					</div> -->
<!-- 				</a> -->
<!-- 			</div> -->


<!-- 			<div class="col-xs-12 col-md-6"> -->
<!-- 				<a -->
<%-- 					href="<%=request.getContextPath()%>/front-end/member_interface/listAllRepm.jsp"> --%>
<!-- 					<div class="item"> -->

<%-- 						<img src="<%=request.getContextPath()%>/front-end/img/food.jpg"> --%>
<!-- 						<p>餐廳促銷</p> -->
<!-- 					</div> -->
<!-- 				</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
		<div class="container">
		<br>
		<br>
		
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<a href="<%=request.getContextPath()%>/front-end/member_interface/listAllStpm.jsp">
					<div class="item">
					<img src="<%=request.getContextPath()%>/front-end/img/shopping.jpg" class="img-responsive">
					</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-8">
				<br><br><br>
					<p>合作店家精選促銷商品！</p>
					<p>送禮自用兩相宜，走過路過別錯過～！</p>
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-sm-push-8">
					<a href="<%=request.getContextPath()%>/front-end/member_interface/listAllRepm.jsp">
					<div class="item">
					<img src="<%=request.getContextPath()%>/front-end/img/food.jpg">
					</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-8 col-sm-pull-4">
				<br><br><br>
					<p>旅途累了？適時後休息一下吧！</p>
					<p>看看合作餐廳所提供優惠吧</p>
				</div>
			</div>
		</div>

		<%@ include file="/front-end/member_interface/script.file"%>
</body>

</html>
