<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.prod.model.*,com.user.model.*"%>
<%
	Integer prod_id = new Integer(request.getParameter("prod_id"));
	UserVO userVO = (UserVO) session.getAttribute("userVO");
	String account = (String) session.getAttribute("account");
	pageContext.setAttribute("prod_id", prod_id);
%>



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
   

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<title>檢舉商品</title>




<style type="text/css">
.ad {
	width: 550px;
	height: 330px;
	background: url(../img/slidebg07.jpg) center center scroll;
	position: relative;
	left: 0px;
	top: 150px;
	border-radius: 25px;
	
}
.form-group{
padding:5px;

}

label {
	background-color: #ffffff;
}
</style>
</head>
<body>
<%@include file="/front-end/member_interface/headerBar.file"%>
	<%
		long seconds = new java.util.Date().getTime();
	%>
	<div class="container ad ">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2 class="panel-title">請輸入檢舉內容與標題</h2>
				</div>

			
			<div class="col-xs-12 col-md-6 col-md-offset-3">
				<form role="form"
					action="<%=request.getContextPath()%>/front-end/report/rppr.do"
					method="post">
					<div class="form-group">
						<input type="text"
							name="rppr_tittle" class="form-control" id="inputTittle"
							placeholder="輸入檢舉標題">
					</div>
					<br>
					<div class="form-group">
						
						<textarea class="form-control" name="rppr_content"
							id="inputReason" rows="5" cols="35" placeholder="輸入檢舉內容"> </textarea>
					</div>

					<input type="hidden" name="prod_id" value="${prod_id}">
					<!--${prodVO.prod_id} -->
					<input type="hidden" name="user_id" value="${userVO.user_id}">
					<!--${userVO.user_id} -->
					<input type="hidden" name="rppr_date" value="<%=seconds%>">
					<input type="hidden" name="action" value="ADDTOReport">
					<button type="submit" class="btn btn-default">送出</button>
				</form>
			</div>
			</div>
		</div>
	</div>


<%@ include file="/front-end/member_interface/script.file" %>


</body>
</html>