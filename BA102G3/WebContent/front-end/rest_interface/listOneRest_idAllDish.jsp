<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,java.lang.*"%>
<%@ page
	import="com.rest.model.*,com.dish.model.*,com.reta.model.*,com.user.model.*"%>
<%
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		RestService restSvc = new RestService();
		RestVO restVO = restSvc.getOneRestByUser_Id(userVO.getUser_id());
		session.setAttribute("restVO",restVO);
		
// 		DishService dishSvc = new DishService();
// 		List<DishVO> list = dishSvc.getDishsByRestId(restVO.getRest_id());
		RetaService retaSvc = new RetaService();
		List<RetaVO> list=retaSvc.getAllRetaByRestID(restVO.getRest_id());
		//計算未審核訂單、未備貨_待取貨的狀態數量
		Integer unCheckOrd_grant=0;
		Integer unFinishOrd_status=0;
		
// 		for(OrdVO ordVO:ordlist){
// 			if(ordVO.getOrd_grant()==1){
// 				unCheckOrd_grant+=1;
// 			}
// 			if(ordVO.getOrd_status()==1||ordVO.getOrd_status()==2){
// 				unFinishOrd_status+=1;
// 			}
// 		}
		pageContext.setAttribute("list", list);
		
%>
<jsp:useBean id="repiSvc" scope="page" class="com.repi.model.RepiService"/>
<jsp:useBean id="avtbSvc" scope="page" class="com.avtb.model.AvtbService"/>	

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>OneStore_idAllProd.jsp</title>

<!-- jQuery library -->

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


<style type="text/css">
.item img {
	height: 300px;
	width: 100%;
	
}

th {
	text-align: center;
	font-size: 14px;
}
td{
 overflow : hidden;
  text-overflow : ellipsis;
  white-space : nowrap;
  width : 240px;
}
</style>
</head>

<body >

	<%@include file="/front-end/rest_interface/headerBar.file"%>
	<div class="callout"></div>
	<div class="container headBar1" >
	
		<div class="row">
			<div class="col-md-4 ">
				<div class="row">
				<img  id="boxshadow" src="<%=request.getContextPath()%>/front-end/user/userImg.do?user_id=${userVO.user_id}" height="150" width="120">
				<h4>目前登入人員:${userVO.user_lastname}${userVO.user_firstname}</h4>
				</div>
			</div>
			<div class="col-md-4  col-xs-6 text-left">
			<div class="callout"></div>
				<div>
					<strong>餐廳名稱:<%=restVO.getRest_name()%></strong></div>
				<div>
					<b>餐廳描述:</b></div>
					<div><%=restVO.getRest_detail()%></div>
				<div>
					餐廳營業時間:<%=restVO.getRest_hours()%></div>
				<div>
					餐廳營業電話:<%=restVO.getRest_phone()%></div>
			</div>
			<div class="col-md-4  col-xs-6">
			<div class="callout"></div>   	
		</div>
	</div>

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

	<div class="container-fluid col-md-12">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-money fa-fw"></i>目前現有料理狀態 :
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<%@include file="listAllDish.jsp" %>
					</div>
				</div>
			</div>

		</div>
	</div>

	<div class="container">
	<div class="row">
	
<!-- 聊天區塊 -->
<!-- 			<div id="messagearea" class="chatbox" style="display: none;" > -->
<!-- 				<div class="chatBar"> -->
<!-- 					<h3 id="test"></h3> -->
<!-- 				</div> -->
<!-- 				<div class="row"> -->
<!-- 					<div id="closechatbox" class="closechatbtn text-center btn-info" -->
<!-- 						onclick="disconnect();">X</div> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						<textarea id="messagesArea" class="message-area" readonly></textarea> -->
<!-- 					</div> -->
<!-- 					<div class="input-area col-md-12"> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-12"> -->
<!-- 								<input id="message" class="text-field" type="text" -->
<!-- 									placeholder="inputMessage Here" size="35px" -->
<!-- 									onkeydown="if (event.keyCode == 13) sendMessage();" /> -->
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 						<div class="col-md-4 ">${userVO.user_lastname}${userVO.user_firstname} --%>
<!-- 							<input type="hidden" id="userName" -->
<%-- 								value="${ userVO.user_lastname}${ userVO.user_firstname}" /> --%>
<!-- 						</div> -->
<!-- 						<div class="col-md-8 "> -->
<!-- 							<input type="submit" id="sendMessage" class="button" value="Send" -->
<!-- 								onclick="sendMessage();" /> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div id="messagebtn" class="chatbtn text-center btn-info" -->
<!-- 				onclick="connect();">ChatBox</div> -->
		           
			<!-- 聊天區塊結束 -->
</div>
</div>
	
	
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<!-- Custom Theme JavaScript -->
	<script type="text/javascript">
		//聊天
		function openMessage() {
			document.getElementById('messagebtn').style.display = 'none';
			document.getElementById('messagearea').style.display = '';

		}
		function closeMessage() {
			document.getElementById('messagebtn').style.display = '';
			document.getElementById('messagearea').style.display = 'none';

		}
		var MyPoint = "/MyEchoServer/"+<%=restVO.getRest_id()%>+"/"+<%=userVO.getUser_id()%>;
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

		var webSocket;
		var inputUserName = document.getElementById("userName");
		var userName = inputUserName.value;
		function connect() {
			// 建立 websocket 物件
			webSocket = new WebSocket(endPointURL);

			webSocket.onopen = function(event) {
				document.getElementById('sendMessage').disabled = false;
				document.getElementById('connect').disabled = true;
				document.getElementById('disconnect').disabled = false;

			};

			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				var message = jsonObj.userName + ": " + jsonObj.message
						+ "\r\n";
				messagesArea.value = messagesArea.value + message;
				webSocket.send(messagesArea.value);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			};

			webSocket.onclose = function(event) {

			};
		}

		//webSocket 區塊

		function sendMessage() {
			var time = new Date();
			var currentDateTime = '(' + time.getHours() + ':'
					+ time.getMinutes() + ')';
			var inputMessage = document.getElementById("message");
			console.log(currentDateTime);
			var message = inputMessage.value.trim() + '\r\n'
					+ currentDateTime.toString();

			if (message === "") {
				alert("訊息請勿空白!");
				inputMessage.focus();
			} else {
				var jsonObj = {
					"userName" : userName,
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}

		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('connect').disabled = false;
			document.getElementById('disconnect').disabled = true;
		}

		//webSocket 結束    

		function init() {
			var mBtn = document.getElementById("messagebtn");
			var closechatbox = document.getElementById("closechatbox");
			mBtn.addEventListener("click", openMessage, false);
			closechatbox.addEventListener("click", closeMessage, false);

		}
		window.onload = init;
	</script>

</body>

</html>
