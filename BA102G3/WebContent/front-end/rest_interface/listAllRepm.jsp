<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.wish.model.*,com.wish.controller.*"%>
<%@ page
	import="com.user.model.*,com.rest.model.*,com.repm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	if (session.getAttribute("userVO") != null) {
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		Integer user_id = userVO.getUser_id();
		pageContext.setAttribute("user_id", user_id);
	}

	RepmService repmSvc = new RepmService();
	List<RepmVO> list = repmSvc.getAll();
	pageContext.setAttribute("list", list);

%>

<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
<jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" />
<jsp:useBean id="repmSvcB	" scope="page" class="com.repm.model.RepmService" />


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>prpm_list</title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/css/stylish-portfolio.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.item img {
	height: 250px;
	width: 100%;
}

.panel-body {
	font-size: 13px;
	border: #dcdcdc 1px solid;;
}

.content: {
	position: relative;
}

.box {
	width: 110px;
	height: 50px;
	position: fixed;
	top: 52px;
	left: 5px;
	margin: auto;
}

#accordion {
	font-size: 12px;
}
</style>
</head>

<body>


	<%@include file="/front-end/member_interface/headerBar.file"%>
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

	<div class="container">
		<div class="row">
			<div class="callout"></div>
			<div class="col-md-11 col-xs-12">
				<h3>促銷資訊</h3>
			</div>
		</div>
	</div>
	</div>
	<header id="myCarousel top" class="carousel slide"> </header>

	<div class="container content">
		<div class="row">
			<header class="header">
				<div class="text-vertical-center">
					<h1>Anytime Grip</h1>
					<h3>SHOP OUR COLLECTIONS &amp; SHOP WITH RUNWAY</h3>
					<br> <a href="#about" class="btn btn-dark btn-lg">Grip
						Now!!</a>
				</div>
			</header>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a>
				</li>
				<li class="active"><a>促銷資訊</a></li>
			</ol>


			<%-- 			<c:forEach var="repmVO" items="${list}" varStatus="count"> --%>
			<%-- 				<c:if test="${repmVO.repm_status == 1}"> --%>
			<!-- 					<div class="panel panel-default"> -->
			<!-- 						<div class="panel-heading " role="tab" id="panel"></div> -->
			<!-- 						<div class="panel-collapse collapse in" role="tabpanel" -->
			<!-- 							aria-labelledby="panel"> -->
			<!-- 							<div class="panel-body text-center"> -->
			<!-- 								內容 -->
			<!-- 								<div class="col-xs-12 col-sm-3"> -->
			<%-- 									<b>${repmVO.repm_name}</b> --%>
			<!-- 								</div> -->
			<!-- 								<div class="col-xs-12 col-sm-3"> -->
			<%-- 									<b>${repmVO.repm_desc}</b> --%>
			<!-- 								</div> -->
			<!-- 								<div class="col-xs-12 col-sm-3"> -->
			<%-- 									<b>${repmVO.repm_startdate} ~ ${repmVO.repm_enddate}</b> --%>
			<!-- 								</div> -->
			<!-- 								<div class="col-xs-12 col-sm-3"> -->
			<!-- 									<a href='#${repmVO.repm_id}' data-toggle="modal" -->
			<!-- 										class="btn btn-primary btn-xs">顯示詳情</a> -->
			<!-- 								</div> -->
			<%-- 								<div class="modal fade" id="${repmVO.repm_id}"> --%>
			<!-- 									<div class="modal-dialog"> -->
			<!-- 										<div class="modal-content"> -->
			<!-- 											<div class="modal-header"> -->
			<!-- 												<button type="button" class="close" data-dismiss="modal" -->
			<!-- 													aria-hidden="true">&times;</button> -->
			<%-- 												<h4 class="modal-title">${repmVO.repm_content}</h4> --%>
			<!-- 											</div> -->
			<!-- 											<div class="modal-body"> -->
			<!-- 												<table border="0" align="center"> -->
			<%-- 													<c:forEach var="prpmVO" items="${prpmList}" --%>
			<%-- 														varStatus="count"> --%>
			<%-- 														<c:if test="${repmVO.repm_id == prpmVO.repm_id}"> --%>
			<!-- 															<tr> -->
			<%-- 																<td width="30%"><b>促銷商品:</b><a>${prpmVO.prod_id}</a></td> --%>

			<!-- 																<td width="30%"><b>原價:</b><font color="blue"> -->
			<%-- 																		<c:if test="${prodList != null}"> --%>
			<%-- 																			<c:forEach var="prodVO" items="${prodList}"> --%>
			<%-- 																				<c:if test="${prpmVO.prod_id == prodVO.prod_id}"> --%>
			<!-- 																				$${prodVO.prod_price} -->
			<%-- 																				</c:if> --%>
			<%-- 																			</c:forEach> --%>
			<%-- 																		</c:if> --%>
			<!-- 																</font></td> -->
			<%-- 																<td width="30%"><b>促銷特價:</b><font color="red">${prpmVO.prpm_price}</font></td> --%>
			<!-- 															</tr> -->
			<%-- 														</c:if> --%>
			<%-- 													</c:forEach> --%>
			<!-- 												</table> -->
			<!-- 											</div> -->
			<!-- 											<div class="modal-footer"> -->
			<!-- 												<button type="button" class="btn btn-default" -->
			<!-- 													data-dismiss="modal">close</button> -->
			<!-- 											</div> -->
			<!-- 										</div> -->
			<!-- 									</div> -->
			<!-- 								</div> -->
			<!-- 								內容結束 -->
			<!-- 							</div> -->
			<!-- 						</div> -->
			<!-- 					</div> -->
			<%-- 				</c:if> --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</div> -->

			<div id="accordion">
				<c:forEach var="repmVO" items="${list}" varStatus="count">
					<c:if test="${repmVO.repm_status == 1}">
						<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;促銷名稱&nbsp;-&nbsp;${repmVO.repm_name}</div>
						<div class="text-center">
							<!-- 內容 -->

							<div class="col-xs-12 col-sm-3">
								<b>${repmVO.repm_name}</b>
							</div>
							<div class="col-xs-12 col-sm-3">
								<b>${repmVO.repm_desc}</b>
							</div>
							<div class="col-xs-12 col-sm-3">
								<b>${repmVO.repm_startdate} ~ ${repmVO.repm_enddate}</b>
							</div>

							<div class="col-xs-12 col-sm-3">
								<a href='#${repmVO.repm_id}' data-toggle="modal" class="btn btn-default btn-xs">顯示詳情</a>
								<a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_id=${prpmVO.prod_id}&action=getOne_For_Display" class="btn btn-default btn-xs">餐廳詳情</a>
							</div>


							<!-- modal -->
