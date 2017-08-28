<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dish.model.*,com.rest.model.*"%>
<%
	DishVO dishVO = (DishVO) request.getAttribute("dishVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<jsp:useBean id="dipiSvc" scope="page" class="com.dipi.model.DipiService"/>
<!DOCTYPE html>
<head>
<title>料理資料修改 - update_dish_input.jsp</title>

	<!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

<style type="text/css">
   #imgPreview {
        	width:600px;
        	height:auto;
        }
  
</style>

</head>
<body>
		
<h3>料理資料修改 - update_emp_input.jsp</h3>

<h3>資料修改:</h3>
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

<div class="container">
	<div class="row">
		<FORM  METHOD="POST" ACTION="<%= request.getContextPath()%>/front-end/restaurant/dish/dish.do" name="form1" enctype="multipart/form-data" role="form">
			<div class="col-md-5 col-md-offset-1">
				<input type="file" name="upfile" accept="image/*" onchange="loadFile(event)">
				<c:forEach var="dipiVO" items="${dipiSvc.findDipisByDishId(dishVO.dish_id)}">
					<c:if test="${dishVO.dish_id==dipiVO.dish_id}">
						<img class="img-thumbnail" id="imgPreview"  src="<%= request.getContextPath()%>/front-end/restaurant/dipi/DBGifReader_dipi.do?dipi_id=${dipiVO.dipi_id}">
						<input type="hidden" name="dipi_id" value="${dipiVO.dipi_id}">
					</c:if>
				</c:forEach>		
			</div>	
			<div class="col-md-5">	
					
					<div class="form-group">
   						<label for="dish_name">*料理名稱 :</label>
    					<input type="TEXT"  class="form-control" name="dish_name" id="dish_name" value="${dishVO.dish_name}" required/>
  					</div>
  					
					<div class="form-group">
   						<label for="dish_price col-xs-8">*料理價格 :</label>
   						 <div class="input-group">
     						 <div class="input-group-addon">$</div>
    						<input type="TEXT"  class="form-control" name="dish_price" id="dish_price" value="${dishVO.dish_price}" required/>
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
				    <input type="hidden" name="action" value="update">
					<input type="submit" class="btn btn-primary" value="確認送出">
				</div>
			</div>	
		</FORM>
	</div>	<!-- row -->
</div>	<!-- container -->

<script>
var loadFile = function(event) {
  var reader = new FileReader();
  reader.onload = function(){
    var output = document.getElementById('imgPreview');
    output.src = reader.result;
  };
  reader.readAsDataURL(event.target.files[0]);
};
</script>
</body>
</html>
