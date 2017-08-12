<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    
    AuthService authSvc = new AuthService();
    List<AuthVO> list = (List<AuthVO>) request.getAttribute("authVOList");
    pageContext.setAttribute("list",list);

    out.println(list.size()+"<BR>");
    
    for(AuthVO authVO : list) {
        out.println(authVO.getSysu_id());
        out.println(authVO.getFunc_id()+"<BR>");
    }
    
%>

<html>
<head>
<title>�Ҧ��޲z�̸�� - listAllAuth.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ��޲z�̸�� - ListAllAuth.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>�޲z�̽s��</th>
		<th>�\��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="authVO1" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${authVO1.sysu_id}</td>
			<td>${authVO1.func_id}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/auth/auth.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="sysu_id" value="${authVO1.sysu_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/auth/auth.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="sysu_id" value="${authVO1.sysu_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
