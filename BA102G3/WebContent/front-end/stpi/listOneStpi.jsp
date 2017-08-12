<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.stpi.model.*,com.stpi.controller.*"%>
<%
StpiVO stpiVO = (StpiVO) request.getAttribute("stpiVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>廠商圖片資料 - listOneStpi.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商圖片資料 - ListOneStpi.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>廠商圖片編號</th>
		<th>廠商編號</th>
		<th>廠商圖片名稱</th>
		<th>廠商圖片</th>
		<th>廠商圖片格式</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=stpiVO.getStpi_id()%></td>
		<td><%=stpiVO.getStore_id()%></td>
		<td><%=stpiVO.getStpi_name()%></td>
		<td><img src="<%= request.getContextPath()%>/front-end/stpi/DBGifReader?stpi_id=${stpiVO.stpi_id}"></td>
		<td><%=stpiVO.getStpi_imgfmt()%></td>
		
	</tr>
</table>

</body>
</html>
