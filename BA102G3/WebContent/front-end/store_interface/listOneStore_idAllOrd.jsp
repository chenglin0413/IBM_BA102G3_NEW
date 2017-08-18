<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*,com.store.model.*,com.item.model.*,com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	
	if(session.getAttribute("userVO")!=null){
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account =(String) session.getAttribute("account");
		StoreService storeSvc= new StoreService();
		StoreVO storeVO=storeSvc.getOneStoreByUsed_Id(userVO.getUser_id());
		pageContext.setAttribute("storeVO",storeVO);
	   
	}
    
    
    
%>

<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<jsp:useBean id="userSvc"  scope="page" class="com.user.model.UserService"/>
<jsp:useBean id="storeSvc"  scope="page" class="com.store.model.StoreService"/>
<jsp:useBean id="listOrds_ByCompositeQuery" scope="request" type="java.util.List" />
<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>OneStore_idAllOrd.jsp</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.item img {
	height: 300px;
	width: 100%;
}

th {
	text-align: center;
	font-size: 14px;
}

li {
	list-style-type: none;
}
</style>
</head>

<body>

    <%@include file="headerBar.file" %>

    <!-- Header -->
    <div class="callout" ></div>

   
	
   			<div id="page-wrapper">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">訂單列表</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            </div>

 			<div id="page-wrapper col-md-12">
				<div class="col-md-3">
                <ol class="breadcrumb">
                	<li>
                        <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllProd.jsp">查看所有商品</a>
                    </li>
                   
                    <li class="active">訂單管理</li>
                </ol>
                
                </div>
                <div class="col-md-6">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do"
							name="form1">
							<div class="col-md-12">
			
								選擇訂單編號: <select size="1" name="ord_id">
									<option name="ord_id" value="">
										<c:forEach var="ordVO"
											items="${ordSvc.getOneStore_idAllOrd(storeVO.store_id)}">
											<option name="ord_id" value="${ordVO.ord_id}">${ordVO.ord_id}
										</c:forEach>
								</select><br> <input type="hidden" name="store_id"
									value="${storeVO.store_id }">
								買家付款狀態:&nbsp;&nbsp;&nbsp;&nbsp; <select name="ord_bill" value="1">
									<option name="ord_bill" value="">
									<option value="1">未付款</option>
									<option value="2">已付款</option>
									<option value="3">已結案</option>
								</select><br>
							</div>
							<div class="col-md-12">
								商店審核訂單狀態:&nbsp;<select name="ord_grant" value="1">
									<option name="ord_grant" value="">
									<option value="1">未審核</option>
									<option value="2">審核</option>
								</select><br>
			
								訂單狀態:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="ord_status"
									value="1">
									<option name="ord_status" value="">
									<option value="1">備貨中</option>
									<option value="2">待取貨</option>
									<option value="3">已取貨</option>
								</select>
							</div>
							<div>
								<button class="btn btn-default">送出</button>
								<!-- 		        <input type="submit" value="送出"> -->
								<input type="hidden" name="action"
									value="listOrds_ByCompositeQuery">
							</div>
						</FORM>
				</div>
				<div class="col-md-3"></div>
                <!-- 查詢訂單，待套用萬用搜尋 -->
                
			</div>
								

                               		 

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


			
            <div class="container-fluid col-md-12">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>目前的訂單狀態  : ${ listOrds_ByCompositeQuery.size()}筆訂單</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
												<th>訂單編號</th>
												<th>會員名稱</th>
												<th>商店名稱</th>
												<th>訂單日期</th>
												<th>訂單總額</th>
												<th>買家付款狀態</th>
												<th>商家審核訂單狀態</th>
												<th>訂單狀態</th>
												<th >操作</th>
											</tr>
											</thead>	



		
		
	
			
		
		<%@ include file="page1_ByCompositeQuery.file" %>
	<c:forEach var="ordVO" items="${listOrds_ByCompositeQuery}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
