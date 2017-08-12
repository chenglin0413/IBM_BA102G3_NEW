<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Store: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Store: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Store: Home</p>

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
  <li><a href='listAllStore.jsp'>List</a> all Stores. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/store/store.do" >
        <b>輸入廠商編號 (如2000001):</b>
        <input type="text" name="store_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/store/store.do" >
       <b>選擇廠商編號:</b>
       <select size="1" name="store_id">
         <c:forEach var="storeVO" items="${storeSvc.all}" > 
          <option value="${storeVO.store_id}">${storeVO.store_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/store/store.do" >
       <b>選擇廠商編號:</b>
       <select size="1" name="store_id">
         <c:forEach var="storeVO" items="${storeSvc.all}" > 
          <option value="${storeVO.store_id}">${storeVO.store_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>廠商管理</h3>

<ul>
  <li><a href='addStore.jsp'>Add</a> a new Store.</li>
</ul>

</body>

</html>
