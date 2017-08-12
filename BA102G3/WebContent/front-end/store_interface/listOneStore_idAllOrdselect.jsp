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
// 		OrdService ordSvc = new OrdService();
// 	    List<OrdVO> list = ordSvc.getOneStore_idAllOrd(storeVO.getStore_id());
// 	    pageContext.setAttribute("list",list);
	   
	}
    
    
    
%>

<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<jsp:useBean id="userSvc"  scope="page" class="com.user.model.UserService"/>
<jsp:useBean id="storeSvc"  scope="page" class="com.store.model.StoreService"/>
<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>OneStore_idAllOrdselect.jsp</title>

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
        .item img{
            height: 300px;
            width: 100%;
        }
        th{
       text-align:center;
       font-size:14px;
       
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
				<div class="col-md-6">
                <ol class="breadcrumb">
                	<li>
                        <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllProd.jsp">查看所有商品</a>
                    </li>
                   
                    <li class="active">訂單管理</li>
                </ol>
                <ul class="nav nav-tabs">
<!--                             <div class="dropdown"> -->
<!--                               <a href="#" class="dropdown-toggle" data-toggle="dropdown">商品種類 <b class="caret"></b></a> -->
<!--                               <ul class="dropdown-menu"> -->
<!--                                 <li><a href="#">零食、點心</a></li> -->
<!--                                 <li><a href="#">免稅菸酒</a></li> -->
<!--                                 <li><a href="#">國際精品</a></li> -->
<!--                               </ul> -->
<!--                             </div> -->
                            

                           
        		 </ul>
                </div>
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
		  <li>   
		    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do" name="form1">
		        <b><font color=blue>萬用複合查詢:</font></b> <br>
		        <div class="col-md-4">
		        
		        	<b>選擇訂單編號: <select size="1" name="ord_id" >
		        					<option name="ord_id" value="">	
		        					<c:forEach var="ordVO" items="${ordSvc.getOneStore_idAllOrd(storeVO.store_id)}" >
						          		<option name="ord_id" value="${ordVO.ord_id}">${ordVO.ord_id}
				         			</c:forEach>  
				         			 
      							 </select><br>
		        	
		        	<input type="hidden" name="store_id" value="${storeVO.store_id }"><br>
		       </div>
		    
		       <div class="col-md-4">   
		       	買家付款狀態:&nbsp;&nbsp;&nbsp;&nbsp; <select name="ord_bill" value="1">
			 <option name="ord_bill" value="">
			  <option value="1">未付款</option>
			  <option value="2">已付款</option>
			  <option value="3">已結案</option>
				</select>
				
				<br>
				商店審核訂單狀態:&nbsp;<select name="ord_grant" value="1">
					   <option name="ord_grant" value="">
					  <option value="1">未審核</option>
					  <option value="2">審核</option>
				</select>
				
				<br>
				
				訂單狀態:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="ord_status" value="1">
					  <option name="ord_status" value="">
					  <option value="1">備貨中</option>
					  <option value="2">待取貨</option>
					  <option value="3">已取貨</option>
				</select>
				</div>
				 <div class="col-md-4">        
		        <input type="submit" value="送出">
		        <input type="hidden" name="action" value="listOrds_ByCompositeQuery">
		        </div>
		     </FORM>
		  </li>
	</div>  
	</div>
            <div class="container-fluid col-md-12">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
												<th>訂單編號</th>
												<th>會員編號</th>
												<th>商店編號</th>
												<th>訂單日期</th>
												<th>訂單總額</th>
												<th>買家付款狀態</th>
												<th>商家審核訂單狀態</th>
												<th>訂單狀態</th>
<!-- 												<th>買家對商店評分</th> -->
<!-- 												<th>商家檢舉訂單日期</th> -->
<!-- 												<th>檢舉訂單理由</th> -->
<!-- 												<th>商家檢舉訂單狀態</th> -->
<!-- 												<th>商家檢舉訂單狀態</th> -->
												<th >操作</th>
												</tr>
											</thead>
											</table>	

						</div>
						</div>
						</div>
</div>
</div>




<script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->

</body>

</html>
