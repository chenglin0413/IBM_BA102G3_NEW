<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link href="<%=request.getContextPath()%>/front-end/css/jquery-ui.css" rel="stylesheet">
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
        
        .restBox{
			width: 240px;
			position: relative;
			float: left;
			margin: 10px;
		}
		.restName{
		    background-color: #fcf8e3;
		    width: 280px;
		    padding: 6px;
		    text-align: center;
		    color: #26234f;
		    position: absolute;
		    left: -20px;
		    top: -14px;
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
<%@include file="headerBar.file" %>
	   
<div class="container">
    <div class="row">
        <div class="col-md-11 col-xs-12" >
            <h3></h3>
        </div>         
    </div>
</div>	   
	   
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
			<fmt:formatNumber type="number" value="${(RestVO.rest_score)/(RestVO.rest_count)}" maxFractionDigits="0" var="num"/>
				
				<div class="col-xs-12 col-md-4">
					<div>
						<c:forEach var="repiVO" items="${repiSvc.all}">
							<c:if test="${RestVO.rest_id==repiVO.rest_id}">
				           		<div class="restBox">  	
				             		<p class="restName">
				 					${RestVO.rest_name}
									 ${num}<span style="font-size:14px;">.0</span><img src="<%=request.getContextPath()%>/front-end/member_interface_rest/rest/images/star.png">
				             		</p>
									<a  href="<%=request.getContextPath()%>/front-end/rest/rest.do?rest_id=${RestVO.rest_id}&action=getOne_For_Display_formember">
										<img  class="img-thumbnail" src="<%= request.getContextPath()%>/front-end/restaurant/repi/DBGifReader_repi.do?repi_id=${repiVO.repi_id}" width="400px">
									</a>
								</div>	
						    </c:if>
						</c:forEach>
					</div>	
							
           		</div>
			</c:forEach>
		</div><!-- row-->
	</div><!-- 餐廳 -->
	
	
<%@ include file="page2.file" %>
	
<%@ include file="/front-end/member_interface/script.file" %>	
<script src="<%= request.getContextPath() %>/front-end/js/jquery-1.9.1.js"></script>
<script src="<%= request.getContextPath() %>/front-end/js/jquery-ui.js"></script>



</body>
</html>
