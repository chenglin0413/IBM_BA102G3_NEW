<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.mfee.model.*"%>
<%@ page import="com.rppr.model.*"%>
<%@ page import="com.rptl.model.*"%>
<%@ page import="java.text.Format"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	Format formatterYear = new SimpleDateFormat("YYYY"); 
	String currentYear = formatterYear.format(new java.util.Date());
  
	Format formatterMonth = new SimpleDateFormat("MMM"); 
	String currentMonth = formatterMonth.format(new java.util.Date());

    MfeeService mfeeSvc = new MfeeService();
    List<MfeeVO> list = mfeeSvc.findUnpay(currentYear, currentMonth);
    pageContext.setAttribute("list",list);

%>

<%@include file="includeHeadForIndex.jsp" %>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->

                <!-- /.row -->

                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-bolt fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">9</div>
                                        <div>未結案的檢舉!</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left"><a href="<%= request.getContextPath() %>/back-end/report/listAllReport.jsp">詳細情形</a></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-exclamation-triangle fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">16</div>
                                        <div>未繳費的會員!</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left"><a href="<%= request.getContextPath() %>/back-end/mfee/mfee.do?mfee_year=<%=currentYear%>&mfee_month=<%=currentMonth%>&action=Find_Unpay">詳細情形</a></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>                    
                                        
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-shopping-cart fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">2</div>
                                        <div>待審核的商店會員申請!</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left"><a href="<%= request.getContextPath() %>/back-end/store/adminStoreWait.jsp">詳細情形</a></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                                        
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-coffee fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">1</div>
                                        <div>待審核的餐廳會員申請!</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left"><a href="<%= request.getContextPath() %>/back-end/rest/adminRestWait.jsp">詳細情形</a></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

                <!-- /.row -->               
                <div class="row">
                
<!-- 檢舉區塊開始 -->

                <jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
				<jsp:useBean id="rpprSvc" scope="page" class="com.rppr.model.RpprService" />
				<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />
		
				<div class="col-lg-6">

					<div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-exclamation-triangle fa-fw"></i><font color="red"><b>未處理</b></font><b>的檢舉</b></h3>
                            </div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>

											<th>被檢舉的商品</th>
											<th>檢舉人</th>
											<th>檢舉時間</th>
											<th>詳情</th>

										</tr>
									</thead>
									<tbody style="background-color: lightblue;">
										<c:forEach var="rpprVO" items="${rpprSvc.getAllByStatus(0)}" varStatus="s">
											<tr>
												
												<td>
													<input type="submit" value="${prodSvc.getOneProd(rpprVO.prod_id).prod_name}" class="btn btn-s btn-warning">
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     									<input type="submit" value="${rpprVO.user_id}" class="btn btn-s btn-success">
			     									<input type="hidden" name="user_id" value="${rpprVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> 									
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>																								
												</td>
												
												<td>${rpprVO.rppr_date}</td>
												<td>
													<form action="<%=request.getContextPath()%>/back-end/report/rppr.do" method="post" name="form1">
														<input type="hidden" name="rppr_id" value="${rpprVO.rppr_id}"> 
														<input type="hidden" name="prod_id" value="${rpprVO.prod_id}"> 
														<input type="hidden" name="action" value="getOne_For_Display">
														<input type="submit" class="btn btn-xs btn-danger"value="查看">
													</form>
												</td>
	
											</tr>
										</c:forEach>
									<tbody>
								</table>
							</div>
						</div>

		        <jsp:useBean id="rptlSvc" scope="page" class="com.rptl.model.RptlService" />
		        <jsp:useBean id="trvlSvc" scope="page" class="com.trvl.model.TrvlService" />	
								
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>被檢舉的日誌</th>
											<th>檢舉人</th>
											<th>檢舉時間</th>
											<th>詳情</th>
										</tr>
									</thead>
									<tbody style="background-color: lightblue;">
										<c:forEach var="rptlVO" items="${rptlSvc.getAllByStatus(0)}" varStatus="s">
											<tr>
												<td>
													<input type="submit" value="${trvlSvc.getOneTrvl(rptlVO.trvl_id).trvl_tittle}" class="btn btn-s btn-warning">
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     									<input type="submit" value="${rptlVO.user_id}" class="btn btn-s btn-success">
			     									<input type="hidden" name="user_id" value="${rptlVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> 									
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>																								
												</td>												
												<td>${rptlVO.rptl_date}</td>
												<td>
													<form action="<%=request.getContextPath()%>/back-end/report/rptl.do" method="post" name="form2">
														<input type="hidden" name="rptl_id" value="${rptlVO.rptl_id}"> 
														<input type="hidden" name="trvl_id" value="${rptlVO.trvl_id}"> 
														<input type="hidden" name="action" value="getOne_For_Display">
														<input type="submit" class="btn btn-xs btn-danger" value="查看">
													</form>
												</td>
											</tr>
										</c:forEach>
									<tbody>
								</table>
							</div>
						</div>
					</div>
