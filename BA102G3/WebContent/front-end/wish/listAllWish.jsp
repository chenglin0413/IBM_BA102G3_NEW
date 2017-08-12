<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.wish.model.*,com.wish.controller.*"%>
<%@ page import="com.prod.model.*,com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="wishSvc" scope="page" class="com.wish.model.WishService" />
<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />


<html>
<head>
<title>列出所有追蹤產品資料 - listAllWish.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有追蹤產品資料 - ListAllWish.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='1400'>
	<tr>
		<th>會員性名-地址</th>
		<th>產品編號</th>
		<th>追蹤產品日期</th>
		<th>產品名稱-描述</th>
		<th  COLSPAN=2>操作</th>
	</tr>
	<c:forEach var="wishVO" items="${wishSvc.all}" varStatus="s">
		<tr align='center' valign='middle'>
		<td><c:forEach var="userVO" items="${userSvc.all}">
                    <c:if test="${wishVO.user_id==userVO.user_id}">
	                    	【${userVO.user_lastname}-${userVO.user_firstname}-${userVO.user_address}】
                    </c:if>
                </c:forEach>
		</td>
		
		<td><A HREF="javascript:presses${s.index}()">${wishVO.prod_id}</a></td>
<%-- 		<td><A HREF="<%=request.getContextPath()%>/front-end/wish/wish.do?prod_id=${wishVO.prod_id}&action=getOne_From05">${wishVO.prod_id}</a></td> --%>
		<td>${wishVO.wish_date}</td>
		
		<td><c:forEach var="prodVO" items="${prodSvc.all}">
                    <c:if test="${wishVO.prod_id==prodVO.prod_id}">
	                  	  【${prodVO.prod_name}-${prodVO.prod_descript}】
                    </c:if>
                </c:forEach>
		</td>		
			
			
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/wish/wish.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="user_id" value="${wishVO.user_id}">
			     <input type="hidden" name="prod_id" value="${wishVO.prod_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/wish/wish.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="user_id" value="${wishVO.user_id}">
			    <input type="hidden" name="prod_id" value="${wishVO.prod_id}">
			    <input type="hidden" name="action"	value="delete"></FORM>
			</td>
		</tr>
		 <script>
         function presses${s.index}(){
        	 document.open("wish.do?prod_id=${wishVO.prod_id}&action=getOne_From05", "" ,"height=700,width=700,left=65,top=157,resizable=yes,scrollbars=yes");
         }
		</script>
	</c:forEach>
</table>

</body>
</html>
