<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.prod.model.*,com.store.model.*,com.item.model.*,com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	if (session.getAttribute("userVO") != null) {
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		StoreService storeSvc = new StoreService();
		StoreVO storeVO = storeSvc.getOneStoreByUsed_Id(userVO.getUser_id());
		ProdService prodSvc = new ProdService();
		List<ProdVO> list = prodSvc.getOneStore_idAllProd(storeVO.getStore_id());
		pageContext.setAttribute("list", list);
	}
%>





<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>OneStore_idAllProd.jsp</title>

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
	<div class="callout"></div>
	<!--     <header class="header"> -->
	<!--         Indicators -->

	<!--         <div class="container" > -->
	<!--             <div class="row"> -->

	<!--                 <div class="col-md-3 col-xs-6"> -->
	<!--                     <div class="panel panel-primary"> -->
	<!--                         <div class="panel-heading"> -->
	<!--                             <div class="row"> -->
	<!--                                 <div class="col-xs-3"> -->
	<!--                                     <i class="fa fa-comments fa-5x"></i> -->
	<!--                                 </div> -->
	<!--                                 <div class="col-xs-9 text-right"> -->
	<!--                                     <div class="huge">26</div> -->
	<!--                                     <div>查看商品留言</div> -->
	<!--                                 </div> -->
	<!--                             </div> -->
	<!--                         </div> -->
	<!--                     </div> -->
	<!--                 </div> -->
	<!--                 <div class="col-md-3 col-xs-6"> -->
	<!--                     <div class="panel panel-primary"> -->
	<!--                         <div class="panel-heading"> -->
	<!--                             <div class="row"> -->
	<!--                                 <div class="col-xs-3"> -->
	<!--                                     <i class="fa fa-book fa-5x"></i> -->
	<!--                                 </div> -->
	<!--                                 <div class="col-xs-9 text-right"> -->
	<!--                                     <div class="huge">124</div> -->
	<!--                                     <div>查看待審核的訂單</div> -->
	<!--                                 </div> -->
	<!--                             </div> -->
	<!--                         </div> -->
	<!--                     </div> -->
	<!--                 </div> -->
	<!--                 </div> -->


	<!--                 </div> -->
	<!--             </div> -->

	<!--         Wrapper for Slides -->


	<!--     </header> -->


	<div id="page-wrapper">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<h3 class="page-header">商品列表</h3>
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>

	<div id="page-wrapper col-md-12">
		<div class="col-md-6">
			<ol class="breadcrumb">
				<!--                     <li> -->
				<%--                         <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllOrd.jsp">查看所有商品</a> --%>
				<!--                     </li> -->
				<li class="active">查看所有商品</li>
			</ol>
		</div>
		<!-- 查詢訂單，待套用萬用搜尋 -->

	</div>




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




	<div class="container-fluid col-md-12">

		<div class="row">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-money fa-fw"></i>目前現有商品狀態 :
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>修改資料</th>
									<th>產品編號</th>
									<th>商家編號</th>
									<th>產品名稱</th>
									<th>產品描述</th>
									<th>產品價格</th>
									<th>產品種類</th>
									<th>產品規格</th>
									<th>產品品牌</th>
									<th>產品更新日期</th>
									<th>產品售出數量</th>
									<th>產品狀態</th>
									<th>產品被評分次數</th>
									<th>產品評分總分</th>






								</tr>

								<c:forEach var="prodVO" items="${list}">
									<tr align='center' valign='middle'>
										<div class="col-xs-12 col-md-10">

											<div class="item">
												<td>
													<!-- 點圖轉交修改 -->
													<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/store_interface/prod.do" enctype="multipart/form-data">
														<input type="hidden" name="prod_id"	value="${prodVO.prod_id}"> <input type="image" src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}"
															width="70" height="50" alt=" "> 
															<input	type="hidden" name="action" value="getOne_For_Update">
													</FORM>
												</td>
												<td><A HREF="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_id=${prodVO.prod_id}&action=getOne_prodChange">${prodVO.prod_id}</a></td>
												<td>${prodVO.store_id}</td>
												<td>${prodVO.prod_name}</td>
												<td>${prodVO.prod_descript}</td>
												<td>$${prodVO.prod_price}</td>
												<td>${prodVO.prod_sort}</td>
												<td>${prodVO.prod_format}</td>
												<td>${prodVO.prod_brand}</td>
												<td>${prodVO.prod_updatetime}</td>
												<td>${prodVO.prod_soldcount}</td>
												<td>${prodVO.prod_status}</td>
												<td>${prodVO.prod_count}</td>
												<td>${prodVO.prod_score}</td>
									</tr>

								</c:forEach>
						</table>

						<div class="col-xs-12 col-md-8 col-md-offset-4"></div>
					</div>
				</div>
			</div>

		</div>
	</div>


	<!-- 跳小視窗模組 -->


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
