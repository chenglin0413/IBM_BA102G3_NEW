<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*,com.store.model.*,com.item.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	
	

if (request.getAttribute("oneord_idAllItem") != null) {
	List<ItemVO> list = (List<ItemVO>) request.getAttribute("oneord_idAllItem");
    pageContext.setAttribute("list",list);
    
}
%>





<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>listOneOrd_idAllItem.jsp</title>

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

    <%@include file="headerBar.file" %>

    <!-- Header -->
    <div class="callout" ></div>
    
   

<!--   header -->

<!--     </header> -->
   
	
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
                        <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllProd.jsp">查看所有商品</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllOrd.jsp">訂單管理</a>
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




            <div class="container-fluid col-md-6 col-md-offset-3">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>目前的訂單狀態  : </h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
												<th>訂單編號</th>
												<th>產品編號</th>
												<th>產品已售出數量</th>
												<th>買家對產品評分</th>
												<th>產品評論</th>
												<th>填寫產品評論日期</th>
												



		
		
	</tr>
	<c:forEach var="itemVO" items="${list}" >
		<tr align='center' valign='middle'>
			
            <td>${itemVO.ord_id}</td>
			<td>${itemVO.prod_id}</td>
			<td>${itemVO.item_qty}</td>
			 <td>                                                    
	            <c:if test="${empty itemVO.item_score ||itemVO.item_score =='0'}" var="condition1" scope="page" > 
	            <p>暫無評分</p>
	            </c:if>
	            <c:if test="${not empty itemVO.item_score && itemVO.item_score !='0'}" var="condition2" scope="page" > 
	            <p>${itemVO.item_score}</p>
	            </c:if>
           </td>
            <td>                                                    
	            <c:if test="${empty itemVO.item_review}" var="condition1" scope="page" > 
	            <p>暫無評論</p>
	            </c:if>
	            <c:if test="${not empty itemVO.item_review}" var="condition2" scope="page" > 
	            <p>${itemVO.item_review }</p>
	            </c:if>
           </td>
            <td>                                                    
	            <c:if test="${empty itemVO.item_reviewdate}" var="condition1" scope="page" > 
	            <p>暫無評分日期</p>
	            </c:if>
	            <c:if test="${not empty itemVO.item_reviewdate}" var="condition2" scope="page" > 
	            <p>${itemVO.item_reviewdate }</p>
	            </c:if>
           </td>
			
			
		</tr>
		
		 
			</c:forEach>
		</table>

						</div>
						</div>
						</div>
</div>
</div>

   <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->



</body>

</html>
