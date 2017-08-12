<%@ page contentType="text/html; charset=UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ page import="com.user.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>會員資料 - listOneUser.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員種類</th>
		<th>會員姓</th>
		<th>會員名</th>
		<th>加入日期</th>
		<th>會員E-mail</th>
		<th>會員狀態</th>
		<th>會員照片</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=userVO.getUser_id()%></td>
		<td><%=userVO.getUser_account()%></td>
		<td><%=userVO.getUser_type()%></td>
		<td><%=userVO.getUser_lastname()%></td>
		<td><%=userVO.getUser_firstname()%></td>
		<td><%=userVO.getUser_joindate()%></td>
		<td><%=userVO.getUser_email()%></td>
		<td><%=userVO.getUser_status()%></td>
		<td><a href="<%= request.getContextPath() %>/back-end/user/userImg.do?user_id=<%=userVO.getUser_id()%>" target="_blank">看圖</a></td>
	</tr>
</table>

</body>
</html>
