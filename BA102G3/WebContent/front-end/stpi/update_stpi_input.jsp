<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.stpi.model.*"%>
<%
	StpiVO stpiVO = (StpiVO) request.getAttribute("stpiVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>廠商圖片修改 - update_stpi_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商圖片修改 - update_stpi_input.jsp</h3>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/front-end/stpi/stpi.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>廠商圖片編號:<font color=red><b>*</b></font></td>
		<td><%=stpiVO.getStpi_id()%></td>
	</tr>
	<tr>
		<td>廠商編號:</td>
		<td><input type="TEXT" name="store_id" size="45" value="<%=stpiVO.getStore_id()%>" /></td>
	</tr>
	<tr>
		<td>廠商圖片名稱:</td>
		<td><input type="TEXT" name="stpi_name" size="45"	value="<%=stpiVO.getStpi_name()%>" /></td>
	</tr>
	
	<tr>
		<td>廠商圖片格式:</td>
		<td><input type="TEXT" name="stpi_imgfmt" size="45"	value="<%=stpiVO.getStpi_imgfmt()%>" /></td>
	</tr>

	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
	<tr>
		<td>廠商編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="store_id">
			<c:forEach var="storeVO" items="${storeSvc.all}">
				<option value="${storeVO.store_id}" ${(stpiVO.store_id==storeVO.store_id)?'selected':'' } >${storeVO.store_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
	<tr><td>廠商圖片:</td>
		<input type="file" id="preview" name="stpi_img" onchange="showImage(0)" multiple accept="image/*"><br/>
		<img src="" id="output1" name="img"  height="60px"><p>
	</tr>

<input type="hidden" name="action" value="update">
<input type="hidden" name="stpi_id" value="<%=stpiVO.getStpi_id()%>">
<input type="submit" value="送出修改"></FORM>



<script type = "text/javascript" >


function showImage(i){
	var file = document.getElementsByName('stpi_img')[i].files[0];
	var fReader = new FileReader();
	fReader.readAsDataURL(file);
	fReader.onloadend = function(){
		var image = document.getElementsByName('img')[i];
		image.src = fReader.result;
		console.log(fReader.result);

	}
}

</script>
</body>
</html>
