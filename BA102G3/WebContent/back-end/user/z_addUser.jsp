<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO");
%>

<html>
<head>
<title>新增會員資料 - addUser.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/back-end/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath() %>/back-end/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>新增會員資料 - addUser.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>會員資料:</h3>
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

<!-- 
<FORM METHOD="post" ACTION="user.do" name="form1">
 -->
 
 <FORM class="uploadImage" METHOD="post" ACTION="user.do" name="form1" enctype="multipart/form-data">

<!---  
<FORM class="uploadImage" action="user.do" method="post" enctype="multipart/form-data">
-->

<table border="0">

	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="user_account" size="45" 
			value="<%= (userVO==null)? "" : userVO.getUser_account()%>" /></td>
	</tr>

	<tr>
		<td>姓:</td>
		<td><input type="TEXT" name="user_lastname" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>" /></td>
	</tr>

	<tr>
		<td>名:</td>
		<td><input type="TEXT" name="user_firstname" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>" /></td>
	</tr>
	
	<tr>
		<td>phone:</td>
		<td><input type="TEXT" name="user_phone" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_phone()%>" /></td>
	</tr>
	
	<tr>
		<td>mobile:</td>
		<td><input type="TEXT" name="user_mobile" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>" /></td>
	</tr>

	<tr>
		<td>address:</td>
		<td><input type="TEXT" name="user_address" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_address()%>" /></td>
	</tr>
	
	<tr>
		<td>會員E-mail:</td>
		<td><input type="TEXT" name="user_email" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_email()%>" /></td>
	</tr>
			
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>加入日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="user_joindate" value="<%= (userVO==null)? date_SQL : userVO.getUser_joindate()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="<%= request.getContextPath() %>/back-end/image/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	
</table>

<br>

 
<input type="file" name="upfile1" id="file-input1" onchange="previewImages()">
<div id="preview"></div>

<br> 
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="user_type" value="1">
<input type="hidden" name="user_status" value="1">
<input type="submit" value="送出新增">
</FORM>
</body>

</html>

<script>
      
function previewImages() {
	
	  var preview = document.getElementById('preview'); 
	  
 	  if (this.files) {
 	    [].forEach.call(this.files, readAndPreview);
 	  }

 	  function readAndPreview(file) {
	    
	    var reader = new FileReader();
	    
 	    reader.addEventListener("load", function() {
	      var image = new Image();
	      image.height = 150;
	      image.title  = file.name;
	      image.src    = this.result;
	      preview.appendChild(image);
 	    }, false);
	    
	    reader.readAsDataURL(file);
	    
 	  }

}

	document.getElementById('file-input1').addEventListener("change", previewImages, false);

</script>
