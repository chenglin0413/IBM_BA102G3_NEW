<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.func.model.*"%>
<%
FuncVO funcVO = (FuncVO) request.getAttribute("funcVO");
%>

<html>
<head>
<title>新增員工資料 - addFunc.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/back-end/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath() %>/back-end/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>新增功能 - addFunc.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>功能資料:</h3>
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
<FORM METHOD="post" ACTION="func.do" name="form1">
 -->
 
 <FORM class="uploadImage" METHOD="post" ACTION="func.do" name="form1">

<!---  
<FORM class="uploadImage" action="func.do" method="post" enctype="multipart/form-data">
-->

<table border="0">

	<tr>
		<td>功能名稱:</td>
		<td><input type="TEXT" name="func_name" size="45" 
			value="<%= (funcVO==null)? "" : funcVO.getFunc_name()%>" /></td>
	</tr>

	<tr>
		<td>功能路徑:</td>
		<td><input type="TEXT" name="func_path" size="45"
			value="<%= (funcVO==null)? "" : funcVO.getFunc_path()%>" /></td>
	</tr>
	
</table>

<br>
 
<br> 
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="func_status" value="1">
<input type="submit" value="送出新增">
</FORM>
</body>

</html>
