<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Func: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Func: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Func: Home</p>

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
  <li><a href='listAllFunc.jsp'>List</a> all Funcs. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="func.do" >
        <b>輸入功能編號 (如4100001):</b>
        <input type="text" name="func_id">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
   
  <li>
     <FORM METHOD="post" ACTION="func.do" >
       <b>選擇功能編號:</b>
       <select size="1" name="func_id">
         <c:forEach var="funcVO" items="${funcSvc.all}" > 
          <option value="${funcVO.func_id}">${funcVO.func_id}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="func.do" >
       <b>選擇功能姓名:</b>
       <select size="1" name="func_id">
         <c:forEach var="funcVO" items="${funcSvc.all}" > 
          <option value="${funcVO.func_id}">${funcVO.func_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>功能管理</h3>

<ul>
  <li><a href='addFunc.jsp'>Add</a> a new Func.</li>
</ul>

</body>

</html>
