<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Item: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Item: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Item: Home</p>

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
  <li><a href='listAllItem.jsp'>List</a> all Items. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/item/item.do" >
        <b>輸入訂單及產品編號 (如2400002 & 2200008):</b>
        <input type="text" name="ord_id">
         <input type="text" name="prod_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/item/item.do" >
       <b>輸入訂單編號 (如2400002):</b>
       <select size="1" name="ord_id">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.ord_id}">${itemVO.ord_id}
         </c:forEach>   
       </select>
       <b>輸入產品編號 (如2200008):</b>
       <select size="1" name="prod_id">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.prod_id}">${itemVO.prod_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/item/item.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ord_id">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.ord_id}">${itemVO.ord_id}
         </c:forEach>   
       </select>
       <b>選擇產品編號:</b>
       <select size="1" name="prod_id">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.prod_id}">${itemVO.prod_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>訂單明細管理</h3>

<ul>
  <li><a href='addItem.jsp'>Add</a> a new Item.</li>
</ul>

</body>

</html>
