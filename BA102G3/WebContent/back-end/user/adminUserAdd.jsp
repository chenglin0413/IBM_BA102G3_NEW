<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%
	UserVO userVO = (UserVO) request.getAttribute("userVO");
%>

<%@include file="includeHeadForUser.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">一般會員管理</a>
                    </li>
                    <li class="active">新增</li>
                </ol>


  <form class="form-horizontal" id="reg_form" action="<%= request.getContextPath() %>/back-end/user/user.do" method="post" enctype="multipart/form-data">
   
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
            <input  name="user_account" placeholder="輸入想用的登入帳號" class="form-control"  type="text" value="<%= (userVO==null)? "" : userVO.getUser_account()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">E-Mail <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
            <input name="user_email" placeholder="輸入E-mail" class="form-control"  type="email" value="<%= (userVO==null)? "" : userVO.getUser_email()%>">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">姓 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input  name="user_lastname" placeholder="輸入姓" class="form-control"  type="text" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >名 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input name="user_firstname" placeholder="輸入名" class="form-control"  type="text" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >手機</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
            <input name="user_mobile" placeholder="輸入手機" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label" >電話</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span>
            <input name="user_phone" placeholder="輸入電話" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label">聯絡地址</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
            <input name="user_address" placeholder="輸入聯絡地址" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_address()%>">
          </div>
        </div>
      </div>
      
  </div>
  
  <div class="col-lg-4">
      
      <div class="form-group">
        <label class="col-md-4 control-label">上傳圖片</label>
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
          		<img src="" height="200" id="ImagePreview" alt="">
          </div>
        </div>
      </div>      
      <br>
	  
	  <div class="form-group">
	     <label class="col-md-4 control-label"></label> 
    	 <div class="col-md-8  inputGroupContainer">
       	 <input class="btn btn-primary btn-s" type="submit" value="送出">
       	 &nbsp;&nbsp; <input class="btn btn-primary btn-s" type="reset" value="重設">
     	</div>                      
  	  </div>
  </div>
                      
  <input type="hidden" name="action" value="insert">
  <input type="hidden" name="user_type" value="1">
  <input type="hidden" name="user_status" value="1">
  <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

  </div>
  </div>
  </form>

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/back-end/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/back-end/js/bootstrap.min.js"></script>
    
<!-- 表單驗證用 --> 
<script src='<%= request.getContextPath() %>/back-end/js/jquery.min.js'></script>
<script src='<%= request.getContextPath() %>/back-end/js/bootstrap.min.js'></script>
<script src='<%= request.getContextPath() %>/back-end/js/bootstrapvalidator.min.js'></script>    

<script>
      
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

</body>

</html>

<!-- 表單驗證用 --> 
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>

