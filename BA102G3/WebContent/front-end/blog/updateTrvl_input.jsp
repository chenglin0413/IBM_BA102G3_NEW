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
    
    <link href="<%= request.getContextPath() %>/front-end/css/fileinput.min.css" rel="stylesheet">

    <link href="<%= request.getContextPath() %>/front-end/image/loading-sm.gif" rel="stylesheet">
    
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
                   <a href="<%=request.getContextPath() %>/front-end/blog/listAllTrvl.jsp">瀏覽遊記</a>
               </li>
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/blog/listAllByUser.jsp">個人遊記</a>
               </li>
               <li class="active">修改遊記
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
		<form class="form-horizontal" enctype="multipart/form-data" method="POST" action="<%=request.getContextPath()%>/front-end/trvl/trvl.do" name="form1" onsubmit="return validateForm()">
		 	<div class="col-xs-12 col-md-4">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title"><strong>遊記標題</strong></h3>
					</div>
					<div class="panel-body">
						<input type="text" name="trvl_tittle" class="form-control" value="${trvlVO.getTrvl_tittle()}" required>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="panel panel-warning">
					<div class="panel-heading">
					<h3 class="panel-title"><strong>遊記地點</strong></h3>
				</div>
				 <div class="panel-body">
					<input type="text" name="trvl_loc" class="form-control" value="${trvlVO.getTrvl_loc()}" required>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title"><strong>遊記日期</strong></h3>
					</div>
					<div class="panel-body">
						<input type="date" name="trvl_date" class="form-control" value="${trvlVO.getTrvl_date()}" required>
					</div>
				</div>
			</div>
		
		
<!-- 		<input type="file" name="upfile" accept="image/*" onchange="loadFile(event)">	 -->
		<input id="input-24" name="input24[]" type="file" multiple class="file-loading">	
			
			<div class="col xs-12 col-md-8 col-md-offset-2">
				<c:forEach var="trpiVO" items="${listTrpis}">
					<c:if test="${trvlVO.trvl_id==trpiVO.trvl_id}">
						<img class="img-thumbnail " id="imgPreview" src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
						<input type="hidden" name="trpi_id" value="${trpiVO.trpi_id}"> <!-- 遊記照片編號-->
					</c:if>
				</c:forEach>			
			</div>
			
			
		<br>
			<div class="col-xs-12 col-md-12">	
				<textarea name="trvl_content">${trvlVO.getTrvl_content()}</textarea>
			</div>		
				<br>
			<div class="col-xs-12 col-md-1 col-md-push-11">	
				<input type="hidden" name="trvl_date" value="${date_SQL}">
				<input type="hidden" name="trvl_id" value="${trvlVO.trvl_id}">
				<input type="hidden" name="action" value="update">
			</div>	
		</form>
	</div>		<!-- row -->	
</div>	<!-- container -->	

<%@ include file="/front-end/member_interface/script.file" %>
<script src="<%= request.getContextPath() %>/front-end/js/fileinput.min.js"></script>


<script>
$(document).on('ready', function() {
    $("#input-24").fileinput({
        initialPreviewAsData: true,
        browseOnZoneClick: true,
        deleteUrl: "/site/file-delete",
        overwriteInitial: false,
        maxFileSize: 1024,
    });
});
</script>

    	
<script>

CKEDITOR.replace('trvl_content', { //轉換編輯器文末Tag
     uiColor: '#F6C555',
     enterMode:CKEDITOR.ENTER_BR,
     shiftEnterMode: CKEDITOR.ENTER_BR, 
 }); 
 
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
  
  function validateForm(){
	  if(CKEDITOR.instances.trvl_content.getData()==""){
		  alert("留言請勿空白！");
		  return false;
	}else {
		return true;
	}
}
  
  $('#input-24').change(function(){
	  	$('#imgPreview').hide();
	});
</script>
   
    
    			
</body>
</html>