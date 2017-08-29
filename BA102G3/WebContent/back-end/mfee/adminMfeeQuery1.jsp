<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mfee.model.*"%>
<%@ page import="com.store.model.*"%>

<%@include file="includeHeadForMfee.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">月費管理</a>
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
   					                    	
     					<FORM METHOD="get" ACTION="<%= request.getContextPath() %>/back-end/mfee/mfee.do" >
     					<div class="input-group">

                                                                         查詢未繳費紀錄
							<select name="mfee_year">
  								<option value="2017" selected>2017</option>
							</select>
							年

							<select name="mfee_month">
  								<option value="01">1</option>
  								<option value="02">2</option>
  								<option value="03">3</option>
  								<option value="04">4</option>
  								<option value="05">5</option>
  								<option value="06">6</option>
  								<option value="07">7</option>
  								<option value="08" selected>8</option>
  								<option value="09">9</option>
  								<option value="10">10</option>
  								<option value="11">11</option>
  								<option value="12">12</option>
							</select>
							月

       						<span class="input-group-btn">
       							<input type="submit" value="查詢" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="Find_Unpay">
       					</div>
    					</FORM>
    					
    					<br>

						<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
						<jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" />

                        <FORM METHOD="get" ACTION="<%= request.getContextPath() %>/back-end/mfee/mfee.do" >
	     					<div class="input-group">
    	   						<label class="input-group-addon">商店繳費紀錄查詢</label>
       							<select size="1" name="user_id" class="form-control">
         							<c:forEach var="storeVO" items="${storeSvc.all}" > 
          							<option value="${storeVO.user_id}">${storeVO.user_id} - ${storeVO.store_name} 
         							</c:forEach>   
       							</select>
       							<span class="input-group-btn">
       								<input type="submit" value="查詢" class="btn btn-primary">
       							</span>
       							<input type="hidden" name="action" value="LIST_USER_PAY_HISTORY">
       						</div>
       					</FORM>	
	     				
	     				<br>
	     					
	     				<FORM METHOD="get" ACTION="<%= request.getContextPath() %>/back-end/mfee/mfee.do" >
	     					<div class="input-group">
    	   						<label class="input-group-addon">餐廳繳費紀錄查詢</label>
       							<select size="1" name="user_id" class="form-control">
         							<c:forEach var="restVO" items="${restSvc.all}" > 
          							<option value="${restVO.user_id}">${restVO.user_id} - ${restVO.rest_name} 
         							</c:forEach>   
       							</select>
       							<span class="input-group-btn">
       								<input type="submit" value="查詢" class="btn btn-primary">
       							</span>
       							<input type="hidden" name="action" value="LIST_USER_PAY_HISTORY">
       						</div>
						</FORM>
						
						<br>
						
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mfee/adminMfeeListAll.jsp">
                            <div class="input-group">
   								<input type="submit" value="列出所有繳費資料" class="btn btn-primary">
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
