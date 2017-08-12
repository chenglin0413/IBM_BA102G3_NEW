<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Sysu: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Sysu: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Sysu: Home</p>

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
  <li><a href='listAllSysu.jsp'>List</a> all Sysus. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="sysu.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="sysu_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="sysuSvc" scope="page" class="com.sysu.model.SysuService" />
   
  <li>
     <FORM METHOD="post" ACTION="sysu.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="sysu_id">
         <c:forEach var="sysuVO" items="${sysuSvc.all}" > 
          <option value="${sysuVO.sysu_id}">${sysuVO.sysu_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="sysu.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="sysu_id">
         <c:forEach var="sysuVO" items="${sysuSvc.all}" > 
          <option value="${sysuVO.sysu_id}">${sysuVO.sysu_lastname}${sysuVO.sysu_firstname}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addSysu.jsp'>Add</a> a new Sysu.</li>
</ul>

</body>

</html>
