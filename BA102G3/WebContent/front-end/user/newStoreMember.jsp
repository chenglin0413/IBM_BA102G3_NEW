<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.stpi.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO");
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
StpiVO stpiVO = (StpiVO) request.getAttribute("stpiVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>

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



<style type="text/css">
        .item img{
            height: auto;
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
   
  <%@include file="/front-end/member_interface/headerBar.file"%>
	



<!--成為商店會員開始-->

<div class="container">		<!-- container -->
		
  <h3>成為會員</h3>
  <ul class="nav nav-tabs">
    <li><a href="<%= request.getContextPath() %>/front-end/user/newMember.jsp">一般會員</a></li>
    <li class="active"><a href="<%= request.getContextPath() %>/front-end/user/newStoreMember.jsp"><b>商店會員</b></a></li>
    <li><a href="<%= request.getContextPath() %>/front-end/user/newRestMember.jsp">餐廳會員</a></li>
  </ul>
  <br>
        
  <form class="form-horizontal" id="reg_form" action="<%= request.getContextPath() %>/front-end/user/userstore.do" method="post" enctype="multipart/form-data">
   
  <div class="container-fluid"> <!-- container-fluid -->
  <div class="row">				<!-- row -->

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
    	<font color='red'>收到申請了,本公司將與您聯絡後續事宜</font><br><br>
    </c:if>

      <div class="form-group">
        <label class="col-md-4 control-label">帳號 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input  name="user_account" id="user_account" placeholder="輸入想用的登入帳號" class="form-control"  type="text" value="<%= (userVO==null)? "" : userVO.getUser_account()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">負責人E-Mail <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
            <input name="user_email" id="user_email" placeholder="輸入負責人E-mail" class="form-control"  type="email" type="email" value="<%= (userVO==null)? "" : userVO.getUser_email()%>">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">負責人姓 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input  name="user_lastname" id="user_lastname" placeholder="輸入負責人姓" class="form-control"  type="text" type="text" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >負責人名 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input name="user_firstname" id="user_firstname" placeholder="輸入負責人名" class="form-control"  type="text" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >負責人手機</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
            <input name="user_mobile" id="user_mobile" placeholder="輸入負責人手機" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label" >負責人電話</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span>
            <input name="user_phone" id="user_phone" placeholder="輸入負責人電話" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label">負責人聯絡地址</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
            <input name="user_address" id="user_address" placeholder="輸入負責人聯絡地址" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_address()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">負責人圖片上傳</label>
        <div class="col-md-8  inputGroupContainer">
		  <div class="fileUpload btn btn-warning">
		      <span>請拖拉圖片到這裡</span>
          	  <input type="file" name="upfile1" onchange="previewFile()" style="height: 60px;" class="upload" >
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">預覽圖片</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> 
          		<img src="" height="200" id="ImagePreview" alt="*\(^_^)/*">
          </div>
        </div>
      </div>      
      <br><br><br><br><br>
  </div>

  <div class="col-lg-6">
 
      <div class="form-group">
        <label class="col-md-4 control-label">商店名稱 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-shopping-cart"></i></span>
            <input  name="store_name" id="store_name" placeholder="輸入商店名稱" class="form-control"  type="text" value="<%= (storeVO==null)? "" : storeVO.getStore_name()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">商店營業時間 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
            <input  name="store_time" id="store_time" placeholder="輸入商店營業時間" class="form-control"  type="text" value="<%= (storeVO==null)? "" : storeVO.getStore_time()%>" >
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label" >商店電話</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span>
            <input name="store_phone" id="store_phone" placeholder="輸入商店電話" class="form-control" type="text" value="<%= (storeVO==null)? "" : storeVO.getStore_phone()%>">
          </div>
        </div>
      </div>  

      <div class="form-group">
        <label class="col-md-4 control-label">商店簡介 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-shopping-cart"></i></span>
            <textarea name="store_describe" id="store_describe" class="form-control" rows="3"  class="form-control"  type="text"> <%= (storeVO==null)? "" : storeVO.getStore_describe()%> </textarea>
          </div>
        </div>
      </div>
      
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="name">所在航廈*</label>
                                    <select class="form-control" name="store_ter">
                                    <option <c:if test="${storeVO.store_ter == '2'}" var="condition" scope="page">selected</c:if> value="2">T2</option>
                                    <option <c:if test="${storeVO.store_ter == '1'}" var="condition" scope="page">selected</c:if> value="1">T1</option>
                                    </select>
                                </div>                                
                            </div>
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="name">所在樓層*</label>
                                    <select class="form-control" name="store_floor">
                                    <option <c:if test="${storeVO.store_floor == '3'}" var="condition" scope="page">selected</c:if> >3</option>
                                    <option <c:if test="${storeVO.store_floor == 'B2'}" var="condition" scope="page">selected</c:if> >B2</option>
                                    <option <c:if test="${storeVO.store_floor == 'B1'}" var="condition" scope="page">selected</c:if> >B1</option>
                                    <option <c:if test="${storeVO.store_floor == '1'}" var="condition" scope="page">selected</c:if> >1</option>
                                    <option <c:if test="${storeVO.store_floor == '2'}" var="condition" scope="page">selected</c:if> >2</option>
                                    <option <c:if test="${storeVO.store_floor == '4'}" var="condition" scope="page">selected</c:if> >4</option>
                                    </select>
                                </div>                               
                            </div>

                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
	                            	<label for="name">出入境位置*</label>
    	                        	<select class="form-control" name="store_inout">
  									<option value="1" <c:if test="${storeVO.store_inout == '1'}" var="condition" scope="page">selected</c:if> >出境區</option>
  									<option value="2" <c:if test="${storeVO.store_inout == '2'}" var="condition" scope="page">selected</c:if> >入境區</option>
									</select>                            
                    	    	</div>
                    	    </div>
                    	                        	                        	    
                            <div class="col-xs-12 col-sm-3">
                            	<div class="form-group">
                            	<label></label>
                            	<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#myModal">圖上標示商店位置</button>
                            	</div>
                            </div><br>
                            
<!-- Pick up location on Map -->
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">商店位置</h4>
      </div>
      <div class="modal-body">

  <p align="center">
      x=<input type="text" name="store_lon" id="store_lon" size="20" value="">
      y=<input type="text" name="store_lat" id="store_lat" size="20" value="">
      <button type="button" class="btn btn-primary" data-dismiss="modal">確定</button>
      <button type="reset" value="reset" id="clear" class="btn btn-primary">重設</button><br>

  </p>
  <p align="center">
      <canvas id="myCanvas" width="853" height="911"></canvas>
      <div style="display:none;">
         <img id="source" src="<%= request.getContextPath() %>/front-end/image/T2_3F_template_25per.png">
      </div>
  </p>

<script>

      var canvas = document.getElementById('myCanvas');
      var context = canvas.getContext('2d');
      
      var image = new Image();
      image.src = "<%= request.getContextPath() %>/front-end/image/T2_3F_template_25per.png";
      image.onload = function() {
          context.drawImage(image, 0, 0, 853, 911, 0, 0, 853, 911);
      };

      function getMousePos(canvas, evt) {
        var rect = canvas.getBoundingClientRect();
        return {
          x: evt.clientX - rect.left,
          y: evt.clientY - rect.top
        };
      }

//       canvas.addEventListener('mousemove', function(evt) {
//           var mousePos = getMousePos(canvas, evt);
//           var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y;
//       });          

      canvas.addEventListener('click', function(evt) {

          var mousePos = getMousePos(canvas, evt);
          var context = canvas.getContext('2d');

          document.getElementById("store_lon").value=9+4*mousePos.x;
          document.getElementById("store_lat").value=3+4*mousePos.y;

          x=mousePos.x;
          y=mousePos.y;

          context.beginPath();
          context.arc(x, y, 20, 0, 2 * Math.PI, false);
          context.lineWidth = 2;
          context.strokeStyle = '#FF0000';
          context.stroke();

          context.beginPath();
          context.moveTo(x,y-30);
          context.lineTo(x,y+30);
          context.lineWidth = 2;
          context.strokeStyle = '#FF0000';
          context.stroke();

          context.moveTo(x-30,y);
          context.lineTo(x+30,y);
          context.stroke();
      });
      
      document.getElementById('clear').addEventListener('click', function() {

          context.drawImage(document.getElementById('source'),
                        0, 0, 853, 911, 0, 0, 853, 911);
          document.getElementById("store_lon").value=0;
          document.getElementById("store_lat").value=0;
      }, false);  

</script>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">關閉</button>
      </div>
    </div>

  </div>
</div>

<!-- End Pick up location on Map -->





                                               	    

      <br><br><br>
                            
      <div class="form-group">
        <label class="col-md-4 control-label">商店圖片上傳</label>
        <div class="col-md-8  inputGroupContainer">
		  <div class="fileUpload btn btn-info">
		      <span>請拖拉商店圖片到這裡</span>
          	  <input type="file" name="upfileStore" id="upfileStore" onchange="previewFileStore()" style="height: 60px;" class="upload" >
          </div>
        </div>
      </div>

      
      <div class="form-group">
        <label class="col-md-4 control-label">預覽商店圖片</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> 
          		<img src="" height="200" id="ImagePreviewStore" alt="*\(^_^)/*">
          </div>
        </div>
      </div>   
                            
		  	<div class="form-group">
		  		<label class="col-md-4 control-label"></label> 
     			<div class="col-md-8  inputGroupContainer">
        			<input class="btn btn-primary btn-lg" type="submit" value="我要加入">
        			&nbsp;&nbsp; <input class="btn btn-primary btn-lg" type="reset" value="重設">
        			
        			<button type="button" id="speedInput"></button>
        			
     			</div>                      
  			</div>

  </div>

  <input type="hidden" name="action" value="insert">
  <input type="hidden" name="user_type" value="2">
  <input type="hidden" name="user_status" value="2">
<!--   <input type="hidden" name="store_lat" value="0"> -->
<!--   <input type="hidden" name="store_lon" value="0"> -->
  <input type="hidden" name="stpi_name" value="1">
  <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

  </div>	<!-- row -->
  </div>	<!-- container-fluid -->
  </form>

</div>		<!-- container -->
 
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
	
function previewFileStore() {
	  var preview = document.getElementById('ImagePreviewStore'); 
	  var file    = document.getElementById('upfileStore').files[0];
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
                        message: '至少打2個字吧'
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
                        message: '至少打1個字吧'
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
                        message: '至少打1個字吧'
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
                        
            store_name: {
                validators: {
                        stringLength: {
                        min: 1,
                        message: '至少打1個字吧'
                    },
                        notEmpty: {
                        message: '請輸入商店名稱'
                    }
                }
            },

            store_describe: {
                validators: {
                        stringLength: {
                        min: 2,
                        message: '至少打2個字吧'
                    },
                        notEmpty: {
                        message: '請輸入店家描述'
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
 
<!--成為商店會員結束-->
 
 
 
 
        

    


  
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
										<a href="#" onclick="alert('請打0800-995-5487跟本公司客服聯絡');">忘記密碼</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/front-end/user/newMember.jsp">加入會員</a>
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
                	window.location='<%= request.getContextPath() %>/front-end/store_interface/listOneStore_idAllProd.jsp'
                }else if(results == 'SUCCESS_REST'){
                	window.location='<%= request.getContextPath() %>/front-end/rest_interface/listOneRest_idAllDish.jsp'
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

<script>

function init(){    
	var speedInput=document.getElementById("speedInput");
    speedInput.onclick=InputText;
}

function InputText(){
    document.getElementById("user_account").value="plankton";
    document.getElementById("user_email").value="ba102g3iii@gmail.com";
    document.getElementById("user_lastname").value="皮";
    document.getElementById("user_firstname").value="老闆";
    document.getElementById("user_mobile").value="0911258102";
    document.getElementById("user_phone").value ="0911258102";
    document.getElementById("user_address").value = "雲林麥寮";
    
    document.getElementById("store_name").value="皮老闆的尚好禮";
    document.getElementById("store_time").value="05:00am~11:00pm";
    document.getElementById("store_phone").value="034227151";
    document.getElementById("store_describe").value="皮老闆不腹黑, 專門販售國內特產及各國進口免稅商品及菸酒、工藝品、香水、化妝品";
        
}

window.onload = init;

</script>


