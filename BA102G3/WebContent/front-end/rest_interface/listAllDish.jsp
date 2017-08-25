<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dish.model.*,com.rest.model.*"%>

<!-- UserVO userVO=(UserVO)session.getAttribute("userVO") -->
<%

if(session.getAttribute("restVO") != null){
	RestVO restVO_dish = (RestVO) session.getAttribute("restVO");
	
    DishService dishSvc = new DishService();
    List<DishVO> lists = dishSvc.getDishsByRestId(restVO_dish.getRest_id());
    pageContext.setAttribute("lists",lists);
}
%>
<jsp:useBean id="dipiSvc" scope="page" class="com.dipi.model.DipiService"/>

<!DOCTYPE HTML> 
<html>
<head>
<title>所有料理資料 - listAllDish.jsp</title>


<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

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
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">


<script>
$( function() {
    $( "#accordion" ).accordion({
      collapsible: true,
      heightStyle: "content",
    });
  });
</script>

<style type="text/css">

 .pic{
 	width:350px;
	height:200px;
 }

#accordion {
    font-size: 20px;
}

.btnPosition {
	margin-top:10px;
	margin-bottom:10px;
	margin-left:15px;
}

#accordion h3{ 
  background:none;
  background-color:#66b2b2;         
}

.item {
	background-color:lightgray;
}

</style>

</head>

<body bgcolor='white'>
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
		<div class="col-md-1">
			<a href="<%=request.getContextPath()%>/front-end/rest_interface/addDish.jsp"><button class="btn-sm btn-primary">新增料理</button></a>			
		</div>
		<div class="col-md-11">
			<div id="accordion">
				<c:forEach var="dishVO" items="${lists}" varStatus="s">
						<h3>
							<span style="color:white;">	${s.count} </span>&nbsp;|&nbsp;
							<span style="color:white;">${dishVO.dish_id}</span>&nbsp;|&nbsp;
							<span style="color:white;">${dishVO.dish_name}</span>&nbsp;|&nbsp; 
							<span style="color:white;">$${dishVO.dish_price}</span>&nbsp;|&nbsp; 
							<span style="color:white;">
								<c:if test="${dishVO.dish_note== 1}">早餐</c:if>
								<c:if test="${dishVO.dish_note== 2}">中餐</c:if>
								<c:if test="${dishVO.dish_note== 3}">晚餐</c:if>
								<c:if test="${dishVO.dish_note== 4}">下午茶</c:if>
								<c:if test="${dishVO.dish_note == 5}">全天供應</c:if>
							</span>&nbsp;|&nbsp;
							<c:if test="${(dishVO.dish_status==1)}">
								<span style="color:#E1E100;" >供應中</span>
							</c:if>
							<c:if test="${(dishVO.dish_status==0)}">
								<span style="color:	red;" >暫不提供</span>
							</c:if>
							
						</h3>
					<div>			
						<c:forEach var="dipiVO" items="${dipiSvc.findDipisByDishId(dishVO.dish_id)}" >
							<c:if test="${dishVO.dish_id==dipiVO.dish_id}">
								<div class="row item">
									<div class="col-md-4">							
										<img class="img-thumbnail pic" src="<%= request.getContextPath()%>/front-end/restaurant/dipi/DBGifReader_dipi.do?dipi_id=${dipiVO.dipi_id}">
										<div class="row">
											<div class="col-md-6">
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/restaurant/dish/dish.do">
												    <input type="hidden" name="dish_id" value="${dishVO.dish_id}">
												    <input type="hidden" name="dipi_id" value="${dipiVO.dipi_id}">
												    <input type="hidden" name="rest_id" value="${dishVO.rest_id}">
												    <input type="hidden" name="action" value="getOne_For_Update">
												    <input type="submit" value="修改" class="btn-warning btnPosition">
												</FORM>
											</div>
											<div class="col-md-6">
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/restaurant/dish/dish.do">
													<input type="hidden" name="dipi_id" value="${dipiVO.dipi_id}">
												  	<input type="hidden" name="dish_id" value="${dishVO.dish_id}">
												    <input type="hidden" name="action" value="delete">
												    <input type="submit" class="btn-danger btnPosition" value="刪除">
												</FORM>
											</div>		
										</div>
									</div>	
									<div class="col-md-8">
									<td>料裡簡介:</td>
										<div style="text-align:left; font-size:120%;" >${dishVO.dish_detail}</div>
									</div> 
								</div><!-- row-end -->
							</c:if>
						</c:forEach>						
					</div>	
				</c:forEach>		
			</div>
		</div><!-- accordin -->
	</div><!-- row_end -->
</div><!-- container_end -->
</body>
</html>
