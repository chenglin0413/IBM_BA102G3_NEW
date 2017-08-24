<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trvl.model.*,com.user.model.*"%>
<%	
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
	Integer user_id =userVO.getUser_id();//取得會員ID
	pageContext.setAttribute("user_id", user_id);
	TrvlVO trvlVO = (TrvlVO)request.getAttribute("addTrvl");
%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Anytime Grip</title>
	 <!-- ckeditor JS -->
	<script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
    <!-- Bootstrap Core CSS -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
   

    <title>個人遊記</title>

	<style type="text/css">
		body {
			background-image: url('<%= request.getContextPath()%>/front-end/blog/img/typewriter.jpg');
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
        
        .imgPreview {
        	width:400px;
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
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/blog/listAllByUser.jsp">瀏覽遊記</a>
               </li>
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/blog/listAllByUser.jsp">個人遊記</a>
               </li>
               <li class="active">新增遊記
               </li>
               
           </ol>
       </div>    
  </div>    
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<font color='red'><li>${message}</li></font>
		</c:forEach>
	</ul>
</c:if>	
    
          
<%	
	java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
%>	
	<div class="container">
		<div class="row">
			<form class="form-horizontal" enctype="multipart/form-data" method="POST"  action="<%=request.getContextPath()%>/front-end/trvl/trvl.do" name="form1" onsubmit="return validateForm()">
			 	<div class="col-xs-12 col-md-3 col-md-offset-1">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<h3 class="panel-title"><strong>遊記標題</strong></h3>
						</div>
						<div class="panel-body">
							<input type="text" name="trvl_tittle" class="form-control title" required>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-3">
					<div class="panel panel-warning">
						<div class="panel-heading">
						<h3 class="panel-title"><strong>遊記地點</strong></h3>
					</div>
					<div class="panel-body">
						<input type="text" name="trvl_loc" class="form-control loc"  required >
					</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-3">
						<div class="panel panel-warning">
						<div class="panel-heading">
							<h3 class="panel-title"><strong>遊記日期</strong></h3>
						</div>
						<div class="panel-body">
							<input type="date" name="trvl_date" value="<%=date_SQL%>">
						</div>
					</div>
				</div>
			<input type="file" name="upfile" accept="image/*" onchange="loadFile(event)">
			<div class="col xs-12 col-md-9 col-md-offset-1">	
				<img class="img-thumbnail" id="imgPreview">
			</div>	
		<br>	
			<div class="col-xs-12 col-md-9 col-md-offset-1">	
				<textarea name="trvl_content"  class="content" required></textarea>
			</div>
			<div class="col-xs-12 col-md-push-10">	
		
				<input type="hidden" name="action" value="insert">
				<input type="submit" class="btn btn-success btn-sm btn" value="發布遊記">
			</div>
			</form>
		</div>	<!-- row -->	
	</div>  <!-- container -->
	
	
<%@ include file="/front-end/member_interface/script.file" %>


<script>
 CKEDITOR.replace('trvl_content', { //轉換編輯器文末Tag
     uiColor: '#F6C555',
     enterMode:CKEDITOR.ENTER_BR,
     shiftEnterMode: CKEDITOR.ENTER_BR, 
 }); 
 
 
 function validateForm(){
	  if(CKEDITOR.instances.trvl_content.getData()==""){
		  alert("留言請勿空白！");
		  return false;
	}else {
		
		return true;
	}
}
 
</script>

<script>
  var loadFile = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('imgPreview');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  };
  
 
</script>



</body>
</html>