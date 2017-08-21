<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.path.model.*"%>
<%
	PathVO pathVO = (PathVO) request.getAttribute("pathVO");
%>

<%@include file="includeHeadForPath.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">導引圖資管理</a>
                    </li>
                    <li class="active">修改</li>
                </ol>


  <form class="form-horizontal" id="reg_form" action="<%= request.getContextPath() %>/back-end/path/path.do" method="post" enctype="multipart/form-data">
   
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
    	<font color='red'>新增成功	</font><br><br>
    </c:if>

      <div class="form-group">
        <label class="col-md-4 control-label">航廈<span class="asteriskField"></span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
            <input  name="path_term" placeholder="輸入航廈" class="form-control"  type="text" value="<%= (pathVO==null)? "" : pathVO.getPath_term()%>">
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-md-4 control-label">起點經度 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
            <input name="path_fromlon" placeholder="輸入起點經度" class="form-control"  type="text" value="<%= (pathVO==null)? "" : pathVO.getPath_fromlon()%>">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">起點緯度 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
            <input name="path_fromlat" placeholder="輸入起點緯度" class="form-control"  type="text" value="<%= (pathVO==null)? "" : pathVO.getPath_fromlat()%>">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">終點經度 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
            <input name="path_tolon" placeholder="輸入終點經度" class="form-control"  type="text" value="<%= (pathVO==null)? "" : pathVO.getPath_tolon()%>">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">終點緯度 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
            <input name="path_tolat" placeholder="輸入終點緯度" class="form-control"  type="text" value="<%= (pathVO==null)? "" : pathVO.getPath_tolat()%>">
          </div>
        </div>
      </div>      

      <div class="form-group">
        <label class="col-md-4 control-label">起點地名 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
            <input name="path_fromplace" placeholder="輸入起點地名" class="form-control"  type="text" value="<%= (pathVO==null)? "" : pathVO.getPath_fromplace()%>">
          </div>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label">終點地名 <span class="asteriskField"> </span> </label>
        <div class="col-md-8  inputGroupContainer">
          <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-road"></i></span>
            <input name="path_toplace" placeholder="輸入終點地名" class="form-control"  type="text" value="<%= (pathVO==null)? "" : pathVO.getPath_toplace()%>">
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
                <img src="<%= request.getContextPath() %>/back-end/path/pathImg.do?path_id=<%=pathVO.getPath_id()%>" height="200" id="ImagePreview">
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
                      
  <input type="hidden" name="path_id" value="<%= pathVO.getPath_id() %>">
  <input type="hidden" name="action" value="update">
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
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>    

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

