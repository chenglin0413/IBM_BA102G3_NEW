<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.prod.model.*"%>
<%
ProdVO prodVO = (ProdVO) request.getAttribute("prodVO"); //EmpServlet.java(Concroller), 存入req的empVO物件

%>
<html>
<head>
<title>產品資料 - listOneProd.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產品資料 - ListOneProd.jsp</h3>
		<a href="listAllProd.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>



<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>產品編號</th>
		<th>商店編號</th>
		<th>產品名稱</th>
		<th>產品描述</th>
		<th>產品價格</th>
		<th>產品類別</th>
		<th>產品規格</th>
		<th>產品品牌</th>
		<th>產品更新時間</th>
		<th>產品賣出日期</th>
		<th>產品狀態</th>
		<th>產品被評分次數</th>
		<th>評價總分</th>
	</tr>
	<tr align='center' valign='middle'>
		
		
		    <td><%=prodVO.getProd_id()%></td>
			<td><%=prodVO.getStore_id()%></td>
			<td><%=prodVO.getProd_name()%></td>
			<td><%=prodVO.getProd_descript()%></td>
			<td><%=prodVO.getProd_price()%></td>
			<td><%=prodVO.getProd_sort()%></td>
			<td><%=prodVO.getProd_format()%></td>
			<td><%=prodVO.getProd_brand()%></td>
			<td><%=prodVO.getProd_updatetime()%></td>
			<td><%=prodVO.getProd_soldcount()%></td>
			<td><%=prodVO.getProd_status()%></td>
			<td><%=prodVO.getProd_count()%></td>
			<td><%=prodVO.getProd_score()%></td>
			<td><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" weight="300" height="250"></td>
	</tr>
<!-- 	 下面兩種EL寫法 -->
<!-- 	<tr align='center' valign='middle'> -->
<%-- 		<td>${empVO.empno}</td> --%>
<%-- 		<td>${empVO.ename}</td> --%>
<%-- 		<td>${empVO.job}</td> --%>
<%-- 		<td>${empVO.hiredate}</td> --%>
<%-- 		<td>${empVO.sal}</td> --%>
<%-- 		<td>${empVO.comm}</td> --%>
<%-- 		<td>${empVO.deptno}</td> --%>
<!-- 	</tr> -->
<!-- <!-- 	EL2.2才開始可以用 --> -->
<!-- 	<tr align='center' valign='middle'> -->
<%-- 		<td>${empVO.getEmpno()}</td> --%>
<%-- 		<td>${empVO.getEname()}</td> --%>
<%-- 		<td>${empVO.getJob()}</td> --%>
<%-- 		<td>${empVO.getHiredate()}</td> --%>
<%-- 		<td>${empVO.getSal()}</td> --%>
<%-- 		<td>${empVO.getComm()}</td> --%>
<%-- 		<td>${empVO.getDeptno()}</td> --%>
<!-- 	</tr> -->
</table>

</body>
</html>
