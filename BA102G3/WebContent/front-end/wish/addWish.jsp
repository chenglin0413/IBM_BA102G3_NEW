<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wish.model.*,com.wish.controller.*,com.prod.model.*"%>
<%
WishVO wishVO = (WishVO) request.getAttribute("wishVO");
%>

<html>
<head>
<title>追蹤產品新增 - addWish.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>追蹤產品新增 - addWish.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>追蹤產品資料:</h3>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/front-end/wish/wish.do" name="form1">
<table border="0">

	<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
	<tr>
		<td>產品名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="user_id">
			<c:forEach var="userVO" items="${userSvc.all}">
				<option value="${userVO.user_id}" >${userVO.user_lastname}
			</c:forEach>
		</select></td>
	</tr>
	<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
	<tr>
		<td>產品名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="prod_id">
			<c:forEach var="prodVO" items="${prodSvc.all}">
				<option value="${prodVO.prod_id}" >${prodVO.prod_name}
			</c:forEach>
		</select></td>
	</tr>
	
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>產品追蹤日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="wish_date" value="<%= (wishVO==null)? date_SQL : wishVO.getWish_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','wish_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="產品追蹤日期"></a>
		</td>
	</tr>
	

</table>

<tr><input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></tr>

</FORM>





</body>

</html>
