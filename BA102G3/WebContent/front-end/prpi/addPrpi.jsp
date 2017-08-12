<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prpi.model.*"%>
<%
	PrpiVO prpiVO = (PrpiVO) request.getAttribute("prpiVO");
%>

<html>
<head>
<title>員工資料新增 - addPrpi.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>員工資料新增 - addPrpi.jsp</h3>
			</td>
			<td><a href="select_page.jsp"><img src="images/tomcat.gif"
					width="100" height="100" border="1">回首頁</a></td>
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

	<FORM METHOD="post" ACTION="prpi.do" name="form1">
		<table border="0">

			<tr>
				<td>產品名稱:</td>
				<td><input type="TEXT" name="prpi_name" size="45"
					value="<%=(prpiVO == null) ? "產品" : prpiVO.getPrpi_name()%>" /></td>
			</tr>
			<!--  
	<tr>
		<td>產品編號:</td>
		<td><input type="TEXT" name="prod_id" size="45" 
			value="<%=(prpiVO == null) ? "2200002" : prpiVO.getProd_id()%>" /></td>
	</tr>	
-->
			<jsp:useBean id="prodSvc" scope="page"
				class="com.prod.model.ProdService" />

			<tr>
				<td>產品編號:<font color=red></font></td>
				<td><select size="1" name="prod_id">
						<c:forEach var="prodVO" items="${prodSvc.all}">
							<option value="${prodVO.prod_id}"
								${(prpiVO.prod_id==prodVO.prod_id)? 'selected':'' }>${prodVO.prod_name}
						</c:forEach>
				</select></td>
			</tr>

</table>	
<br>	
			<tr>
				<input type="file" id="preview" name="stpi_img" onchange="showImage(0)" multiple accept="image/*"><br/>
				<img src="" id="output1" name="img"  height="150px"><p>
			</tr>

		
		<tr><input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增"></tr>

			
	</FORM>
	
	
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
