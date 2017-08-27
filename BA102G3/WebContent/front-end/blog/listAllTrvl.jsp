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
        	width:600px;
        	height:auto;
        }
  		#trvl_tittle{
        	position: relative;
        }
        #user_information {
        	position: absolute;
        	margin-left:20px;
    		bottom: 0;
    		font-size:15px;
        }
  	
  		    #custom-search-form {
        margin:0;
        margin-top: 5px;
        padding: 0;
    }
 
    #custom-search-form .search-query {
        padding-right: 3px;
        padding-right: 4px \9;
        padding-left: 3px;
        padding-left: 4px \9;
        /* IE7-8 doesn't have border-radius, so don't indent the padding */
 
        margin-bottom: 0;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
	
	
	
	.search-form .form-group {
  float: right !important;
  transition: all 0.35s, border-radius 0s;
  width: 32px;
  height: 32px;
  background-color: #fff;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
  border-radius: 25px;
  border: 1px solid #ccc;
}
.search-form .form-group input.form-control {
  padding-right: 20px;
  border: 0 none;
  background: transparent;
  box-shadow: none;
  display:block;
}
.search-form .form-group input.form-control::-webkit-input-placeholder {
  display: none;
}
.search-form .form-group input.form-control:-moz-placeholder {
  /* Firefox 18- */
  display: none;
}
.search-form .form-group input.form-control::-moz-placeholder {
  /* Firefox 19+ */
  display: none;
}
.search-form .form-group input.form-control:-ms-input-placeholder {
  display: none;
}
.search-form .form-group:hover,
.search-form .form-group.hover {
  width: 100%;
  border-radius: 4px 25px 25px 4px;
}
.search-form .form-group span.form-control-feedback {
  position: absolute;
  top: -1px;
  right: -2px;
  z-index: 2;
  display: block;
  width: 34px;
  height: 34px;
  line-height: 34px;
  text-align: center;
  color: #3596e0;
  left: initial;
  font-size: 14px;
  margin:0;
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


  <section class="publicaciones-blog-home">
      <div class="container">
          <h2>
			    <div class="row">
			        <div class="col-md-12 col-md-offset-3">
			            <form ACTION="<%=request.getContextPath()%>/front-end/trvl/trvl.do" method="post" class="search-form">
			                <div class="col-md-6">
				                <div class="form-group has-feedback">
				            		<label for="search" class="sr-only">Search</label>
				            		<input type="text" class="form-control" name="trvl_loc" id="search" placeholder="地點收尋">
				              		<input type="hidden" name="action" value="listTrvls_ByCompositeQuery" />
				              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
				            	</div>
				            </div>
			            </form>
			        </div>
			    </div>
          </h2>
        <div class="row">
          <div class="row-page row">
          <%@ include file="page/page1.file"%>
			<c:forEach var="trvlVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" step="1" varStatus="s">
          
			    <div class="col-page col-sm-4 col-md-4">
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
                	<p id="user_information">${userSvc.getOneUser(trvlVO.user_id).user_account}
	               	 	<small>(${trvlVO.trvl_date})</small>
	               	 	<img src="<%=request.getContextPath()%>/front-end/blog/img/footstep.png" width="40">
	               	 	${trvlVO.trvl_count}
                	</p>
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
		<p><%@include file="page/page2.file"%></p>
	</div>
</div>	
	
	

	
<%@ include file="/front-end/member_interface/script.file" %>	
<script src="<%= request.getContextPath() %>/front-end/js/jquery-1.9.1.js"></script>

<script type="text/javascript">
	
	
$(function(){
    var len = 100; // 超過len個字以"..."取代
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