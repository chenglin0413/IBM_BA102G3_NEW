<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trvl.model.*"%>
<%
	TrvlService trvlSvc = new TrvlService();
	List<TrvlVO> list = trvlSvc.getTopTrvlCounts();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="trpiSvc" scope="page" class="com.trpi.model.TrpiService" />

<html>
<head>
<title>GripAnyTime</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
		<div class="container">
			<div class="row">
				<c:forEach var="trvlVO" items="${list}">
					<div class="col-sm-6 col-md-3">
						<div class="ehdiv">							
							<c:forEach var="trpiVO" items="${trpiSvc.all}">
								<c:if test="${trvlVO.trvl_id==trpiVO.trvl_id}">
					            	<a  href="<%=request.getContextPath()%>/front-end/trvl/trvl.do?trvl_id=${trvlVO.trvl_id}&action=getOne_For_Display">
							        	<img class="img-thumbnail" src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
							        </a>
							    </c:if>
							</c:forEach>
							<div class="row">
								<div class="col-sm-6 col-md-4">
									<strong>${trvlVO.trvl_tittle}</strong> 
								</div>
								<div class="col-sm-6 col-md-4 col-md-push-4">
									${trvlVO.trvl_count}
								</div>
							</div><!-- row2 -->
						</div>	
					</div>
				</c:forEach>
			</div><!-- row -->
		</div><!-- container -->
</body>
</html>