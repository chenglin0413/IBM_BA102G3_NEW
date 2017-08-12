<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*,com.prod.controller.*"%>
<%
ProdVO prodVO = (ProdVO) request.getAttribute("prodVO");
%>

<html>
<head>
<title>產品新增 - addProd.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產品新增 - addProd.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>產品資料:</h3>
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
		<td>商家編號:</td>
		<td><input type="TEXT" name="store_id" size="45"	value="<%= (prodVO==null)? "2000003":prodVO.getStore_id()%>" /></td>
	</tr>
	<tr>
		<td>產品名稱:</td>
		<td><input type="TEXT" name="prod_name" size="45"	value="<%= (prodVO==null)? "白蘭氏傳統雞精盒":prodVO.getProd_name()%>" /></td>
	</tr>
	<tr>
		<td>產品描述:</td>
		<td><input type="TEXT" name="prod_descript" size="45"	value="<%= (prodVO==null)? "零脂肪、零膽固醇、低鈉，且不添加防腐劑及人工色素 。":prodVO.getProd_descript()%>" /></td>
	</tr>
	<tr>
		<td>產品價格:</td>
		<td><input type="TEXT" name="prod_price" size="45"	value="<%= (prodVO==null)? "450":prodVO.getProd_price()%>" /></td>
	</tr>
	<tr>
		<td>產品種類:</td>
		<td><input type="TEXT" name="prod_sort" size="45"	value="<%= (prodVO==null)? "伴手禮":prodVO.getProd_sort()%>" /></td>
	</tr>
	<tr>
		<td>產品規格:</td>
		<td><input type="TEXT" name="prod_format" size="45"	value="<%= (prodVO==null)? "無":prodVO.getProd_format()%>" /></td>
	</tr>
	<tr>
		<td>產品品牌:</td>
		<td><input type="TEXT" name="prod_brand" size="45"	value="<%= (prodVO==null)? "白蘭氏" :prodVO.getProd_brand()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<c:set scope="page" var="prod_updatetime" value="<%=date_SQL%>"></c:set>
		<td>產品更新日期:<font color=red><b>*</b></font></td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="prod_updatetime" value="${(param.prod_updatetime==null or param.prod_updatetime=='')? prod_updatetime : param.prod_updatetime }">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','prod_updatetime','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="產品更新日期"></a>
		</td><td>${errorMsgs.prod_updatetime}</td>
	</tr>
	<tr>
		<td>產品售出數量:</td>
		<td><input type="TEXT" name="prod_soldcount" size="45"	value="<%= (prodVO==null)? "25":prodVO.getProd_soldcount()%>" /></td>
	</tr>
	<tr>
		<td>產品狀態:</td>
		<td><input type="TEXT" name="prod_status" size="45"	value="<%= (prodVO==null)? "1":prodVO.getProd_status()%>" /></td>
	</tr>
	<tr>
		<td>產品被評分次數:</td>
		<td><input type="TEXT" name="prod_count" size="45"	value="<%= (prodVO==null)? "5":prodVO.getProd_count()%>" /></td>
	</tr>
	<tr>
		<td>產品評分總分:</td>
		<td><input type="TEXT" name="prod_score" size="45"	value="<%= (prodVO==null)? "100":prodVO.getProd_score()%>" /></td>
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
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
