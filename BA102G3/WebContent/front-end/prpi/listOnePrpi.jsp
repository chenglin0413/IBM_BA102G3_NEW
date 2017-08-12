<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.prpi.model.*"%>
<%
PrpiVO prpiVO = (PrpiVO) request.getAttribute("prpiVO"); //PrpiServlet.java(Concroller), 存入req的prpiVO物件
%>
<html>
<head>
<title>產品圖片 - listOnePrpi.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產品圖片 - ListOnePrpi.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>產品圖片編號</th>
		<th>產品編號</th>
		<th>圖片名稱</th>
		<th>圖片</th>

	</tr>
	<tr align='center' valign='middle'>
		<td>${prpiVO.prpi_id}</td>
		<td>${prpiVO.prod_id}</td>
		<td>${prpiVO.prpi_name}</td>
		<td><img src="<%=request.getContextPath()%>/front-end/prpi/DBGifReader?prpi_id=${prpiVO.prpi_id}" weight="300" height="250"></td>

	</tr>
</table>
</body>
</html>
