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
 
    <%@include file="headerBar.file" %>
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
   
   <div class="hero-text">
            <h1 >Anytime login, anytime Grip</h1>
            <p>Enjoy your life with our service </p>
    </div>
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

<table border='1' bordercolor='#CCCCFF' width='100%'>
	<tr>
		<th>會員性名-地址</th>
		<th>產品編號</th>
		<th>追蹤產品日期</th>
		<th>產品名稱-描述</th>
		<th>操作</th>
	</tr>
	<c:forEach var="wishVO" items="${wishSvc.getOneUser_idAllWish(user_id)}" varStatus="s">
		<tr align='center' valign='middle'>
		<td><c:forEach var="userVO" items="${userSvc.all}">
                    <c:if test="${wishVO.user_id==userVO.user_id}">
	                    	【${userVO.user_lastname}-${userVO.user_firstname}-${userVO.user_address}】
                    </c:if>
                </c:forEach>
		</td>
		
		<td><c:forEach var="prodVO" items="${prodSvc.all}">
                    <c:if test="${wishVO.prod_id==prodVO.prod_id}">
	                    	【<A HREF="javascript:presses${s.index}()">${wishVO.prod_id}</a>-${prodVO.prod_name}】
                    </c:if>
                </c:forEach>
		</td>
		<td>${wishVO.wish_date}</td>
		
		<td><c:forEach var="prodVO" items="${prodSvc.all}">
                    <c:if test="${wishVO.prod_id==prodVO.prod_id}">
	                  	  【${prodVO.prod_name}-${prodVO.prod_descript}】
                    </c:if>
                </c:forEach>
		</td>
	   <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/wish/wish.do">
			    <input type="submit" value="移除">
			    <input type="hidden" name="user_id" value="${wishVO.user_id}">
			    <input type="hidden" name="prod_id" value="${wishVO.prod_id}">
			    <input type="hidden" name="action"	value="delete"></FORM>
			    
	  </td>
		</tr>
		 <script>
         function presses${s.index}(){
        	 document.open("<%=request.getContextPath()%>/front-end/wish/wish.do?prod_id=${wishVO.prod_id}&action=getOne_From05", "" ,"height=700,width=700,left=65,top=157,resizable=yes,scrollbars=yes");
         }
		</script>
	</c:forEach>
</table>


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
							
								<form action="<%=request.getContextPath()%>/front-end/UserLoginHandler" method="POST" id="loginForm">
									<div class="form-group">
										<label for="name">帳號</label> <input type="text"
											class="form-control" name="account" id="name"
											placeholder="請輸入您的帳號">
									</div>
									<div class="form-group">
										<label for="pw">密碼</label> <input type="password"
											class="form-control" name="password" id="pw" placeholder="請輸入您的密碼">
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

</div>
</div>
<div class="callout"></div>
 <a id="to-top" href="#top" class="btn btn-dark btn-lg"></a>
 
    <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
   
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.0.min.js"></script> 
<script>
//easy-sidebar-toggle-right
$('.easy-sidebar-toggle').click(function(e) {
e.preventDefault();
//$('body').toggleClass('toggled-right');
$('body').toggleClass('toggled');
//$('.navbar.easy-sidebar-right').removeClass('toggled-right');
$('.navbar.easy-sidebar').removeClass('toggled');
});
</script>
     <!-- Menu Toggle Script -->
   <script>
    // Closes the sidebar menu
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    // Opens the sidebar menu
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    // Scrolls to the selected menu item on the page
    $(function() {
        $('a[href*=#]:not([href=#],[data-toggle],[data-target],[data-slide])').click(function() {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });
    });
    //#to-top button appears after scrolling
    var fixed = false;
    $(document).scroll(function() {
        if ($(this).scrollTop() > 250) {
            if (!fixed) {
                fixed = true;
                // $('#to-top').css({position:'fixed', display:'block'});
                $('#to-top').show("slow", function() {
                    $('#to-top').css({
                        position: 'fixed',
                        display: 'block'
                    });
                });
            }
        } else {
            if (fixed) {
                fixed = false;
                $('#to-top').hide("slow", function() {
                    $('#to-top').css({
                        display: 'none'
                    });
                });
            }
        }
    });
    // Disable Google Maps scrolling
    // See http://stackoverflow.com/a/25904582/1607849
    // Disable scroll zooming and bind back the click event
    var onMapMouseleaveHandler = function(event) {
        var that = $(this);
        that.on('click', onMapClickHandler);
        that.off('mouseleave', onMapMouseleaveHandler);
        that.find('iframe').css("pointer-events", "none");
    }
    var onMapClickHandler = function(event) {
            var that = $(this);
            // Disable the click handler until the user leaves the map area
            that.off('click', onMapClickHandler);
            // Enable scrolling zoom
            that.find('iframe').css("pointer-events", "auto");
            // Handle the mouse leave event
            that.on('mouseleave', onMapMouseleaveHandler);
        }
        // Enable map zooming with mouse scroll when the user clicks the map
    $('.map').on('click', onMapClickHandler);
    </script>
    <!--購物_數量選擇 -->
<script type="text/javascript">
  $(document).on('click', '.number-spinner button', function () {    
  var btn = $(this),
    oldValue = btn.closest('.number-spinner').find('input').val().trim(),
    newVal = 0;
  
  if (btn.attr('data-dir') == 'up') {
    newVal = parseInt(oldValue) + 1;
  } else {
    if (oldValue > 1) {
      newVal = parseInt(oldValue) - 1;
    } else {
      newVal = 1;
    }
  }
  btn.closest('.number-spinner').find('input').val(newVal);
});
</script>
<script>
//登入模組        
	var $userName = $("#account");
	var $password = $("#password");
	$("#login").click(function() {
		$.ajax({
			url : '<%=request.getContextPath()%>/front-end/UserLoginHandler',
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
</body>
</html>
