<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.path.model.*"%>

<%@include file="includeHeadForPath.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">導引圖資管理</a>
                    </li>
                    <li class="active">查詢</li>
                </ol>

            <div class="container-fluid">
                <div class="row">
   					<div class="col-xs-12 col-sm-6">
   					
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

						<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/path/path.do" >
                            <div class="input-group">
                               	<label class="input-group-addon">輸入圖資編號</label>
                               	<input type="text" name="path_id" class="form-control">
                               	<span class="input-group-btn">
									<input type="submit" value="搜尋" class="btn btn-primary">
								</span>                               	
                               	<input type="hidden" name="action" value="getOne_For_Display">
							</div>
						</FORM>
						
						<br>

					  <jsp:useBean id="pathSvc" scope="page" class="com.path.model.PathService" />						
     					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/path/path.do" >
     					<div class="input-group">
       						<label class="input-group-addon">從</label>
       						<select size="1" name="path_fromplace" class="form-control">
         						<c:forEach var="pathVO" items="${pathSvc.all}" > 
          						<option value="${pathVO.path_fromplace}">${pathVO.path_fromplace} 
         						</c:forEach>   
       						</select>

       						<label class="input-group-addon">到</label>
       						<select size="1" name="path_toplace" class="form-control">
         						<c:forEach var="pathVO" items="${pathSvc.all}" > 
          						<option value="${pathVO.path_toplace}">${pathVO.path_toplace}
         						</c:forEach>   
       						</select>
       						
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_From_To_For_Display">
       					</div>
    					</FORM>
						
						<br>
									
     					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/path/path.do" >
     					<div class="input-group">
       						<label class="input-group-addon">圖資編號</label>
       						<select size="1" name="path_id" class="form-control">
         						<c:forEach var="pathVO" items="${pathSvc.all}" > 
          						<option value="${pathVO.path_id}">${pathVO.path_id}
         						</c:forEach>   
       						</select>
       						
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_For_Display">
       					</div>
    					</FORM>
						
						<br>						
						
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/path/adminPathListAll.jsp">
                            <div class="input-group">
   								<input type="submit" value="列出所有圖資" class="btn btn-primary">
                            </div>
                        </FORM>
 						
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
