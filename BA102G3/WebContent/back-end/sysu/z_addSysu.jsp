<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sysu.model.*"%>
<%
SysuVO sysuVO = (SysuVO) request.getAttribute("sysuVO");
%>

<html>
<head>
<title>新增員工資料 - addSysu.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/back-end/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath() %>/back-end/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>新增員工資料 - addSysu.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>員工資料:</h3>
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

<!-- 
<FORM METHOD="post" ACTION="sysu.do" name="form1">
 -->
 
 <FORM class="uploadImage" METHOD="post" ACTION="sysu.do" name="form1">

<!---  
<FORM class="uploadImage" action="sysu.do" method="post" enctype="multipart/form-data">
-->

<table border="0">

	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT" name="sysu_account" size="45" 
			value="<%= (sysuVO==null)? "" : sysuVO.getSysu_account()%>" /></td>
	</tr>

	<tr>
		<td>姓:</td>
		<td><input type="TEXT" name="sysu_lastname" size="45"
			value="<%= (sysuVO==null)? "" : sysuVO.getSysu_lastname()%>" /></td>
	</tr>

	<tr>
		<td>名:</td>
		<td><input type="TEXT" name="sysu_firstname" size="45"
			value="<%= (sysuVO==null)? "" : sysuVO.getSysu_firstname()%>" /></td>
	</tr>

	<tr>
		<td>員工型態:</td>
		<td><input type="TEXT" name="sysu_type" size="45"
			value="<%= (sysuVO==null)? "" : sysuVO.getSysu_firstname()%>" /></td>
	</tr>
		
	<tr>
		<td>員工E-mail:</td>
		<td><input type="TEXT" name="sysu_email" size="45"
			value="<%= (sysuVO==null)? "" : sysuVO.getSysu_email()%>" /></td>
	</tr>
			
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>加入日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="sysu_joindate" value="<%= (sysuVO==null)? date_SQL : sysuVO.getSysu_joindate()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="<%= request.getContextPath() %>/back-end/image/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	
</table>

<br>
 
<br> 
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="sysu_status" value="1">
<input type="submit" value="送出新增">
</FORM>
</body>

</html>
