<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.flsc.model.*"%>

<%
    FlscService flscSvc = new FlscService();
    List<FlscVO> list = flscSvc.getAllArrive();
    pageContext.setAttribute("list",list);
%>

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
<link href="./css/bootstrap.css" rel="stylesheet">

</head>

<body bgcolor='white'>

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
		
     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/schedule/flsc.do" >
     					<div class="input-group">
       						<label class="input-group-addon">選擇抵達航班</label>
       						<select size="1" name="flsc_id" class="form-control">
         						<c:forEach var="flscVO" items="${list}" > 
          							<option value="${flscVO.flsc_id}">${flscVO.flsc_sdate} - ${flscVO.flsc_airlinecode}${flscVO.flsc_flno}
         						</c:forEach>   
       						</select>
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_For_Display_By_PK">
       					</div>
    					</FORM><br>		


     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/schedule/flsc.do" >
     					<div class="input-group">
       						<label class="input-group-addon">選擇抵達航班</label>
       						<select size="1" name="flsc_id" class="form-control">
         						<c:forEach var="flscVO" items="${list}" > 

                                <c:if test="${flscVO.flsc_term == '2'}" var="condition" scope="page">
          							<option value="${flscVO.flsc_id}">${flscVO.flsc_gate} - ${flscVO.flsc_sdate} - ${flscVO.flsc_airlinecode}${flscVO.flsc_flno}
								</c:if>						

         						</c:forEach>   
       						</select>
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_For_Display_By_PK">
       					</div>
    					</FORM><br>
    					
    					
     					<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/front-end/schedule/flsc.do" >
     					<div class="input-group">
       						<label class="input-group-addon">輸入的抵達的航班</label>

									<input type="text" name="flsc_sdate" class="form-control" placeholder="YYYY-MM-DD">
									<input type="text" name="flsc_airlinecode" class="form-control" placeholder="airline code">
									<input type="text" name="flsc_flno" class="form-control" placeholder="flight number">
			
       						<span class="input-group-btn">
       							<input type="submit" value="送出" class="btn btn-primary">
       						</span>
       						<input type="hidden" name="action" value="getOne_For_Display_inlistAllFlsc">
       					</div>
    					</FORM><br>	    							

	</div>
	<br>
	<br>

	

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</body>

</html>
