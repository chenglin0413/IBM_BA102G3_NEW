<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.rppr.model.*"%>
<%@ page import="com.rptl.model.*"%>
<%@ page import="java.util.*"%>

<%
	RpprService rpprSvc = new RpprService();
	List<RpprVO> list = rpprSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>
 
<%@include file="includeHeadForReport.jsp" %>

        <div id="page-wrapper">
        
                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">檢舉管理</a>
                    </li>
                    <li class="active">未處理</li>
                </ol>
        
            <div class="container-fluid">

                <!-- Page Heading -->

                <!-- /.row -->

                <!-- /.row -->

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home"><h4>產品檢舉</h4></a></li>
		<li><a data-toggle="tab" href="#menu1"><h4>遊記檢舉</h4></a></li>
	</ul>
	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">
			<div class="row">


				<div class="col-lg-10">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title"></h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>項次</th>
											<th>檢舉編號</th>
											<th>檢舉時間</th>
											<th>檢舉人</th>
											<th>詳情</th>
											<th>處理狀況</th>
										</tr>
									</thead>
									<tbody style="background-color: lightblue;">
										<c:forEach var="rpprVO" items="${list}" varStatus="s">
											<tr>
												<td>${s.count}</td>
												<td>${rpprVO.rppr_id}</td>
												<td>${rpprVO.rppr_date}</td>
												<td>${rpprVO.user_id}</td>
												<td>
													<form action="<%=request.getContextPath()%>/back-end/report/rppr.do" method="post" name="form1">
														<input type="hidden" name="rppr_id" value="${rpprVO.rppr_id}"> 
														<input type="hidden" name="prod_id" value="${rpprVO.prod_id}"> 
														<input type="hidden" name="action" value="getOne_For_Display">
														<input type="submit" class="btn btn-xs btn-primary"value="詳情">
													</form>
												</td>
												<td>
													<c:if test="${rpprVO.rppr_status==0}">
														 <a href="#" class="btn btn-xs btn-danger " role="button">待審檢舉</a>
													</c:if>		
													<c:if test="${rpprVO.rppr_status==1}">
														 <a href="#" class="btn btn-xs btn-warning " role="button">檢舉審核通過,已下架產品</a>
													</c:if>	
													<c:if test="${rpprVO.rppr_status==2}">
														 <a href="#" class="btn  btn-xs btn-success " role="button">檢舉審核未通過,保留產品</a>
													</c:if>		
												</td>	
											</tr>
										</c:forEach>
									<tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			
			
			
			</div> <!-- row -->
		</div><!-- home-end -->
	<jsp:useBean id="rptlSvc" scope="page" class="com.rptl.model.RptlService" />	
		
		<div id="menu1" class="tab-pane fade">
			<div class="row">
				<div class="col-lg-10">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title"></h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>項次</th>
											<th>檢舉編號</th>
											<th>檢舉時間</th>
											<th>檢舉人</th>
											<th>詳情</th>
											<th>處理狀況</th>
										</tr>
									</thead>
									<tbody style="background-color: lightblue;">
										<c:forEach var="rptlVO" items="${rptlSvc.all}" varStatus="s">
											<tr>
												<td>${s.count}</td>
												<td>${rptlVO.rptl_id}</td>
												<td>${rptlVO.rptl_date}</td>
												<td>${rptlVO.user_id}</td>
												<td>
													<form action="<%=request.getContextPath()%>/back-end/report/rptl.do" method="post" name="form2">
														<input type="hidden" name="rptl_id" value="${rptlVO.rptl_id}"> 
														<input type="hidden" name="trvl_id" value="${rptlVO.trvl_id}"> 
														<input type="hidden" name="action" value="getOne_For_Display">
														<input type="submit" class="btn btn-xs btn-primary" value="詳情">
													</form>
												</td>
												<td>
													<c:if test="${rptlVO.rptl_status==0}">
														 <a href="#" class="btn btn-xs btn-danger" role="button">待審檢舉</a>
													</c:if>		
													<c:if test="${rptlVO.rptl_status==1}">
														 <a href="#" class="btn btn-xs btn-warning" role="button">檢舉審核通過,已移除遊記</a>
													</c:if>	
													<c:if test="${rptlVO.rptl_status==2}">
														 <a href="#" class="btn btn-xs btn-success" role="button">檢舉審核未通過,保留遊記</a>
													</c:if>		
												</td>
											</tr>
										</c:forEach>
									<tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div> <!-- row -->
		</div><!-- menu1-end -->
	</div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/back-end/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/back-end/js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="<%= request.getContextPath() %>/back-end/js/plugins/morris/raphael.min.js"></script>
    <script src="<%= request.getContextPath() %>/back-end/js/plugins/morris/morris.min.js"></script>
    <script src="<%= request.getContextPath() %>/back-end/js/plugins/morris/morris-data.js"></script>

</body>

</html>
