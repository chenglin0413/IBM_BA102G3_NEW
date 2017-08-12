<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.store.model.*"%>
<%
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>商家資料 - listOneStore.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商資料 - ListOneStore.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>商家編號</th>
		<th>使用者編號</th>
		<th>商家名稱</th>
		<th>營業時間</th>
		<th>商家電話</th>
		<th>商家描述</th>
		<th>所在航廈</th>
		<th>所在樓層</th>
		<th>經度</th>
		<th>緯度</th>
		<th>出境_入境</th>
		<th>商家評分次數</th>
		<th>商家得分</th>
	</tr>
	<tr align='center' valign='middle'>
		
		
		    <td><%=storeVO.getStore_id()%></td>
			<td><%=storeVO.getUser_id()%></td>
			<td><%=storeVO.getStore_name()%></td>
			<td><%=storeVO.getStore_time()%></td>
			<td><%=storeVO.getStore_phone()%></td>
			<td><%=storeVO.getStore_describe()%></td>
			<td><%=storeVO.getStore_ter()%></td>
			<td><%=storeVO.getStore_floor()%></td>
			<td><%=storeVO.getStore_lon()%></td>
			<td><%=storeVO.getStore_lat()%></td>
			<td><%=storeVO.getStore_inout()%></td>
			<td><%=storeVO.getStore_count()%></td>
			<td><%=storeVO.getStore_score()%></td>
	</tr>
</table>

</body>
</html>
