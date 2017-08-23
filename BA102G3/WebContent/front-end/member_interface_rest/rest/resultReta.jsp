<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reta.model.*"%>
<%@ page import="com.avtb.model.*"%>
<%
request.setCharacterEncoding("UTF-8");
RetaVO retaVO = (RetaVO) request.getAttribute("retaVO");
AvtbVO avtbVO = (AvtbVO) request.getAttribute("avtbVO");
%>

<html>
<head>
<title>確認訂位資訊 - resultReta.jsp</title>
<meta charset="UTF-8">	
</head>


<body bgcolor='white'>

<jsp:useBean id="avtbSvc" scope="page" class="com.avtb.model.AvtbService"></jsp:useBean>

<h3>確認訂位資料 - resultReta.jsp</h3>

<h3>訂位資料:</h3>
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
<%
java.sql.Timestamp sql = new Timestamp(System.currentTimeMillis()); 
%>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/restaurant/reta/reta.do" name="form1" id="form1">
<table border="0">

	<tr>
		<td>所選時段:</td>
		<td>${retaVO.avtb_id}</td>
	</tr>
	<tr>
		<td>使用者編號:</td>
		<td>${retaVO.user_id}</td>
	</tr>
	<tr>
		<td>訂位人數:</td>
		<td>${retaVO.reta_number}</td>
	</tr>
	
	<tr>
		<td>到位狀況:</td>
		<td>${retaVO.reta_status}</td>
	</tr>
</table>
<br>
<a href="<%=request.getContextPath() %>/front-end/member_interface_rest/rest/listAllRest.jsp">回首頁</a>

</FORM>
</body>

</html>
