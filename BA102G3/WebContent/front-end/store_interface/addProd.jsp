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
					<div>
						<label>圖片名稱:&nbsp;&nbsp;</label><input type="TEXT"
							name="prpi_name" required="required" size="15" /> <img
							id="output" height="300" width="350" /> <input type="file" name="prpi_img" multiple accept="image/*" required="required" onchange="loadFile(event)" />

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
										<td><input type="TEXT" id="name" name="prod_name"	required="required" size="15" /></td>
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
			document.getElementById('name').value = '原味鳳梨酥';
			document.getElementById('descript').value = '奶蛋素。皮薄餡多、鬆香、不黏牙，夾藏著口感細緻的鳳梨美味，輕輕一口就可以感受到精緻茗點。';
			document.getElementById('price').value = 500;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '禮盒包裝*12入';
			document.getElementById('brand').value = '航站精品館';
		}
		
		function magiclittlebtn2() {
			document.getElementById('name').value = '精選花生糖';
			document.getElementById('descript').value = '選用粒粒分明的花生，搭配上香濃的麥芽糖，又香又脆的好滋味，怎麼能夠錯過！簡單又樸實的古早味，一口咬下，童年的美好回憶隨著化開的花生糖，再次回憶~';
			document.getElementById('price').value = 200;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '禮盒包裝*10入';
			document.getElementById('brand').value = '航站精品館';
		}
		
		function magiclittlebtn3() {
			document.getElementById('name').value = '楓糖太陽餅';
			document.getElementById('descript').value = '嚴選至加拿大的楓糖、咬一口如濃烈瑪琪朵般香氣、陣陣酥軟的滋味撲鼻而來…幸福口感直撲天頂、來Espaso、Cappuccino、花茶…有如夢中奇緣的仙境，是日本、香港、新加坡、馬來西亞的貴賓們最佳必帶的神秘饗食伴手禮物。 ';
			document.getElementById('price').value = 500;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '禮盒包裝*12入';
			document.getElementById('brand').value = '航站精品館';
		}
		
		function magiclittlebtn4() {
			document.getElementById('name').value = '冠軍麻糬';
			document.getElementById('descript').value = '冠軍麻糬,共有四種口味,分別為:黑天使-巧克力口味,海王子-XO干貝口味,幸福紫米-花生芝麻口味,含吉薯糬-地瓜葡萄干口味。';
			document.getElementById('price').value = 250;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '禮盒包裝*6入';
			document.getElementById('brand').value = '航站精品館';
		}
		
		function magiclittlebtn5() {
			document.getElementById('name').value = '透心涼冰心綠豆糕';
			document.getElementById('descript').value = '咬一口微甜的綠豆糕，再喝一口熱茶，感覺整個人都飄了起來。直接吃就超讚的綠豆糕，冰過後更多了一種透心涼的舒爽，風味更佳超推薦！';
			document.getElementById('price').value = 600;
			document.getElementById('sort').value = '伴手禮';
			document.getElementById('format').value = '禮盒包裝*20入';
			document.getElementById('brand').value = '航站精品館';
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