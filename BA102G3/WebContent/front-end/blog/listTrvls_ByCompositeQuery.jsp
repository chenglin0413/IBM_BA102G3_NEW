<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trvl.model.*"%>

<jsp:useBean id="listTrvls_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="trpiSvc" scope="page" class="com.trpi.model.TrpiService" />
<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService"/>  
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
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,100italic,100,300,300italic,900italic,900,700,700italic,500italic,500,400italic" rel="stylesheet" type="text/css">
	<link href="<%= request.getContextPath() %>/front-end/blog/listAllTrvl.css" rel="stylesheet">
	
    <title>遊記瀏覽</title>

	<style type="text/css">
       	body {
			background-image: url('<%=request.getContextPath()%>/front-end/blog/img/28.jpg');
			background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-size: cover;
		}
       
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
        
        .pic {
        	border-top-left-radius:1em;
        	border-top-right-radius:1em;
        	width:500px;
        	height:auto;
        }
  		#trvl_tittle{
        	position: relative;
        }
        #user_information {
        	position: absolute;
        	margin-left:0;
    		bottom: 0;
        }
  		#textSearch{
			 border-radius: 999px;
			 width:150px;
 			 height: 30px;
			 box-sizing: border-box;
			 font-family: courier;
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
               <li>
               		<a href="<%=request.getContextPath() %>/front-end/blog/listAllTrvl.jsp">遊記瀏覽</a>
               </li>
               <li class="active">收尋結果
               </li>
               
           </ol>
  	</div>
</div>         
 <div class="container">	
  		<div class="row">
  			<form id="serchForm" ACTION="<%=request.getContextPath()%>/front-end/trvl/trvl.do" method="post">
	  		     <div class="col-sm-6 col-md-2">
	  				<input type="text" placeholder="地點蒐尋" name="trvl_loc" id="textSearch"/>
	    		</div> 
	    		 <div class="col-sm-6 col-md-2">
	  				<input type="text" placeholder="遊記標題蒐尋" name="trvl_tittle" id="textSearch"/>
	    		</div> 
	  		     <div class="col-sm-6 col-md-1">
	  		     	<input type="hidden" name="action" value="listTrvls_ByCompositeQuery"  />
	  		     	<input type="submit" value="查詢" class="btn btn-primary btn-sm btnSearch" />
	    		</div>
	    	</form>	
    	</div>
	</div>  
<%@ include file="page/page1_ByCompositeQuery.file"%>
  <section class="publicaciones-blog-home">
      <div class="container">
          <h2></h2>
        <div class="row">
          <div class="row-page row">
			<c:forEach var="trvlVO" items="${listTrvls_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" step="1" varStatus="s">
          
			    <div class="col-page col-sm-4 col-md-3">
              		<a href="<%=request.getContextPath()%>/front-end/trvl/trvl.do?trvl_id=${trvlVO.trvl_id}&action=getOne_For_Display"  class="fondo-publicacion-home">
                <div class="img-publicacion-home">
                  	<c:forEach var="trpiVO" items="${trpiSvc.all}">
						<c:if test="${trvlVO.trvl_id==trpiVO.trvl_id}">
							<img class="img-responsive img-thumbnail pic" src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
						</c:if>
					</c:forEach>
                </div>
                  	<h3>${trvlVO.trvl_tittle}</h3>
                <div class="contenido-publicacion-home JQellipsis">
                 	 <p>${trvlVO.trvl_content}</p>
                </div>
                <p id="user_information">${userSvc.getOneUser(trvlVO.user_id).user_account}&nbsp;&nbsp;<small>(${trvlVO.trvl_date})</small></p>
                <div class="mascara-enlace-blog-home">
                  	<span>Read More</span>
                </div>
             		 </a>
              </div>
            </c:forEach>
         </div>
       </div>
    </div>
 </section>
<div class="row">
	<div class="col-sm-6 col-md-4 col-md-offset-4">
		<p><%@ include file="page/page2_ByCompositeQuery.file" %></p>
	</div>
</div>	
	
	


<%@ include file="/front-end/member_interface/script.file" %>	
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