<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trvl.model.*"%>
<%@ page import="com.trpi.model.*"%>
<%@ page import="java.util.*"%>
<%
	TrvlVO trvlVO = (TrvlVO) request.getAttribute("trvlVO");
	List<TrpiVO> listTrpis = (List<TrpiVO>) request.getAttribute("listTrpis");
%>
<!DOCTYPE html>
<head>

	<title>遊記修改 - updateTrvl_input.jsp</title>

  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>修改遊記</title>
	<!--  ckeditor JS -->
	<script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/front-end/blog/css/stylish-portfolio.css" rel="stylesheet">
	
    <!-- Custom Fonts -->
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
        
        .imgPreview {
        	width:600px;
        	height:auto;
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
		<form class="form-horizontal" enctype="multipart/form-data" method="POST" action="<%=request.getContextPath()%>/front-end/trvl/trvl.do" name="form1">
		 	<div class="col-xs-12 col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><strong>遊記標題</strong></h3>
					</div>
					<div class="panel-body">
						<input type="text" name="trvl_tittle" class="form-control" value="${trvlVO.getTrvl_tittle()}" required>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
					<h3 class="panel-title"><strong>遊記地點</strong></h3>
				</div>
				 <div class="panel-body">
					<input type="text" name="trvl_loc" class="form-control" value="${trvlVO.getTrvl_loc()}" required>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><strong>遊記日期</strong></h3>
					</div>
					<div class="panel-body">
						<input type="date" name="trvl_date" class="form-control" value="${trvlVO.getTrvl_date()}" required>
					</div>
				</div>
			</div>
		<input type="file" name="upfile" accept="image/*" onchange="loadFile(event)">	
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
				<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
				<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">
				<input type="hidden" name="action" value="update">
				<input type="submit" class="btn btn-primary btn-sm btn" value="確認修改上傳遊記">
			</div>	
		</form>
	</div>		<!-- row -->	
</div>	<!-- container -->	

<!-- 登入模組 開始-->
	<div class="modal fade" id="modal-login">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">會員登入</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
							
								<form action="<%= request.getContextPath() %>/front-end/UserLoginHandler" method="POST" id="loginForm">
									<div class="form-group">
										<label for="name">帳號</label> <input type="text"
											class="form-control" name="account" id="name"
											placeholder="請輸入您的帳號">
									</div>
									<div class="form-group">
										<label for="pw">密碼</label> <input type="password"
											class="form-control" name="password" id="pw" placeholder="請輸入您的密碼">
									</div>
									
									<div>
										<a href="#">忘記密碼</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/front-end/user/newMember.jsp">加入會員</a>
									</div>
									
									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

								</form>
								
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="login" type="button" class="btn btn-primary">登入</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-sign-in">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">新生註冊</h4>
				</div>
				<div class="modal-body">註冊表單</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary">註冊</button>
				</div>
			</div>
		</div>
	</div>
<!-- 登入模組 結束-->	
 
<script>
   	CKEDITOR.replace('trvl_content', { //關閉編輯器文末<p>
        uiColor: '#9AB8F3',
        enterMode:CKEDITOR.ENTER_BR,
        shiftEnterMode: CKEDITOR.ENTER_BR, 
    }); 
</script>
<!-- jQuery -->
    <script src="<%= request.getContextPath() %>/front-end/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/front-end/js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
<script type="text/javascript">
//登入模組        
	var $userName = $("#account");
	var $password = $("#password");
	$("#login").click(function() {
		$.ajax({
			url : '<%= request.getContextPath() %>/front-end/UserLoginHandler',
			data : {
				name : $userName.val(),
				password : $password.val()
			},
			type : 'POST',
			error : function(xhr) {
				alert('Ajax request 發生錯誤');
			},
			success : function(result) {
				if (result == '帳號不存在') {
					console.log("帳號或密碼有錯");
				} else {
					console.log("123456");
					$("#loginForm").submit();
				}
			}
		});
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
	</script>
    
    
    			
</body>
</html>