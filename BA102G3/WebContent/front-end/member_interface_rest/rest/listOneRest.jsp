<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rest.model.*,com.repi.model.*,com.avtb.model.*,com.reta.model.*"%>

<%
	RestVO restVO = (RestVO) session.getAttribute("restVO");
// 	AvtbVO avtbVO = (AvtbVO) request.getAttribute("avtbVO");
// 	RetaVO retaVO = (RetaVO) request.getAttribute("retaVO");
%>
<jsp:useBean id="dipiSvc" scope="page" class="com.dipi.model.DipiService" />
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />
<jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" />
<jsp:useBean id="repiSvc" scope="page" class="com.repi.model.RepiService"/>
<jsp:useBean id="avtbSvc" scope="page" class="com.avtb.model.AvtbService"/>

<!DOCTYPE html>
<head>
<title>餐廳資料 - listOnerest.jsp</title>

 <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<style>
.restName {
	text-align: center;
}

.bannerPic{
	text-align:center;
	margin-top:0;
}

.red {
	width: 1100px;
	height: 180px;
}
.dishPic {
	width: 500px;
	margin-top:0;
}

</style>

</head>
<%
java.util.Date sql = new Date(System.currentTimeMillis()); 
%>
<body>
<%@include file="headerBar.file" %>
	<div class="bannerPic">
		<img class="red" src="<%=request.getContextPath()%>/front-end/member_interface_rest/rest/images/red.png">
	</div>
	<div class="container">
		<div class="row">
		<!-------------    餐廳介紹          ------------>
			<div class="col-sm-6 col-md-4">
				<c:forEach var="repiVO" items="${repiSvc.all}">
					<c:if test="${restVO.rest_id==repiVO.rest_id}">
						<img class="img-thumbnail restPic"
							src="<%= request.getContextPath()%>/front-end/restaurant/repi/DBGifReader_repi.do?repi_id=${repiVO.repi_id}">
					</c:if>
				</c:forEach>
				<ul>
					<li>
						<p><small>餐廳名稱:</small><%=restVO.getRest_name()%></p>
					</li>
					<li>
						<p><small>電話:</small><%=restVO.getRest_phone()%></p>
					</li>
					<li>
						<p><small>營業時間:</small><%=restVO.getRest_hours()%></p>
					</li>
					<li>
						<p><small>位址:</small><%=restVO.getRest_address()%></p>
					</li>
					<li>
						 <p class="click-callback"><small>評等:</small></p>
						
						<div class="col-md-6">
									<c:if test="${empty userVO.user_account}" var="condition1" scope="session" > 
											<div class="btn btn-info"><a href='#modal-login' data-toggle="modal">請先登入</a></div>
									</c:if>		
						 <c:if test="${not empty userVO.user_account}" var="condition2" scope="session" >
							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/rest/rest.do"
								name="form2" id="form2"> 
								<input type="submit" value="訂位">
								<input type="hidden" name="rest_id" value="${restVO.rest_id}">
								<input type="hidden" name="action" value="getOne_For_Reta_formember">
							</FORM>
							</c:if>
							</div>
						</li>
					</ul>
				
				
			</div>
		<!-------------    餐廳介紹         ------------>	
			<div class="col-sm-6 col-md-8">
				<h2>餐廳介紹:</h2>
				<p>&nbsp;&nbsp;<%=restVO.getRest_detail()%></p>
		<!-------------    菜餚             ------------->			
					<div class="row">
							<c:forEach var="dishVO" items="${dishSvc.getDishsByRestId(restVO.rest_id)}" varStatus="s">
								
								<div class="col-sm-2 col-md-3">	
									<!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-info btn-basic" data-toggle="modal" data-target="#myModal${s.count}" >${dishVO.dish_name}</button>
									<br>
									<br>
								
									<!-- Modal -->
								  	<div class="modal fade" id="myModal${s.count}" role="dialog">
								    <div class="modal-dialog modal-lg">
								      <div class="modal-content">
								        
								        <div class="modal-header">
								          	<button type="button" class="close" data-dismiss="modal">&times;</button>
								         	<h2 class="modal-title bg-primary">${restVO.rest_name}</h2>
								        </div>
								        <div class="modal-body">
									        <div class="row">
												<div class="col-md-6">
										        	<c:forEach var="dipiVO" items="${dipiSvc.findDipisByDishId(dishVO.dish_id)}" >
														<c:if test="${dishVO.dish_id==dipiVO.dish_id}">
										        			<img class="img-thumbnail dishPic"src="<%= request.getContextPath()%>/front-end/restaurant/dipi/DBGifReader_dipi.do?dipi_id=${dipiVO.dipi_id}">
										        			<h3 ><small>料理名稱 :</small>&nbsp;${dishVO.dish_name}</h3>
										        			<h3>
										        				<small>供應時段 :</small>
									        					<span>${(dishVO.dish_status==1)? '目前提供中':'目前不提供此餐點 '}</span>&nbsp; | &nbsp;
									        					<c:if test="${dishVO.dish_note== 1}">
																	早餐
																</c:if>
																<c:if test="${dishVO.dish_note== 2}">
																	中餐
																</c:if>
																<c:if test="${dishVO.dish_note == 3}">
																	晚餐
																</c:if>
																<c:if test="${dishVO.dish_note== 4}">
																	下午茶
																</c:if>
																<c:if test="${dishVO.dish_note == 5}">
																	全天供應
																</c:if>
										        			</h3>
										        		</c:if>
										        	</c:forEach>
									        	</div>
									        	<div class="col-md-6">
									        		<h3 class="modal-title">料理簡介:</h3>
								        			${dishVO.dish_detail}
								        			
								        			<h3><small>料理價格 :</small>&nbsp; NT$. &nbsp;${dishVO.dish_price} &nbsp;</h3>
								        		</div>
								        	</div>
								        </div>
								        <div class="modal-footer">
								          	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								        </div>
								      </div>
								    </div>
								</div>
							</div>
							
						</c:forEach>
					</div>	  
				</div>
	<!-------------    菜餚  end        ------------>		
		</div>
	</div>
<%@ include file="/front-end/member_interface/script.file" %>	
<script src="<%= request.getContextPath() %>/front-end/js/stars.min.js"></script>


<script>

$(".click-callback").stars({ 
	value:${restVO.getRest_score()/restVO.getRest_count()},

	click: function(i) {
        i 
    }
});

</script>
</body>
</html>
