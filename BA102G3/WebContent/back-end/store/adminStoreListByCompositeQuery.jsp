<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.user.model.*"%>

<jsp:useBean id="listStores_ByCompositeQuery" scope="request" type="java.util.List" />

<%@include file="includeHeadForStore.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">商家會員管理</a>
                    </li>
                    <li class="active">複合查詢</li>
                </ol>

            <div class="container-fluid">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>所有的商家會員</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                            	<th>商店編號</th>
												<th>商店名稱</th>
												<th>商店電話</th>
												<th>所在位置</th>
												<th>狀態</th>
												<th>商店負責人</th>
												<th></th>
												<th></th>
                                            </tr>
                                        </thead>

										<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />                                        
                                        
										<%@ include file="pages/page1_ByCompositeQuery.file" %>
										<tbody style="background-color: lightblue;">
										<c:forEach var="storeVO" items="${listStores_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

                                            <tr align='center' valign='middle' ${(storeVO.user_id==param.user_id) ? 'style="outline: thick solid yellow;;"':''}>
                                                <td>${storeVO.store_id}</td>
                                                <td>${storeVO.store_name} 
                                                </td>
                                                <td>${storeVO.store_phone}</td>
                                                <td>
                                                    ${storeVO.store_ter}航廈${storeVO.store_floor}樓,
                                                	<c:if test="${storeVO.store_inout == '1'}" var="condition" scope="page" > 
                                                		出境區
                                                	</c:if>
                                                	<c:if test="${storeVO.store_inout == '2'}" var="condition" scope="page" > 
                                                		入境區
                                                	</c:if>
                                                </td>
                                                <td>                                                    
                                                	<c:if test="${userSvc.getOneUser(storeVO.user_id).user_status == '1'}" var="condition" scope="page" > 
                                                		<img src="<%=request.getContextPath()%>/back-end/image/circleGreen.png" height="15" width="15">
                                                	</c:if>
                                                	<c:if test="${userSvc.getOneUser(storeVO.user_id).user_status == '0'}" var="condition" scope="page" > 
                                                		<img src="<%=request.getContextPath()%>/back-end/image/circleRed.png" height="15" width="15">
                                                	</c:if>
                                                	<c:if test="${userSvc.getOneUser(storeVO.user_id).user_status == '2'}" var="condition" scope="page" > 
                                                		<img src="<%=request.getContextPath()%>/back-end/image/circleYellow.png" height="15" width="15">
                                                	</c:if>	                                                		                                                		
                                                </td>
                                                <td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     									<input type="submit" value="${storeVO.user_id}" class="btn btn-success">
			     									<input type="hidden" name="user_id" value="${storeVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                                    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			     									
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>                                                
                                                </td>                                                
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/store.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
			     									<input type="hidden" name="store_id" value="${storeVO.store_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                                    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			     												     									
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/store.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
			    									<input type="hidden" name="store_id" value="${storeVO.store_id}">
			    									<input type="hidden" name="user_id" value="${storeVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                                    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			     												    									
			    									<input type="hidden" name="action" value="delete"></FORM>
												</td>
                                            </tr>                                        
                                        </c:forEach>    
                                        </tbody>                                        
                                    </table>
                                    <%@ include file="pages/page2_ByCompositeQuery.file" %>

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
