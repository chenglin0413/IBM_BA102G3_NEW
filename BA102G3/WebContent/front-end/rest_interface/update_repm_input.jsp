<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repm.model.*,com.prod.model.*,com.prpm.model.*"%>

<%
	RepmVO repmVO = (RepmVO) request.getAttribute("repmVO");
	Integer repm_id = repmVO.getRepm_id();

	request.setAttribute("repm_id", repm_id);
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
	
	    <div class="callout" ></div>
    
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">修改促銷專案</h3>
                </div>
            </div>

	<div class="container">
		<div class="row"></div>
		<div class="col-xs-12 col-sm-12" align="center">

			<div align="center">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty okMsgs}">
					<font color='blue'>※<c:forEach var="message" items="${okMsgs}">
							<b>${message}</b>
						</c:forEach>
					</font>
				</c:if>
				
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font color='red'>※<c:forEach var="message" items="${errorMsgs}">
							<b>${message}</b>
						</c:forEach>
					</font>
				</c:if>
			</div>

			<div class="callout"></div>

			<FORM METHOD="post" ACTION="repm.do" name="form1">
				<table border="0">
					<tr>
						<td><label>促銷編號:&nbsp;&nbsp;</label><font color=red><b>＊</b></font></td>
						<td><%=repmVO.getRepm_id()%></td>
					</tr>
					<tr>
						<td><label>商店代號:&nbsp;&nbsp;</label><font color=red><b>＊</b></font></td>
						<td><%=repmVO.getRest_id()%></td>
					</tr>

					<tr>
						<td><label>促銷名稱:&nbsp;&nbsp;</label></td>
						<td><input type="TEXT" name="repm_name" size="15"
							value="<%=repmVO.getRepm_name()%>" /></td>
					</tr>
					<tr>
						<td><label>促銷說明:</label></td>
						<td><textarea rows="4" cols="40" name="repm_desc"
								required="required"><%=repmVO.getRepm_desc()%></textarea></td>
					</tr>

					<tr>
						<td><label>促銷文案:&nbsp;&nbsp;</label></td>
						<td><textarea rows="4" cols="40" name="repm_content"
								required="required"><%=repmVO.getRepm_content()%></textarea></td>
					</tr>
					<tr>
						<td><label>開始日期:&nbsp;&nbsp;</label></td>
						<td><input type="date" name="repm_startdate" size="15"
							value="<%=repmVO.getRepm_startdate()%>" /></td>
					</tr>
					<tr>
						<td><label>結束日期:&nbsp;&nbsp;</label></td>
						<td><input type="date" name="repm_enddate" size="15"
							value="<%=repmVO.getRepm_enddate()%>" /></td>
					</tr>

					<tr>
						<td><label>促銷狀態:&nbsp;&nbsp;</label></td>

						<%!String isTrue = "selected=\"true\"";%>
						<%!String isFalse = "";%>
						<%!String valueA = null;%>
						<%!String valueB = null;%>

						<%
							if (repmVO.getRepm_status() == 1) {
								valueA = isFalse;
								valueB = isTrue;
							} else if (repmVO.getRepm_status() == 0) {
								valueA = isTrue;
								valueB = isFalse;
							}
						%>

						<td><select name="repm_status">
								<option value="0" <%=valueA%>>促銷資訊(off)</option>
								<option value="1" <%=valueB%>>促銷資訊(on)</option>
						</select>
						<td>
					</tr>
				</table>
				<br> <input type="hidden" name="action" value="update"> 
				<input type="hidden" name="repm_id"	value="<%=repmVO.getRepm_id()%>"> 
				<input type="hidden" name="rest_id" value="<%=repmVO.getRest_id()%>"> 
				<button type="submit" class="btn btn-default btn-sm">修改專案資訊</button>
			</FORM>
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
