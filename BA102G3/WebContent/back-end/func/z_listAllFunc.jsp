<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.func.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	FuncService funcSvc = new FuncService();
	List<FuncVO> list = funcSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有管理者資料 - listAllFunc.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有管理者資料 - ListAllAuth.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>功能編號</th>
		<th>功能名稱</th>
		<th>功能路徑</th>
		<th>功能狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="funcVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${funcVO.func_id}</td>
			<td>${funcVO.func_name}</td>
			<td>${funcVO.func_path}</td>
			<td>${funcVO.func_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/func/func.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="func_id" value="${funcVO.func_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/func/func.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="func_id" value="${funcVO.func_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
