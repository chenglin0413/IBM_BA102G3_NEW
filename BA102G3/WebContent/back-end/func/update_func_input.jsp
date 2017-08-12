<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.func.model.*"%>
<%
	FuncVO funcVO = (FuncVO) request.getAttribute("funcVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<title>功能資料修改 - update_func_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/back-end/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath() %>/back-end/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>功能資料修改 - update_func_input.jsp</h3>
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

<FORM METHOD="post" ACTION="func.do" name="form1">
<table border="0">
	<tr>
		<td>功能編號:<font color=red><b>*</b></font></td>
		<td><%=funcVO.getFunc_id()%></td>
	</tr>
	<tr>
		<td>功能名稱:</td>
		<td><input type="TEXT" name="func_name" size="45" value="<%=funcVO.getFunc_name()%>" /></td>
	</tr>
	<tr>
		<td>功能路徑:</td>
		<td><input type="TEXT" name="func_name" size="45" value="<%=funcVO.getFunc_path()%>" /></td>
	</tr>	
			
</table>
<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="func_id" value="<%=funcVO.getFunc_id()%>">
<input type="hidden" name="func_name" value="<%=funcVO.getFunc_name()%>">
<input type="hidden" name="func_path" value="<%=funcVO.getFunc_path()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
