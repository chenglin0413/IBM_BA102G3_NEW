<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dish.model.*,com.rest.model.*"%>
<!-- UserVO userVO=(UserVO)session.getAttribute("userVO") 餐廳編號-->
<%
	DishVO dishVO = (DishVO) request.getAttribute("dishVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<title>菜單資料新增 - addDish.jsp</title>

    
    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/stylish-portfolio.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <style type="text/css">
        th{
       text-align:center;
       font-size:14px;
       
        }
        li {
			list-style-type:none;
			}
				
		.uploader {position:relative; overflow:hidden;  height:350px; background:#f3f3f3; border:2px dashed #e8e8e8;}

#filePhoto{
    position:absolute;
    height:350px;
    top:-50px;
    left:0;
    z-index:2;
    opacity:0;
    cursor:pointer;
}

.uploader img{
    position:absolute;
    width:400px;
    height:350px;
    top:-1px;
    left:-1px;
    z-index:1;
    border:none;
}	
			
    </style>
</head>

<body>
<%@include file="headerBar.file" %>

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

<div class="callout"></div>
<div class="container">
	<div class="row">
		<FORM  METHOD="POST" ACTION="<%= request.getContextPath()%>/front-end/restaurant/dish/dish.do" name="form1" enctype="multipart/form-data" role="form"  class="form-horizontal">
			<div class="col-md-5 col-md-offset-1">
				<div class="callout"></div>
				<div class="uploader" onclick="$('#filePhoto').click()">
					 <div class="callout"></div>
					 <div class="callout"></div>
					 drag here your images for preview
					<input type="file" name="userprofile_picture" id="filePhoto" accept="image/*" onchange="loadFile(event)">
					<img id="imgPreview" src="<%= request.getContextPath()%>/front-end/restaurant/dipi/DBGifReader_dipi.do?dipi_id=${dipiVO.dipi_id}">
				</div>
			</div>	
			<div class="col-md-5">	
					<div class="form-group">
   						<label for="dish_name">*料理名稱 :</label>
   						 <div class="input-group">
     						 <div class="input-group-addon"><span class="glyphicon glyphicon-cutlery"></span></div>
    							<input type="TEXT"  class="form-control" name="dish_name" id="dish_name" value="${dishVO.dish_name}" required/>
  							</div>
  					</div>
					<div class="form-group">
						
   						<label for="dish_price col-xs-8">*料理價格 :</label>
   						 <div class="input-group">
     						 <div class="input-group-addon"> <span class="glyphicon glyphicon-usd"></span></div>
    							<input type="number"  class="form-control" name="dish_price" size="5" id="dish_price" value="${dishVO.dish_price}" required/>
  							</div>
  						</div>
  					<div class="form-group">
  					<label>*上架狀態 :</label>
	   					<label class="radio-inline">
						  <input type="radio" name="dish_status" id="inlineRadio1" value="0" checked> 下架
						</label>
						<label class="radio-inline">
						  <input type="radio" name="dish_status" id="inlineRadio2" value="1"> 供應
						</label>
	   				</div>		
					<div class="form-group">
						<label for="dish_note">*供應時段 :</label>
						<select class="c-select"name="dish_note" id="dish_note">
							<option value="1">早餐</option>
							<option value="2">午餐</option>
							<option value="3">晚餐</option>
							<option value="4">下午茶</option>
							<option value="5" selected>全天</option>
					 	</select>
				 	</div>
					<div class="form-group">
   						<label for="dish_detail">*料理簡介 :</label>
						<textarea class="form-control" name="dish_detail" id="dish_detail" cols="40" rows="8" required>${dishVO.dish_detail}</textarea>
  					</div>


				    <input type="hidden" name="rest_id" value="${dishVO.rest_id}"> <!-- ${userVO.user_id} -->
				    <input type="hidden" name="dish_id" value="${dishVO.dish_id}">
				    <input type="hidden" name="dipi_id" value="${dipiVO.dipi_id}">
				    <input type="hidden" name="action" value="update">
					<input type="submit" class="btn btn-primary" value="確認送出">
				
				</div>
			</div>	
		</FORM>
	</div>	<!-- row -->
</div>	<!-- container -->

<script>
var imageLoader = document.getElementById('filePhoto');
imageLoader.addEventListener('change', handleImage, false);

function handleImage(e) {
var reader = new FileReader();
reader.onload = function (event) {
    
    $('.uploader img').attr('src',event.target.result);
}
reader.readAsDataURL(e.target.files[0]);
}
</script>



<script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->

</body>

</html>
