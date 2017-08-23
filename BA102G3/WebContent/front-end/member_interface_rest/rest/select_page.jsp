<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>BA102G3 Rest: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>BA102G3 Rest: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for BA102G3 Rest: Home</p>

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
  <li><a href='listAllRest.jsp'>List</a> all rests</li>
  <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="rest.do" >
        <b>輸入餐廳編號 (如3000001):</b>
        <input type="text" name="rest_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" />
   
  <li>
     <FORM METHOD="post" ACTION="rest.do" >
       <b>選擇餐廳編號:</b>
       <select size="1" name="rest_id">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.rest_id}">${restVO.rest_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="rest.do" >
       <b>選擇餐廳名稱:</b>
       <select size="1" name="rest_id">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.rest_id}">${restVO.rest_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>餐廳管理</h3>

<ul>
  <li><a href='addRest.jsp'>Add</a> a new Rest.</li>
</ul>


</body>

</html>
