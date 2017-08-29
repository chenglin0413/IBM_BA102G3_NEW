<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mfee.model.*"%>
<%
MfeeVO mfeeVO = (MfeeVO) request.getAttribute("mfeeVO");
%>

<%@include file="includeHeadForMfee.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">月費管理</a>
                    </li>
                    <li>
	                    ${mfeeVO.mfee_id}
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
                                            	<th>繳費編號</th>
												<th>店家名稱</th>
												<th>負責人編號</th>
												<th>繳費期限</th>
												<th>繳費日期</th>
<!-- 												<th></th>
												<th></th> -->
                                            </tr>
                                        </thead>
                            
                                        <jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
                                        <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
                                        <jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" />
                                                        
										<tbody style="background-color: lightblue;">
							
                                            <tr>
                                                <td>${mfeeVO.mfee_id}</td>
                                                
                                                <td>
                                                	<c:if test="${userSvc.getOneUser(mfeeVO.user_id).user_type == '2'}" var="condition1" scope="page" >
                                                		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/store.do">
			     										<input type="submit" value="${storeSvc.getOneStoreByUsed_Id(mfeeVO.user_id).store_name}" class="btn btn-warning">
			     										<input type="hidden" name="store_id" value="${storeSvc.getOneStoreByUsed_Id(mfeeVO.user_id).store_id}">
                                                        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->								
	    		     									<input type="hidden" name="action" value="getOne_For_Update">
	    		     									</FORM>
			     									</c:if>
                                                	<c:if test="${userSvc.getOneUser(mfeeVO.user_id).user_type == '3'}" var="condition2" scope="page" >
                                                		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/rest/rest.do">
			     										<input type="submit" value="${restSvc.getOneRestByUser_Id(mfeeVO.user_id).rest_name}" class="btn btn-warning">
			     										<input type="hidden" name="rest_id" value="${restSvc.getOneRestByUser_Id(mfeeVO.user_id).rest_id}">
                                                        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->								
	    		     									<input type="hidden" name="action" value="getOne_For_Update">
	    		     									</FORM>

			     									</c:if>
                                                </td>                                                
                                                
                                                <td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     									<input type="submit" value="${mfeeVO.user_id}" class="btn btn-success">
			     									<input type="hidden" name="user_id" value="${mfeeVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>                                            
                                                </td>

                                                <td>${mfeeVO.mfee_date}</td>
                                                <td>${mfeeVO.pay_date}</td>
<%-- 												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mfee/mfee.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
			     									<input type="hidden" name="mfee_id" value="${mfeeVO.mfee_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mfee/mfee.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
			    									<input type="hidden" name="mfee_id" value="${mfeeVO.mfee_id}">
												    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    									<input type="hidden" name="action" value="delete"></FORM>
												</td> --%>
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
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mfee/adminMfeeQuery.jsp">
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