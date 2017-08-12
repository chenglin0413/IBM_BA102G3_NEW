<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ord.model.*"%>
<%
OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>訂單資料 - listOneOrd.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訂單資料 - ListOneOrd.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>商店編號</th>
		<th>訂單日期</th>
		<th>訂單總額</th>
		<th>買家付款狀態</th>
		<th>商店審核訂單狀態</th>
		<th>訂單狀態</th>
		<th>買家對商店評分</th>
		<th>商家檢舉訂單日期</th>
		<th>檢舉訂單理由</th>
		<th>商家檢舉訂單狀態</th>
	</tr>
	<tr align='center' valign='middle'>
		
		
		    <td><%=ordVO.getOrd_id()%></td>
			<td><%=ordVO.getUser_id()%></td>
			<td><%=ordVO.getStore_id()%></td>
			<td><%=ordVO.getOrd_date()%></td>
			<td><%=ordVO.getOrd_total()%></td>
			<td><%=ordVO.getOrd_bill()%></td>
			<td><%=ordVO.getOrd_grant()%></td>
			<td><%=ordVO.getOrd_status()%></td>
			<td><%=ordVO.getOrd_sscore()%></td>
			<td><%=ordVO.getOrd_rpdate()%></td>
			<td><%=ordVO.getOrd_rpcomm()%></td>
			<td><%=ordVO.getOrd_rpstatus()%></td>
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
