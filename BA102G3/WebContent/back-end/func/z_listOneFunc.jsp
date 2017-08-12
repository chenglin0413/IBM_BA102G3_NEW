<%@ page contentType="text/html; charset=UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ page import="com.func.model.*"%>
<%
FuncVO funcVO = (FuncVO) request.getAttribute("funcVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>功能資料 - listOneFunc.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>功能資料 - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>功能編號</th>
		<th>功能名稱</th>
		<th>功能路徑</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=funcVO.getFunc_id()%></td>
		<td><%=funcVO.getFunc_name()%></td>
		<td><%=funcVO.getFunc_path()%></td>
	</tr>
</table>

</body>
</html>
