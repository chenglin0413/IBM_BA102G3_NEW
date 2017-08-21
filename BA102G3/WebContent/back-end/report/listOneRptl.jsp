<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rptl.model.*"%>

<%
	RptlVO rptlVO=(RptlVO)request.getAttribute("RptlVO");
%>

<%@include file="includeHeadForReport.jsp" %>


<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
</head>
<body>

<div id="page-wrapper">

<jsp:useBean id="trvlSvc" scope="page" class="com.trvl.model.TrvlService" />
	<div class="container-fluid">
		<div class="row">
			<div class="row">
				<div class="col-lg-8">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-money fa-fw"></i><b>檢舉編號:${rptlVO.rptl_id}</b>
							</h3>
						</div>
						<div class="panel-body">
							<p>
								<b>檢舉日期:</b> ${rptlVO.rptl_date}
							</p>
							<p>
								<b>檢舉人編號:</b> ${rptlVO.user_id}
							</p>
							<p>
								<b>被檢舉遊記:</b>${trvlSvc.getOneTrvl(rptlVO.trvl_id).trvl_tittle}
							</p>
							<p>
								<b>被檢舉帳號:</b> ${trvlSvc.getOneTrvl(rptlVO.trvl_id).user_id}
							<p>
								<b>檢舉內文:</b><br>
								${rptlVO.rptl_content}
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<c:if test="${rptlVO.rptl_status==0}">
						<div class="col-xs-12 col-md-1 col-md-push-4">
							<form method="POST" action="<%=request.getContextPath()%>/back-end/report/rptl.do" name="form1">
								<input type="hidden" name="rptl_status" value="1">
								<input type="hidden" name="rptl_id" value="${rptlVO.rptl_id}">
								<input type="hidden" name="action" value="update">
								<input type="submit" class="btn btn-sm btn-danger" value="移除遊記">
							</form>
						</div>	
						<div class="col-xs-12 col-md-1 col-md-push-5">	
							<form method="POST" action="<%=request.getContextPath()%>/back-end/report/rptl.do" name="form2">
								<input type="hidden" name="rptl_status" value="2">
								<input type="hidden" name="rptl_id" value="${rptlVO.rptl_id}">
								<input type="hidden" name="action" value="update">
								<input type="submit" class="btn btn-sm btn-danger" value="保留遊記">
							</form>
						</div>
					</c:if>		
				</div>
			</div>
		</div><!-- /.row -->
	</div>
</div>	

</body>
</html>