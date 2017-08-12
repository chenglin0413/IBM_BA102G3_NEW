<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%
UserVO userVO = (UserVO) session.getAttribute("userVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
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
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</style>

</head>

<body >
    
    <!-- Navigation -->
    <!-- <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a> -->
    
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a href="<%= request.getContextPath() %>/front-end/index.jsp"><img src="<%= request.getContextPath() %>/front-end/img/logo3.png"></a>
                <a class="navbar-brand page-scroll" href="index.html">Anytime Grip</a>
            </div>
                
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            
                <ul class="nav navbar-nav navbar-right">
                    

                    <li>
                        <a class="page-scroll" href="<%= request.getContextPath() %>/front-end/store/store.html">商品</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="<%= request.getContextPath() %>/front-end/rest/rest__2.html">餐廳</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="<%= request.getContextPath() %>/front-end/rest/rest.html">促銷</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="<%= request.getContextPath() %>/front-end/blog/blog2.html">旅遊日記<span class="badge">10</span></a>
                    </li>
                    
                    <li>
                        <a class="page-scroll" href="#contact">交通運輸資訊</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact"><i class="fa fa-question fa-fw"></i>檢舉管理</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="about.html"><i class="fa fa-tasks fa-fw"></i>關於我們</a>
                    </li>
			 
			<c:if test="${empty userVO.user_account}" var="condition1" scope="session" > 

						<li><a href='#modal-login' data-toggle="modal">
						    <i class="glyphicon glyphicon-user"></i> 登入</a></li>

			</c:if>				


			<c:if test="${not empty userVO.user_account}" var="condition2" scope="session" > 
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="<%= request.getContextPath() %>/front-end/user/memberProfile.html"><i class="fa fa-info fa-fw"></i>${userVO.user_lastname},您好</a>
                        </li>
                        <li><a href="<%= request.getContextPath() %>/front-end/user/memberOrderRecord.html"><i class="fa fa-info fa-fw"></i>消費記錄</a>
                        </li>
                        <li><a href="<%= request.getContextPath() %>/front-end/user/memberReserveRecord.html"><i class="fa fa-book fa-fw"></i>餐廳訂位記錄</a>
                        </li>
                        <li><a href="<%= request.getContextPath() %>/front-end/user/memberProfile.jsp"><i class="fa fa-gear fa-fw"></i> 基本資訊修改</a>
                        </li>
                        <li><a href="<%= request.getContextPath() %>/front-end/user/memberPayFee.jsp"><i class="fa fa-gear fa-fw"></i> 繳費</a>
                        </li>                        
                        <li class="divider"></li>
                        <li><a href="<%= request.getContextPath() %>/front-end/logout.jsp"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
                        </li>
                    </ul>
                    </li>
                    
			</c:if>			                             
                    
                    
                    <li>
                        <a class="page-scroll" href="shopcar/shopcar.html">
                        <i class="fa fa-shopping-cart"></i></a>
                    </li>
                    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    
    <br><br><br><br>

<div class="container">
	<div class="row">
	

            <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/user/user.do" name="form1" enctype="multipart/form-data">

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
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

<%-- 新增成功 --%>
<c:if test="${not empty successMsgs}">
	<font color='red'>註冊成功	</font><br><br>
</c:if>



    
							註冊成會員<br><br>
							                    
                            <div class="input-group">
                                <label class="input-group-addon">帳號</label>
                                <input type="text" name="user_account" value="<%= (userVO==null)? "" : userVO.getUser_account()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">姓</label>
                                <input type="text" name="user_lastname" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>" class="form-control"><div> </div>
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">名</label>
                                <input type="text" name="user_firstname" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>" class="form-control"><div> </div>
                            </div><br>                                

                            <div class="input-group">
                                <label class="input-group-addon">電話</label>
                                <input type="text" name="user_phone" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">手機</label>
                                <input type="text" name="user_mobile" value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">住址</label>
                                <input type="text" name="user_address" value="<%= (userVO==null)? "" : userVO.getUser_address()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">Email</label>
                                <input type="text" name="user_email" value="<%= (userVO==null)? "" : userVO.getUser_email()%>" class="form-control">
                            </div><br>

                    </div>                
                     
                    <div class="col-lg-6">
                                                                        選擇圖片
                        <input type="file" name="upfile1" id="file-input1" class="file" onchange="previewImages()">
                        <div id="preview"></div>
                        
                    </div>
                    
    			</div>
                <!-- /.row -->

			</div>
            <!-- /.container-fluid -->
            
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-1">
                        <input class="btn btn-primary" type="submit" value="送出">
                    </div>
                    <div class="col-xs-12 col-sm-1">
                        <input class="btn btn-primary" type="reset" value="重設">    
                    </div>
                </div>
            </div>

            <input type="hidden" name="action" value="insert">
            <input type="hidden" name="user_type" value="1">
            <input type="hidden" name="user_status" value="1">
            <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

            </form>
	
	</div>
</div>

    <!-- Footer -->
    <footer>
        <div class="container" >
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                    <h4><strong>Anytime Grip</strong>
                    </h4>
                    <p>320桃園市中壢區中大路300號
                        <br>"擁有超過30年歷史的資訊專長培訓中心"</p>
                    <ul class="list-unstyled">
                        <li><i class="fa fa-phone fa-fw"></i> (香港) 334-5678</li>
                        <li><i class="fa fa-envelope-o fa-fw"></i> <a href="mailto:name@example.com">Stephen Chow@gmail.com</a>
                        </li>
                    </ul>
                    <br>
                    <ul class="list-inline">
                        <li>
                            <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-dribbble fa-fw fa-3x"></i></a>
                        </li>
                    </ul>
                    <hr class="small">
                    <p class="text-muted">Copyright &copy; Your Website 2017</p>
                </div>
            </div>
        </div>
        <a id="to-top" href="#top" class="btn btn-dark btn-lg"><i class="fa fa-chevron-up fa-fw fa-1x"></i></a>
    </footer>
    
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
							
								<form action="UserLoginHandler" method="POST" id="loginForm">
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


    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/front-end/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/front-end/js/bootstrap.min.js"></script>
    
    <script src="<%= request.getContextPath() %>/front-end/assets/js/jquery-1.7.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/front-end/assets/js/jquery.validate.js"></script>
    <script src="<%= request.getContextPath() %>/front-end/user/memberProfileValidation.js"></script> 
    
    
    <!-- Custom Theme JavaScript -->
    <script>
    
	addEventListener('load', prettyPrint, false);
	$(document).ready(function(){
	$('pre').addClass('prettyprint linenums');
		});
    
	
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
        
//登入模組        
	var $userName = $("#account");
	var $password = $("#password");
	$("#login").click(function() {
		$.ajax({
			url : 'UserLoginHandler',
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
    $('.carousel').carousel({
        interval: 3000 //changes the speed
    })
    </script>
</body>

</html>

<script>

function previewImages() {
    
    var preview = document.getElementById('preview'); 
    
    if (this.files) {
      [].forEach.call(this.files, readAndPreview);
    }

    function readAndPreview(file) {
      
      var reader = new FileReader();
      
      reader.addEventListener("load", function() {
        var image = new Image();
        image.height = 150;
        image.title  = file.name;
        image.src    = this.result;
        preview.appendChild(image);
      }, false);
      
      reader.readAsDataURL(file);
      
    }

}

  document.getElementById('file-input1').addEventListener("change", previewImages, false);

</script>
