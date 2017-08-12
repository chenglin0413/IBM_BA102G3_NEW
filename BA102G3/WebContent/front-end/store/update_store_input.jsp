<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*,com.store.controller.*"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>廠商修改 - update_store_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商圖片修改 - update_store_input.jsp</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/store/store.do" name="form1">
<table border="0">
	<tr>
		<td>廠商編號:<font color=red><b>*</b></font></td>
		<td><%=storeVO.getStore_id()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="user_id" size="45" value="<%=storeVO.getUser_id()%>" /></td>
	</tr>
	<tr>
		<td>廠商名稱:</td>
		<td><input type="TEXT" name="store_name" size="45"	value="<%=storeVO.getStore_name()%>" /></td>
	</tr>
	<tr>
		<td>營業時間:</td>
		<td><input type="TEXT" name="store_time" size="45"	value="<%=storeVO.getStore_time()%>" /></td>
	</tr>
	<tr>
		<td>連絡電話:</td>
		<td><input type="TEXT" name="store_phone" size="45"	value="<%=storeVO.getStore_phone()%>" /></td>
	</tr>
	<tr>
		<td>廠商描述:</td>
		<td><input type="TEXT" name="store_describe" size="45"	value="<%=storeVO.getStore_describe()%>" /></td>
	</tr>
	<tr>
		<td>所在航廈:</td>
		<td><input type="TEXT" name="store_ter" size="45"	value="<%=storeVO.getStore_ter()%>" /></td>
	</tr>
	<tr>
		<td>所在樓層:</td>
		<td><input type="TEXT" name="store_floor" size="45"	value="<%=storeVO.getStore_floor()%>" /></td>
	</tr>
	<tr>
		<td>所在經度:</td>
		<td><input type="TEXT" name="store_lon" size="45"	value="<%=storeVO.getStore_lon()%>" /></td>
	</tr>
	<tr>
		<td>所在緯度:</td>
		<td><input type="TEXT" name="store_lat" size="45"	value="<%=storeVO.getStore_lat()%>" /></td>
	</tr>
	<tr>
		<td>出入境位置:</td>
		<td><input type="TEXT" name="store_inout" size="45"	value="<%=storeVO.getStore_inout()%>" /></td>
	</tr>
	<tr>
		<td>評分次數:</td>
		<td><input type="TEXT" name="store_count" size="45"	value="<%=storeVO.getStore_count()%>" /></td>
	</tr>
	<tr>
		<td>評分總分:</td>
		<td><input type="TEXT" name="store_score" size="45"	value="<%=storeVO.getStore_score()%>" /></td>
	</tr>

<%-- 	<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>會員編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="user_id"> -->
<%-- 			<c:forEach var="userVO" items="${userSvc.all}"> --%>
<%-- 				<option value="${userVO.user_id}" ${(storeVO.user_id==userVO.user_id)?'selected':'' } >${userVO.user_account} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="store_id" value="<%=storeVO.getStore_id()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
