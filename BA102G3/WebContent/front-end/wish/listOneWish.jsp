<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.wish.model.*,com.prod.model.*,com.user.model.*"%>
<%
WishVO wishVO = (WishVO) request.getAttribute("wishVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>


<% 

	ProdDAO dao = new ProdDAO();
	ProdVO prodVO = dao.findByPrimaryKey(wishVO.getProd_id());

	UserDAO dao2 = new UserDAO();
	UserVO userVO = dao2.findByPrimaryKey(wishVO.getUser_id());	

%>
<html>
<head>
<title>追蹤產品單筆資料 - listOneWish.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>追蹤產品單筆資料 - ListOneWish.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>會員編號</th>
		<th>產品編號</th>
		<th>追蹤產品日期</th>
	</tr>
	<tr align='center' valign='middle'>
		
		
		    <td><%=wishVO.getUser_id()%><%=userVO.getUser_lastname()%>/<%=userVO.getUser_firstname()%></td>
			<td><%=wishVO.getProd_id()%><%=prodVO.getProd_name() %></td>
			<td><%=wishVO.getWish_date()%></td>
	</tr>
</table>

</body>
</html>
