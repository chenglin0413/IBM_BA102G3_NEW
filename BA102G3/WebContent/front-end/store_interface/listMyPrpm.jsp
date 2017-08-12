<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prpm.model.*,com.prod.model.*"%>

<%
	List<PrpmVO> list = (List<PrpmVO>) request.getAttribute("list");
	Integer stpm_id = (Integer) request.getAttribute("stpm_id");

	PrpmService prpmSvc = new PrpmService();
	List<PrpmVO> prpm = prpmSvc.getStpmID(stpm_id);

	List<ProdVO> prodList = new ArrayList<ProdVO>();

	ProdService prodSvc = new ProdService();

	for (PrpmVO p : prpm) {
		ProdVO prodvo = prodSvc.getOneProd(p.getProd_id());
		prodList.add(prodvo);
	}
	request.setAttribute("prodList", prodList);
%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>促銷商品列表</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/stylish-portfolio.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="js/bootstrap-datepicker3.min.css" />

</head>

<body>

	<br>

	<table border="1" align="center">
		<tr height="79" style="background-color: rgb(229, 246, 253);">
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷商品</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">商品原價</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">促銷價格</span><br />
			<td height="79"
				style="border-color: rgb(204, 204, 204); width: 208px; height: 79px; text-align: center; background-color: rgb(178, 32, 98);"><span
				style="color: rgb(229, 246, 253);">價格修改</span><br />
		</tr>
		<c:forEach var="prpmVO" items="${list}">
			<tr align='center' valign='middle'>
				<td>${prpmVO.prod_id}</td>

				<%-- 取得原始價格 --%>

				<td><c:if test="${prodList != null}">
						<c:forEach var="prodVO" items="${prodList}">
							<c:if test="${prpmVO.prod_id == prodVO.prod_id}">
				$${prodVO.prod_price} 
				</c:if>
						</c:forEach>
					</c:if></td>

				<td>$${prpmVO.prpm_price}</td>
				<td>

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/store_interface/prpm.do" name="form3">
						<input type="submit" value="修改"> 
						<input type="hidden" name="stpm_id" value="${prpmVO.stpm_id}">
						<input type="hidden" name="prod_id" value="${prpmVO.prod_id}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
