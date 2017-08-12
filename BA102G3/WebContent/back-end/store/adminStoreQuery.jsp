<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%@include file="includeHeadForStore.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">商家會員管理</a>
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
   					                    	
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/store.do" >
                            <div class="input-group">
                               	<label class="input-group-addon">輸入編號</label>
                               	<input type="text" name="store_id" class="form-control">
                               	<span class="input-group-btn">
									<input type="submit" value="搜尋" class="btn btn-primary">
								</span>                               	
                               	<input type="hidden" name="action" value="getOne_For_Display">
							</div>
						</FORM>
						
						<br>

    				    <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" /> 						
     					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/store.do" >
     					<div class="input-group">
       						<label class="input-group-addon">選擇商家帳號[姓名]</label>
       						<select size="1" name="store_id" class="form-control">
         						<c:forEach var="storeVO" items="${storeSvc.all}" > 
          						<option value="${storeVO.store_id}">${storeVO.store_name}
         						</c:forEach>   
       						</select>
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_For_Display">
       					</div>
    					</FORM>
 						
						<br>
						
						<div>
						<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/store/store.do">

						   <fieldset>
							<legend>複合查詢</legend>
							航廈
	 						<select name="store_ter">
  								<option value="1">T1</option>
  								<option value="2" selected>T2</option>
							</select> &nbsp; &nbsp;
							 
							樓層						
							<select name="store_floor">
  								<option value="" selected>--</option>
  								<option value="B2">B2</option>
  								<option value="B1">B1</option>
  								<option value="1">1</option>
  								<option value="2">2</option>
  								<option value="3" selected>3</option>
  								<option value="4">4</option>
							</select> &nbsp; &nbsp;
							 							
							區域						
							<select name="store_inout">
								<option value="" selected>--</option>
  								<option value="1" selected>出境</option>
  								<option value="2">入境</option>
  								<option value="3">非管制區</option>
							</select> &nbsp; &nbsp;					        
					        
         					<input type="hidden" name="action" value="listStores_ByCompositeQuery">
					        <input type="submit" value="送出" >
					       </fieldset>  
							 

						</form>							
						</div>
						
						<br>
						
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/store/adminStoreListAll.jsp">
                            <div class="input-group">
   								<input type="submit" value="列出所有商家" class="btn btn-primary">
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
