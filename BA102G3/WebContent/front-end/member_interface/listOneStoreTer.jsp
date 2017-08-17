<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prod.model.*,com.store.model.*,com.rppr.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	
	
// 	ProdService prodSvc = new ProdService();
	List<ProdVO> list = (List<ProdVO>)session.getAttribute("onestoreterlist");
//     Integer store_ter=(Integer)session.getAttribute("store_ter");
    pageContext.setAttribute("list",list);
//     pageContext.setAttribute("store_ter",store_ter);
    //提示檢舉的商品
    RpprVO rpprVO=(RpprVO)request.getAttribute("rpprVO");
    pageContext.setAttribute("rpprVO",rpprVO);
%>

<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<jsp:useBean id="PrpmSvc" class="com.prpm.model.PrpmService"/>
<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品(航廈)</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   <style type="text/css">
        .rpprForm {
        	display:none;
        }
        li {
			list-style-type:none;
			}
    </style>
</head>

<body>
 
     <%@include file="/front-end/member_interface/headerBar.file" %>

        

<div class="container">
    <div class="row">
        <div class="callout"></div>
        <div class="col-md-11 col-xs-12" >
            <h3>商品瀏覽</h3>
        </div>         
    </div>
</div>

    <!-- Callout間隔區 -->
    <div class="callout"></div>

     
    <!-- Portfolio -->
<!-- Portfolio Grid Section -->
<div class="container content">
        
        <div class="row">
        <header  class="header">
        <div class="text-vertical-center">
            <h1>Anytime Grip</h1>
            <h3>SHOP OUR COLLECTIONS &amp; SHOP WITH RUNWAY</h3>
            <br>
            <a href="#about" class="btn btn-dark btn-lg">Grip Now!!</a>
        </div>
       </header> 
</div>
</div>
 <div class="container">	
	<div class="row ">         
             
        <c:forEach var="prodVO" items="${list}" >
	        <c:if test="${rpprVO!=null }">    
		        <c:if test="${prodVO.prod_id==rpprVO.prod_id}">
		       		<div>檢舉產品:<font color='red'>${prodVO.prod_name}</font>,檢舉原因:<font color='red'>${rpprVO.rppr_tittle }</font>成功</div>
		        </c:if>
	        </c:if>
   		  </c:forEach> 
       
       
        
          <ol class="breadcrumb">
               <li>
                    <a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a>
               </li>
               <li>
                   <a href="<%=request.getContextPath()%>/front-end/member_interface/listAllProd.jsp">所有商品</a>
               </li>
               <li>商品(航廈)</li>
               
           </ol>
        
        <ul class="nav nav-tabs">
                             <li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?store_ter=1&action=getOneStoreTer_For_Display"><i class="fa fa-arrow-circle-o-up"></i>第一航廈</a></li>
                             <li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?store_ter=2&action=getOneStoreTer_For_Display"><i class="fa fa-arrow-circle-o-up"></i>第二航廈</a></li>
                            <li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_sort=3&action=getOneSort_For_Display"><i class="fa fa-arrow-circle-o-up"></i>伴手禮</a></li>
                            <li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_sort=4&action=getOneSort_For_Display"><i class="fa fa-arrow-circle-o-up"></i>酒類</a></li>
							<li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_sort=5&action=getOneSort_For_Display"><i class="fa fa-arrow-circle-o-up"></i>文具用品</a></li>   
                            <li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_sort=6&action=getOneSort_For_Display"><i class="fa fa-arrow-circle-o-up"></i>生活用品</a></li>
                             <li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_sort=7&action=getOneSort_For_Display"><i class="fa fa-arrow-circle-o-up"></i>化妝品</a></li>
                              <li><a href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_sort=8&action=getOneSort_For_Display"><i class="fa fa-arrow-circle-o-up"></i>精品</a></li>
         </ul>

 <div class="container">
<div class="row">
<%@ include file="page1.file"%>
</div>
</div>

	<c:forEach var="prodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
			<div class="col-xs-12 col-md-4">
                <div class="item">
