<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rest.model.*"%>
<%
RestVO restVO = (RestVO) request.getAttribute("restVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>料理資料修改 - update_rest_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>料理資料修改 - update_emp_input.jsp</h3>
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

<FORM METHOD="post" ACTION="rest.do" name="form1">
<table border="0">
	<tr>
		<td>餐廳編號:</td>
		<td><input type="TEXT" name="rest_id" size="45" 
			value="<%= (restVO==null)?"3000001":restVO.getRest_id() %>" />
			</td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="user_id" size="45" 
			value="<%= (restVO==null)?"1000046":restVO.getUser_id() %>" />
			</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>餐廳名稱:</td> -->
<!-- 		<td><input type="TEXT" name="rest_name" size="45"  -->
<%-- 			value="<%= (restVO==null)?"雲象泰式餐廳":restVO.getRest_name() %>" /> --%>
<!-- 			</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>地址:</td> -->
<!-- 		<td><input type="TEXT" name="rest_address" size="45" -->
<%-- 			value="<%= (restVO==null)? "T2-3F" : restVO.getRest_address()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="rest_phone" size="45"
			value="<%= (restVO==null)? "03-383-3133#261" : restVO.getRest_phone()%>"/></td>
	</tr>
	<tr>
		<td>餐廳描述:</td>
		<% String xxx="雲象泰式餐廳為台南知名小吃周氏蝦捲餐飲集團，為了滿足消費者對於泰式料理的需求，特別遠從泰國禮聘重量級實力派主廚“泰拿吉”，為的就是要呈現出原汁原味的泰國風味，希望能給消費者全新的美味感受。"; %>
<%-- 		<td><textarea name="rest_detail" ><%= (restVO==null)? xxx :restVO.getrest_detail() %></texrarea></td> --%>
			<td><textarea name="rest_detail" cols="40" rows="5"><%= (restVO==null)? xxx :restVO.getRest_detail() %></textarea></td>
	</tr>
	<tr>
		<td>營業時段:</td>
		<td><input type="TEXT" name="rest_hours" size="45"
			value="<%= (restVO==null)? "06:00-22:30" : restVO.getRest_hours()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>所在航廈:</td> -->
<!-- 		<td><input type="TEXT" name="rest_ter" size="45" -->
<%-- 			value="<%= (restVO==null)? "2" : restVO.getRest_ter()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>所在樓層:</td> -->
<!-- 		<td><input type="TEXT" name="rest_floor" size="45" -->
<%-- 			value="<%= (restVO==null)? "3" : restVO.getRest_floor()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>出入境位置:</td> -->
<!-- 		<td><input type="TEXT" name="rest_inout" size="45" -->
<%-- 			value="<%= (restVO==null)? "2" : restVO.getRest_inout()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>種類:</td> -->
<!-- 		<td><input type="TEXT" name="rest_type" size="45" -->
<%-- 			value="<%= (restVO==null)? "1" : restVO.getRest_type()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>被評分次數:</td> -->
<!-- 		<td><input type="TEXT" name="rest_count" size="45" -->
<%-- 			value="<%= (restVO==null)? "5" : restVO.getRest_count()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>評分總分:</td> -->
<!-- 		<td><input type="TEXT" name="rest_score" size="45" -->
<%-- 			value="<%= (restVO==null)? "4" : restVO.getRest_score()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>經度:</td> -->
<!-- 		<td><input type="TEXT" name="rest_lon" size="45" -->
<%-- 			value="<%= (restVO==null)? "121.6498489" : restVO.getRest_lon()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>緯度:</td> -->
<!-- 		<td><input type="TEXT" name="rest_lon" size="45" -->
<%-- 			value="<%= (restVO==null)? "25.6498489" : restVO.getRest_lat()%>" /></td> --%>
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="rest_id" value="<%= restVO.getRest_id()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
