<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord.model.*,com.ord.controller.*"%>
<%
OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
%>

<html>
<head>
<title>新增訂單 - addOrd.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訂單新增 - addOrd.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>產品資料:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/memberAO.do" name="form1">
<table border="0">

	
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="user_id" size="45"	value="<%= (ordVO==null)? "1000003":ordVO.getUser_id()%>" /></td>
	</tr>
	<tr>
		<td>商家編號:</td>
		<td><input type="TEXT" name="store_id" size="45"	value="<%= (ordVO==null)? "2000004":ordVO.getStore_id()%>" /></td>
	</tr>
    <tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<p>訂單日期:</p>
		    <input type="date" name="ord_date">
		</p>${errorMsgs.ord_date}</p>
	</tr>
	<tr>
		<td>總價:</td>
		<td><input type="TEXT" name="ord_total" size="45"	value="<%= (ordVO==null)? "6000":ordVO.getOrd_total()%>" /></td>
	</tr>
	

<%-- 	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>商家名稱:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="store_id"> -->
<%-- 			<c:forEach var="storeVO" items="${storeSvc.all}"> --%>
<%-- 				<option value="${storeVO.store_id}" ${(storeVO.store_id==ordVO.store_id)?'selected':'' } >${storeVO.store_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
