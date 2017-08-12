<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%@ page import="com.store.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO");
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<html>
<head>
<title>商店會員資料新增 - addUser.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/back-end/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath() %>/back-end/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商店會員資料新增 - addUser.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>商店會員:</h3>
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
 
 <FORM class="uploadImage" METHOD="post" ACTION="userStore.do" name="form1" enctype="multipart/form-data">

<!---  
<FORM class="uploadImage" action="user.do" method="post" enctype="multipart/form-data">
-->

<table border="0">

	<tr>
		<td>商店負責人帳號:</td>
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
		<td>負責人電話:</td>
		<td><input type="TEXT" name="user_phone" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_phone()%>" /></td>
	</tr>
	
	<tr>
		<td>負責人手機:</td>
		<td><input type="TEXT" name="user_mobile" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>" /></td>
	</tr>

	<tr>
		<td>負責人地址:</td>
		<td><input type="TEXT" name="user_address" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_address()%>" /></td>
	</tr>
	
	<tr>
		<td>負責人E-mail:</td>
		<td><input type="TEXT" name="user_email" size="45"
			value="<%= (userVO==null)? "" : userVO.getUser_email()%>" /></td>
	</tr>
	
	<br>
		<input type="file" name="upfile1" id="file-input1" onchange="previewImages()">
		<div id="preview"></div>
	<br>	
		
	<tr>
		<td>商店名稱:</td>
		<td><input type="TEXT" name="store_name" size="45"	value="<%= (storeVO==null)? "":storeVO.getStore_name()%>" /></td>
	</tr>
	<tr>
		<td>營業時間:</td>
		<td><input type="TEXT" name="store_time" size="45"	value="<%= (storeVO==null)? "":storeVO.getStore_time()%>" /></td>
	</tr>
	<tr>
		<td>連絡電話:</td>
		<td><input type="TEXT" name="store_phone" size="45"	value="<%= (storeVO==null)? "":storeVO.getStore_phone()%>" /></td>
	</tr>
	<tr>
		<td>廠商描述:</td>
		<td><input type="TEXT" name="store_describe" size="45"	value="<%= (storeVO==null)? "":storeVO.getStore_describe()%>" /></td>
	</tr>
	<tr>
		<td>所在航廈:</td>
		<td><input type="TEXT" name="store_ter" size="45"	value="<%= (storeVO==null)? "2":storeVO.getStore_ter()%>" /></td>
	</tr>
	<tr>
		<td>所在樓層:</td>
		<td><input type="TEXT" name="store_floor" size="45"	value="<%= (storeVO==null)? "3":storeVO.getStore_floor()%>" /></td>
	</tr>
	<tr>
		<td>所在經度:</td>
		<td><input type="TEXT" name="store_lon" size="45"	value="<%= (storeVO==null)? "121.234805267":storeVO.getStore_lon()%>" /></td>
	</tr>
	<tr>
		<td>所在緯度:</td>
		<td><input type="TEXT" name="store_lat" size="45"	value="<%= (storeVO==null)? "25.077234812696":storeVO.getStore_lat()%>" /></td>
	</tr>
	<tr>
		<td>出入境位置:</td>
		<td><input type="TEXT" name="store_inout" size="45"	value="<%= (storeVO==null)? "2":storeVO.getStore_inout()%>" /></td>
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
