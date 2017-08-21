<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%@ page import="com.store.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO");
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<%@include file="includeHeadForStore.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">商店會員管理</a>
                    </li>
                    <li class="active">新增</li>
                </ol>


  <form class="form-horizontal" id="reg_form" action="<%= request.getContextPath() %>/back-end/user/userstore.do" method="post" enctype="multipart/form-data">
   
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
            <input  name="user_account" placeholder="輸入想用的登入帳號" class="form-control"  type="text" value="<%= (userVO==null)? "" : userVO.getUser_account()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">負責人E-Mail <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
            <input name="user_email" placeholder="輸入負責人E-mail" class="form-control"  type="email" type="email" value="<%= (userVO==null)? "" : userVO.getUser_email()%>">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">負責人姓 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input  name="user_lastname" placeholder="輸入負責人姓" class="form-control"  type="text" type="text" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >負責人名 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input name="user_firstname" placeholder="輸入負責人名" class="form-control"  type="text" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label" >負責人手機</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
            <input name="user_mobile" placeholder="輸入負責人手機" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label" >負責人電話</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span>
            <input name="user_phone" placeholder="輸入負責人電話" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label">負責人聯絡地址</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
            <input name="user_address" placeholder="輸入負責人聯絡地址" class="form-control" type="text" value="<%= (userVO==null)? "" : userVO.getUser_address()%>">
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
          		<img src="" height="200" id="ImagePreview" alt="">
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
            <input  name="store_name" placeholder="輸入商店名稱" class="form-control"  type="text" value="<%= (storeVO==null)? "" : storeVO.getStore_name()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">商店營業時間 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
            <input  name="store_time" placeholder="輸入商店營業時間" class="form-control"  type="text" value="<%= (storeVO==null)? "" : storeVO.getStore_time()%>" >
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label" >商店電話</label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span>
            <input name="store_phone" placeholder="輸入商店電話" class="form-control" type="text" value="<%= (storeVO==null)? "" : storeVO.getStore_phone()%>">
          </div>
        </div>
      </div>  

      <div class="form-group">
        <label class="col-md-4 control-label">商店簡介 <span class="asteriskField">*</span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-shopping-cart"></i></span>
            <textarea name="store_describe" class="form-control" rows="3"  class="form-control"  type="text"> <%= (storeVO==null)? "" : storeVO.getStore_describe()%> </textarea>
          </div>
        </div>
      </div>
      
                            <div class="col-xs-12 col-sm-4">
                                <div class="form-group">
                                    <label for="name">所在航廈*</label>
                                    <select class="form-control" name="store_ter">
                                    <option <c:if test="${storeVO.store_ter == '2'}" var="condition" scope="page">selected</c:if> value="2">T2</option>
                                    <option <c:if test="${storeVO.store_ter == '1'}" var="condition" scope="page">selected</c:if> value="1">T1</option>
                                    </select>
                                </div>                                
                            </div>
                            <div class="col-xs-12 col-sm-4">
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

                            <div class="col-xs-12 col-sm-4">
                                <div class="form-group">
	                            	<label for="name">出入境位置*</label>
    	                        	<select class="form-control" name="store_inout">
  									<option value="1" <c:if test="${storeVO.store_inout == '1'}" var="condition" scope="page">selected</c:if> >出境區</option>
  									<option value="2" <c:if test="${storeVO.store_inout == '2'}" var="condition" scope="page">selected</c:if> >入境區</option>
									</select>                            
                    	    	</div>
                    	    </div><br>

                            <div class="col-xs-12 col-sm-6">
                                <div class="input-group">
                                    <label class="input-group-addon" >所在經度</label>
                                    <input type="text" name="store_lon" value="<%= (storeVO==null)? "" : storeVO.getStore_lon()%>" class="form-control">
                                </div>                                
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <div class="input-group">
                                    <label class="input-group-addon" >所在緯度</label>
                                    <input type="text" name="store_lat" value="<%= (storeVO==null)? "" : storeVO.getStore_lat()%>" class="form-control">
                                </div><br>                                
                            </div>
  
		  	<div class="form-group">
		  		<label class="col-md-4 control-label"></label> 
     			<div class="col-md-8  inputGroupContainer">
        			<input class="btn btn-primary btn-s" type="submit" value="新增">
        			&nbsp;&nbsp; <input class="btn btn-primary btn-s" type="reset" value="重設">
     			</div>                      
  			</div>

  </div>
 
  <input type="hidden" name="action" value="insert">
  <input type="hidden" name="user_type" value="2">
  <input type="hidden" name="user_status" value="1">
  <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

  </div>	<!-- row -->
  </div>	<!-- container-fluid -->
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

</body>

</html>


<!-- 表單驗證用 --> 
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>