<%--                 			<c:if test="${storeSvc.getOneStore(prodVO.store_id).store_ter==store_ter}"> --%>
							<div><a href="<%=request.getContextPath()%>/front-end/store/store.do?store_id=${prodVO.store_id}&action=seeOneStoredetail">${storeVO.store_name}</a></div>
	                    	 <div id="boxshadow"><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" width="300" height="250"></div>
							 <div><h4>${prodVO.prod_name}</h4></div>
							 <div><h4>$${prodVO.prod_price}</h4></div>
							 <div><h4>${prodVO.prod_sort}</h4></div>
							<a href='#${prodVO.prod_id}' data-toggle="modal" class="btn btn-info">瀏覽詳情</a>
					<div class="modal fade" id="${prodVO.prod_id}">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">${prodVO.prod_name}</h4>
								</div>
								<div class="modal-body">
									<div id="boxshadow"><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" width="300" height="250"></div>
									<div class="col-md-6">
									<div><b>產品描述:</b><br> ${prodVO.prod_descript}</div>
									<div>評分次數: ${prodVO.prod_count}</div>
									<div>評分總分: ${prodVO.prod_score}</div>
									
									<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='1'}" >
										<div><del><p>價格:$<font color="red">${prodVO.prod_price}</font></p></del></div> 
										<div><p>促銷價格:$<font color="red">${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_price}</font></p></div>
									</c:if>
									<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='0'||PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status==null}" >
										<div><p>價格:$<font color="red">${prodVO.prod_price}</font></p></div> 
									</c:if>
									</div>
									<div class="col-md-6">
									<c:if test="${empty userVO.user_account}" var="condition1" scope="session" > 
											<div class="btn btn-info"><a href='#modal-login' data-toggle="modal">請先登入</a></div>
											<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
									</c:if>		
									 <c:if test="${not empty userVO.user_account}" var="condition2" scope="session" >
									 
								 		 
									 
									 
									  
									 <form name="shoppingForm" action="<%=request.getContextPath()%>/front-end/eshop/ShoppingServlet" method="POST">	
												<div>數量： <input type="number" name="quantity" min="1" max="10" value="1" size="2"></div>
							                  <div class="btn btn-default"><input type="submit" name="Submit" value="放入購物車"></div>
										  <input type="hidden" name="prod_id" value="${prodVO.prod_id}">
								      	  <input type="hidden" name="store_id" value="${prodVO.store_id}">
									      <input type="hidden" name="prod_name" value="${prodVO.prod_name}">
									      <input type="hidden" name="prod_descript" value="${prodVO.prod_descript}">
											<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='1'}" >
												 <input type="hidden" name="prod_price" value="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_price}">
											</c:if>
											<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='0'||PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status==null}" >
												<input type="hidden" name="prod_price" value="${prodVO.prod_price}">
											</c:if>
									     
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
								       
 													<!-- 	       檢舉區塊 -->
<%--  					<div class="btn btn-default"><a href="<%=request.getContextPath()%>/front-end/member_interface/addRppr.jsp?prod_id=${prodVO.prod_id}">檢舉商品</a></div> --%>
<!-- 					<!-- 檢舉btn,預設隱藏 -->
              		<li>  								
              		<%long seconds = new java.util.Date().getTime();%>
             		 <!-- 檢舉btn,預設隱藏 -->
	              		<button class="btn-danger btn-xs btnReport">檢舉遊記</button>	
	              		<form action="<%=request.getContextPath()%>/back-end/report/rppr.do" method="post"  class="form-horizontal rpprForm">
							<div class="form-group">
								<label class="col-sm-3 control-label" >檢舉標題</label>
								<div class="col-sm-9">
									<input type="text" name="rppr_tittle" class="form-contrl" size="15" required>
								</div>										
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">檢舉內容</label>
								<div class="col-sm-9">
									<textarea  name="rppr_content" class="form-contrl" required></textarea>
								</div>										
							</div>
							<div class="text-center">
								<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> 
								<input type="hidden" name="user_id" value="${userVO.user_id}">
								<input type="hidden" name="prod_id" value="${prodVO.prod_id}">
								<input type="hidden" name="rppr_date" value="<%= seconds%>">
								<input type="hidden" name="action" value="insert">
								<input type="submit" class="btn btn-danger btn-sm" value="送出檢舉">
							</div>
						</form>
              			</li>
	              	<!-- 檢舉結尾 -->

											      
											       </c:if>
												
												</div>
											</div>
											
											<div class="modal-footer">
												
											</div>
										</div>
									</div>
								</div>
<%--                    			</c:if> --%>
			</div>
	        </div>
	</c:forEach>
	</div>
	</div>
	
	<div class="col-xs-12 col-md-8 col-md-offset-4">
	<%@ include file="page2.file"%>
	</div>
	
 
   <a id="to-top" href="#top" class="btn btn-dark btn-lg"><i class="fa fa-chevron-up fa-fw fa-1x"></i></a>
<%@ include file="/front-end/member_interface/script.file" %> 
  <script type="text/javascript">
  $(document).ready(function(){
    		  $(".btnReport").click(function(){
    		  $(".rpprForm").toggle();
    		  });
    		  
    		});
  </script>
</body>
</html>
