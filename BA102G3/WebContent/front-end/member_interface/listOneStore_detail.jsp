<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*, com.prod.model.*,com.user.model.*,com.wish.model.*"%>


<%	
	if(request.getParameter("store_id")!=null){
		Integer store_id=new Integer(request.getParameter("store_id"));
		session.setAttribute("store_id",store_id);//把store_id存起來
		StoreService storeSvc=new StoreService();
		StoreVO storeVO=storeSvc.getOneStore(store_id);
	    pageContext.setAttribute("storeVO",storeVO);
	}else{
		Integer store_id=(Integer)session.getAttribute("store_id");
		StoreService storeSvc=new StoreService();
		StoreVO storeVO=storeSvc.getOneStore(store_id);
	    pageContext.setAttribute("storeVO",storeVO);
	}
	
%>
<jsp:useBean id="prodSvc" class="com.prod.model.ProdService"/>

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商店詳情</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

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
            height: 180px;
            width:70%;
            padding:5px;
            
        }
       
        .content: {
          position: relative;
        }
        .item{
          width: 100%;
          height: 250px;  
          margin: 10px;
        }
    </style>
</head>

<body>
 
     <%@include file="/front-end/member_interface/headerBar.file" %>

<div class="container">
    <div class="row">
        <div class="callout"></div>
        <div class="col-md-11 col-xs-12" >
            <h3>商店詳情</h3>
        </div>         
    </div>
</div>
</div>
<div class="container content">
        
        <div class="row">
          <ol class="breadcrumb">
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/index.jsp">首頁</a>
               </li>
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/member_interface/listAllProd.jsp">商品</a>
               </li>
               		<li class="active">商店詳情</a>
               		</li>
               
           </ol>
        



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
<div class="row ">
</div>
</div>
		
		<div class="col-xs-12 col-md-12">
              
         <div class="item">
         
			<div class="col-md-6"><div class="item">
			<img src="<%=request.getContextPath()%>/front-end/stpi/DBGifReader?store_id=${storeVO.store_id}" width="300" height="250">
			
			</div></div>
			<div class="col-md-6">
			<div class="row">
			<div><h3>商店名稱: ${storeVO.store_name}</h3></div>
			<div>商店描述: ${storeVO.store_describe}</div>
			<div>商店評價總分: ${storeVO.store_score}</div>
			<div><h4>本商店位於: ${storeVO.store_ter}航廈，${storeVO.store_floor}樓</h4></div>
			
			
	     </div>
		</div>
		</div>
		</div>
		
		
		<div class="col-md-12">
		<div class="row">
		<h3><div>本店販售商品如下:</div></h3>
		<c:forEach var="prodVO" items="${prodSvc.getOneStore_idAllProd(storeVO.store_id)}">
					<div class="col-xs-12 col-md-4">
                	<div class="item " >
					<a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_id=${prodVO.prod_id}&action=getOne_For_Display"><div>${prodVO.prod_name}</div></a>
					<div id="boxshadow" ><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" width="300" height="250"></div>
					</div>
					</div>
		</c:forEach>
		
		</div>
		</div>
<%-- </c:forEach> --%>

<div class="col-xs-12 col-md-8 col-md-offset-4">
</div>

<%@ include file="/front-end/member_interface/script.file" %>
 

</body>
</html>