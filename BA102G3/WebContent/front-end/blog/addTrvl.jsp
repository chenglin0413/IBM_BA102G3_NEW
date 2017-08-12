<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trvl.model.*,com.user.model.*"%>

<!-- 	UserVO userVO = (UserVO) session.getAttribute("userVO"); 會員id --> 
<%	
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
	Integer user_id =userVO.getUser_id();//取得會員ID
	pageContext.setAttribute("user_id", user_id);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Anytime Grip</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/front-end/blog/css/stylish-portfolio.css" rel="stylesheet">
	
    <!-- Custom Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<style type="text/css">
        .item img{
            height: 250px;
            width:100%;
            
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
			      
    </style>

</head>

<body>
 <div class="callout"></div>
    <%@include file="/front-end/member_interface/headerBar.file" %>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>	

<div class="container">
    <div class="row">
        <div class="col-md-11 col-xs-12" >
            <h3>_</h3>
        </div>         
    </div>
</div>


<div class="callout"></div>

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
           
           

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<%	
	java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
%>	
	<div class="container">
		<div class="row">
			<form class="form-horizontal" enctype="multipart/form-data" method="POST" action="<%=request.getContextPath()%>/front-end/trvl/trvl.do" name="form1">
			 	<div class="col-xs-12 col-md-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title"><strong>遊記標題</strong></h3>
						</div>
						<div class="panel-body">
							<input type="text" name="trvl_tittle" class="form-control">
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
						<h3 class="panel-title"><strong>遊記地點</strong></h3>
					</div>
					<div class="panel-body">
						<input type="text" name="trvl_loc" class="form-control">
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-4">
						<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title"><strong>遊記日期</strong></h3>
						</div>
						<div class="panel-body">
							<input type="text" name="trvl_date" value="<%=date_SQL%>">
						</div>
					</div>
				</div>
		<input type="file" name="upfile" value="">
			<div class="col xs-12 col-md-8 col-md-offset-2">	
				<img class="img-thumbnail">
			</div>	
	<br>	
		<div class="col-xs-12 col-md-12">	
			<textarea name="trvl_content"></textarea>
		</div>
			<div class="col-xs-12 col-md-push-12">	
				<input type="hidden" name="user_id" value="${user_id}">   <!-- ${userVO.user_id} -->
				<input type="hidden" name="action" value="insert">
				<input type="submit" class="btn btn-primary btn-sm btn" value="確認上傳遊記">
			</div>	
			</form>
		</div>	<!-- row -->	
	</div>  <!-- container -->

<script>
 CKEDITOR.replace('trvl_content', { //關閉編輯器文末<p>
     uiColor: '#0066FF',
     enterMode:CKEDITOR.ENTER_BR,
     shiftEnterMode: CKEDITOR.ENTER_BR, 
 }); 
</script>




</body>
</html>