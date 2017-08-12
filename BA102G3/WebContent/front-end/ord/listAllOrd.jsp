<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    OrdService ordSvc = new OrdService();
    List<OrdVO> list = ordSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有產品資料 - listAllOrd.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有產品資料 - ListAllOrd.jsp</h3>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>商店編號</th>
		<th>訂單日期</th>
		<th>總價</th>
		<th>買家付款狀態</th>
		<th>商家審核訂單狀態</th>
		<th>訂單狀態</th>
		<th>買家對商店評分</th>
		<th>商家檢舉訂單日期</th>
		<th>檢舉訂單理由</th>
		<th>商家檢舉訂單狀態</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${ordVO.ord_id}</td>
			<td>${ordVO.user_id}</td>
			<td>${ordVO.store_id}</td>
			<td>${ordVO.ord_date}</td>
			<td>${ordVO.ord_total}</td>
			<td>${ordVO.ord_bill}</td>
			<td>${ordVO.ord_grant}</td>
			<td>${ordVO.ord_status}</td>
			<td>${ordVO.ord_sscore}</td>
			<td>${ordVO.ord_rpdate}</td>
			<td>${ordVO.ord_rpcomm}</td>
			<td>${ordVO.ord_rpstatus}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ord_id" value="${ordVO.ord_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="ord_id" value="${ordVO.ord_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
