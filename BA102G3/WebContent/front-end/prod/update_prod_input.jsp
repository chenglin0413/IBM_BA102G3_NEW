<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*"%>
<%
	ProdVO prodVO = (ProdVO) request.getAttribute("prodVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>產品修改 - update_prod_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產品修改 - update_prod_input.jsp</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/prod/prod.do" name="form1">
<table border="0">
	<tr>
		<td>廠商編號:<font color=red><b>*</b></font></td>
		<td><%=prodVO.getProd_id()%></td>
	</tr>
	<tr>
		<td>商家編號:</td>
		<td><input type="TEXT" name="store_id" size="45"	value="<%=prodVO.getStore_id()%>" /></td>
	</tr>
	<tr>
		<td>產品名稱:</td>
		<td><input type="TEXT" name="prod_name" size="45"	value="<%=prodVO.getProd_name()%>" /></td>
	</tr>
	<tr>
		<td>產品描述:</td>
		<td><input type="TEXT" name="prod_descript" size="45"	value="<%=prodVO.getProd_descript()%>" /></td>
	</tr>
	<tr>
		<td>產品價格:</td>
		<td><input type="TEXT" name="prod_price" size="45"	value="<%=prodVO.getProd_price()%>" /></td>
	</tr>
	<tr>
		<td>產品種類:</td>
		<td><input type="TEXT" name="prod_sort" size="45"	value="<%=prodVO.getProd_sort()%>" /></td>
	</tr>
	<tr>
		<td>產品規格:</td>
		<td><input type="TEXT" name="prod_format" size="45"	value="<%=prodVO.getProd_format()%>" /></td>
	</tr>
	<tr>
		<td>產品品牌:</td>
		<td><input type="TEXT" name="prod_brand" size="45"	value="<%=prodVO.getProd_brand()%>" /></td>
	</tr>
	<tr>
		<td>產品更新日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="prod_updatetime" value="<%=prodVO.getProd_updatetime()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','prod_updatetime','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="產品更新日期"></a>
		</td>
	</tr>
	<tr>
		<td>產品售出數量:</td>
		<td><input type="TEXT" name="prod_soldcount" size="45"	value="<%=prodVO.getProd_soldcount()%>" /></td>
	</tr>
	<tr>
		<td>產品狀態:</td>
		<td><input type="TEXT" name="prod_status" size="45"	value="<%=prodVO.getProd_status()%>" /></td>
	</tr>
	<tr>
		<td>產品被評分次數:</td>
		<td><input type="TEXT" name="prod_count" size="45"	value="<%=prodVO.getProd_count()%>" /></td>
	</tr>
	<tr>
		<td>產品評分總分:</td>
		<td><input type="TEXT" name="prod_score" size="45"	value="<%=prodVO.getProd_score()%>" /></td>
	</tr>

	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
	<tr>
		<td>商家名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="store_id">
			<c:forEach var="storeVO" items="${storeSvc.all}">
				<option value="${storeVO.store_id}" ${(prodVO.store_id==storeVO.store_id)?'selected':'' } >${storeVO.store_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_id" value="<%=prodVO.getProd_id()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
