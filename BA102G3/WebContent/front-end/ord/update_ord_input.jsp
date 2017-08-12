<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord.model.*"%>
<%
	OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>訂單修改 - update_ord_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訂單修改 - update_ord_input.jsp</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do" name="form1">
<table border="0">
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=ordVO.getOrd_id()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="user_id" size="45"	value="<%=ordVO.getUser_id()%>" /></td>
	</tr>
	<tr>
		<td>商家編號:</td>
		<td><input type="TEXT" name="store_id" size="45"	value="<%=ordVO.getStore_id()%>" /></td>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="ord_date" value="<%=ordVO.getOrd_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','ord_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="訂單日期"></a>
		</td>
	</tr>
	<tr>
		<td>總價:</td>
		<td><input type="TEXT" name="ord_total" size="45"	value="<%=ordVO.getOrd_total()%>" /></td>
	</tr>
	<tr>
		<td>買家付款狀態:</td>
		<td><input type="TEXT" name="ord_bill" size="45"	value="<%=ordVO.getOrd_bill()%>" /></td>
	</tr>
	<tr>
		<td>商店審核訂單狀態:</td>
		<td><input type="TEXT" name="ord_grant" size="45"	value="<%=ordVO.getOrd_grant()%>" /></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="ord_status" size="45"	value="<%=ordVO.getOrd_status()%>" /></td>
	</tr>
	<tr>
		<td>買家對於商店評分:</td>
		<td><input type="TEXT" name="ord_sscore" size="45"	value="<%=ordVO.getOrd_sscore()%>" /></td>
	</tr>
	<tr>
		<td>商家檢舉訂單日期:</td>
		<td><input type="TEXT" name="ord_rpdate" size="45"	value="<%=ordVO.getOrd_rpdate()%>" /></td>
	</tr>
	<tr>
		<td>檢舉訂單理由:</td>
		<td><input type="TEXT" name="ord_rpcomm" size="45"	value="<%=ordVO.getOrd_rpcomm()%>" /></td>
	</tr>
	<tr>
		<td>商家檢舉訂單狀態:</td>
		<td><input type="TEXT" name="ord_rpstatus" size="45"	value="<%=ordVO.getOrd_status()%>" /></td>
	</tr>

	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
	<tr>
		<td>商家名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="store_id">
			<c:forEach var="storeVO" items="${storeSvc.all}">
				<option value="${storeVO.store_id}" ${(ordVO.store_id==storeVO.store_id)?'selected':'' } >${storeVO.store_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ord_id" value="<%=ordVO.getOrd_id()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
