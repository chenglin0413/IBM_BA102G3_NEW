<%@page import="java.sql.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reta.model.*,com.avtb.model.*,com.rest.model.*,com.user.model.*"%>
<%
request.setCharacterEncoding("UTF-8");
RetaVO retaVO = (RetaVO) request.getAttribute("retaVO");
AvtbVO avtbVO = (AvtbVO) request.getAttribute("avtbVO");
RestVO restVO = (RestVO) request.getAttribute("restVO");
// UserVO userVO = (UserVO) session.getAttribute("userVO");
%>

<jsp:useBean id="avtbSvc" scope="page" class="com.avtb.model.AvtbService"></jsp:useBean>

<html>
<head>
<title>新增訂位</title>
<meta charset="UTF-8">	

	<!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">




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


<body bgcolor='white'>
<%@include file="headerBar.file" %>
	<br>
	<br>

	<h3>新增訂位</h3><a href="<%=request.getContextPath() %>/front-end/member_interface_rest/rest/listAllRest.jsp">回首頁</a>
	
	
	
<h3>訂位資料:</h3>
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
<%
java.util.Date sql = new Date(System.currentTimeMillis()); 
%>


<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/restaurant/reta/reta.do" name="form1" id="form1">
<table border="0">

	
	<b>選擇訂位日期:</b>
       <select size="1" name="avtb_id" id="select">
       <option value="123">
         <c:forEach var="avtbVO" items="${avtbSvc.findByPrimaryKeyByRest(restVO.rest_id)}" >
          	<option value="${avtbVO.avtb_id}">${avtbVO.avtb_date_s}--${avtbVO.avtb_date_e}
         </c:forEach>   
       </select>
	<tr>
	
		<td>使用者編號:</td>
		<td>${userVO.user_id}</td>
	</tr>
	<tr>
		<td>訂位人數:</td>
		<td><input type="TEXT" name="reta_number" size="45" 
			value="<%= (retaVO==null)? "" :retaVO.getReta_number() %>" required/>
		</td>
	</tr>
	<tr>
		<td>訂位成立日期:</td>
		<td><input type="TEXT" name="reta_date" size="45"
			value="<%= (retaVO==null)? sql : retaVO.getReta_date()%>" style="disable"/></td>
	</tr>
	
	<tr>
		<td>剩餘訂位人數:</td>
		<td id="number">${avtbVO.avtb_max_reservation-avtbVO.avtb_reservation}</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="user_id" value="${userVO.user_id}">
<input type="hidden" name="reta_grant" value="0">
<input type="hidden" name="rest_rpstatus" value="0">
<input type="hidden" name="reta_id" value="3400001">
<input type="hidden" name="reta_rank_res" value="0">
<input type="hidden" name="reta_status" value="0">
<input type="hidden" name="reta_review" value="">
<input type="submit" value="送出新增">
</FORM>



<%@ include file="/front-end/member_interface/script.file" %>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>


<script>
	$().ready(function() {
		$("#form1").validate({
			rules : {
				reta_number : {
					required : true,
					range : [ 1, 99 ],
					digits : true,
					number : true
				}
			},
			messages : {
				reta_number : {
					required : "請輸入訂位人數",
					digits : "請輸入整數",
					number : "請輸入整數",
					range : "輸入錯誤/人數過多"
				}
			}
		});
	

			
		
		$("#select").on('change', function() {
			 
			var sel_text = $("[id$=select]").find("option:selected").text()
		    var sel_val = $("[id$=select]").val();
			
			if(sel_val != 123){
			$.ajax({ 
		  		url : '<%=request.getContextPath()%>/front-end/restaurant/avtb/avtb.do', 
		  		type : 'POST', 
		  		data : { 
			   		
		  			action:'FFF',
		  			avtb_id: sel_val,
			  	}, 
		  	
			  	dataType: "text",
			  	
			    success : function(msg) { 
			    	console.log(msg)
			    	$("#number").html(msg).css("color","red");
			     }, 
			    error : function(xhr) { 
			     	alert("ajax發生錯誤"); 
			     	alert(xhr.status);  
			    } 
			   }); 
			}
		  });
		
		
	});
</script> 
</body>
</html>
