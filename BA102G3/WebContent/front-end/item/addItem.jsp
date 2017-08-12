<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*,com.prod.model.*,com.ord.model.*"%>
<%
ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");
%>

<html>
<head>
<title>訂單明細新增 - addItem.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訂單明細新增 - addItem.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/item/item.do" name="form1">
<table border="0">

	<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="ord_id">
			<c:forEach var="ordVO" items="${ordSvc.all}">
				<option value="${ordVO.ord_id}" ${(itemVO.ord_id==ordVO.ord_id)? 'selected':'' } >${ordVO.ord_id}
			</c:forEach>
		</select></td>
	</tr>
	<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
	<tr>
		<td>產品名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="prod_id">
			<c:forEach var="prodVO" items="${prodSvc.all}">
				<option value="${prodVO.prod_id}" ${(itemVO.prod_id==prodVO.prod_id)? 'selected':'' } >${prodVO.prod_name}
			</c:forEach>
		</select></td>
	</tr>

	<tr>
		<td>產品數量:</td>
		<td><input type="TEXT" name="item_qty" size="45"
			value="<%= (itemVO==null)? "50" : itemVO.getItem_qty()%>" /></td>
	</tr>
	<tr>
		<td>買家對產品評分:</td>
		<td><input type="TEXT" name="item_score" size="45"
			value="<%= (itemVO==null)? "5" : itemVO.getItem_score()%>" /></td>
	</tr>
	<tr>
		<td>產品評論:</td>
		<td><input type="TEXT" name="item_review" size="45"
			value="<%= (itemVO==null)? "好難吃" : itemVO.getItem_review()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>填寫產品評論日期:</td>
		<td bgcolor="#CCCCFF">
		    <input type="date"	 name="item_reviewdate" value="<%= (itemVO==null)? date_SQL :itemVO.getItem_reviewdate()%>">
		
		</td>
	</tr>



</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
