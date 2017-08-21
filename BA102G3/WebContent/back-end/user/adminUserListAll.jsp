<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.model.*"%>
<%
    UserService userSvc = new UserService();
    List<UserVO> list = userSvc.getAllByType(1);
    pageContext.setAttribute("list",list);
%>

<%@include file="includeHeadForUser.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">一般會員管理</a>
                    </li>
                    <li class="active">修改 查詢</li>
                </ol>

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
                                            	<th>會員編號</th>
												<th>帳號</th>
												<th>姓</th>
												<th>名</th>
												<th>E-mail</th>
												<th>狀態</th>
												<th></th>
												<th></th>
                                            </tr>
                                        </thead>
                                                                                
                                        <%@ include file="page1.file" %>
										<tbody style="background-color: lightblue;">
										<c:forEach var="userVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                                            <tr align='center' valign='middle' ${(userVO.user_id==param.user_id) ? 'style="outline: thick solid yellow;;"':''}>
                                                <td>${userVO.user_id}</td>
                                                <td>${userVO.user_account}</td>
                                                <td>${userVO.user_lastname}</td>
                                                <td>${userVO.user_firstname}</td>
                                                <td>${userVO.user_email}</td>
                                                <td>
                                                	<c:if test="${userVO.user_status == '1'}" var="condition" scope="page" > 
                                                		<img src="../image/circleGreen.png" height="15" width="15">
                                                	</c:if>
                                                	<c:if test="${userVO.user_status == '0'}" var="condition" scope="page" > 
                                                		<img src="../image/circleRed.png" height="15" width="15">
                                                	</c:if>
                                                	<c:if test="${userVO.user_status == '2'}" var="condition" scope="page" > 
                                                		<img src="../image/circleYellow.png" height="15" width="15">
                                                	</c:if>	                                                		                                                		
                                                </td>                                                
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
			     									<input type="hidden" name="user_id" value="${userVO.user_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                                    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/user.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
			    									<input type="hidden" name="user_id" value="${userVO.user_id}">
												    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    									<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    									<input type="hidden" name="action" value="delete"></FORM>
												</td>
                                            </tr>                                        
                                        </c:forEach>    
                                        </tbody>                                        
                                    </table>
                                    <%@ include file="page2.file" %>

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
