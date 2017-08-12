<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%
	ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>訂單明細資料修改 - update_item_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訂單明細資料修改 - update_item_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>訂單明細資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/item/item.do" name="form1">
<table border="0">
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=itemVO.getOrd_id()%></td>
	</tr>
	<tr>
		<td>產品編號:<font color=red><b>*</b></font></td>
		<td><%=itemVO.getProd_id()%></td>
	</tr>
	<tr>
		<td>產品數量:</td>
		<td><input type="TEXT" name="item_qty" size="45"  value="<%=itemVO.getItem_qty()%>" /></td>
	</tr>
	<tr>
		<td>買家對產品評分:</td>
		<td><input type="TEXT" name="item_score" size="45"	value="<%=itemVO.getItem_score()%>" /></td>
	</tr>
	<tr>
		<td>產品評論:</td>
		<td><input type="TEXT" name="item_review" size="45"	value="<%=itemVO.getItem_review()%>" /></td>
	</tr>
	
	<tr>
		<td>填寫產品評論日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="item_reviewdate" value="<%=itemVO.getItem_reviewdate()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','item_reviewdate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="填寫產品評論日期"></a>
		</td>
	</tr>
	

	<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="prod_id">
			<c:forEach var="prodVO" items="${prodSvc.all}">
				<option value="${prodVO.prod_id}" ${(itemVO.prod_id==prodVO.prod_id)?'selected':'' } >${prodVO.prod_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ord_id" value="<%=itemVO.getOrd_id()%>">
<input type="hidden" name="prod_id" value="<%=itemVO.getProd_id()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
