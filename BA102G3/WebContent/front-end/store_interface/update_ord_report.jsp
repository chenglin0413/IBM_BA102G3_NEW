<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*"%>
<%
	OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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

    <!-- Navigation -->
    <!-- <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a> -->
    
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a href="index.html"><img src="<%=request.getContextPath()%>/front-end/img/logo3.png"></a>
                <a class="navbar-brand page-scroll" href="index.html">Anytime Grip</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    
                   
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-gift"></i>商品資料管理<i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="index.html"><i class="fa fa-info fa-fw"></i>查看所有商品</a>
                        </li>
                        <li><a href="product_update.html"><i class="fa fa-smile-o fa-fw"></i>上架新商品</a>
                        </li>
                        <li><a href="product_message.html"><i class="fa fa-smile-o fa-fw"></i>查看商品留言</a>
                        </li>
                        <li><a href="query_all_promotion.html"><i class="fa fa-github-alt fa-fw"></i> 查看所有促銷專案</a>
                        </li>
                        <li><a href="bulid_Promotion.html"><i class="fa fa-warning fa-fw"></i> 建立促銷專案</a>
                        </li>
                        
                    </ul>
                    </li>    

                    <li>
                       
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                         <i class="fa fa-bar-chart-o fa-fw"></i>訂單管理<i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="order_list.html"><i class="fa fa-info fa-fw"></i>查看訂單</a>
                        </li>
                        <li><a href="order_list_detail.html"><i class="fa fa-power-off fa-fw"></i>查看訂單明細</a>
                        </li>
                        
                        
                    </ul>
                    </li>
                    
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>商家資訊管理<i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-info fa-fw"></i>商家資訊修改</a>
                        </li>
                        <li><a href="#"><i class="fa fa-power-off fa-fw"></i>關店申請</a>
                        </li>
                        <li><a href="#"><i class="fa fa-warning fa-fw"></i> 商品檢舉審查</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
                        </li>
                    </ul>
                    </li>
                    
                    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <div class="callout" ></div>
   <nav class="navbar navbar-inverse easy-sidebar">
<div class="container-fluid"> 
<div class="navbar-header">
<!--easy-sidebar-toggle-right -->
<button type="button" class="navbar-toggle easy-sidebar-toggle" aria-expanded="false"> 
<span class="sr-only">Toggle navigation</span> 
<span class="icon-bar"></span>
<span class="icon-bar"></span> 
<span class="icon-bar"></span>
</button>
<a class="navbar-brand" href="#">管理介面</a> 
</div>
<ul class="nav navbar-nav">
                         <li><a href="index.html" >查看所有商品</a></li>
                        <li><a href="product_update.html" >上架新商品</a></li>
                        <li><a href="order_list.html" >查看訂單</a></li>
                        <li><a href="product_message.html">查看商品留言</a></li>
                        <li><a href="query_all_promotion.html" >查看所有促銷專案</a></li>
                        <li><a href="bulid_Promotion.html" >建立促銷專案</a></li>
                        
</ul>
</div>
<!-- /.container-fluid --> 
</nav>
   
	
   			<div id="page-wrapper">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">修改訂單檢舉狀態</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            </div>

 			<div id="page-wrapper col-md-12">
				<div class="col-md-6">
                <ol class="breadcrumb">
                   <li>
                         <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllOrdselect.jsp">訂單管理</a>
                    </li>
                    <li class="active">修改訂單檢舉狀態</li>
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

<div class="container-fluid col-md-7 col-md-offset-3">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>目前的訂單狀態  :</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do" name="form1">
										<tr>
											<td>訂單編號:<font color=red><b>*</b></font></td>
											<td><%=ordVO.getOrd_id()%></td>
										</tr>
										<tr>
											<td>會員編號:</td>
											<td><%=ordVO.getUser_id()%></td>
										</tr>
										<tr>
											<td>商家編號:</td>
											<td><%=ordVO.getStore_id()%></td>
										</tr>
										<tr>
											<td>訂單日期:</td>
											<td><%=ordVO.getOrd_date()%></td>
										</tr>
										<tr>
											<td>總價:</td>
											<td><%=ordVO.getOrd_total()%></td>
										</tr>
										<tr>
											<td>買家付款狀態:</td>
											<td><%=ordVO.getOrd_bill()%></td>
										</tr>
										<tr>
											<td>商店審核訂單狀態:</td>
											<td><%=ordVO.getOrd_grant()%></td>
										</tr>
										<tr>
											<td>訂單狀態:</td>
											<td><%=ordVO.getOrd_status()%></td>
										</tr>
										<tr>
											<td>買家對於商店評分:</td>
											<td><%=ordVO.getOrd_sscore()%></td>
										</tr>
										<tr>
											<td>商家檢舉訂單日期:</td>
											<td><input type="DATE" name="ord_rpdate" size="45"	value="<%=ordVO.getOrd_rpdate()%>" /></td>
										</tr>
										<tr>
											<td>檢舉訂單理由:</td>
											<td><input type="TEXT" name="ord_rpcomm" size="45"	value="<%=ordVO.getOrd_rpcomm()%>" /></td>
										</tr>
									
									
									</table>
									<table>
											<tr>商家檢舉訂單狀態:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="ord_rpstatus" value="<%=ordVO.getOrd_rpstatus()%>">
												  <option value="0">未處理</option>
												  <option value="1">已處理</option>
											</select>
											</tr>
									</table>
									<input type="hidden" name="action" value="update_report">
									<input type="hidden" name="ord_id" value="<%=ordVO.getOrd_id()%>">
									<input type="hidden" name="user_id" value="<%=ordVO.getUser_id()%>">
									<input type="hidden" name="store_id" value="<%=ordVO.getStore_id()%>">
									<input type="hidden" name="ord_date" value="<%=ordVO.getOrd_date()%>">
									<input type="hidden" name="ord_total" value="<%=ordVO.getOrd_total()%>">
									<input type="hidden" name="ord_sscore" value="<%=ordVO.getOrd_sscore()%>">
									<input type="hidden" name="ord_bill" value="<%=ordVO.getOrd_bill()%>">
									<input type="hidden" name="ord_grant" value="<%=ordVO.getOrd_grant()%>">
									<input type="hidden" name="ord_status" value="<%=ordVO.getOrd_status()%>">
									<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
									<input type="submit" value="送出修改"></FORM>
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
