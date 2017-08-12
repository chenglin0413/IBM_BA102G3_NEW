<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*,com.store.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    StoreService storeSvc = new StoreService();
    List<StoreVO> list = storeSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有廠商資料 - listAllStore.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有廠商資料 - ListAllStore.jsp</h3>
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
		<th>商家編號</th>
		<th>使用者編號</th>
		<th>商家名稱</th>
		<th>營業時間</th>
		<th>商家電話</th>
		<th>商家描述</th>
		<th>所在航廈</th>
		<th>所在樓層</th>
		<th>經度</th>
		<th>緯度</th>
		<th>出境_入境</th>
		<th>商家評分次數</th>
		<th>商家得分</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="storeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(storeVO.store_id==param.store_id) ? 'bgcolor=#CCCCFF':''}>
			<td>${storeVO.store_id}</td>
			<td>${storeVO.user_id}</td>
			<td>${storeVO.store_name}</td>
			<td>${storeVO.store_time}</td>
			<td>${storeVO.store_phone}</td>
			<td>${storeVO.store_describe}</td>
			<td>${storeVO.store_ter}</td>
			<td>${storeVO.store_lon}</td>
			<td>${storeVO.store_lat}</td>
			<td>${storeVO.store_inout}</td>
			<td>${storeVO.store_count}</td>
			<td>${storeVO.store_score}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/store/store.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="store_id" value="${storeVO.store_id}">
			      <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			      <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/store/store.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="store_id" value="${storeVO.store_id}">
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