<!-- 			<TD> -->
<%-- 			<FORM METHOD="POST" ACTION="<%=REQUEST.GETCONTEXTPATH()%>/FRONT-END/ITEM/ITEM.DO"> --%>
<%-- 			<INPUT TYPE="SUBMIT" VALUE="${ORDVO.ORD_ID}" CLASS="BTN BTN-SUCCESS"> --%>
<%-- 			<INPUT TYPE="HIDDEN" NAME="ORD_ID" VALUE="${ORDVO.ORD_ID}"> --%>
<%--             <INPUT TYPE="HIDDEN" NAME="REQUESTURL"	VALUE="<%=REQUEST.GETSERVLETPATH()%>"><!--送出本網頁的路徑給CONTROLLER--> --%>
<!-- 			<INPUT TYPE="HIDDEN" NAME="ACTION" VALUE="GETONE_ORD_ID_ALLITEM"></FORM>                                                 -->
<!--             </TD>         -->
          	<td>  【<A HREF="<%=request.getContextPath()%>/front-end/item/item.do?ord_id=${ordVO.ord_id}&action=getOne_Ord_id_AllItem">${ordVO.ord_id}</a>】</td>
          		
          		
          	
          	
          	
			<td><c:forEach var="userVO" items="${userSvc.all}">
                    <c:if test="${ordVO.user_id==userVO.user_id}">
	                    	【${userVO.user_lastname}-${userVO.user_firstname}】
                    </c:if>
                </c:forEach>
			</td>
			<td><c:forEach var="storeVO" items="${storeSvc.all}">
                    <c:if test="${ordVO.store_id==storeVO.store_id}">
	                    	【${storeVO.store_name}】
                    </c:if>
                </c:forEach>
			</td>
			<td>${ordVO.ord_date}</td>
			<td>$${ordVO.ord_total}</td>
			<td>                                                    
	            <c:if test="${ordVO.ord_bill == '1'}" var="condition" scope="page" > 
	            <span class="label label-danger">未付款</span>
	            </c:if>
	            <c:if test="${ordVO.ord_bill  == '2'}" var="condition" scope="page" > 
	           <span class="label label-warning">已付款</span>
	           </c:if>
	            <c:if test="${ordVO.ord_bill  == '3'}" var="condition" scope="page" > 
	           	<span class="label label-success">已結案</span>
	           </c:if>
           </td>
			<td>                                                    
	            <c:if test="${ordVO.ord_grant == '1'}" var="condition" scope="page" > 
	             <span class="label label-danger">未審核</span>
	            </c:if>
	            <c:if test="${ordVO.ord_grant  == '2'}" var="condition" scope="page" > 
	           <span class="label label-success">已審核</span>
	           </c:if>
           </td>
           <td>                                                    
	            <c:if test="${ordVO.ord_status == '1'}" var="condition" scope="page" > 
	            <span class="label label-danger">備貨中</span>
	            </c:if>
	            <c:if test="${ordVO.ord_status  == '2'}" var="condition" scope="page" > 
	           <span class="label label-warning">待取貨</span>
	           </c:if>
	            <c:if test="${ordVO.ord_status  == '3'}" var="condition" scope="page" > 
	           	<span class="label label-success">已取貨</span>
	           </c:if>
           </td>
<%-- 			<td>${ordVO.ord_sscore}</td> --%>
<%-- 			<td>${ordVO.ord_rpdate}</td> --%>
<%-- 			<td>${ordVO.ord_rpcomm}</td> --%>
<!-- 			<td>                                                     -->
<%--             <c:if test="${ordVO.ord_rpstatus == '1'}" var="condition" scope="page" >  --%>
<%--             <img src="<%=request.getContextPath() %>/front-end/image/circleGreen.png" height="15" width="15"> --%>
<!--             <p>已處理</p> -->
<%--             </c:if> --%>
<%--             <c:if test="${ordVO.ord_rpstatus == '0'}" var="condition" scope="page" >  --%>
<%--            <img src="<%=request.getContextPath() %>/front-end/image/circleRed.png" height="15" width="15"> --%>
<!--            <p>未處理</p> -->
<%--            </c:if> --%>
<%--             <c:if test="${ordVO.ord_rpstatus == '2'}" var="condition" scope="page" >  --%>
<!--            		<p>無檢舉</p> -->
<%--            </c:if> --%>
<!--            </td> -->
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do">
			<input type="submit" value="修改" class="btn btn-primary">
		    <input type="hidden" name="ord_id" value="${ordVO.ord_id}">
	        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			<input type="hidden" name="action" value="getOne_ChangeStatus"></FORM>
		</td>
<!-- 		<td> -->
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do"> --%>
<!-- 			<input type="submit" value="檢舉" class="btn btn-primary"> -->
<%-- 			<input type="hidden" name="ord_id" value="${ordVO.ord_id}"> --%>
<%-- 	         <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<!-- 			<input type="hidden" name="action" value="getOne_UpdateReport"></FORM> -->
<!-- 		</td> -->
		</tr>
		<script>
         function presses${s.index}(){
        	 document.open("'<%=request.getContextPath()%>/front-end/item/item.do?ord_id=${ordVO.ord_id}&action=getOne_Ord_id_AllItem', '' ,'height=550,width=800,left=65,top=157,resizable=yes,scrollbars=yes'");
         }
		</script>
	</c:forEach>
</table>
<%@ include file="page2_ByCompositeQuery.file" %>
						</div>
						</div>
						</div>
</div>
</div>


<!-- 跳小視窗模組 -->


<script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->

</body>

</html>
