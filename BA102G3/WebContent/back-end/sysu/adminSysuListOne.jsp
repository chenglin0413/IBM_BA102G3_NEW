<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sysu.model.*"%>
<%
SysuVO sysuVO = (SysuVO) request.getAttribute("sysuVO");
%>

<%@include file="includeHeadForSysu.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">平台員工管理</a>
                    </li>
                    <li>
                                                               員工: ${sysuVO.sysu_id}; ${sysuVO.sysu_account}
                    </li>
                    <li class="active">詳細資料及更新</li>
                </ol>


            <!-- /.container-fluid -->
            
            <div class="container-fluid">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>所有的一般會員</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                            	<th>員工編號</th>
												<th>帳號</th>
												<th>姓</th>
												<th>名</th>
												<th>職務</th>
												<th>E-mail</th>
												<th>狀態</th>
												<th></th>
												<th></th>
                                            </tr>
                                        </thead>
                            
										<tbody style="background-color: lightblue;">
							
                                            <tr>
                                                <td>${sysuVO.sysu_id}</td>
                                                <td>${sysuVO.sysu_account}</td>
                                                <td>${sysuVO.sysu_lastname}</td>
                                                <td>${sysuVO.sysu_firstname}</td>
												<td>
                                                	<c:if test="${sysuVO.sysu_type == '1'}" var="condition" scope="page" > 
                                                		系統管理員
                                                	</c:if>
                                                	<c:if test="${sysuVO.sysu_type == '2'}" var="condition" scope="page" > 
                                                		客服
                                                	</c:if>
                                                	<c:if test="${sysuVO.sysu_type == '3'}" var="condition" scope="page" > 
                                                		營運
                                                	</c:if>
                                                </td>
                                                <td>${sysuVO.sysu_email}</td>
                                                <td>
                                                	<c:if test="${sysuVO.sysu_status == '1'}" var="condition" scope="page" > 
                                                		<img src="../image/circleGreen.png" height="15" width="15">
                                                	</c:if>
                                                	<c:if test="${sysuVO.sysu_status == '0'}" var="condition" scope="page" > 
                                                		<img src="../image/circleRed.png" height="15" width="15">
                                                	</c:if>
                                                	<c:if test="${sysuVO.sysu_status == '2'}" var="condition" scope="page" > 
                                                		<img src="../image/circleYellow.png" height="15" width="15">
                                                	</c:if>	                                                		                                                		
                                                </td>                                                
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/sysu/sysu.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
			     									<input type="hidden" name="sysu_id" value="${sysuVO.sysu_id}">
			     									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/sysu/sysu.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
			    									<input type="hidden" name="sysu_id" value="${sysuVO.sysu_id}">
			    									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    									<input type="hidden" name="action" value="delete"></FORM>
												</td>
                                            </tr>                                        
                                
                                        </tbody>                                        
                                    </table>

                                </div>
                            </div>
                        </div>
                    
                </div>

            </div>            
            
            
            <!-- /.container-fluid -->
            
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-1">
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/sysu/adminSysuQuery.jsp">
                            <div class="input-group">
   								<input type="submit" value="回查詢" class="btn btn-primary">
                            </div>
                        </FORM>                                	
                    </div>
                </div>
            </div>

            </form>

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