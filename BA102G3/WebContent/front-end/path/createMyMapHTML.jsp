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

<title>我的地圖</title>

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
		
	    String user_id=request.getParameter("user_id");
	
		Integer flsc_id_arrive=new Integer(request.getParameter("flsc_id_arrive"));
		Integer flsc_id_out=new Integer(request.getParameter("flsc_id_out"));
	
	    FlscService flscSvc = new FlscService();
	    
	    FlscVO flscVOarrive=flscSvc.findByPK(flsc_id_arrive);
	    FlscVO flscVOout=flscSvc.findByPK(flsc_id_out);

    %>

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
		
<div>
		<%=flscVOarrive.getFlsc_sdate() %>, 抵達航班:<%=flscVOarrive.getFlsc_airlinecode() %><%=flscVOarrive.getFlsc_flno() %><br>
		<%=flscVOout.getFlsc_sdate() %>, 出發航班:<%=flscVOout.getFlsc_airlinecode() %><%=flscVOout.getFlsc_flno() %><br>
		
		<img src="<%= request.getContextPath() %>/front-end/image/tagYellow.png" width=40><b>:轉機安檢出口</b>
		<img src="<%= request.getContextPath() %>/front-end/image/tagBlue.png" width=40><b>:商店取貨</b>
		<img src="<%= request.getContextPath() %>/front-end/image/tagGreen.png" width=40><b>:已訂位的餐廳</b>
		<img src="<%= request.getContextPath() %>/front-end/image/tagRed.png" width=40><b>:下一航班登機口</b>
		
		<br>
		
		<img id="image" src="<%= request.getContextPath() %>/front-end/map/createMyMap.do?user_id=${param.user_id}&flsc_sdate1=<%=flscVOarrive.getFlsc_sdate() %>&flsc_airlinecode1=<%=flscVOarrive.getFlsc_airlinecode() %>&flsc_flno1=<%=flscVOarrive.getFlsc_flno() %>&flsc_sdate2=<%=flscVOout.getFlsc_sdate() %>&flsc_airlinecode2=<%=flscVOout.getFlsc_airlinecode() %>&flsc_flno2=<%=flscVOout.getFlsc_flno() %>" width=1000>

</div>

	</div>
	
                    <div align="center">
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/path/findPath.jsp">
                            <div class="input-group">
   								<input type="submit" value="回上頁" class="btn btn-primary">
                            </div>
                        </FORM>                                	
                    </div>		
	
	<br>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	
	<link rel="stylesheet" href="./leaflet.css">
	<script src="./leaflet.js"></script>
	<script src="./jquery-ui.min.js"></script>
	<script src="./imgViewer2.js"></script>

</body>


<script>

$("#image").imgViewer2();

$("#image").imgViewer2({

	  // zoom step
	  zoomStep: 0.5,

	  // the limit on the maximum zoom level of the image
	  zoomMax: undefined,

	  // is zoomable
	  zoomable: true,

	  // is draggable
	  dragable: true,

	  // callbacks
	  onClick: $.noop,
	  onReady: $.noop
	  
	});
</script>

</html>