<%-- 							<div class="modal fade" id="${repmVO.repm_id}"> --%>
<!-- 								<div class="modal-dialog modal-lg"> -->
<!-- 									<div class="modal-content"> -->
<!-- 										<div class="modal-header"> -->
<!-- 											<button type="button" class="close" data-dismiss="modal" -->
<!-- 												aria-hidden="true">&times;</button> -->
<%-- 											<h4 class="modal-title">${repmVO.repm_content}</h4> --%>
<!-- 										</div> -->
<!-- 										<div class="modal-body"> -->

<!-- 											<table border="0" align="center"> -->
<%-- 												<c:forEach var="prpmVO" items="${prpmList}" --%>
<%-- 													varStatus="count"> --%>
<%-- 													<c:if test="${repmVO.repm_id == prpmVO.repm_id}"> --%>
<!-- 														<tr> -->
<!-- 															<td width="20%"><b>促銷商品:</b> -->
<!-- 															<a> -->
<%-- 																<c:forEach var="prodVOB" items="${prodSvcB.all}" varStatus="count"> --%>
<%-- 																	<c:if test="${prodVOB.prod_id == prpmVO.prod_id}"> --%>
<%-- 																		${prodVOB.prod_name} --%>
<%-- 																	</c:if> --%>
<%-- 																</c:forEach> --%>
<!-- 															</a> -->
<!-- 															</td> -->

<!-- 															<td width="20%"><b>原價:</b><font color="blue"> -->
<%-- 																	<c:if test="${prodList != null}"> --%>
<%-- 																		<c:forEach var="prodVO" items="${prodList}"> --%>
<%-- 																			<c:if test="${prpmVO.prod_id == prodVO.prod_id}"> --%>
<!-- 																				$${prodVO.prod_price} -->
<%-- 																				</c:if> --%>
<%-- 																		</c:forEach> --%>
<%-- 																	</c:if> --%>
<!-- 															</font></td> -->
<%-- 															<td width="20%"><b>促銷特價:</b><font color="red">${prpmVO.prpm_price}</font></td> --%>
															
<!-- 															<td width="20%"> -->
<%-- 															<a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_id=${prpmVO.prod_id}&action=getOne_For_Display" class="btn btn-default btn-xs">商品詳情</a> --%>
<!-- 															</td> -->
<!-- 														</tr> -->
<%-- 													</c:if> --%>
<%-- 												</c:forEach> --%>
<!-- 											</table> -->
<!-- 										</div> -->
<!-- 										<div class="modal-footer"> -->
<!-- 											<button type="button" class="btn btn-default" -->
<!-- 												data-dismiss="modal">close</button> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
						<!-- 內容結束 -->
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>

	<div class="callout"></div>
	<a id="to-top" href="#top" class="btn btn-dark btn-lg"><i
		class="fa fa-chevron-up fa-fw fa-1x"></i></a>


	<!-- Custom Theme JavaScript -->
	<%@ include file="/front-end/member_interface/script.file"%>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#accordion").accordion();
		});
	</script>
</body>
</html>
