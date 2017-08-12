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
   					                    	
     					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/mfee/mfee.do" >
     					<div class="input-group">

                                                                         查詢未繳費紀錄
							<select name="mfee_year">
  								<option value="2017" selected>2017</option>
							</select>
							年

							<select name="mfee_month">
  								<option value="JAN">1</option>
  								<option value="FEB">2</option>
  								<option value="MAR">3</option>
  								<option value="APR">4</option>
  								<option value="MAY">5</option>
  								<option value="JUN">6</option>
  								<option value="JUL">7</option>
  								<option value="AUG" selected>8</option>
  								<option value="SEP">9</option>
  								<option value="OCT">10</option>
  								<option value="NOV">11</option>
  								<option value="DEC">12</option>
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

                        <jsp:useBean id="mfeeSvc" scope="page" class="com.mfee.model.MfeeService" />
					  						
     					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/mfee/mfee.do" >
     					<div class="input-group">
       						<label class="input-group-addon">選擇編號</label>
       						<select size="1" name="mfee_id" class="form-control">
         						<c:forEach var="mfeeVO" items="${mfeeSvc.all}" > 
          						<option value="${mfeeVO.mfee_id}">${mfeeVO.mfee_id}, 店家: ${storeSvc.getOneStoreByUsed_Id(mfeeVO.user_id).store_name}, 付費日期: ${mfeeVO.pay_date} 
         						</c:forEach>   
       						</select>
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_For_Display">
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
