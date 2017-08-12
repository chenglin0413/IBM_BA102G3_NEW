<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sysu.model.*"%>

<%@include file="includeHeadForSysu.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">平台員工管理</a>
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
   					                    	
						<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/sysu/sysu.do" >
                            <div class="input-group">
                               	<label class="input-group-addon">輸入員工編號</label>
                               	<input type="text" name="sysu_id" class="form-control">
                               	<span class="input-group-btn">
									<input type="submit" value="搜尋" class="btn btn-primary">
								</span>                               	
                               	<input type="hidden" name="action" value="getOne_For_Display">
							</div>
						</FORM>
						
						<br>

					  <jsp:useBean id="sysuSvc" scope="page" class="com.sysu.model.SysuService" />						
     					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/sysu/sysu.do" >
     					<div class="input-group">
       						<label class="input-group-addon">選擇會員帳號[姓名]</label>
       						<select size="1" name="sysu_id" class="form-control">
         						<c:forEach var="sysuVO" items="${sysuSvc.all}" > 
          						<option value="${sysuVO.sysu_id}">${sysuVO.sysu_account}&nbsp;[${sysuVO.sysu_lastname}&nbsp;${sysuVO.sysu_firstname}]
         						</c:forEach>   
       						</select>
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_For_Display">
       					</div>
    					</FORM>
						
						<br>
						
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/sysu/adminSysuListAll.jsp">
                            <div class="input-group">
   								<input type="submit" value="列出所有員工" class="btn btn-primary">
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
