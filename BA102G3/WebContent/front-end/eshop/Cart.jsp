<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.prod.model.ProdVO" %>




<% 
	
		Vector<ProdVO> buylist = (Vector<ProdVO>) session.getAttribute("shoppingcart");
		Vector<Integer> buyqty = (Vector<Integer>) session.getAttribute("shoppingqty");
		
%>

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>購物車</title>

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
     
		item img{
		height: 250px;
        align:center;
		}
        .item{
          width: 100%px;
          height: 250px;  
          margin: auto;
        }
		body {
			background-image: url(<%=request.getContextPath()%>/front-end/img/bg004.jpg);
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-position: center;
			background-size: cover;
		}


    </style>
</head>

<body>
 
     <%@include file="/front-end/member_interface/headerBar.file" %>
        

<div class="container">
    <div class="row">
        
        <div class="col-md-11 col-xs-12" >
           <font size="+3">目前您購物車的內容如下：</font><p>
        </div>         
    </div>
</div>
    
   
<div class="container">
<div class="row">
           <ol class="breadcrumb">
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/index.jsp" >首頁</a>
               </li>
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/member_interface/listAllProd.jsp">商品</a>
               </li>
               <li class="active">購物車</li>
           </ol>


 <div class="col-xs-12 col-sm-4  "><h4 class="text-center">商品</h4></div>
        <div class="col-xs-12 col-sm-8 ">
            <div class="row">
            <div class="col-xs-12 col-sm-12 ">
            <div class="col-xs-12 col-sm-3 ">單價</div>
            <div class="col-xs-12 col-sm-3 ">數量</div>
            <div class="col-xs-12 col-sm-3 ">種類</div>
            <div class="col-xs-12 col-sm-3 ">操作</div>
			</div>
         
        </div>
      </div>
      </div>
<%if (buylist != null && (buylist.size() > 0)) {%>      
<%
	 for (int index = 0; index < buylist.size(); index++) {
		ProdVO prodVO = buylist.get(index);
	%>


      <div class="container">
          <div class="row  ">
          
           <div class="col-xs-12 col-sm-4 ">
           		 	<div class="row">
                    <div class="col-md-12 text-left"><h5><%=prodVO.getProd_name()%></h5></div>
                    <div class="col-md-12 "><div class="item"> <img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=<%=prodVO.getProd_id()%>" width="180px" ></div></div>
           			</div>
           </div>
        <div class="col-xs-12 col-sm-8 ">
            <div class="row">
               			<div class="col-xs-12 col-sm-3">
	                       <%=prodVO.getProd_price()%>
	                     </div>
                     <div class="col-xs-12 col-sm-3">
                         <%=buyqty.get(index)%>    
              		</div>
                     <div class="col-xs-12 col-sm-3">
                       	<%=prodVO.getProd_sort()%>
                     </div>
                     <div class="col-xs-12 col-sm-3">
                         <form name="deleteForm" action="<%=request.getContextPath() %>/front-end/eshop/ShoppingServlet" method="POST">
			              <input type="hidden" name="action" value="DELETE">
			              <input type="hidden" name="del" value="<%= index %>">
			              <input type="submit" value="刪除">
			        	</form>
                     </div>
	       	 </div>
	      	</div>
	      	
      	</div>
       </div>   

</div>
</div>


	
	
	
<%-- 		<td width="200"><div align="center"><b><%=prodVO.getProd_id()%></b></div></td> --%>
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getStore_id()%></b></div></td> --%>
<!-- 		<td width="100"><div align="center"><b></b></div></td> -->
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_descript()%></b></div></td> --%>
<!-- 		<td width="100"><div align="center"><b></b></div></td> -->
<!-- 		<td width="200"><div align="center"><b></b></div></td> -->
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_format()%></b></div></td> --%>
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_updatetime()%></b></div></td> --%>
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_soldcount()%></b></div></td> --%>
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_format()%></b></div></td> --%>
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_status()%></b></div></td> --%>
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_count()%></b></div></td> --%>
<%-- 		<td width="100"><div align="center"><b><%=prodVO.getProd_score()%></b></div></td> --%>
<!-- 		<td width="100"><div align="center"><b></b></div></td> -->
<!-- 		<td width="100"><div align="center"></td> -->
         
	
	<%}%>

	<div class="container">
    <div class="row">
   
			<div class="col-xs-12 col-md-11">
        		<div class="btn btn-default"><a href="<%=request.getContextPath()%>/front-end/member_interface/listAllProd.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i>繼續購物</a></div>
      		</div>
			<div class="col-xs-12 col-md-1">
		          <div class="btn btn-default"><a href="<%=request.getContextPath()%>/front-end/eshop/ShoppingServlet?action=CHECKOUT">付款結帳</a></div>
<%-- 		          <form name="checkoutForm" action="<%=request.getContextPath()%>/front-end/eshop/ShoppingServlet" method="POST"> --%>
<!-- 		          <div class="btn btn-default"><i class="fa fa-shopping-cart" aria-hidden="true"></i> -->
<!-- 		              <input type="hidden" name="action"	value="CHECKOUT">  -->
<!-- 		              <input type="submit" value="付款結帳"> -->
<!-- 		           </div>    -->
<!-- 		          </form> -->
          </div>
    
    </div>
 	</div>
 	<div class="callout"></div>
<%}%>


        <a id="to-top" href="#top" class="btn btn-dark btn-lg"><i class="fa fa-chevron-up fa-fw fa-1x"></i></a>


<%@ include file="/front-end/member_interface/script.file" %>



</body>
</html>