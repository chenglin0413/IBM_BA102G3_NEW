<%@ page contentType="text/html; charset=UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ page import="com.sysu.model.*"%>
<%
SysuVO sysuVO = (SysuVO) request.getAttribute("sysuVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>會員資料 - listOneSysu.jsp</title>
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
		<th>員工編號</th>
		<th>員工帳號</th>
		<th>員工種類</th>
		<th>員工姓</th>
		<th>員工名</th>
		<th>加入日期</th>
		<th>員工E-mail</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=sysuVO.getSysu_id()%></td>
		<td><%=sysuVO.getSysu_account()%></td>
		<td><%=sysuVO.getSysu_type()%></td>
		<td><%=sysuVO.getSysu_lastname()%></td>
		<td><%=sysuVO.getSysu_firstname()%></td>
		<td><%=sysuVO.getSysu_joindate()%></td>
		<td><%=sysuVO.getSysu_email()%></td>
	</tr>
</table>

</body>
</html>
