<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.flsc.model.*"%>
<%@ page import="com.path.model.*"%>

<%
FlscVO flscVO = (FlscVO) request.getAttribute("flscQuery");

PathService pathSvc = new PathService();
String customs="customs";

PathVO pathVO1 = pathSvc.getOnePathFromTo(flscVO.getFlsc_gate(),customs);
PathVO pathVO2 = pathSvc.getOnePathFromTo(customs,flscVO.getFlsc_bag());

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


<body bgcolor='white'>

	 <%@include file="/front-end/member_interface/headerBar.file" %>
    

	<div align="center">
	
<div>
     <h2>
		航班: <%=flscVO.getFlsc_airlinecode()%><%=flscVO.getFlsc_flno()%>
		將在登機門<%=flscVO.getFlsc_gate()%>下飛機<br>
		由登機門<%=flscVO.getFlsc_gate()%>到海關的路徑為<br>
		<img id="image" src="<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO1.getPath_id()%>" width="800" id="ImagePreview"><br>
		驗證完畢後,請到行李轉盤<%=flscVO.getFlsc_bag()%>領行李<br>
		<img id="image1" src="<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO2.getPath_id()%>" width="800" id="ImagePreview">
	</h2>
</div>
	
                    <div align="center">
                 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/path/findPath.jsp">
                            <div class="input-group">
   								<input type="submit" value="回上頁" class="btn btn-primary">
                            </div>
                        </FORM>                                	
                    </div>

<br>
		
		
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/path/leaflet.css">
	<script src="<%= request.getContextPath() %>/front-end/path/leaflet.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/path/jquery-ui.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/path/imgViewer2.js"></script>
	
</body>

<script>

$("#image").imgViewer2();
$("#image1").imgViewer2();

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
