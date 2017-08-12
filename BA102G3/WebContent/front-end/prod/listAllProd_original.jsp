<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prod.model.*,com.prod.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ProdService prodSvc = new ProdService();
    List<ProdVO> list = prodSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有產品資料 - listAllProd.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有廠商資料 - listAllProd.jsp</h3>
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
		<th>產品編號</th>
		<th>商店編號</th>
		<th>產品名稱</th>
		<th>產品描述</th>
		<th>產品價格</th>
		<th>產品種類</th>
		<th>產品規格</th>
		<th>產品品牌</th>
		<th>產品更新日期</th>
		<th>產品已售出數量</th>
		<th>產品狀態</th>
		<th>產品被評分次數</th>
		<th>產品總分</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="prodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(prodVO.prod_id==param.prod_id) ? 'bgcolor=#CCCCFF':''}>
			<td>${prodVO.prod_id}</td>
			<td>${prodVO.store_id}</td>
			<td>${prodVO.prod_name}</td>
			<td>${prodVO.prod_descript}</td>
			<td>${prodVO.prod_price}</td>
			<td>${prodVO.prod_sort}</td>
			<td>${prodVO.prod_format}</td>
			<td>${prodVO.prod_brand}</td>
			<td>${prodVO.prod_updatetime}</td>
			<td>${prodVO.prod_soldcount}</td>
			<td>${prodVO.prod_status}</td>
			<td>${prodVO.prod_count}</td>
			<td>${prodVO.prod_score}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/prod/prod.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prod_id" value="${prodVO.prod_id}">
			      <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			      <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/prod/prod.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="prod_id" value="${prodVO.prod_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
