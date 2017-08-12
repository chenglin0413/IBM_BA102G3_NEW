<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>User: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>User: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for User: Home</p>

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
  <li><a href='listAllUser.jsp'>List</a> all Users. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="user.do" >
        <b>輸入會員編號 (如1000001):</b>
        <input type="text" name="user_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
   
  <li>
     <FORM METHOD="post" ACTION="user.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="user_id">
         <c:forEach var="userVO" items="${userSvc.all}" > 
          <option value="${userVO.user_id}">${userVO.user_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="get" ACTION="user.do" >
       <b>選擇會員姓名:</b>
       <select size="1" name="user_id">
         <c:forEach var="userVO" items="${userSvc.all}" > 
          <option value="${userVO.user_id}">${userVO.user_lastname}${userVO.user_firstname}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>會員管理</h3>

<ul>
  <li><a href='addUser.jsp'>Add</a> a new Emp.</li>
</ul>

</body>

</html>
