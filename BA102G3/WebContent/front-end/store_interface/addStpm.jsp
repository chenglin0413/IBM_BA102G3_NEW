<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.store.model.*,com.user.model.*,com.stpm.model.*"%>


<%
	if (session.getAttribute("userVO") != null) {
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		StoreService storeSvc = new StoreService();
		StoreVO storeVO = storeSvc.getOneStoreByUsed_Id(userVO.getUser_id());
		Integer store_id = storeVO.getStore_id();
		pageContext.setAttribute("store_id", store_id);
		
		session.setAttribute("key", "val");
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

<title>建立促銷專案</title>

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
    
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">建立促銷專案</h3>
                </div>
            </div>

	<div class="container">
		<div class="row"></div>
		<div class="col-xs-12 col-sm-12" align="center">

			<div align="center">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font color='red'>錯誤: <c:forEach var="message"	items="${errorMsgs}">
							<b>${message}</b>
						</c:forEach>
					</font>
				</c:if>
			</div>
			
			<div class="callout"></div>

			<FORM METHOD="post" ACTION="stpm.do" name="form1">
				<table border="0">
					<tr>
						<td><label>商店代號:&nbsp;&nbsp;</label></td>
						<td><font color=red><b>＊</b></font>${storeVO.store_id}<input type="hidden" name="store_id" required="required"　size="15" value="${storeVO.store_id}" /></td>
					</tr>
					<tr>

						<td><label>促銷名稱:&nbsp;&nbsp;</label></td>
						<td><input type="TEXT" id="name" name="stpm_name" required="required" size="15" /></td>

					</tr>

					<tr>
						<td><label>促銷說明:&nbsp;&nbsp;</label></td>
						<td><textarea rows="4" cols="40" id="desc" name="stpm_desc"	required="required"></textarea></td>

					</tr>

					<tr>
						<td><label>促銷文案:&nbsp;&nbsp;</label></td>
						<td><textarea rows="4" cols="40" id="content" name="stpm_content"></textarea></td>
					</tr>

					<tr>
						<%
							java.sql.Date startdate_SQL = new java.sql.Date(System.currentTimeMillis());
						%>
						<td><label>開始日期:&nbsp;&nbsp;</label></td>
<%-- 				<td><input type="date" name="stpm_startdate" required="required" value="<%=startdate_SQL%>"></td> --%>
						<td><input type="text" name="stpm_startdate" value="<%=startdate_SQL%>" required="required"></td>
					</tr>

					<tr>
						<%
							java.sql.Date enddate_SQL = new java.sql.Date(System.currentTimeMillis());
						%>
						<td><label>結束日期:&nbsp;&nbsp;</label></td>
				<%-- <td><input type="date" name="stpm_enddate"	required="required" value="<%=enddate_SQL%>"></td> --%>
						<td><input type="text" name="stpm_enddate" value="<%=enddate_SQL%>" required="required"></td>
					</tr>

					<tr>
<!-- <td><label>促銷狀態:&nbsp;&nbsp;</label></td> -->
						<td><input type="hidden" name="stpm_status"	required="required" value="0"></td>
						<td>
					</tr>
				</table>
				<br> <input type="hidden" name="action" value="insert">
				 <button type="submit" class="btn btn-default btn-sm">建立專案</button>
				 <br><br>

			</FORM>
			
				 <button class="btn btn-danger btn-sm"  onclick="magiclittlebtn1();"></button>
				 <button class="btn btn-danger btn-sm"  onclick="magiclittlebtn2();"></button>

			<br> <br>
		</div>
	</div>

	<div class="callout"></div>
	
	<script type="text/javascript">

		function magiclittlebtn1() {
			document.getElementById('name').value = '航站最便宜';
			document.getElementById('desc').value = '期間限定折扣滿滿滿';
			document.getElementById('content').value = '期間折扣專案';
		}
		
		function magiclittlebtn2() {
			document.getElementById('name').value = '熱門伴手禮';
			document.getElementById('desc').value = '最優質人氣伴手禮';
			document.getElementById('content').value = '期間折扣專案';
		}
		
	</script>



	<script
		src="<%=request.getContextPath()%>/front-end/js_store/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker.js"></script>

	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker.zh-TW.js"></script>
		
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://jq-simple-dtpicker-gh-master.herokuapp.com/jquery.simple-dtpicker.js"></script>
<link type="text/css" href="http://jq-simple-dtpicker-gh-master.herokuapp.com/jquery.simple-dtpicker.css" rel="stylesheet" />

	<script type="text/javascript">
		$(function() {
			$('*[name=stpm_startdate]').appendDtpicker({
				"dateOnly" : true,
				"futureOnly": true,
				"dateFormat" : "YYYY-MM-DD"
			});
			$('*[name=stpm_enddate]').appendDtpicker({
				"dateOnly" : true,
				"futureOnly": true,
				"dateFormat" : "YYYY-MM-DD"
			});
		});
	</script>

</body>
</html>