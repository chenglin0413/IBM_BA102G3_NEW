<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Ord: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Ord: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Ord: Home</p>

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
  <li><a href='listAllOrd.jsp'>List</a> all Ords. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do" >
        <b>輸入訂單編號 (如2400001):</b>
        <input type="text" name="ord_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ord_id">
         <c:forEach var="ordVO" items="${ordSvc.all}" > 
          <option value="${ordVO.ord_id}">${ordVO.ord_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ord_id">
         <c:forEach var="ordVO" items="${ordSvc.all}" > 
          <option value="${ordVO.ord_id}">${ordVO.ord_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>產品管理</h3>

<ul>
  <li><a href='addOrd.jsp'>Add</a> a new Ord.</li>
</ul>

</body>

</html>
