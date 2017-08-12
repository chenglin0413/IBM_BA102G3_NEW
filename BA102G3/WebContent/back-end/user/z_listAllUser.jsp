<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    UserService userSvc = new UserService();
    List<UserVO> list = userSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有會員資料 - listAllUser.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有會員資料 - ListAllUser.jsp</h3>
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

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>會員編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>型態</th>
		<th>姓</th>
		<th>名</th>
		<th>電話</th>
		<td>手機</td>
    	<td>地址</td>
		<td>E-mail</td>
	    <td>加入日期</td>
		<td>狀態</td>
		<th></th>
		<th></th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="userVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${userVO.user_id}</td>
			<td>${userVO.user_account}</td>
			<td>${userVO.user_passwd}</td>
			<td>${userVO.user_type}</td>
			<td>${userVO.user_lastname}</td>
			<td>${userVO.user_firstname}</td>
			<td>${userVO.user_phone}</td>
			<td>${userVO.user_mobile}</td>
			<td>${userVO.user_address}</td>
			<td>${userVO.user_email}</td>
			<td>${userVO.user_joindate}</td>
			<td>${userVO.user_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="user_id" value="${userVO.user_id}">
			     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="user_id" value="${userVO.user_id}">
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
