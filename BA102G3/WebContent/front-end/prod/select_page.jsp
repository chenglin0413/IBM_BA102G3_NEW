<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Prod: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Prod: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Prod: Home</p>

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
  <li><a href='listAllProd.jsp'>List</a> all Prods. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/prod/prod.do" >
        <b>輸入產品編號 (如2200001):</b>
        <input type="text" name="prod_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/prod/prod.do" >
       <b>選擇產品編號:</b>
       <select size="1" name="prod_id">
         <c:forEach var="prodVO" items="${prodSvc.all}" > 
          <option value="${prodVO.prod_id}">${prodVO.prod_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/prod/prod.do" >
       <b>選擇產品編號:</b>
       <select size="1" name="prod_id">
         <c:forEach var="prodVO" items="${prodSvc.all}" > 
          <option value="${prodVO.prod_id}">${prodVO.prod_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>產品管理</h3>

<ul>
  <li><a href='addProd.jsp'>Add</a> a new Prod.</li>
</ul>

</body>

</html>