</div>
	
<!-- 檢舉區塊結束 -->

<!-- 未付費區塊開始 -->                    
<!-- /BA102G3/back-end/mfee/mfee.do?action=Find_Unpay -->
                    
                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-exclamation-triangle fa-fw"></i><font color="red"><b>未繳費</b></font><b>的商家及餐廳會員</b></h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        
                                        <thead>
                                            <tr>
												<th>店家名稱</th>
												<th>負責人編號</th>
												<th>繳費期限</th>
												<th>繳費紀錄</th>
                                        </thead>
                                        
                                        <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
                                        <jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" />
                                                                                
										<tbody style="background-color: lightblue;">
										<c:forEach var="mfeeVO" items="${list}" >
                                            <tr align='center' valign='middle' >

                                                <td>                                                	
                                                	<c:if test="${userSvc.getOneUser(mfeeVO.user_id).user_type == '2'}" var="condition1" scope="page" >
                                                		
                                                		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/store.do">
			     										<input type="submit" value="${storeSvc.getOneStoreByUsed_Id(mfeeVO.user_id).store_name}" class="btn btn-s btn-warning">
			     										<input type="hidden" name="store_id" value="${storeSvc.getOneStoreByUsed_Id(mfeeVO.user_id).store_id}">
                                                        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->								
	    		     									<input type="hidden" name="action" value="getOne_For_Update">
	    		     									</FORM>
			     									</c:if>
                                                	<c:if test="${userSvc.getOneUser(mfeeVO.user_id).user_type == '3'}" var="condition2" scope="page" >
                                                		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/rest/rest.do">
			     										<input type="submit" value="${restSvc.getOneRestByUser_Id(mfeeVO.user_id).rest_name}" class="btn btn-s btn-warning">
			     										<input type="hidden" name="rest_id" value="${restSvc.getOneRestByUser_Id(mfeeVO.user_id).rest_id}">
                                                        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->								
	    		     									<input type="hidden" name="action" value="getOne_For_Update">
	    		     									</FORM>

			     									</c:if>
			     									         
                                                </td>                                                
                                                
                                                <td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     									<input type="submit" value="${mfeeVO.user_id}" class="btn btn-s btn-success">
			     									<input type="hidden" name="user_id" value="${mfeeVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> 									
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>                                            
                                                </td>

                                                <td>1-<%=currentMonth%>-<%=currentYear%></td>
                                                
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mfee/mfee.do">
			     									<input type="submit" value="查看" class="btn btn-xs btn-danger">
			     									<input type="hidden" name="mfee_id" value="${mfeeVO.mfee_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>

                                            </tr>                                        
                                        </c:forEach>    
                                        </tbody>    
                                        
                                    </table>

                                    <div class="text-center">
                                        <ul class="pagination">
                                          <li><a href="#">&laquo;</a></li>
                                          <li class="active"><a href="#">1</a></li>
                                          <li><a href="#">2</a></li>
                                          <li><a href="#">3</a></li>
                                          <li><a href="#">4</a></li>
                                          <li><a href="#">5</a></li>
                                          <li><a href="#">&raquo;</a></li>
                                        </ul>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
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
