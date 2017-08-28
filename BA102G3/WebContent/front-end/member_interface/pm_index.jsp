<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.wish.model.*,com.wish.controller.*"%>
<%@ page
	import="com.prod.model.*,com.user.model.*,com.store.model.*,com.stpm.model.*,com.prpm.model.*,com.repm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	if (session.getAttribute("userVO") != null) {
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		Integer user_id = userVO.getUser_id();
		pageContext.setAttribute("user_id", user_id);
	}

	StpmService stpmSvc = new StpmService();
	List<StpmVO> list = stpmSvc.getAll();
	pageContext.setAttribute("list", list);

	PrpmService prpmSvc = new PrpmService();
	List<PrpmVO> prpmList = prpmSvc.getAll();
	pageContext.setAttribute("prpmList", prpmList);

	ProdService prodSvc = new ProdService();
	List<ProdVO> prodList = new ArrayList<ProdVO>();

	for (PrpmVO p : prpmList) {
		ProdVO prodvo = prodSvc.getOneProd(p.getProd_id());
		prodList.add(prodvo);
	}
	request.setAttribute("prodList", prodList);

	RepmService repmSvc = new RepmService();
	List<RepmVO> repmlist = repmSvc.getAll();
	pageContext.setAttribute("repmlist", repmlist);
%>

<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<jsp:useBean id="userSvc" scope="page"
	class="com.user.model.UserService" />
<jsp:useBean id="storeSvc" scope="page"
	class="com.store.model.StoreService" />
<jsp:useBean id="prodSvcB" scope="page"
	class="com.prod.model.ProdService" />
<jsp:useBean id="restSvc" scope="page"
	class="com.rest.model.RestService" />
<jsp:useBean id="repmSvcB" scope="page"
	class="com.repm.model.RepmService" />
<jsp:useBean id="repiSvc" scope="page"
	class="com.repi.model.RepiService" />

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>listOneUser_idAllOrd</title>

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



/* #accordion { */
/* 	font-size: 12px; */
/* } */

/* #accordions { */
/* 	font-size: 12px; */
/* } */

/* body { */
/* 	background: url(../img/slidebg15.jpg) no-repeat center center scroll; */
/* } */
body {
	background-image: url(<%=request.getContextPath()%>/front-end/img/bg004.jpg);
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
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
			<ol class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a>
				</li>
				<li class="active">促銷資訊</li>
			</ol>

			<h3>商店資訊</h3>

			<c:forEach var="stpmVO" items="${list}" varStatus="count">

				<c:if test="${stpmVO.stpm_status == 1}">

					<div class="panel panel-default">

						<div align="center">
							<h4>${stpmVO.stpm_name}</h4>
						</div>

						<div class="panel-body">

							<!-- 內容 -->
							<div class="col-xs-12 col-sm-4">
								<img
									src="<%=request.getContextPath()%>/front-end/stpi/DBGifReader?store_id=${stpmVO.store_id}"
									class="img-thumbnail" style="height: 200px;" />
							</div>

							<div class="col-xs-12 col-sm-8 ">
								<textarea class="overflow form-control" rows="6" cols="40"
									style="resize: none; border: 0px; background-color: white; font-size: 20px; font-weight: bold;"
									readonly>${stpmVO.stpm_startdate} - ${stpmVO.stpm_enddate} ~ ${stpmVO.stpm_desc}</textarea>
							</div>
						</div>

						<div class="panel-body text-right">
							<a href='#${stpmVO.stpm_id}' data-toggle="modal"
								class="btn btn-default btn-lg">顯示詳情&nbsp;</a>
						</div>
						<!-- 內容結束 -->

						<!-- stpm_modal -->
						<div class="modal fade" id="${stpmVO.stpm_id}">
							<div class="modal-dialog modal-md">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<c:forEach var="prpmVO" items="${prpmList}" varStatus="count">
											<c:if test="${stpmVO.stpm_id == prpmVO.stpm_id}">
												<c:forEach var="prodVOB" items="${prodSvcB.all}"
													varStatus="count">
													<c:if test="${prodVOB.prod_id == prpmVO.prod_id}">
														<div align="center">
															<b>促銷商品: ${prodVOB.prod_name}</b>
														</div>
														<div class="callout"></div>

														<div align="center">
															<img
																src="<%=request.getContextPath()%>/front-end/store_interface/DBGifReader?prod_id=${prodVOB.prod_id}"
																height="200" />
														</div>
													</c:if>
												</c:forEach>
												<div class="callout"></div>
												<div class="callout"></div>
												<c:if test="${prodList != null}">
													<c:forEach var="prodVO" items="${prodList}">
														<c:if test="${prpmVO.prod_id == prodVO.prod_id}">
															<div align="center">
																<b>原價: </b> <font color="blue"><s>$${prodVO.prod_price}</s></font>
																<br> <b>特價: </b> <font color="red">$<font
																	size="18">${prpmVO.prpm_price}</font></font>
															</div>
														</c:if>
													</c:forEach>
												</c:if>

												<div align="right">
													<a
														href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_id=${prpmVO.prod_id}&action=getOne_For_Display"
														class="btn btn-default btn-md">商品詳情</a>
												</div>
											</c:if>
										</c:forEach>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">close</button>
									</div>
								</div>
							</div>
						</div>
						<!-- modal -->
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<h3>餐廳資訊</h3>
			<c:forEach var="repmVO" items="${repmlist}" varStatus="count">
				<div class="panel panel-default">
					<div align="center">
						<h4>${repmVO.repm_name}</h4>
					</div>
					<c:if test="${repmVO.repm_status == 1}">
						<div class="panel-body">

							<!-- 內容 -->
							<div class="col-xs-12 col-sm-4">
								<c:forEach var="repiVO" items="${repiSvc.all}">
									<c:if test="${repmVO.rest_id==repiVO.rest_id}">
										<img
											src="<%= request.getContextPath()%>/front-end/restaurant/repi/DBGifReader_repi.do?repi_id=${repiVO.repi_id}"
											class="img-thumbnail" style="height: 200px;" />
									</c:if>
								</c:forEach>
							</div>
							<div class="col-xs-12 col-sm-8 ">
								<textarea class="overflow form-control" rows="6" cols="40"
									style="resize: none; border: 0px; background-color: white; font-size: 20px; font-weight: bold;"
									readonly>${repmVO.repm_startdate} - ${repmVO.repm_enddate} ~ ${repmVO.repm_desc}</textarea>
							</div>
						</div>
						<div class="panel-body text-right">
							<c:forEach var="restVO" items="${restSvc.all}">
								<c:if test="${repmVO.rest_id == restVO.rest_id}">
									<a
										href="<%=request.getContextPath()%>/front-end/rest/rest.do?rest_id=${restVO.rest_id}&action=getOne_For_Display_formember"
										class="btn btn-default btn-lg">前往餐廳頁面&nbsp;</a>
								</c:if>
							</c:forEach>
						</div>
						<!-- 內容結束 -->
					</c:if>
				</div>
			</c:forEach>
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


	<script>
		$(function() {
			$("#accordions").accordion();
		});
	</script>
</body>
</html>
