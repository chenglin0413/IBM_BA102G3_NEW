<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prpi.model.*"%>
<%
	PrpiVO prpiVO = (PrpiVO) request.getAttribute("prpiVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>產品圖片資料修改 - update_prpi_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產品圖片資料修改 - update_prpi_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="prpi.do" name="form1">
<table border="0">
	<tr>
		<td>產品圖片編號:<font color=red><b>*</b></font></td>
		<td><%=prpiVO.getPrpi_id()%></td>
	</tr>
	<tr>
		<td>產品圖片名稱:</td>
		<td><input type="TEXT" name="prpi_name" size="45" value="<%=prpiVO.getPrpi_name()%>" /></td>
	</tr>
	
	<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
	<tr>
		<td>產品編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="prod_id">
			<c:forEach var="prodVO" items="${prodSvc.all}">
				<option value="${prodVO.prod_id}" ${(prpiVO.prod_id==prodVO.prod_id)?'selected':'' } >${prodVO.prod_name}
			</c:forEach>
		</select></td>
	</tr>
	
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="prpi_img" size="45" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prpi_id" value="<%=prpiVO.getPrpi_id()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
