<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.path.model.*"%>
<%
    PathService pathSvc = new PathService();
    List<PathVO> list = pathSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%@include file="includeHeadForPath.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">導引圖資管理</a>
                    </li>
                    <li class="active">修改 查詢</li>
                </ol>

            <div class="container-fluid">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>所有的圖資</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                            	<th>圖資編號</th>
                                            	<th>航廈</th>
												<th>起點經度</th>
												<th>起點緯度</th>
												<th>終點經度</th>
												<th>終點緯度</th>
												<th>起點地名</th>
												<th>終點地名</th>
												<th></th>
												<th></th>
                                            </tr>
                                        </thead>
                                                                                
                                        <%@ include file="page1.file" %>
										<tbody style="background-color: lightblue;">
										<c:forEach var="pathVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                                            <tr align='center' valign='middle' ${(pathVO.path_id==param.path_id) ? 'style="outline: thick solid yellow;;"':''}>
                                                <td>${pathVO.path_id}</td>
                                                <td>${pathVO.path_term}</td>
                                                <td>${pathVO.path_fromlon}</td>
                                                <td>${pathVO.path_fromlat}</td>
                                                <td>${pathVO.path_tolon}</td>
                                                <td>${pathVO.path_tolat}</td>
                                                <td>${pathVO.path_fromplace}</td>
                                                <td>${pathVO.path_toplace}</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/path/path.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
                                                    <input type="hidden" name="path_id" value="${pathVO.path_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                                    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/path/path.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
												    <input type="hidden" name="path_id" value="${pathVO.path_id}">
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
