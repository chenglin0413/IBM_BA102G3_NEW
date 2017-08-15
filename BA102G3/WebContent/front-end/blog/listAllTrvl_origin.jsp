<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trvl.model.*"%>
<%
	TrvlService trvlSvc = new TrvlService();
	List<TrvlVO> list = trvlSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>
<jsp:useBean id="trpiSvc" scope="page" class="com.trpi.model.TrpiService" />

<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Anytime Grip</title>

    <!-- Bootstrap Core CSS -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>遊記瀏覽</title>

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
        
        .overflowBox {
			padding: 5px;
		}

		.ellipsis {
			overflow:hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		.aReadMore {
			margin-left:450px;
		}
		
		.pic {
			width:250px;
			height:auto;
		}
		      
    </style>
</head>
<body>

<%@include file="/front-end/member_interface/headerBar.file" %>

<div class="container">
    <div class="row">
        <div class="col-md-11 col-xs-12" >
            <h3></h3>
        </div>         
    </div>
</div>



 <div class="container">	
	<div class="row ">         
            
       
        
          <ol class="breadcrumb">
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/index.jsp">首頁</a>
               </li>
               <li class="active">遊記瀏覽
               </li>
               
           </ol>
  	</div>
</div>         
           
           	
	<div class="container-fluid" >	
		<div class="row">
			<div class="col-md-10 col-md-push-1">
				<ul>   			
					<%@ include file="page1.file"%>
						<c:forEach var="trvlVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
							<li>
								<div class="row">
									<div class="col-md-4">
										<c:forEach var="trpiVO" items="${trpiSvc.all}">
											<c:if test="${trvlVO.trvl_id==trpiVO.trvl_id}">
												<img class="img-thumbnail pic" src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
						                    </c:if>
										</c:forEach>
									</div>
									<div class="col-md-8 relative" >												
										 <table class="table">
										  	<tr>
										  		<th class="sm-info"><h4>${trvlVO.trvl_tittle}</h4></th> 
										  	</tr>
										  	<tr>	
										  		<td>
										  			<div class="overflowBox">
														<p class="JQellipsis">${trvlVO.trvl_content}</p>
														<a class="btn-lg" href="<%=request.getContextPath()%>/front-end/trvl/trvl.do?trvl_id=${trvlVO.trvl_id}&action=getOne_For_Display">
															<img class="aReadMore" src="<%=request.getContextPath()%>/front-end/blog/img/forward-arrow.png">&nbsp Read more
														</a>
													</div>
										  		</td>
											</tr>
			
										 </table>
									</div> 
								</div>	
							</li>  <!-- 	一篇文章		 -->
						</c:forEach>
					</ul>
					<div class="row">
						<div class="col-sm-6 col-md-8 col-md-offset-4">
							<p><%@include file="page2.file"%></p>
						</div>
					</div>	
				</div>
			</div> <!-- /.row -->
		</div> <!-- /.container -->
	
	
	
	
 <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/front-end/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/front-end/js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
<script type="text/javascript">

	
	$(function(){
	    var len = 80; // 超過len個字以"..."取代
	    $(".JQellipsis").each(function(i){
	        if($(this).text().length>len){
	            $(this).attr("title",$(this).text());
	            var text=$(this).text().substring(0,len-1)+"...";
	            $(this).text(text);
	        }
	    });
	});
	
    </script>
</body>
</html>