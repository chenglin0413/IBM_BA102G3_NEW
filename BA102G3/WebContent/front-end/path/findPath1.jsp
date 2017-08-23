<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.flsc.model.*"%>
<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>航班時刻表</title>

<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

</head>

<body>

	 <%@include file="/front-end/member_interface/headerBar.file" %>

	<%
		FlscService flscSvc = new FlscService();

		List<FlscVO> listArrive = flscSvc.getAllArrive();
    	pageContext.setAttribute("listArrive",listArrive);

    	List<FlscVO> listOut = flscSvc.getAllOut();
    	pageContext.setAttribute("listOut",listOut);	
    %>

</head>
<body bgcolor='white'>

<br>

<div>
  <ul class="nav nav-tabs">
    <li><a href="<%= request.getContextPath() %>/front-end/schedule/listAllFlsc.jsp">班機查詢</a></li>
    <li class="active"><a href="<%= request.getContextPath() %>/front-end/path/findPath.jsp"><b>室內地圖導引</b></a></li>
  </ul>
  <br>
</div>


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

		<%
			java.sql.Date sdate_SQL = new java.sql.Date(System.currentTimeMillis());
		%>
		


<div>
						<label><font color="red">我的地圖1</font></label>
<!--   
     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/map/createMyMap.do">
-->     					

     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/path/createMyMapHTML1.jsp">
     					<div class="input-group">
       							
       							會員編號<input type="text" name="user_id" value="1000091" placeshold="1000091"><br>
       							
       							抵達日期<input type="text" name="flsc_sdate1" value="2017-08-20"><br>
       							航空公司<input type="text" name="flsc_airlinecode1" value="BR"><br>
       							班機編號<input type="text" name="flsc_flno1" value="868"><br>
       							
       							<br><br>

       							出發日期<input type="text" name="flsc_sdate2" value="2017-08-21"><br>
       							航空公司<input type="text" name="flsc_airlinecode2" value="BR"><br>
       							班機編號<input type="text" name="flsc_flno2" value="138"><br><br>
       							
       							<input type="submit" value="查詢" class="btn btn-primary">
       						
       					</div>
    					</FORM>
    					<br>	

</div>

<div>

						<label><font color="red">我的地圖2</font></label>
     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/path/createMyMapHTML.jsp">
     					<div class="input-group">
       							
       						會員編號<input type="text" name="user_id" value="1000091" placeshold="1000091"><br>

       						<label>選擇抵達航班</label>
       						<select size="1" name="flsc_id_arrive">
         						<c:forEach var="flscVOarrive" items="${listArrive}" > 
         						
                                	<c:if test="${flscVOarrive.flsc_term == '2'}" var="condition" scope="page">         						
          								<option value="${flscVOarrive.flsc_id}">${flscVOarrive.flsc_sdate} - ${flscVOarrive.flsc_stime}抵達 - 來自   ${flscVOarrive.flsc_location_c} - ${flscVOarrive.flsc_airline_c} - ${flscVOarrive.flsc_airlinecode}${flscVOarrive.flsc_flno} - ${flscVOarrive.flsc_gate}
          							</c:if>	
          								
         						</c:forEach>   
       						</select><br>

       						<label>選擇轉機航班</label>
       						<select size="1" name="flsc_id_out">
         						<c:forEach var="flscVOout" items="${listOut}" > 
         						
                                	<c:if test="${flscVOout.flsc_term == '2'}" var="condition" scope="page">         						
          								<option value="${flscVOout.flsc_id}">${flscVOout.flsc_sdate} - ${flscVOout.flsc_stime}出發 - 飛往   ${flscVOout.flsc_location_c} - ${flscVOout.flsc_airline_c} - ${flscVOout.flsc_airlinecode}${flscVOout.flsc_flno} - ${flscVOout.flsc_gate}
          							</c:if>	
          								
         						</c:forEach>   
       						</select>
       						
       						<input type="submit" value="查詢" class="btn btn-primary">

						</div>
    					</FORM>

</div>

<br><br>

<div> 
						<label><font color="red">入境路線導引</font>:尋找航班抵達的登機門及出關,提領行李的路徑</label>
     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/schedule/flsc.do">
     					<div class="input-group">
       						<label>選擇抵達航班</label>
       						<select size="1" name="flsc_id">
         						<c:forEach var="flscVOarrive" items="${listArrive}" > 
         						
                                	<c:if test="${flscVOarrive.flsc_term == '2'}" var="condition" scope="page">         						
          								<option value="${flscVOarrive.flsc_id}">${flscVOarrive.flsc_sdate} - ${flscVOarrive.flsc_stime}抵達 - 來自   ${flscVOarrive.flsc_location_c} - ${flscVOarrive.flsc_airline_c} - ${flscVOarrive.flsc_airlinecode}${flscVOarrive.flsc_flno} - ${flscVOarrive.flsc_gate}
          							</c:if>	
          								
         						</c:forEach>   
       						</select>
       		
       							<input type="submit" value="查詢" class="btn btn-primary">
       						
       						<input type="hidden" name="action" value="getOne_For_Display_By_PK">
       					</div>
    					</FORM>
    					<br>	
</div>		
		
<div> 
						<label><font color="red">出境路線導引</font></label>
     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/schedule/flsc.do">
     					<div class="input-group">
       						<label>選擇出發航班</label>
       						<select size="1" name="flsc_id">
         						<c:forEach var="flscVOout" items="${listOut}" > 
         						
                                	<c:if test="${flscVOout.flsc_term == '2'}" var="condition" scope="page">         						
          								<option value="${flscVOout.flsc_id}">${flscVOout.flsc_sdate} - ${flscVOout.flsc_stime}出發 - 飛往   ${flscVOout.flsc_location_c} - ${flscVOout.flsc_airline_c} - ${flscVOout.flsc_airlinecode}${flscVOout.flsc_flno} - ${flscVOout.flsc_gate}
          							</c:if>	
          								
         						</c:forEach>   
       						</select>
       		
       							<input type="submit" value="查詢" class="btn btn-primary">
       						
       						<input type="hidden" name="action" value="getOne_For_Display_By_PK">
       					</div>
    					</FORM>
    					<br>	
</div>			
		


<br><br>


		
		<FORM METHOD="post" ACTION="flsc.do">



			<b>航空代碼&班次編號:</b> <input type="text" name="flsc_airlinecode"
				size="20"> <input type="text" name="flsc_flno" size="20">
			<b>表定日期：</b> <input type="date" name="flsc_sdate"
				value="<%=sdate_SQL%>"> <input type="submit" value="送出">
			<input type="hidden" name="action"
				value="getOne_For_Display_inlistAllFlsc">
		</FORM>

	</div>
	
	<br>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$("tr:even").css("background-color", "#CFE0E1");
			$("tr:odd").css("background-color", "#ffffff");
		});
	</script>

</body>

</html>