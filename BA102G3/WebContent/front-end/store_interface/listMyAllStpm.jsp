<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stpm.model.*,com.user.model.*,com.store.model.*,com.prod.model.*"%>


<!-- click -->
<%
if(session.getAttribute("userVO")!=null){
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account =(String) session.getAttribute("account");
		StoreService storeSvc= new StoreService();
		StoreVO storeVO=storeSvc.getOneStoreByUsed_Id(userVO.getUser_id());
		Integer store_id = storeVO.getStore_id();
		StpmService stpmSvc = new StpmService();
		List<StpmVO> list = stpmSvc.findByStoreID(store_id);
		pageContext.setAttribute("list", list);
	}
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
<link href="<%=request.getContextPath() %>/front-end/css_store/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath() %>/front-end/css_store/stylish-portfolio.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/js_store/bootstrap-datepicker3.min.css" />

</head>

<body>

<%@ include file="headerBar.file"%>

	 <!-- Header -->
    <div class="callout" ></div>

   
	
<!--    			<div id="page-wrapper"> -->
<!--             <div class="row"> -->
<!--                 <div class="col-md-10 col-md-offset-1"> -->
<!--                     <h3 class="page-header">查詢所有促銷專案</h3> -->
<!--                 </div> -->
<!--                 /.col-lg-12 -->
<!--             </div> -->
<!--             </div> -->

<!--  			<div id="page-wrapper col-md-12"> -->
<!-- 				<div class="col-md-6"> -->
<!--                 <ol class="breadcrumb"> -->
<!--                     <li class="active">查詢所有促銷專案</li> -->
<!--                 </ol> -->
<!--                 </div> -->
                
<!-- 			</div> -->
	
	<br>
	<br>
	<div class="col-md-12">
	<table border="1" align="center">
		<tr height="79" style="background-color: rgb(229, 246, 253);">
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷編號</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷名稱</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷開始</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷結束</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">目前狀態</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">詳情修改</span><br />
		</tr>

		<c:forEach var="stpmVO" items="${list}">
			<tr align='center' valign='middle'>
				<td>${stpmVO.stpm_id}</td>
				<td>${stpmVO.stpm_name}</td>
				<td>${stpmVO.stpm_startdate}</td>
				<td>${stpmVO.stpm_enddate}</td>
				<td>${stpmVO.stpm_status}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/store_interface/stpm.do">
						<input type="submit" value="修改"> <input type="hidden"
							name="stpm_id" value="${stpmVO.stpm_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<div class="callout"></div>


	<script src="<%=request.getContextPath() %>/front-end/js_store/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath() %>/front-end/js_store/bootstrap-datepicker.js"></script>

	<script src="<%=request.getContextPath() %>/front-end/js_store/bootstrap-datepicker.zh-TW.js"></script>

</body>

</html>