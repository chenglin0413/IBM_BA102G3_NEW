<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.prod.model.*"%>
<%
ProdVO prodVO = (ProdVO) request.getAttribute("prodVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
pageContext.setAttribute("prodVO",prodVO);
%>


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>listOneProd_forStore.jsp</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
<div class="callout"></div>
   <%@include file="headerBar.file" %>
		
        
<!--         Wrapper for Slides -->
        

<!--     </header> -->
   
	
   			<div id="page-wrapper">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">產品</h3>
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
                    <li class="active">商品明細</li>
                </ol>
                </div>
			</div>




<div class="container-fluid col-md-6 col-md-offset-3">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>產品明細 : </h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                      	<tr><td>產品編號 : </td><td>${prodVO.prod_id}</td></tr>
										<tr><td>商店編號</td><td>${prodVO.store_id}</td></tr>
										<tr><td>產品名稱</td><td>${prodVO.prod_name}</td></tr>
										<tr><td>產品描述</td><td>${prodVO.prod_descript}</td></tr>
										<tr><td>產品價格</td><td>$${prodVO.prod_price}</td></tr>
										<tr><td>產品類別</td><td>${prodVO.prod_sort}</td></tr>
										<tr><td>產品規格</td><td>${prodVO.prod_format}</td></tr>
										<tr><td>產品品牌</td><td>${prodVO.prod_brand}</td></tr>
										<tr><td>產品更新時間</td><td>${prodVO.prod_updatetime}</td></tr>
										<tr><td>產品賣出數量</td><td>${prodVO.prod_soldcount}</td></tr>
										<tr><td>產品狀態 </td>                                            
									            <c:if test="${prodVO.prod_status == '1'}" var="condition" scope="page" > 
									            <td>上架</td>
									            </c:if>
									            <c:if test="${prodVO.prod_status  == '2'}" var="condition" scope="page" > 
									           <td>下架</td>
									           </c:if>
								        </tr>
										<tr><td>產品評分次數</td><td>${prodVO.prod_count}</td></tr>
										<tr><td>產品評分總分</td><td>${prodVO.prod_score}</td></tr>

		
		
</table>
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
