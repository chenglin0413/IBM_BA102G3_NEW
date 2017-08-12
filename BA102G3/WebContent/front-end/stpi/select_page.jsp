<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Stpi: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Stpi: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Stpi: Home</p>

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
  <li><a href='listAllStpi.jsp'>List</a> all Stpis. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/front-end/stpi/stpi.do" >
        <b>輸入廠商圖片編號 (如2100001):</b>
        <input type="text" name="stpi_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="stpiSvc" scope="page" class="com.stpi.model.StpiService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/front-end/stpi/stpi.do" >
       <b>選擇廠商圖片編號:</b>
       <select size="1" name="stpi_id">
         <c:forEach var="stpiVO" items="${stpiSvc.all}" > 
          <option value="${stpiVO.stpi_id}">${stpiVO.stpi_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
 
</ul>


<h3>廠商圖片管理</h3>

<ul>
  <li><a href='addStpi.jsp'>Add</a> a new Stpi.</li>
</ul>

</body>

</html>
