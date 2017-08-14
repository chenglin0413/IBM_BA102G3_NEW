<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trvl.model.*"%>
<%@ page import="com.trpi.model.*"%>
<%@ page import="java.util.*"%>
<%
	TrvlVO trvlVO = (TrvlVO) request.getAttribute("trvlVO");
	List<TrpiVO> listTrpis = (List<TrpiVO>) request.getAttribute("listTrpis");
%>
<html>
<head>

	<title>遊記修改 - updateTrvl_input.jsp</title>
	<script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>

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
   

    <title>修改遊記</title>

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

<%@include file="/front-end/member_interface/headerBar.file" %>


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
                   <a href="<%=request.getContextPath() %>/front-end/blog/listAllTrvl.jsp">瀏覽遊記</a>
               </li>
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/blog/listAllByUser.jsp">個人遊記</a>
               </li>
               <li class="active">修改遊記
               </li>
               
           </ol>




<h3>資料修改:</h3>
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
						<input type="text" name="trvl_tittle" class="form-control" value="${trvlVO.getTrvl_tittle()}">
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
					<h3 class="panel-title"><strong>遊記地點</strong></h3>
				</div>
				 <div class="panel-body">
					<input type="text" name="trvl_loc" class="form-control" value="${trvlVO.getTrvl_loc()}">
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><strong>遊記日期</strong></h3>
					</div>
					<div class="panel-body">
						<input type="date" name="trvl_date" class="form-control" value="${trvlVO.getTrvl_date()}">
					</div>
				</div>
			</div>
		<input type="file" name="upfile" value="">	
			<div class="col xs-12 col-md-8 col-md-offset-2">
				<c:forEach var="trpiVO" items="${listTrpis}">
					<c:if test="${trvlVO.trvl_id==trpiVO.trvl_id}">
						<img class="img-thumbnail" src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
						<input type="hidden" name="trpi_id" value="${trpiVO.trpi_id}"> <!-- 遊記照片編號-->
					</c:if>
				</c:forEach>			
			</div>
		<br>
			<div class="col-xs-12 col-md-12">	
				<textarea name="trvl_content">${trvlVO.getTrvl_content()}</textarea>
			</div>		
				<br>
			<div class="col-xs-12 col-md-push-12">	
				<input type="hidden" name="user_id" value="${trvlVO.getUser_id()}">
				<input type="hidden" name="trvl_date" value="${date_SQL}">
				<input type="hidden" name="trvl_id" value="${trvlVO.trvl_id}">
				<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
				<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">
				<input type="hidden" name="action" value="update">
				<input type="submit" class="btn btn-primary btn-sm btn" value="確認上傳遊記">
			</div>	
		</form>
	</div>		<!-- row -->	
</div>	<!-- container -->	
		<div class="callout"></div>

<%@ include file="/front-end/member_interface/script.file" %>
 
<script>
   	CKEDITOR.replace('trvl_content', { //關閉編輯器文末<p>
        uiColor: '#9AB8F3',
        enterMode:CKEDITOR.ENTER_BR,
        shiftEnterMode: CKEDITOR.ENTER_BR, 
    }); 
</script>
			
</body>
</html>