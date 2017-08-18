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
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laboriosam quod modi vitae esse, molestiae quo dolor optio, voluptatem ex culpa deleniti inventore incidunt autem iste facilis cumque obcaecati, doloremque soluta?</p>
					<p>Vel dolores, corporis itaque delectus architecto aperiam blanditiis debitis animi, facere accusamus nostrum eveniet, praesentium necessitatibus suscipit cupiditate veritatis error iste, culpa ea placeat rerum magnam explicabo reprehenderit labore. Dolore.</p>
					<p>Similique dolore mollitia consequuntur vel libero inventore iure, incidunt repellendus, eaque error ipsum omnis ullam ipsa, officia. Ea eligendi tenetur reiciendis itaque hic qui, consectetur, necessitatibus sit eos, illum minus.</p>
				
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
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laboriosam quod modi vitae esse, molestiae quo dolor optio, voluptatem ex culpa deleniti inventore incidunt autem iste facilis cumque obcaecati, doloremque soluta?</p>
					<p>Vel dolores, corporis itaque delectus architecto aperiam blanditiis debitis animi, facere accusamus nostrum eveniet, praesentium necessitatibus suscipit cupiditate veritatis error iste, culpa ea placeat rerum magnam explicabo reprehenderit labore. Dolore.</p>
					<p>Similique dolore mollitia consequuntur vel libero inventore iure, incidunt repellendus, eaque error ipsum omnis ullam ipsa, officia. Ea eligendi tenetur reiciendis itaque hic qui, consectetur, necessitatibus sit eos, illum minus.</p>
				</div>
			</div>
		</div>

		<%@ include file="/front-end/member_interface/script.file"%>
</body>

</html>
