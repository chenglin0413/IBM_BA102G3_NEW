<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*, com.prod.model.*,com.user.model.*,com.wish.model.*"%>


<%	

	ProdVO prodVO = (ProdVO) session.getAttribute("prodVO");
	UserVO userVO = (UserVO) request.getAttribute("userVO");
	pageContext.setAttribute("userVO",userVO);
	System.out.println(prodVO.getProd_id());

%>


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品詳情</title>

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
            height: 250px;
            width:70%;
            
        }
       
        .content: {
          position: relative;
        }
        .item{
          width: 100%;
          height: 250px;  
          margin: auto;
        }
    </style>
</head>

<body>
 
<%--     <%@include file="/front-end/member_interface/headerBar.file" %> --%>
<jsp:useBean id="PrpmSvc" class="com.prpm.model.PrpmService"/>
<div class="container">
    <div class="row">
        <div class="callout"></div>
        <div class="col-md-11 col-xs-12" >
            <h3>商品詳情</h3>
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
               		<li class="active">商品詳情</a>
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
			<img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" >
			</div></div>
			<div class="col-md-6">
			<div class="row">
			<div><h3>產品名稱: ${prodVO.prod_name}</h3></div>
			<div>產品描述: ${prodVO.prod_descript}</div>
			<div>評分次數: ${prodVO.prod_count}</div>
			<div>評分總分: ${prodVO.prod_score}</div>
			<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='1'}" >
			<div><del><p>價格:$<font color="red">${prodVO.prod_price}</font></p></del></div> 
				<div><p>促銷價格:$<font color="red">${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_price}</font></p></div>
			</c:if>
			<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='0'||PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status==null}" >
				<div><p>價格:$<font color="red">${prodVO.prod_price}</font></p></div> 
			</c:if>
			
<%-- 			<div><p>$<font color="red">${prodVO.prod_price}</font></p></div> --%>
			<c:if test="${empty userVO.user_account}" var="condition1" scope="session" > 
					<div class="btn btn-info"><a href='#modal-login' data-toggle="modal">請先登入</a></div>
				
			</c:if>		
                    
             <c:if test="${not empty userVO.user_account}" var="condition2" scope="session" > 
		 	<form name="shoppingForm" action="<%=request.getContextPath()%>/front-end/eshop/ShoppingServlet" method="POST">
			<div>數量： <input type="number" name="quantity" min="1" max="10" value="1"></div>
                  <div class="btn btn-default"><input type="submit" name="Submit" value="放入購物車"></div>
			  <input type="hidden" name="prod_id" value="${prodVO.prod_id}">
	      	  <input type="hidden" name="store_id" value="${prodVO.store_id}">
		      <input type="hidden" name="prod_name" value="${prodVO.prod_name}">
		      <input type="hidden" name="prod_descript" value="${prodVO.prod_descript}">
		      <input type="hidden" name="prod_price" value="${prodVO.prod_price}">
		      <input type="hidden" name="prod_sort" value="${prodVO.prod_sort}">
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->	     	
	      		<input type="hidden" name="action" value="ADD">
	       </form>
	       <form name="wishForm" action="<%=request.getContextPath()%>/front-end/wish/wish.do" method="POST">
	       	  <input type="hidden" name="user_id" value="${userVO.user_id}">			
	       	  <input type="hidden" name="prod_id" value="${prodVO.prod_id}">
		       <div class="btn btn-default"><input type="submit" name="Submit" value="加入追蹤"></div>
		       <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->
		       <input type="hidden" name="action" value="ADDTOWish">
	       </form>
	       </c:if>
	     </div>
		</div>
	     	
		</div>
		</div>
<%-- </c:forEach> --%>

<div class="col-xs-12 col-md-8 col-md-offset-4">
</div>
  
<%@ include file="/front-end/member_interface/script.file" %>
    
</body>
</html>