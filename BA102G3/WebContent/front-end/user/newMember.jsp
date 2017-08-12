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

</head>

<body >
    
    <%@include file="/front-end/member_interface/headerBar.file" %>
	<br><br>
 
   <center><h3>註冊成為會員</h3></center><br>
   
   
   
   <form class="form-horizontal" id="reg_form" action="<%= request.getContextPath() %>/front-end/user/user.do" method="post" enctype="multipart/form-data">
      <fieldset class="form-group row">
   
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
	<font color='red'>註冊成功	,將於E-mail中收到確認信</font><br><br>
</c:if>

      <div class="form-group">
        <label class="col-md-4 control-label">帳號 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input  name="user_account" placeholder="輸入想用的登入帳號" class="form-control"  type="text">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">E-Mail <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
            <input name="user_email" placeholder="輸入E-mail" class="form-control"  type="email">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">姓 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input  name="user_lastname" placeholder="輸入姓" class="form-control"  type="text">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >名 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input name="user_firstname" placeholder="輸入名" class="form-control"  type="text">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >手機</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
            <input name="user_mobile" placeholder="輸入手機" class="form-control" type="text">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label" >電話</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
            <input name="user_phone" placeholder="輸入電話" class="form-control" type="text">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label">聯絡地址</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
            <input name="user_address" placeholder="輸入聯絡地址" class="form-control" type="text">
          </div>
        </div>
      </div>
      
      
      </div>
      <div class="col-lg-3">
      
      <div class="form-group">
        <label class="col-md-4 control-label">上傳圖片</label>
        <div class="col-md-8  inputGroupContainer">
<!--           <div class="input-group">   -->
		  <div class="fileUpload btn btn-warning">
		      <span>請拖拉圖片到這裡</span>
          	  <input type="file" onchange="previewFile()" style="height: 60px;" class="upload" >
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">預覽圖片</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> 
          		<img src="" height="200" id="ImagePreview" alt="*\(^_^)/*"> </div>
          </div>
        </div>
            
                      
      </div>      
                      <br><br><br>
                      
					<br><br>
          </div>
                      
                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input class="btn btn-primary btn-lg" type="submit" value="送出">
                                &nbsp;&nbsp; <input class="btn btn-primary btn-lg" type="reset" value="重設">
                            </div>
                        </div>                      
                <!-- /.row -->

      </div>
            <!-- /.container-fluid -->

            <input type="hidden" name="action" value="insert">
            <input type="hidden" name="user_type" value="1">
            <input type="hidden" name="user_status" value="1">
            <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

    </fieldset>
  </form>
</div>
 
<!-- 表單驗證用 --> 
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>


<!-- 上傳圖片用 -->
<script type="text/javascript">
	
function previewFile() {
	  var preview = document.getElementById('ImagePreview'); 
	  var file    = document.querySelector('input[type=file]').files[0];
	  var reader  = new FileReader();

	  reader.addEventListener("load", function () {
	    preview.src = reader.result;
	  }, false);

	  if (file) {
	    reader.readAsDataURL(file);
	  }
	}

</script>

<!-- 表單驗證用 -->
<script type="text/javascript">
 
   $(document).ready(function() {
    $('#reg_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {

            user_account: {
                validators: {
                        stringLength: {
                        min: 2,
                    },
                        notEmpty: {
                        message: '請輸入想用的帳號'
                    }
                }
            },

            user_firstname: {
                validators: {
                        stringLength: {
                        min: 1,
                    },
                        notEmpty: {
                        message: '請輸入您的名'
                    }
                }
            },
            
             user_lastname: {
                validators: {
                     stringLength: {
                        min: 1,
                    },
                    notEmpty: {
                        message: '請輸入您的姓'
                    }
                }
            },
           
            user_phone: {
                validators: {
            
                }
            },
            
            user_mobile: {
                validators: {
                    notEmpty: {
                        message: '請輸入您的手機'
                    },
                    phone: {
                        country: 'US',
                        message: '請輸入有效的手機號碼'
                    }                    

                }
            },
            
            user_address: {
                validators: {

                }
            },

            user_email: {
                validators: {
                    notEmpty: {
                        message: '請輸入您的Email'
                    },
                    emailAddress: {
                        message: '請輸入有效的E-mail'
                    }
                }
            },
          
                  
            }
        })
      
        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
                $('#reg_form').data('bootstrapValidator').resetForm();
 
            // Prevent form submission
            e.preventDefault();
 
            // Get the form instance
            var $form = $(e.target);
 
            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');
 
            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });
});
 
 </script>

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
    
<!-- 登入表單 開始 --------------->
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
											class="form-control" name="username" id="username"
											placeholder="請輸入您的帳號">
									</div>
									<div class="form-group">
										<label for="pw">密碼</label> <input type="password"
											class="form-control" name="password" id="password" placeholder="請輸入您的密碼">
									</div>
									 <div id="messageDiv" style="display:none;"></div>
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
<!-- 登入表單 結束 --------------->

    <!-- jQuery -->
 
<!--     <script src="<%= request.getContextPath() %>/front-end/js/jquery.js"></script>  -->

    <!-- Bootstrap Core JavaScript -->
<!--    <script src="<%= request.getContextPath() %>/front-end/js/bootstrap.min.js"></script>  -->

 
    <!-- Custom Theme JavaScript -->
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
        
//登入模組開始-------------------------------------------------        
            $("#login").on('click', function(){
                var username = $("#username").val();
                var password = $("#password").val();
                if(username == ""){
                    $('#messageDiv').css("display","none");
                    alert("Username is required");
                    return;
                }
                if(password == ""){
                    $('#messageDiv').css("display","none");
                    alert("Password is required");
                    return;
                }
                $.ajax({
                    url : '<%= request.getContextPath() %>/front-end/UserLoginHandler',
                    type : 'POST',
                    data : {
                        username : username,
                        password : password
                    },
                    success : function(results, newurl){
                        if(results != null && results != ""){
                            showMessage(results);
                            $('#messageDiv').css("display","block");
                        }else{
                            $('#messageDiv').css("display","none");
                            $('#messageDiv').html("");
                            alert("Some exception occurred! Please try again.");
                        }
                    }
                });
            });
             
            //function to display message to the user
            function showMessage(results, newurl){
                if(results == 'SUCCESS'){
                    window.location='<%= request.getContextPath() %><%=request.getServletPath()%>'
                }else if(results == 'SUCCESS_STORE'){
                	window.location='<%= request.getContextPath() %>/front-end/store/'
                }else if(results == 'SUCCESS_REST'){
                	window.location='<%= request.getContextPath() %>/front-end/rest/'
                }else if(results == 'FAILURE'){
                    $('#messageDiv').html("<font color='red'>帳號或密碼不正確 </font>")
                }else if(results == 'ACCOUNT SUSPENDED'){
                    $('#messageDiv').html("<font color='red'>帳號被停權,請聯絡本公司</font>")
                }
            }
//登入模組結束-------------------------------------------------            

    </script>

<script>
    $('.carousel').carousel({
        interval: 3000 //changes the speed
    })
    </script>
</body>

</html>
