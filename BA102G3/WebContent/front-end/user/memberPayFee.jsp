<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ page import="com.user.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.rest.model.*"%>
<%
UserVO userVO = (UserVO) session.getAttribute("userVO");
StoreVO storeVO = (StoreVO) session.getAttribute("storeVO");
RestVO restVO = (RestVO) session.getAttribute("restVO");
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
	

            <FORM METHOD="get" ACTION="<%= request.getContextPath() %>/front-end/mfee/mfee.do" name="form1">

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
	<font color='red'>繳費成功	</font><br><br>
</c:if>

							<h3>繳交月費</h3><br>
							                    
                            <div class="input-group">
                                                                                   會員編號: <%=userVO.getUser_id()%> - <%=userVO.getUser_lastname()%> <%=userVO.getUser_firstname()%> 
                            </div><br>

							<c:if test="${userVO.user_type == '2'}" var="condition" scope="page" >
	                            <div class="input-group">
          	                                                                         商店編號: <%=storeVO.getStore_id()%> - <%=storeVO.getStore_name()%> 
            	                </div><br>
            	            </c:if>    
							<c:if test="${userVO.user_type == '3'}" var="condition" scope="page" >
	                            <div class="input-group">
          	                                                                         餐廳編號: <%=restVO.getRest_id()%> - <%=restVO.getRest_name()%> 
            	                </div><br>
            	            </c:if>    

                            <div class="input-group">
                                                                                   月費月份
								<select name="mfee_year">
  									<option value="2017" selected>2017</option>
								</select>
								年

								<select name="mfee_month">
  									<option value="08" selected>8</option>
  									<option value="09">9</option>
								</select>
								月
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">姓</label>
                                <input type="text" name="user_lastname" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">名</label>
                                <input type="text" name="user_firstname" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>" class="form-control">
                            </div><br>                                

                            <div class="input-group">
                                <label class="input-group-addon">電話</label>
                                <input type="text" name="user_phone" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">住址</label>
                                <input type="text" name="user_address" value="<%= (userVO==null)? "" : userVO.getUser_address()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">信用卡號碼</label>
                                <input type="text" name="creditcard_no" class="form-control">
                            </div><br>
                            
                            <div class="input-group">
                                <label class="input-group-addon">信用卡安全碼(CSV)</label>
                                <input type="text" name="creditcard_csv" class="form-control">
                            </div><br>
                            
                            <div class="input-group">
                                <label class="input-group-addon">信用卡到期日期</label>
                                <input type="date" name="creditcard_duedate" min="2017-08-25">
                            </div><br>

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
            <input type="hidden" name="user_id" value="<%=userVO.getUser_id()%>">
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



    <!-- jQuery -->
    <!-- Bootstrap Core JavaScript -->
    
    <script src="<%= request.getContextPath() %>/front-end/assets/js/jquery-1.7.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/front-end/assets/js/jquery.validate.js"></script>
    <script src="<%= request.getContextPath() %>/front-end/user/memberProfileValidation.js"></script> 
    
    
  <%@ include file="/front-end/member_interface/script.file" %>


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