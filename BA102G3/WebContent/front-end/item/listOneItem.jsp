<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.item.model.*"%>
<%
ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>訂單明細資料 - listOneItem.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訂單明細資料 - ListOneItem.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>訂單編號</th>
		<th>產品編號</th>
		<th>產品數量</th>
		<th>買家對產品評分</th>
		<th>產品評論</th>
		<th>填寫產品評分日期</th>
	</tr>
	<tr align='center' valign='middle'>
		
		
		    <td><%=itemVO.getOrd_id()%></td>
			<td><%=itemVO.getProd_id()%></td>
			<td><%=itemVO.getItem_qty()%></td>
			<td><%=itemVO.getItem_score()%></td>
			<td><%=itemVO.getItem_review()%></td>
			<td><%=itemVO.getItem_reviewdate()%></td>
			
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
