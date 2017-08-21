<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*,com.store.model.*,com.item.model.*,com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	
	

if (session.getAttribute("oneord_idAllItem") != null) {
	List<ItemVO> list = (List<ItemVO>) session.getAttribute("oneord_idAllItem");
	Integer ord_id=list.get(0).getOrd_id();
	pageContext.setAttribute("ord_id",ord_id);
	pageContext.setAttribute("list",list);
    
}
%>


<jsp:useBean id="prodSvc" class="com.prod.model.ProdService"/>


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>listOneOrd_idAllItem_formember.jsp</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

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

     <%@include file="/front-end/member_interface/headerBar.file" %>

	
   			<div id="page-wrapper">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">訂單明細</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            </div>

 			<div id="page-wrapper col-md-12">
				<div class="col-md-6 　">
                <ol class="breadcrumb">
                    <li>
                        <a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/front-end/item/item.do?ord_id=${ItemVO.ord_id}&action=getOne_Ord_id_AllItem_formember">消費記錄</a>
                    </li>
                    <li class="active">訂單明細</li>
                </ol>
                </div>
			</div>
					
<div class="callout"></div>
                               		 

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




            <div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12"></div>
		
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">訂單明細</h3>
			  </div>
			  <div class="panel-body text-center">
			  			<div class="col-xs-12 col-md-3">訂單編號</div>
						<div class="col-xs-12 col-md-2">訂單編號</div>
						<div class="col-xs-12 col-md-2">產品名稱</div>
						<div class="col-xs-12 col-md-2">購買數量</div>
						<div class="col-xs-12 col-md-2">買家對產品評分</div>
						

			</div>
			<c:forEach var="itemVO" items="${list}" >
			<div class="panel-body text-center">
			 			<div class="col-md-3"><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${itemVO.prod_id}" width="70" height="50"></div>
			 	
						<div class="col-xs-12 col-md-2">${itemVO.ord_id}</div>
						<div class="col-xs-12 col-md-2">
							<c:forEach var="prodVO" items="${prodSvc.all}">
							<c:if test="${itemVO.prod_id==prodVO.prod_id}">
							<a href="<%=request.getContextPath() %>/front-end/item/item.do?prod_id=${itemVO.prod_id}&ord_id=${itemVO.ord_id}&action=getOne_prod_id_upate_count_score">${prodVO.prod_name}</a>
							 </c:if>
							 </c:forEach>
						</div>
						<div class="col-xs-12 col-md-2">${itemVO.item_qty}</div>
						<div class="col-xs-12 col-md-2">
							<c:if test="${empty itemVO.item_score ||itemVO.item_score =='0'}" var="condition1" scope="page" > 
				            <p>暫無評分</p>
				            </c:if>
				            <c:if test="${not empty itemVO.item_score && itemVO.item_score !='0'}" var="condition2" scope="page" > 
				            <p>${itemVO.item_score}</p>
				            </c:if>
						
						</div>
						
			
			</div>
			</c:forEach>
			
		</div>
		<input onclick="window.close();" value="關閉視窗" type="button">	
		
	</div>
	
	            
<%-- 	            <c:if test="${empty itemVO.item_review}" var="condition1" scope="page" >  --%>
<!-- 	            <p>暫無評論</p> -->
<%-- 	            </c:if> --%>
<%-- 	            <c:if test="${not empty itemVO.item_review}" var="condition2" scope="page" >  --%>
<%-- 	            <p>${itemVO.item_review }</p> --%>
<%-- 	            </c:if> --%>
<!--            </td> -->
<!--             <td>                                                     -->
<%-- 	            <c:if test="${empty itemVO.item_reviewdate}" var="condition1" scope="page" >  --%>
<!-- 	            <p>暫無評分日期</p> -->
<%-- 	            </c:if> --%>
<%-- 	            <c:if test="${not empty itemVO.item_reviewdate}" var="condition2" scope="page" >  --%>
<%-- 	            <p>${itemVO.item_reviewdate }</p> --%>
<%-- 	            </c:if> --%>
<!--            </td> -->
		 
<%@ include file="/front-end/member_interface/script.file" %>
</body>

</html>
