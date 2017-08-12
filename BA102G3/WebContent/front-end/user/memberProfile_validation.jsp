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
    
    <link href="<%= request.getContextPath() %>/front-end/assets/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/front-end/user/styleValidation.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</style>

</head>

<body >
    
   <%@include file="/front-end/member_interface/headerBar.file" %>
    
    <br><br>

		<div class="container">
			<div class="row">

<FORM form id="registration-form" class="form-horizontal" METHOD="post" ACTION="user.do" name="form1" enctype="multipart/form-data">

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
                            <div class="form-horizontal">
                                <label class="col-sm-3 control-label" for="name">帳號</label>
                                <div class="col-sm-3">
                                	<input type="text" name="user_account" id="user_account" value="<%= (userVO==null)? "" : userVO.getUser_account()%>">
                            	</div>
                            </div><br>

                            <div class="form-control-group">
                                <label class="control-label" for="name">姓</label>
                                <div class="controls">
                                	<input type="text" class="form-control" name="user_lastname" id="user_lastname" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>">
                            	</div>
                            </div><br>

                            <div class="form-control-group">
                                <label class="control-label" for="name">名</label>
                                <div class="controls">
                                	<input type="text" class="form-control" name="user_firstname" id="user_firstname" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>">
                            	</div>
                            </div><br>                                

                            <div class="form-control-group">
                                <label class="control-label" for="name">電話</label>
                                <div class="controls">
                                	<input type="text" class="form-control" name="user_phone" id="user_phone" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>">
                            	</div>
                            </div><br>

                            <div class="form-control-group">
                                <label class="control-label" for="name">手機</label>
                                <div class="controls">
                                	<input type="text" class="form-control" name="user_mobile" id="user_mobile" value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>">
                            	</div>
                            </div><br>

                            <div class="form-control-group">
                                <label class="control-label" for="message">住址</label>
                                <div class="controls">
                            		<textarea class="form-control" name="user_address" id="user_address" rows="2"> <%= (userVO==null)? "" : userVO.getUser_address()%> </textarea>
                            	</div>
                            </div><br>

                            <div class="form-control-group">
                                <label class="control-label" for="email">Email</label>
                                <div class="controls">
                                	<input type="text" class="form-control" name="user_email" id="user_email" value="<%= (userVO==null)? "" : userVO.getUser_email()%>">
 								</div>                           
                            </div><br>

                    </div>
                    <div class="col-lg-6">
                        	 <input type="button" class="btn btn-me btn-danger" value="重設密碼"><br><br>
                    </div>                    
                    <div class="col-lg-6">
                    	 <c:if test="${userVO.user_status == '2'}" var="condition" scope="page" >
                        	 <input type="button" class="btn btn-xs btn-danger" value="重送確認信"><br><br>
                         </c:if>	                                                          
                    </div>
                    <div class="col-lg-6">
                    	加入時間: <%=userVO.getUser_joindate()%>
                    </div>
                    <div class="col-lg-6">
					          照片<br>
						<img src="<%= request.getContextPath() %>/front-end/user/userImg.do?user_id=<%=userVO.getUser_id()%>" height="200">
                    </div>
                                    
                    <div class="col-lg-6">
                                                               更新圖片
                        <input type="file" name="upfile1" id="file-input1" class="file" onchange="previewImages()">
                        </label>
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
						<input type="hidden" name="user_id" value="<%=userVO.getUser_id()%>">
						<input type="hidden" name="user_passwd" value="<%=userVO.getUser_passwd()%>">
						<input type="hidden" name="user_type" value="<%=userVO.getUser_type()%>">
						<input type="hidden" name="user_joindate" value="<%=userVO.getUser_joindate()%>">
						<input type="hidden" name="user_status" value="<%=userVO.getUser_status()%>">
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
                        <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp-->
                        <button class="btn btn-primary" name="action" value="update">確認修改</button>
                    </div>
                    <div class="col-xs-12 col-sm-1">
                    	<button onclick="window.history.back();" class="btn btn-primary">回上一頁</button>
                    </div>
                </div>
            </div>

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
    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/front-end/assets/js/jquery-1.7.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/front-end/assets/js/jquery.validate.js"></script>
    <script src="<%= request.getContextPath() %>/front-end/user/memberProfileValidation.js"></script> 
    <%@ include file="/front-end/member_interface/script.file" %>
    
    <!-- Custom Theme JavaScript -->
    <script>
    
	addEventListener('load', prettyPrint, false);
	$(document).ready(function(){
	$('pre').addClass('prettyprint linenums');
		});
    
	
   </script>


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
</body>

</html>