<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mfee.model.*"%>
<%    
    MfeeService mfeeSvc = new MfeeService();
    List<MfeeVO> list = (List<MfeeVO>) session.getAttribute("mfeeVOList");
    session.setAttribute("list",list);
    
    String pyear= (String) session.getAttribute("mfee_year");
    String pmonth= (String) session.getAttribute("mfee_month");
    
%>

<%@include file="includeHeadForMfee.jsp" %>

        <jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
        <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
        <jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" />

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">月費管理</a>
                    </li>
                    <li class="active">繳費紀錄</li>
                </ol>

            <div class="container-fluid">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>繳費紀錄</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
												<th>店家名稱</th>
												<th>負責人編號</th>
												<th>繳費期限</th>
												<th>繳費日期</th>
												<th></th>
												<th></th>
                                            </tr>
                                        </thead>
                                                                                                                        
                                        <%@ include file="page1.file" %>
										<tbody style="background-color: lightblue;">
										<c:forEach var="mfeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                                            <tr align='center' valign='middle' >

                                                <td>                                                	
                                                	<c:if test="${userSvc.getOneUser(mfeeVO.user_id).user_type == '2'}" var="condition1" scope="page" >
                                                		
                                                		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/store.do">
			     										<input type="submit" value="${storeSvc.getOneStoreByUsed_Id(mfeeVO.user_id).store_name}" class="btn btn-warning">
			     										<input type="hidden" name="store_id" value="${storeSvc.getOneStoreByUsed_Id(mfeeVO.user_id).store_id}">
                                                        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                    			     	<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->								
	    		     									<input type="hidden" name="action" value="getOne_For_Update">
	    		     									</FORM>
			     									</c:if>
                                                	<c:if test="${userSvc.getOneUser(mfeeVO.user_id).user_type == '3'}" var="condition2" scope="page" >
                                                		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/rest/rest.do">
			     										<input type="submit" value="${restSvc.getOneRestByUser_Id(mfeeVO.user_id).rest_name}" class="btn btn-warning">
			     										<input type="hidden" name="rest_id" value="${restSvc.getOneRestByUser_Id(mfeeVO.user_id).rest_id}">
                                                        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                    			     	<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->								
	    		     									<input type="hidden" name="action" value="getOne_For_Update">
	    		     									</FORM>

			     									</c:if>
         
                                                </td>                                                
                                                
                                                <td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     									<input type="submit" value="${mfeeVO.user_id}" class="btn btn-success">
			     									<input type="hidden" name="user_id" value="${mfeeVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                    			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> 									
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>                                            
                                                </td>

                                                <td>${mfeeVO.mfee_date}</td>
                                                <td><font color="blue">${mfeeVO.pay_date}</font></td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mfee/mfee.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
			     									<input type="hidden" name="mfee_id" value="${mfeeVO.mfee_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                    				<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mfee/mfee.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
			    									<input type="hidden" name="mfee_id" value="${mfeeVO.mfee_id}">
												    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    									<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    									<input type="hidden" name="action" value="delete"></FORM>
												</td>
                                            </tr>                                        
                                        </c:forEach>    
                                        </tbody>                                        
                                    </table>
                                    <%@ include file="page2.file" %>

                                    <div class="text-center">
                                        <ul class="pagination">
                                        <li><a href="#">&laquo;</a></li>
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li class="active"><a href="#">3</a></li>
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
