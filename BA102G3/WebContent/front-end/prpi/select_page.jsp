<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Prpi: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Prpi: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Prpi: Home</p>

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
  <li><a href='listAllPrpi.jsp'>List</a> all Prpis. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/prpi/prpi.do" >
        <b>��J���~�s�� (�p2300205):</b>
        <input type="text" name="prpi_id">
        <input type="submit" value="�e�X"><font color=blue></font> 
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  

  <jsp:useBean id="prpiSvc" scope="page" class="com.prpi.model.PrpiService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/prpi/prpi.do" >
       <b>��ܲ��~�s��:</b>
       <select size="1" name="prpi_id">
         <c:forEach var="prpiVO" items="${prpiSvc.all}" > 
          <option value="${prpiVO.prpi_id}">${prpiVO.prpi_id}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/prpi/prpi.do" >
       <b>��ܲ��~�W��:</b>
       <select size="1" name="prpi_id">
         <c:forEach var="prpiVO" items="${prpiSvc.all}" > 
          <option value="${prpiVO.prpi_id}">${prpiVO.prpi_name}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>

<h3>���u�޲z</h3>

<ul>
  <li><a href='addPrpi.jsp'>Add</a> a new Prpi.</li>
</ul>
</body>

</html>
