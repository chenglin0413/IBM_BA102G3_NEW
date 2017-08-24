<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rest.model.*,com.repi.model.*"%>

<%
    RestService restSvc = new RestService();
    List<RestVO> list = restSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="repiSvc" scope="page" class="com.repi.model.RepiService"/>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

<title>所有餐廳資料 - listAllRest.jsp</title>

<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/front-end/css/stylish-portfolio.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">


<style type="text/css">
       
        .content: {
          position: relative;
        }
        .box{
          width: 110px;
          height: 50px;
          
          position: fixed;
          top: 52px;
          left: 5px;
          margin: auto;
        }
        
        .restPic {
            height: auto;
            width: 400px;
        }
    </style>


</head>
<body>
<%@include file="headerBar.file" %>
	   
<div class="container content">
        <div class="row">
        <ol class="breadcrumb">
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/index.jsp">首頁</a>
               </li>
               <li class="active">餐廳</a>
               </li>
               
           </ol>
     
     </div>       <!-- Pagination -->
 </div>        <!-- /.row -->
	 
	 
	 <!--橫幅廣告頁-->
	<div id="myCarousel" class="carousel slide">
    	<div class="container">
       		<div class="row carousel-holder">
                <div class="col-md-12">
                	<div class="row">
	                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	                        <div class="carousel-inner">
								<c:forEach var="repiVO" items="${repiSvc.all}" varStatus="s" begin="1" end="21" step="1">
									<c:if test="${s.count==1}">                      
			                            <div class="item active">
			                            <div class="row">
			                         </c:if>
			                     <c:if test="${s.count!=1 && s.count%4==1}">                      
			                          <div class="item">
			                          		<div class="row">
			                     </c:if>      
                                		<div class="col-md-3">
											<img class="img-thumbnail" src="<%= request.getContextPath()%>/front-end/restaurant/repi/DBGifReader_repi.do?repi_id=${repiVO.repi_id}">
                                		</div>
		                          <c:if test="${s.count%4==0}"> 		
		                                   	</div>
		                				</div>
                               		</c:if>   
                        		 </c:forEach>       
                     		</div>          
               			</div>
                     <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                         <span class="glyphicon glyphicon-chevron-left"></span>
                     </a>
                     <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                         <span class="glyphicon glyphicon-chevron-right"></span>
                     </a>
              </div>
          </div> 
      </div>   
      <!--------------- 廣告橫幅 ---------------->
	  
	  <div class="col-md-12 col-xs-12">
         <h2 class="text-center bg-info">Dining & Menu</h2>
      </div>
       
 	<!--------------- 餐廳 ---------------------->	
	<div class="container-fluid container">
		<div class="row">
			<%@ include file="page1.file" %> 
			<c:forEach var="RestVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="col-xs-12 col-md-4">
	              	<div class="item">
	              		<div class="row">
							<div class="col-xs-12 col-md-6">
								<c:forEach var="repiVO" items="${repiSvc.all}">
									<c:if test="${RestVO.rest_id==repiVO.rest_id}">
										<a href="<%=request.getContextPath()%>/front-end/rest/rest.do?rest_id=${RestVO.rest_id}&action=getOne_For_Display_formember">
											<img class="img-thumbnail restPic" src="<%= request.getContextPath()%>/front-end/restaurant/repi/DBGifReader_repi.do?repi_id=${repiVO.repi_id}">
										</a>
								   			<h4><label class=" bg-priamry">${RestVO.rest_name}</h4></label>
								   		</a>
								    </c:if>
								</c:forEach>
							</div>
							<div class="col-xs-12 col-md-6">
			                <P><img src="<%=request.getContextPath()%>/front-end/restaurant/rest/images/old-handphone.png"><strong class="text-left">電話 :</strong></P>
			                <P>${RestVO.rest_phone}</P>
			               	<P><img src="<%=request.getContextPath()%>/front-end/restaurant/rest/images/clock.png"><strong class="text-left">營業時間 :</strong></P>
			               	<P>${RestVO.rest_hours}</P>
		          		</div>
		          	</div>
	             </div>
           		</div>
			</c:forEach>
		</div><!-- row-->
	</div><!-- 餐廳 -->
	
<%@ include file="page2.file" %>
	
<%@ include file="/front-end/member_interface/script.file" %>	
<script src="<%= request.getContextPath() %>/front-end/js/stars.min.js"></script>




</body>
</html>
