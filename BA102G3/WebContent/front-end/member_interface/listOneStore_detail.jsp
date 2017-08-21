<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.store.model.*, com.prod.model.*,com.user.model.*,com.wish.model.*"%>

<%
	
	UserVO userVO=(UserVO)session.getAttribute("UserVO");
	StoreVO storeVO = null;
	Integer store_id=null;
	String store_no=null;
	if (request.getParameter("store_id") != null) {
		store_id = new Integer(request.getParameter("store_id"));
		session.setAttribute("store_id", store_id);//把store_id存起來
		StoreService storeSvc = new StoreService();
		storeVO = storeSvc.getOneStore(store_id);
		pageContext.setAttribute("storeVO", storeVO);
	} else {
		store_id = (Integer) session.getAttribute("store_id");
		StoreService storeSvc = new StoreService();
		storeVO = storeSvc.getOneStore(store_id);
		pageContext.setAttribute("storeVO", storeVO);
	}
	
%>
<jsp:useBean id="prodSvc" class="com.prod.model.ProdService" />

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>商店詳情</title>

<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/css/stylish-portfolio.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/front-end/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.item img {
	height: 180px;
	width: 70%;
	padding: 5px;
}

.content: {
	position: relative;
}

.item {
	width: 100%;
	height: 250px;
	margin: 10px;
}
#online{
	width:25px;
	height:25px;
}
</style>
</head>

<body  onunload="disconnect();">

	<%@include file="/front-end/member_interface/headerBar.file"%>
<div class="callout"></div>
	<div class="container">
		<div class="row">
		<h4>::::頭上的storeid${storeVO.store_id}:::客廳的storeid${applicationScope.store_id}</h4><!-- 測試商店上線狀態 -->
			<div class="col-md-12 ">
				<c:if test="${applicationScope.store_id== storeVO.store_id}" var="condition">  
							           <td>此商店上線狀態:&nbsp;<img id="online" src="<%=request.getContextPath() %>/front-end/image/circleGreen2.png"></td>
				</c:if>
				<c:if test="${applicationScope.store_id!= storeVO.store_id}" var="condition"> 
							           <td>此商店上線狀態:&nbsp;<img id="online" src="<%=request.getContextPath() %>/front-end/image/circleRed2.png"></td>
				</c:if>
				<h3>商店詳情</h3>
			</div>
		</div>
	</div>
	<div class="container content">

		<div class="row">
			<ol class="breadcrumb">
				<li><a
					href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
				<li><a
					href="<%=request.getContextPath()%>/front-end/member_interface/listAllProd.jsp">商品</a>
				</li>
				<li class="active">商店詳情
				</li>

			</ol>




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
				<div class="row "></div>
			</div>

			<div class="col-xs-12 col-md-12">

				<div class="item">

					<div class="col-md-6">
						<div class="item">
							<img
								src="<%=request.getContextPath()%>/front-end/stpi/DBGifReader?store_id=${storeVO.store_id}"
								width="300" height="250">

						</div>
					</div>
					<div class="col-md-6">
						<div class="row">
							<%-- 			<div id="store_id">${storeVO.store_id}</div> --%>
							<div id="store_name">
								<h3>商店名稱: ${storeVO.store_name}</h3>			
								</div>
							<div>商店描述: ${storeVO.store_describe}</div>
							<div>商店評價總分: ${storeVO.store_score}</div>
							<div>
								<h4>本商店位於: ${storeVO.store_ter}航廈，${storeVO.store_floor}樓</h4>
							</div>


						</div>
					</div>
				</div>
			</div>


			<div class="col-md-12">
				<div class="row">
					
						<div><h3>本店販售商品如下:</h3></div>
					
					<c:forEach var="prodVO"
						items="${prodSvc.getOneStore_idAllProd(storeVO.store_id)}">
						<div class="col-xs-12 col-md-4">
							<div class="item ">
								<a
									href="<%=request.getContextPath()%>/front-end/prod/prod.do?prod_id=${prodVO.prod_id}&action=getOne_For_Display"><div>${prodVO.prod_name}</div></a>
								<div id="boxshadow">
									<img
										src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}"
										width="300" height="250">
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
			<!-- 聊天區塊 -->
			         <c:if test="${userVO!=null}">
			<div id="messagearea" class="chatbox" style="display: none;"
				>
				<div class="chatBar">
					<h3 id="test"></h3>
				</div>
				<div class="row">
					<div id="closechatbox" class="closechatbtn text-center btn-info" >X</div>
					<div class="col-md-12">
						<textarea id="messagesArea" class="message-area" readonly></textarea>
					</div>
					<div class="input-area col-md-12">
						<div class="row">
							<div class="col-md-12">
								<input id="message" class="text-field" type="text"
									placeholder="inputMessage Here" size="35px"
									onkeydown="if (event.keyCode == 13) sendMessage();" />
							</div>
						</div>
						<div class="col-md-4 ">${ userVO.user_lastname}${ userVO.user_firstname}
							<input type="hidden" id="userName" value="${ userVO.user_id}" />
						</div>
						<div class="col-md-8 ">
							<input type="submit" id="sendMessage" class="button" value="Send"
								onclick="sendMessage();" />
						</div>
					</div>
				</div>
			</div>
			<div id="messagebtn" class="chatbtn text-center btn-info"
				onclick="connect();">ChatBox</div>
			       </c:if>
			<!-- 聊天區塊結束 -->
			<%-- </c:forEach> --%>

		</div>
	</div>

	<%@ include file="/front-end/member_interface/script.file"%>
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
		var MyPoint = "/MyEchoServer/"+<%=storeVO.getStore_id()%>+"/"+<%=storeVO.getStore_id()%>;
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

// 			webSocket.onclose = function(event) {
// 			};
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