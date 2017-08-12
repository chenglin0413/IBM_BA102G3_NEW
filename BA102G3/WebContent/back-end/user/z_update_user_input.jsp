<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%
	UserVO userVO = (UserVO) request.getAttribute("userVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<title>會員資料修改 - update_user_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/back-end/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath() %>/back-end/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員資料修改 - update_user_input.jsp</h3>
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

<FORM METHOD="post" ACTION="user.do" name="form1">
<table border="0">
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=userVO.getUser_id()%></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="user_account" size="45" value="<%=userVO.getUser_account()%>" /></td>
	</tr>	
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="user_passwd" size="45" value="<%=userVO.getUser_passwd()%>" /></td>
	</tr>
	<tr>
		<td>會員姓:</td>
		<td><input type="TEXT" name="user_lastname" size="45" value="<%=userVO.getUser_lastname()%>" /></td>
	</tr>
	<tr>
		<td>會員名:</td>
		<td><input type="TEXT" name="user_firstname" size="45"	value="<%=userVO.getUser_firstname()%>" /></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="user_phone" size="45"	value="<%=userVO.getUser_phone()%>" /></td>
	</tr>
	<tr>
		<td>會員手機:</td>
		<td><input type="TEXT" name="user_mobile" size="45"	value="<%=userVO.getUser_mobile()%>" /></td>
	</tr>
	<tr>
		<td>會員地址:</td>
		<td><input type="TEXT" name="user_address" size="45"	value="<%=userVO.getUser_address()%>" /></td>
	</tr>
	<tr>
		<td>會員E-mail:</td>
		<td><input type="TEXT" name="user_email" size="45"	value="<%=userVO.getUser_email()%>" /></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td><input type="TEXT" name="user_status" size="45"	value="<%=userVO.getUser_status()%>" /></td>
	</tr>
	
</table>
<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="user_id" value="<%=userVO.getUser_id()%>">
<input type="hidden" name="user_type" value="<%=userVO.getUser_type()%>">
<input type="hidden" name="user_joindate" value="<%=userVO.getUser_joindate()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
