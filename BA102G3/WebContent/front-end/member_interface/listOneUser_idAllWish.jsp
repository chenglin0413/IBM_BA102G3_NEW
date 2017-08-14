<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.wish.model.*,com.wish.controller.*"%>
<%@ page import="com.prod.model.*,com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%if (session.getAttribute("userVO") != null) {
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
	Integer user_id =userVO.getUser_id();
    pageContext.setAttribute("user_id",user_id);
}

%>
<jsp:useBean id="wishSvc" scope="page" class="com.wish.model.WishService" />
<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>listOneUser_idAllItem</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
        <div class="callout"></div>
        <div class="col-md-11 col-xs-12" >
            <h3>追蹤商品</h3>
        </div>         
    </div>
</div>
    </div>
    <header id="myCarousel top" class="carousel slide">
    </header>
   
<div class="container content">
        
        <div class="row">
        <header  class="header">
        <div class="text-vertical-center">
            <h1>Anytime Grip</h1>
            <h3>SHOP OUR COLLECTIONS &amp; SHOP WITH RUNWAY</h3>
            <br>
            <a href="#about" class="btn btn-dark btn-lg">Grip Now!!</a>
        </div>
       </header> 
</div>
</div>
<div class="container">
<div class="row">        
          <ol class="breadcrumb">
               <li>
                   <a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a>
               </li>
               <li class="active">追蹤商品</a>
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

<div class="panel panel-default">
			  <div class="panel-title text-center "  >
						<div class="col-xs-12 col-sm-2">產品</div>
						<div class="col-xs-12 col-sm-2">產品編號</div>
						<div class="col-xs-12 col-sm-2">追蹤產品日期</div>
						<div class="col-xs-12 col-sm-4">產品名稱-描述</div>
						<div class="col-xs-12 col-sm-2">操作</div>
					

			</div>
			<c:forEach var="wishVO" items="${wishSvc.getOneUser_idAllWish(user_id)}" varStatus="s">
				  <div class="panel-body text-center">
			       		<!-- 內容 -->
			       		
			       		<div class="col-xs-12 col-sm-2">
  									<img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${wishVO.prod_id}" width="150" height="100">
  						</div>
						<div class="col-xs-12 col-sm-2">
							<c:forEach var="prodVO" items="${prodSvc.all}">
		                    <c:if test="${wishVO.prod_id==prodVO.prod_id}">
			                    	【<A HREF="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_id=${wishVO.prod_id}&action=getOne_For_Display">${wishVO.prod_id}</a>】
		                    </c:if>
              			  </c:forEach>
						</div>
						<div class="col-xs-12 col-sm-2">
							${wishVO.wish_date}
						</div>
						<div class="col-xs-12 col-sm-4">
							<c:forEach var="prodVO" items="${prodSvc.all}">
		                    <c:if test="${wishVO.prod_id==prodVO.prod_id}">
			                  	  【${prodVO.prod_name}-${prodVO.prod_descript}】
		                    </c:if>
		                </c:forEach>

						</div>
						<div class="col-xs-12 col-sm-2">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/wish/wish.do">
					    <input type="submit" value="移除">
					    <input type="hidden" name="user_id" value="${wishVO.user_id}">
					    <input type="hidden" name="prod_id" value="${wishVO.prod_id}">
					    <input type="hidden" name="action"	value="delete"></FORM>
					    </div>
						
			       		<!-- 內容結束 -->
			      </div>
			      <br>
			      </c:forEach>
		</div>



</div>
</div>
<div class="callout"></div>
 <a id="to-top" href="#top" class="btn btn-dark btn-lg"></a>

<%@ include file="/front-end/member_interface/script.file" %>
</body>
</html>
