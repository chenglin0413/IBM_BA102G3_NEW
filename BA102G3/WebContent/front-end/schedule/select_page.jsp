<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBM schedule: Home</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM schedule: Home</h3>
				<font color=red>( MVC )</font></td>
		</tr>
	</table>

	<p>This is the Home page for IBM schedule: Home</p>

	<h3>資料查詢:</h3>
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

	<ul>

		<li><a href='listAllBusc.jsp'>List</a> all Busc.</li>
		<br>
		<br>
		<li><a href='listAllFlsc.jsp'>List</a> all Flsc.</li>
		<br>
		<br>

		<li>
			<FORM METHOD="post" ACTION="flsc.do">
				<b>Flsc_flno:</b> <input type="text" name="flsc_flno"> <input
					type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="flscSvc" scope="page"
			class="com.flsc.model.FlscService" />
</body>

</html>
