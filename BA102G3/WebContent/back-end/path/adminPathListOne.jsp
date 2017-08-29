<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.path.model.*"%>
<%
PathVO pathVO = (PathVO) request.getAttribute("pathVO");
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
                    <li>
                                                              圖資編號:${pathVO.path_id}
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
                                            	<th>圖資編號</th>
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
                            
										<tbody style="background-color: lightblue;">
							
                                            <tr>
                                                <td>${pathVO.path_id}</td>
                                                <td>${pathVO.path_fromlon}</td>
                                                <td>${pathVO.path_fromlat}</td>
                                                <td>${pathVO.path_tolon}</td>
                                                <td>${pathVO.path_tolat}</td>
                                                <td>${pathVO.path_fromplace}</td>
                                                <td>${pathVO.path_toplace}</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/path/path.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
			     									<input type="hidden" name="path_id"	value="${pathVO.path_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/path/path.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
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
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/user/adminUserQuery.jsp">
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