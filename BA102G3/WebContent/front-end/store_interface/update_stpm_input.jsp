<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stpm.model.*,com.prod.model.*,com.prpm.model.*"%>

<%
	StpmVO stpmVO = (StpmVO) request.getAttribute("stpmVO");
	Integer stpm_id = stpmVO.getStpm_id();

	PrpmService prpmSvc = new PrpmService();
	PrpmVO prpmChecked = prpmSvc.getPrice(stpm_id);
	List<PrpmVO> list = prpmSvc.getStpmID(stpm_id);

	request.setAttribute("stpm_id", stpm_id);
%>


<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>詳情修改</title>

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

</head>

<body>

	<%@ include file="headerBar.file"%>

	<div class="container">
		<div class="row"></div>
		<div class="col-xs-12 col-sm-12" align="center">

			<br>

			<div class="callout"></div>
			<div align="center">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty okMsgs}">
					<font color='blue'>※<c:forEach var="message"
							items="${okMsgs}">
							<b>${message}</b>
						</c:forEach>
					</font>
				</c:if>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font color='red'>錯誤: <c:forEach var="message"
							items="${errorMsgs}">
							<a>${message}</a>
						</c:forEach>

					</font>
				</c:if>
			</div>

			<div class="callout"></div>



<!-- 			<div id="page-wrapper"> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-10 col-md-offset-1"> -->
<!-- 						<h3 class="page-header">修改促銷專案</h3> -->
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
<!-- 						<li class="active">修改促銷專案</li> -->
<!-- 					</ol> -->
<!-- 				</div> -->

<!-- 			</div> -->

				<FORM METHOD="post" ACTION="stpm.do" name="form1">
					<table border="0">
						<tr>
							<td><label>促銷編號:&nbsp;&nbsp;</label><font color=red><b>＊</b></font></td>
							<td><%=stpmVO.getStpm_id()%></td>
						</tr>
						<tr>
							<td><label>商店代號:&nbsp;&nbsp;</label><font color=red><b>＊</b></font></td>
							<td><%=stpmVO.getStore_id()%></td>
						</tr>

						<tr>
							<td><label>促銷名稱:&nbsp;&nbsp;</label></td>
							<td><input type="TEXT" name="stpm_name" size="15"
								value="<%=stpmVO.getStpm_name()%>" /></td>
						</tr>
						<tr>
							<td><label>促銷說明:</label></td>
							<td><textarea rows="4" cols="40" name="stpm_desc"
									required="required"><%=stpmVO.getStpm_desc()%></textarea></td>
						</tr>

						<tr>
							<td><label>促銷文案:&nbsp;&nbsp;</label></td>
							<td><textarea rows="4" cols="40" name="stpm_content"
									required="required"><%=stpmVO.getStpm_content()%></textarea></td>
						</tr>
						<tr>
							<td><label>開始日期:&nbsp;&nbsp;</label></td>
							<td><input type="date" name="stpm_startdate" size="15" value="<%=stpmVO.getStpm_startdate()%>" /></td>
						</tr>
						<tr>
							<td><label>結束日期:&nbsp;&nbsp;</label></td>
							<td><input type="date" name="stpm_enddate" size="15" value="<%=stpmVO.getStpm_enddate()%>" /></td>
						</tr>

						<tr>
							<td><label>促銷狀態:&nbsp;&nbsp;</label></td>

							<%!String isTrue = "selected=\"true\"";%>
							<%!String isFalse = "";%>
							<%!String valueA = null;%>
							<%!String valueB = null;%>

							<%
								if (stpmVO.getStpm_status() == 1) {
									valueA = isFalse;
									valueB = isTrue;
								} else if (stpmVO.getStpm_status() == 0) {
									valueA = isTrue;
									valueB = isFalse;
								}
							%>

							<td><select name="stpm_status">
									<option value="0" <%=valueA%>>促銷資訊(off)</option>
									<option value="1" <%=valueB%>>促銷資訊(on)</option>
							</select>
							<td>
						</tr>
					</table>
					<br> <br> <input type="hidden" name="action" value="update"> <input type="hidden" name="stpm_id" value="<%=stpmVO.getStpm_id()%>"> <input type="submit" value="修改促銷資訊">
				</FORM>
			</div>
		</div>


		<div align="center">
			<FORM METHOD="post" ACTION="prpm.do" name="form2">
				<input type="hidden" name="stpm_id" value="<%=stpmVO.getStpm_id()%>" />
				<input type="hidden" name="action" value="getAdd"> <input type="submit" value="新增促銷商品">
			</FORM>
		</div>

		<div class="container">
			<div class="row"></div>
			<div class="col-xs-12 col-sm-12" align="center">

				<%
					if (prpmChecked != null) {
						request.setAttribute("list", list);
				%>
				<jsp:include page="listMyPrpm.jsp" />
				<%
					}
				%>

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