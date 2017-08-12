<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sysu.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    SysuService sysuSvc = new SysuService();
    List<SysuVO> list = sysuSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有管理者資料 - listAllSysu.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有管理者資料 - ListAllSysu.jsp</h3>
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
		<th>編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>型態</th>
		<th>姓</th>
		<th>名</th>
		<td>E-mail</td>
	    <td>加入日期</td>
		<td>狀態</td>
		<th></th>
		<th></th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="sysuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${sysuVO.sysu_id}</td>
			<td>${sysuVO.sysu_account}</td>
			<td>${sysuVO.sysu_passwd}</td>
			<td>${sysuVO.sysu_type}</td>
			<td>${sysuVO.sysu_lastname}</td>
			<td>${sysuVO.sysu_firstname}</td>
			<td>${sysuVO.sysu_email}</td>
			<td>${sysuVO.sysu_joindate}</td>
			<td>${sysuVO.sysu_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/sysu/sysu.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="sysu_id" value="${sysuVO.sysu_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/sysu/sysu.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="sysu_id" value="${sysuVO.sysu_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
