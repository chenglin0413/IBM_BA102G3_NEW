<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*,com.user.model.*"%>

<%
	if (session.getAttribute("userVO") != null) {
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		StoreService storeSvc = new StoreService();
		StoreVO storeVO = storeSvc.getOneStoreByUsed_Id(userVO.getUser_id());
		Integer store_id = storeVO.getStore_id();

		pageContext.setAttribute("store_id", store_id);
		pageContext.setAttribute("storeVO", storeVO);
%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>建立促銷專案</title>

<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/css_store/bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/css_store/stylish-portfolio.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker3.min.css" />

<style type="text/css">
.content: {
	position: relative;
}


#filePhoto{
    position:absolute;
    height:300px;
    top:1px;
    left:18px;
    z-index:2;
    opacity:0;
    cursor:pointer;
    border: 2px solid gray;
}

input[type="file"]{
 color: transparent;


}
</style>
</head>





<body>

<%@ include file="headerBar.file"%>

    <div class="callout" ></div>
    
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">新增商品</h3>
                </div>
            </div>

	<br>
	<div align="center">
		<br>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>錯誤: <c:forEach var="message"
					items="${errorMsgs}">
					<a>${message}</a>
				</c:forEach>

			</font>
		</c:if>
	</div>

	<script>
		var loadFile = function(event) {
			var output = document.getElementById('output');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>

	
		<div class="container">
		<FORM ACTION="<%=request.getContextPath()%>/front-end/store_interface/prod.do" method=post name="form1"	enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-6 col-xs-12">
				<div class="col-md-12">請將圖片拖曳至此</div>
					<div class="uploader">
					
						 <img
							id="output" height="300" width="350" /> <input id="filePhoto" type="file" name="prpi_img" multiple accept="image/*" required="required" onchange="loadFile(event)" />
					     <div class="col-md-12"><label>圖片名稱:&nbsp;&nbsp;</label><input type="TEXT" 
							id="prpi_name" name="prpi_name" required="required" size="20" /></div>		
					</div>
				</div>
				<div class="col-md-6 col-xs-12">
					<div class="row">
						<div class="col-md-12 col-xs-6">
							<div class="row">
								<table border="0">
									<tr>
										<td><label>商店代號:&nbsp;&nbsp;</label></td>
										<td><font color=red><b>＊</b></font><%=storeVO.getStore_id()%></td>
										<td><input type="hidden" name="store_id" value="<%=storeVO.getStore_id()%>" /></td>
									</tr>
									<tr>
										<td><label>產品名稱:&nbsp;&nbsp;</label></td>
										<td><input type="TEXT" id="name" name="prod_name"	required="required" size="20" /></td>
									</tr>
									<tr>
										<td><label>產稱描述:&nbsp;&nbsp;</label></td>
										<td><textarea rows="4" id="descript" cols="40" name="prod_descript" required="required"></textarea></td>
									</tr>
									<tr>
										<td><label>產品價格:&nbsp;&nbsp;</label></td>
										<td><input type="number" id="price" name="prod_price"	required="required" size="15" /></td>
									</tr>
									<tr>
										<td><label>產品類別:&nbsp;&nbsp;</label></td>
										<td><input type="TEXT" id="sort" name="prod_sort"	required="required" size="15" /></td>
									</tr>
									<tr>
										<td><label>產品規格:&nbsp;&nbsp;</label></td>
										<td><input type="TEXT" id="format" name="prod_format" size="15" /></td>
									</tr>
									<tr>
										<td><label>產品品牌:&nbsp;&nbsp;</label></td>
										<td><input type="TEXT" id="brand" name="prod_brand" size="15" /></td>
									</tr>
									<tr>
										<%
											java.sql.Date startdate_SQL = new java.sql.Date(System.currentTimeMillis());
										%>
										<!-- <td><label>更新時間:&nbsp;&nbsp;</label></td> -->
										<td><input type="hidden" name="prod_updatetime"
											required="required" value="<%=startdate_SQL%>" /></td>
									</tr>
									<tr>
										<!-- <td><label>賣出數量:&nbsp;&nbsp;</label></td> -->
										<td><input type="hidden" name="prod_soldcount"
											required="required" size="15" value="0" /></td>
									</tr>
									<tr>
										<td><label>產品狀態:&nbsp;&nbsp;</label></td>
										<td><select name="prod_status">
												<option value="1">上架</option>
												<option value="2">下架</option>
										</select>
										<td>
									</tr>
									<tr>
										<!-- <td><label>評分:&nbsp;&nbsp;</label></td> -->
										<td><input type="hidden" name="prod_count" value="0" /></td>
									</tr>
									<tr>
										<!-- <td><label>總分:&nbsp;&nbsp;</label></td> -->
										<td><input type="hidden" name="prod_score" value="0" /></td>
									</tr>
								</table>
								<input type="hidden" name="action" value="insert_prod">
								<button type="submit" class="btn btn-default btn-sm">新增商品</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			</FORM>
		
				 <button class="btn btn-danger btn-sm"  onclick="magiclittlebtn1();"></button>
				 <button class="btn btn-danger btn-sm"  onclick="magiclittlebtn2();"></button>
				 <button class="btn btn-danger btn-sm"  onclick="magiclittlebtn3();"></button>
				 <button class="btn btn-danger btn-sm"  onclick="magiclittlebtn4();"></button>
				 <button class="btn btn-danger btn-sm"  onclick="magiclittlebtn5();"></button>
		</div>
	
	
	<br><br>
	


<% } %>

	<div class="callout"></div>
	
		
		<script type="text/javascript">

		function magiclittlebtn1() {
			document.getElementById('prpi_name').value = '海之霸餐廳招牌_海霸棒';
			document.getElementById('name').value = '海之霸餐廳招牌_海霸棒';
			document.getElementById('descript').value = '皮老闆做過的一個食品，曾讓蟹老闆想得到海霸棒秘方，曾讓納德去洗胃好幾次，曾在蟹蟹水果報裡說過「海霸棒讓你拉到爆」。';
			document.getElementById('price').value = 350;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '1入，個人享用。';
			document.getElementById('brand').value = '海之霸餐廳獨賣。';
		}
		
		function magiclittlebtn2() {
			document.getElementById('prpi_name').value = '海之霸餐廳_肉醬堡';
			document.getElementById('name').value = '海之霸餐廳_肉醬堡';
			document.getElementById('descript').value = '皮老闆做過的一個食品，因為實在是太難吃了，已至於讓皮老闆積極的想方設法偷取蟹老闆的美味蟹堡秘方。';
			document.getElementById('price').value = 450;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '1入，個人享用';
			document.getElementById('brand').value = '海之霸餐廳獨賣。';
		}
		
		function magiclittlebtn3() {
			document.getElementById('prpi_name').value = '海之霸餐廳_梅子蛋糕';
			document.getElementById('name').value = '海之霸餐廳_梅子蛋糕';
			document.getElementById('descript').value = '皮老闆做過的一個食品，發臭的梅子代替麵包發酵用的酵母菌，使麵包內部蓬鬆，外部軟爛，是海之霸餐廳的客人必點的點心。';
			document.getElementById('price').value = 150;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '1入，個人享用。';
			document.getElementById('brand').value = '海之霸餐廳獨賣。';
		}
		
		function magiclittlebtn4() {
			document.getElementById('prpi_name').value = '海之霸餐廳_藍莓冰茶';
			document.getElementById('name').value = '海之霸餐廳_藍莓冰茶';
			document.getElementById('descript').value = '皮老闆發明的特調，藍莓冰茶，嗯，沒甚麼好說的，就是一個飲料(濃縮果汁依獨家比例調配)。';
			document.getElementById('price').value = 200;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '1入，個人享用。';
			document.getElementById('brand').value = '海之霸餐廳獨賣。';
		}
		
		function magiclittlebtn5() {
			document.getElementById('prpi_name').value = '海之霸餐廳_驚奇辣海霸醬';
			document.getElementById('name').value = '海之霸餐廳_驚奇辣海霸醬';
			document.getElementById('descript').value = '皮老闆發明的獨特醬料，辣度堪比朝天椒；喜歡吃辣的人非常推薦帶一打回家享用。';
			document.getElementById('price').value = 450;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '12入。';
			document.getElementById('brand').value = '海之霸餐廳獨賣。';
		}
		
	</script>

	<script
		src="<%=request.getContextPath()%>/front-end/js_store/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker.js"></script>

	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap-datepicker.zh-TW.js"></script>

</body>
</html>