<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*,com.prod.model.*"%>
<%
	ProdVO prodVO = (ProdVO) request.getAttribute("prodVO"); 
	pageContext.setAttribute("prodVO",prodVO);
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>OneStore_order_list</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
                    <h3 class="page-header">訂單明細</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            </div>

 			<div id="page-wrapper col-md-12">
				<div class="col-md-6">
                <ol class="breadcrumb">
                    <li>
                        <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllOrd.jsp">查詢訂單列表</a>
                    </li>
                    <li class="active">訂單明細</li>
                </ol>
                </div>
                
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

<div class="container-fluid col-md-6 col-md-offset-3 col-xs-10">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>目前的訂單狀態  :</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
												<tr>
													<td>訂單編號:</td>
													<td>${ordVO.ord_id }</td>
												</tr>
												<tr>
													<td>會員編號:</td>
													<td>${ordVO.user_id}</td>
												</tr>
												<tr>
													<td>商家編號:</td>
													<td>${ordVO.store_id}</td>
												</tr>
												<tr>
													<td>訂單日期:</td>
													<td>${ordVO.ord_date }</td>
												</tr>
												<tr>
													<td>訂單總額:</td>
													<td>${ordVO.ord_total}</td>
												</tr>
												<tr>   
													<td>買家付款狀態:</td>                                                 
	          									  	<c:if test="${ordVO.ord_bill == '1'}" var="condition" scope="page" > 
										            <td>未付款</td>
										            </c:if>
										            <c:if test="${ordVO.ord_bill  == '2'}" var="condition" scope="page" > 
										           <td>已付款</td>
										           </c:if>
										            <c:if test="${ordVO.ord_bill  == '3'}" var="condition" scope="page" > 
										           	<td>已結案</td>
										           </c:if>
									           </tr>
												<tr>
													<td>商家出貨審核:</td>                                               
										            <c:if test="${ordVO.ord_grant == '1'}" var="condition" scope="page" > 
										            <td>未審核</td>>
										            </c:if>
										            <c:if test="${ordVO.ord_grant  == '2'}" var="condition" scope="page" > 
										           <td>已審核</td>
										           </c:if>
									           </tr>
									           <tr>   
									           		<td>訂單狀態:</td>                                                   
										            <c:if test="${ordVO.ord_status == '1'}" var="condition" scope="page" > 
										            <td>備貨中</td>
										            </c:if>
										            <c:if test="${ordVO.ord_status  == '2'}" var="condition" scope="page" > 
										           <td>待取貨</td>
										           </c:if>
										            <c:if test="${ordVO.ord_status  == '3'}" var="condition" scope="page" > 
										           	<td>已取貨</td>
										           </c:if>
									           </tr>
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
  <script>
//easy-sidebar-toggle-right
$('.easy-sidebar-toggle').click(function(e) {
e.preventDefault();
//$('body').toggleClass('toggled-right');
$('body').toggleClass('toggled');
//$('.navbar.easy-sidebar-right').removeClass('toggled-right');
$('.navbar.easy-sidebar').removeClass('toggled');
});
</script>


</body>
</html>
