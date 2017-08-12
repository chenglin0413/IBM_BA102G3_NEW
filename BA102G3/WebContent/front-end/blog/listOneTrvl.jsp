<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trvl.model.*,com.trpi.model.*,com.tlcm.model.*,com.user.model.*"%>
<%@ page import="java.util.*"%>

<%	if(session.getAttribute("userVO")!=null){
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
	Integer user_id =userVO.getUser_id();//取得會員ID
	pageContext.setAttribute("user_id",user_id);
}
	
	TrvlVO trvlVO = (TrvlVO) session.getAttribute("trvlVO");
	List<TrpiVO> listTrpis = (List<TrpiVO>)session.getAttribute("listTrpis");
	List<TlcmVO> listTlcms = (List<TlcmVO>) session.getAttribute("listTlcms");
%>

<html>
<head>
<title>AnyTimeGrip</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>



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
   

    <title>瀏覽單篇</title>

    <!-- Custom Fonts -->
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

<div class="callout"></div>
<div class="container">
    <div class="row">
        <div class="col-md-11 col-xs-12" >
            <h3>_</h3>
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


    <div class="container">
      <div class="blog-header">
        <h1 class="blog-title">${trvlVO.getTrvl_tittle()}</h1>
      </div>
      <div class="row">
        <div class="col-sm-8 blog-main">
        	<div class="blog-post">
          		<h2 class="blog-post-title">${trvlVO.getTrvl_loc()}</h2>
          		<p class="blog-post-meta"><img src="<%=request.getContextPath()%>/front-end/blog/img/agenda.png"> &nbsp ${trvlVO.getTrvl_date()} &nbsp by &nbsp<a href="">${trvlVO.getUser_id()}</a></p>
        	</div>
			<div><!-- /.blog-post -->
				<c:forEach var="trpiVO" items="${listTrpis}">
					<c:if test="${trvlVO.trvl_id==trpiVO.trvl_id}">
						<img class="img-thumbnail" src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
					</c:if>
				</c:forEach>
			</div>
			<div><p>${trvlVO.getTrvl_content()}</p></div>
		<br>
		<br>
		<br>
		<br>
			<%	
				java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
			%>	
			<c:forEach var="tlcmVO" items="${listTlcms}">
				<div class="panel panel-info">
					<div class="panel-heading">
					    <h3 class="panel-title"><img src="<%=request.getContextPath()%>/front-end/blog/img/batman.png">${tlcmVO.user_id}<small>於${tlcmVO.tlcm_date}留言</small></h3>
					</div>
					<div class="panel-body">
						${tlcmVO.tlcm_content}
					</div>
				</div>
			</c:forEach>
			<br>
			<br>
			<c:if test="${not empty userVO.user_account}" var="condition" scope="session" > 
                    	<form method="POST" action="<%=request.getContextPath()%>/front-end/tlcm/tlcm.do" name="form1">			
							<textarea name="tlcm_content"></textarea>
							<br>
							<input type="hidden" name="trvl_id" value="${trvlVO.trvl_id}">			
							<input type="hidden" name="user_id" value="${user_id}">   <!-- ${UserVO.user_id} -->
							<input type="hidden" name="tlcm_date" value="<%=date_SQL%>">
							<input type="hidden" name="action" value="insert">
							<input type="submit" value="送出留言" class="btn-primary sendMessage">					
						</form>
			</c:if>	
		<br>
		<br>
		<br>
		<br>
        </div><!-- /.blog-main -->
 <jsp:useBean id="trvlSvc" scope="page" class="com.trvl.model.TrvlService" />             
        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module">
          <h2><img src="<%=request.getContextPath()%>/front-end/blog/img/top-games-star.png">人氣遊記</h2>
            <ol class="list-unstyled">
             <c:forEach var="trvlVO" items="${trvlSvc.getTopTrvlCounts()}">
					<li>
						<br>
							<a href="<%=request.getContextPath()%>/front-end/trvl/trvl.do?trvl_id=${trvlVO.trvl_id}&action=getOne_For_Display">
							<h4 class="post-body"><img src="<%=request.getContextPath()%>/front-end/blog/img/tag-black-shape.png"><em>${trvlVO.trvl_tittle}</em></h4>
							</a>
					</li>
				</c:forEach>
            </ol>
          </div>
        </div><!-- /.blog-sidebar -->
      </div><!-- /.row -->
    </div><!-- /.container -->
</div>
</div>

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

CKEDITOR.replace('tlcm_content', { //轉換編輯器文末<p>
  uiColor: '808080',
  enterMode:CKEDITOR.ENTER_BR,
  shiftEnterMode: CKEDITOR.ENTER_BR, 
}); 

</script>

</body>
</html>