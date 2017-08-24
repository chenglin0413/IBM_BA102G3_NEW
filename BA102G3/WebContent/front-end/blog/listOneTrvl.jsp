<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trvl.model.*,com.trpi.model.*,com.tlcm.model.*,com.rptl.model.*,com.user.model.*"%>
<%@ page import="java.util.*"%>



<%	if(session.getAttribute("userVO")!=null){
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
	Integer user_id =userVO.getUser_id();//取得會員ID
	pageContext.setAttribute("user_id",user_id);
}
	TrvlVO trvlVO = (TrvlVO) request.getAttribute("trvlVO");
%>
<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService"/>    
<jsp:useBean id="trvlSvc" scope="page" class="com.trvl.model.TrvlService"/>
<!DOCTYPE html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Anytime Grip</title>
    
    
    <!-- ckeditor  -->
	<script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
	
    <!-- Bootstrap Core CSS -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
   

    <title>瀏覽單篇</title>

	<style type="text/css">
		 body {
			background-image: url('<%=request.getContextPath()%>/front-end/blog/img/photo.jpeg');
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
        .bg-success {
        	text-align:center;
        }
        #rptlForm {
        	display:none;
        }
        .img-thumbnail{
        	width:800px;
        	border-radius:5px;
        }
       	.profilePic {
 			margin-left:15px;      		
       	}
       	.sidebar-module-inset > h4{
       		margin-left:40px;
       		background-color:
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
               <li class="active">瀏覽單篇遊記
               </li>
               
           </ol>
	</div>
</div>

<div class="container">
	<div class="blog-header">
       <h1 class="blog-title">${trvlVO.getTrvl_tittle()}</h1>
    </div>
	<div class="row">
    	<div class="col-sm-6 col-md-8  blog-main">
	    	<div class="blog-post">
	        <h2 class="blog-post-title">${trvlVO.getTrvl_loc()}</h2>
	        	<p class="blog-post-meta">
	       			<img src="<%=request.getContextPath()%>/front-end/blog/img/passage-of-time.png"> &nbsp ${trvlVO.getTrvl_date()} &nbsp by &nbsp
	       			<img src="<%=request.getContextPath()%>/front-end/blog/img/man-user.png">&nbsp<a href="#">${userSvc.getOneUser(trvlVO.user_id).user_account}</a>
	          	</p>
	        </div>
		<div>
			<c:forEach var="trpiVO" items="${trvlSvc.getTrpisByTrvlId(trvlVO.trvl_id)}">
				<img class="img-thumbnail" src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
			</c:forEach>
		</div>
		<br/>
		<div>
			<p>${trvlVO.getTrvl_content()}</p>
		</div><!-- /.blog-post -->
		<br>
		<br>
		<%	
			java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
		%>	
		<c:forEach var="tlcmVO" items="${trvlSvc.getTlcmsByTrvlId(trvlVO.trvl_id)}">
			<div class="panel panel-info">
				<div class="panel-heading">
				    <h3 class="panel-title" style="margin-left:0">
				    	<div class="row">
						    <div class="col-md-1" style="padding-right: 0px;">
							    <img class="img-responsive img-circle" width="50" height="50" src="<%= request.getContextPath() %>/front-end/user/userImg.do?user_id=${tlcmVO.user_id}">
						    </div>
						     <div class="col-md-8" style="margin:0 ,0 ,0, 0;"><strong>${userSvc.getOneUser(tlcmVO.user_id).user_account}</strong>
						    	<small>於${tlcmVO.tlcm_date}留言</small> &nbsp <img src="<%=request.getContextPath()%>/front-end/blog/img/pencil.png">
				   			 </div>
				    	</div>
				    </h3>
				</div>
				<div class="panel-body">
					${tlcmVO.tlcm_content}
				</div>
			</div>
		</c:forEach>
			<br>
			<br>
			<!-- 有登入才顯示留言框      -->
			<c:if test="${not empty userVO.user_account}" var="condition" scope="session" > 
                  <form method="POST" action="<%=request.getContextPath()%>/front-end/tlcm/tlcm.do" name="form1" onsubmit="return validateForm()">			
					<textarea name="tlcm_content" class="inputTextArea"></textarea>
					<br>
					<input type="hidden" name="trvl_id" value="${trvlVO.trvl_id}">			
					<input type="hidden" name="user_id" value="${user_id}">   <!-- ${UserVO.user_id} -->
					<input type="hidden" name="tlcm_date" value="<%=date_SQL%>">
					<input type="hidden" name="action" value="insert">
					<input type="submit" value="送出留言" class="btn-primary sendMessage">					
				</form>
			</c:if>	
			<c:if test="${empty userVO.user_account}" var="condition" scope="session" > 
            	<div class="bg-success">請先登入後在留言</div>
			</c:if>	
        </div><!-- /.blog-main -->
        
        
        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module sidebar-module-inset">
            <h4>About me</h4>
            <img class="img-responsive profilePic" src="<%= request.getContextPath() %>/front-end/user/userImg.do?user_id=${trvlVO.user_id}">
            <p>${userSvc.getOneUser(trvlVO.user_id).user_firstname}  ${userSvc.getOneUser(trvlVO.user_id).user_lastname}</p>
            <a href="#">${userSvc.getOneUser(trvlVO.user_id).user_email}</a>
            <p> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed.</p>
          </div>
          <div class="sidebar-module">
            <h4>Archives</h4>
            <ol class="list-unstyled">
              <li><a href="#">March 2014</a></li>
              <li><a href="#">February 2014</a></li>
              <li><a href="#">January 2014</a></li>
              <li><a href="#">December 2013</a></li>
              <li><a href="#">November 2013</a></li>
            </ol>
          </div>
          <div class="sidebar-module">
           <h4>	<img src="<%= request.getContextPath()%>/front-end/blog/img/handdown.png" width="32">Find and Follow</h4>
            <ol class="list-unstyled">
              <li><a href="#"><img src="<%= request.getContextPath() %>/front-end/blog/img/github.png" width="32">GitHub</a></li>
              <li><a href="#"><img src="<%= request.getContextPath() %>/front-end/blog/img/twitter.png" width="32">Twitter</a></li>
              <li><a href="#"><img src="<%= request.getContextPath() %>/front-end/blog/img/facebook.png" width="32">Facebook</a></li>
              <li><a href="#"><img src="<%= request.getContextPath() %>/front-end/blog/img/instagram.png" width="32">Instagram</a></li>
              <li>  								
              <%long seconds = new java.util.Date().getTime();%>
              
             		<!-- 檢舉btn,預設隱藏 -->
              		<c:if test="${not empty userVO.user_account}" var="condition" scope="session" > 
	              		<button class="btn-danger btn-xs btnReport">檢舉遊記</button>	
							<div class="form-horizontal" id="rptlForm">
								<div class="form-group ">
									<label class="col-sm-6 control-label">檢舉標題</label>
									<div class="col-sm-9">
										<input type="text" name="rptl_tittle"  id="rptlTittle" class="form-contrl" size="15" required>
									</div>										
								</div>
								
								<div class="form-group">
									<label class="col-sm-6 control-label">檢舉內容</label>
									<div class="col-sm-9">
										<textarea name="rptl_content" id="rptlContent" class="form-contrl" required></textarea>
									</div>										
								</div>
								<div class="text-center">
									<input type="hidden" name="trvl_id" id="trvlId" value="${trvlVO.trvl_id}">
									<input type="hidden" name="rptl_date" id="rptlDate" value="<%= seconds%>">
									<input type="hidden" name="action" id="action" value="insert">
									<input type="submit" class="btn btn-danger btn-sm sendReport" value="送出檢舉" >
								</div>
							</div>
						    <div class="comment">  
								<p id="resultJsonText"></p>  
						    </div>  
	              	</c:if>	
              	</li>
            </ol>
          </div>
        </div><!-- /.blog-sidebar -->
    </div><!-- /.row -->
</div><!-- /.container -->

 
<%@ include file="/front-end/member_interface/script.file" %>	

<script>

CKEDITOR.replace('tlcm_content', { //轉換編輯器文末<p>
  uiColor: '808080',
  enterMode:CKEDITOR.ENTER_BR,
  shiftEnterMode: CKEDITOR.ENTER_BR, 
}); 

function validateForm(){
	  if(CKEDITOR.instances.tlcm_content.getData()==""){
		  alert("留言請勿空白！");
		  return false;
	}else {
		
		return true;
	}
}


$(".sendReport").click(function() { 
 	$.ajax({ 
  		url : '<%=request.getContextPath()%>/front-end/report/rptl.do', 
  		type : 'POST', 
  		data : { 
	   		action :      "insert",
	   		trvl_id:      $('#trvlId').val(),
	   		rptl_date:	  $('#rptlDate').val(),
	   		rptl_tittle:  $('#rptlTittle').val(),
	   		rptl_content: $('#rptlContent').val(),
	  	}, 
  	
	  	dataType: "text",
	  	
	    success : function(msg) { 
	    	console.log(msg)
	    	$(".btnReport").hide();
	    	$("#rptlForm").hide();
	      	$("#resultJsonText").html(msg).css("color","red");
	     }, 
	    error : function(xhr) { 
	     	alert("ajax發生錯誤"); 
	     	alert(xhr.status);  
	    } 
	   }); 
  });


$(document).ready(function(){
  $(".btnReport").click(function(){
 	 $("#rptlForm").toggle();
  });
   
});

</script>

</body>
</html>