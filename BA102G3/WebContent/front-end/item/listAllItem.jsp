<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ItemService itemSvc = new ItemService();
    List<ItemVO> list = itemSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>訂單明細資料 - listAllItem.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有訂單明細資料 - ListAllItem.jsp</h3>
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
		<th>產品編號</th>
		<th>產品數量</th>
		<th>買家對產品評分</th>
		<th>產品評論</th>
		<th>填寫產品評論日期</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${itemVO.ord_id}</td>
			<td>${itemVO.prod_id}</td>
			<td>${itemVO.item_qty}</td>
			<td>${itemVO.item_score}</td>
			<td>${itemVO.item_review}</td>
			<td>${itemVO.item_reviewdate}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/item/item.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ord_id" value="${itemVO.ord_id}">
			      <input type="hidden" name="prod_id" value="${itemVO.prod_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/item/item.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="ord_id" value="${itemVO.ord_id}">
			      <input type="hidden" name="prod_id" value="${itemVO.prod_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
