<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Auth: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Auth: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Auth: Home</p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllAuth.jsp'>List</a> all Auths. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="auth.do" >
        <b>��J���u�s�� (�p7001):</b>
        <input type="text" name="sysu_id">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="authSvc" scope="page" class="com.auth.model.AuthService" />
  <jsp:useBean id="sysuSvc" scope="page" class="com.sysu.model.SysuService" />
   
  <li>
     <FORM METHOD="post" ACTION="auth.do" >
       <b>��ܭ��u�s�� :</b>
       <select size="1" name="sysu_id">
         <c:forEach var="sysuVO" items="${sysuSvc.all}" > 
          <option value="${sysuVO.sysu_id}">${sysuVO.sysu_account}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
</ul>


<h3>���u�v���޲z</h3>

<ul>
  <li><a href='addAuth.jsp'>Add</a> a new Auth.</li>
</ul>

</body>

</html>
