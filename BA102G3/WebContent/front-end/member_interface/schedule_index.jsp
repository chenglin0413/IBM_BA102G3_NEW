<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>交通運輸資訊</title>

<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/front-end/css/stylish-portfolio.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<style type="text/css">
.item img {
	height: 250px;
	width: 100%;
}

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
</style>
</head>

<body>

	 <%@include file="/front-end/member_interface/headerBar.file" %>



	<div class="container">
		<div class="row">
			<br>
			<div class="col-xs-12 col-md-4">

				<a href="<%=request.getContextPath()%>/front-end/member_interface/listAllBusc.jsp">
					<div class="item">

						<img
							src="https://api.fnkr.net/testimg/700x400/00CED1/FFF/?text=img+placeholder">
						<p>客運時刻表</p>
					</div>
				</a>
			</div>



			<div class="col-xs-12 col-md-4">
				<a href="<%=request.getContextPath()%>/front-end/member_interface/listAllFlsc.jsp">
					<div class="item">
						<img
							src="https://api.fnkr.net/testimg/700x400/00CED1/FFF/?text=img+placeholder">
						<p>航班時刻表</p>
					</div>
				</a>
			</div>


			<div class="col-xs-12 col-md-4">
				<a href="<%=request.getContextPath() %>/front-end/member_interface/go_airport.jsp">
					<div class="item">

						<img
							src="https://api.fnkr.net/testimg/700x400/00CED1/FFF/?text=img+placeholder">
						<p>捷運時刻表</p>
					</div>
				</a>
			</div>
		</div>

<div>
<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14454.237853016903!2d121.22945339494773!3d25.082916358161672!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x34429fc062d215d5%3A0x70a3b690a9b5b109!2z6Ie654Gj5qGD5ZyS5ZyL6Zqb5qmf5aC0IChUUEUp!5e0!3m2!1szh-TW!2stw!4v1501479825287" width="100%" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
</div>

<%@ include file="/front-end/member_interface/script.file" %>	
</body>

</html>
