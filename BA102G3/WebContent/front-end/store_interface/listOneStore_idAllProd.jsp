<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,java.lang.*"%>
<%@ page
	import="com.prod.model.*,com.store.model.*,com.item.model.*,com.user.model.*,com.ord.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account = (String) session.getAttribute("account");
		StoreService storeSvc = new StoreService();
		StoreVO storeVO = storeSvc.getOneStoreByUsed_Id(userVO.getUser_id());
		//將store_id存在session中
		Integer store_id=new Integer(storeVO.getStore_id());
		session.setAttribute("store_id",store_id);
		//將上線的store_id用set存起來
		Set<Integer> store_ids=new HashSet<Integer>();
		store_ids.add(store_id);
		application.setAttribute("store_ids", store_ids);
		//取得該商店會員所擁有的prod
		ProdService prodSvc = new ProdService();
		List<ProdVO> list = prodSvc.getOneStore_idAllProd(storeVO.getStore_id());
		pageContext.setAttribute("list", list);
		//計算未審核訂單、未備貨_待取貨的狀態數量
		OrdService ordSvc = new OrdService();
		List<OrdVO> ordlist=ordSvc.getOneStore_idAllOrd(storeVO.getStore_id());
		Integer unCheckOrd_grant=0;
		Integer unFinishOrd_status=0;
		for(OrdVO ordVO:ordlist){
			if(ordVO.getOrd_grant()==1){
				unCheckOrd_grant+=1;
			}
			if(ordVO.getOrd_status()==1||ordVO.getOrd_status()==2){
				unFinishOrd_status+=1;
			}
		}
		
		
%>
		




<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>OneStore_idAllProd.jsp</title>

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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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

<body  onunload="disconnect();">

	<%@include file="/front-end/store_interface/headerBar.file"%>
	<div class="callout"></div>
	<div class="container headBar1" >
		<div class="row">
			<div class="col-md-4 ">
				<div class="row">
				<h3 class="page-header">商品列表</h3>
				<img  id="boxshadow" src="<%=request.getContextPath()%>/front-end/user/userImg.do?user_id=${userVO.user_id}" height="150" width="120">
				<h4>目前登入人員:${userVO.user_lastname}${userVO.user_firstname}</h4>
				</div>
			</div>
			<div class="col-md-4  col-xs-6 text-left">
			<div class="callout"></div>
				<div>
					<strong>商店名稱:<%=storeVO.getStore_name()%></strong></div>
				<div>
					<b>商店描述:</b></div>
					<div><%=storeVO.getStore_describe()%></div>
				<div>
					商店營業時間:<%=storeVO.getStore_time()%></div>
				<div>
					商店營業電話:<%=storeVO.getStore_phone()%></div>
			</div>
			<div class="col-md-4  col-xs-6">
			<div class="callout"></div>
	                	<div class="panel panel-primary">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-comments fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge"><%=unFinishOrd_status %></div>
	                                    <div>尚未完結訂單數:</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                	
	                    <div class="panel panel-primary">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-book fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge"><%=unCheckOrd_grant%></div>
	                                    <div>待審核的訂單數:</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
		</div>
	</div>

	<div id="page-wrapper col-md-12">
		<div class="col-md-6">
			<ol class="breadcrumb">
				<li class="active">查看所有商品</li>
				
			</ol>
			<%@ include file="page1.file" %>
		</div>
		<!-- 查詢訂單，待套用萬用搜尋 -->

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
						<i class="fa fa-money fa-fw"></i>目前現有商品狀態 :
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>修改資料</th>
									<th>產品編號</th>
<!-- 									<th>商家編號</th> -->
									<th>產品名稱</th>
<!-- 									<th>產品描述</th> -->
									<th>產品價格</th>
									<th>產品種類</th>
<!-- 									<th>產品規格</th> -->
<!-- 									<th>產品品牌</th> -->
									<th>產品更新日期</th>
									<th>產品售出數量</th>
									<th>產品狀態</th>
									<th>產品被評分次數</th>
									<th>產品評分總分</th>






								</tr>
								
								<c:forEach var="prodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr align='center' valign='middle'>
										<div class="col-xs-12 col-md-10">
											<div class="item">
												<td>
													<!-- 點圖轉交修改 -->
													<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/store_interface/prod.do" enctype="multipart/form-data">
														<input type="hidden" name="prod_id"	value="${prodVO.prod_id}"> <input type="image" src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}"
															width="70" height="50" alt=" "> 
															<input	type="hidden" name="action" value="getOne_For_Update">
													</FORM>
												</td>
												<td>
														<a href='#${prodVO.prod_id}' data-toggle="modal" class="btn btn-info">${prodVO.prod_id}</a>
												<div class="modal fade" id="${prodVO.prod_id}">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
																<h4 class="modal-title">${prodVO.prod_name}</h4>
															</div>
															<div class="modal-body">
																<div id="boxshadow"><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" width="300" height="250"></div>
																<div class="col-md-12">
																<div>評分次數: ${prodVO.prod_count}</div>
																<div>評分總分: ${prodVO.prod_score}</div>
																
																<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='1'}" >
																	<div><del><p>價格:$<font color="red">${prodVO.prod_price}</font></p></del></div> 
																	<div><p>促銷價格:$<font color="red">${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_price}</font></p></div>
																</c:if>
																<c:if test="${PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status=='0'||PrpmSvc.getOneRmPrice_prod(prodVO.prod_id).prpm_status==null}" >
																	<div><p>價格:$<font color="red">${prodVO.prod_price}</font></p></div> 
																</c:if>
																</div>
															</div>
															
															<div class="modal-footer">
																
															</div>
														</div>
													</div>
												</div>
												</td>
<%-- 												<td>${prodVO.store_id}</td> --%>
												<td>${prodVO.prod_name}</td>
<%-- 												<td >${prodVO.prod_descript}</td> --%>
												<td>$${prodVO.prod_price}</td>
												<td>${prodVO.prod_sort}</td>
<%-- 												<td>${prodVO.prod_format}</td> --%>
<%-- 												<td>${prodVO.prod_brand}</td> --%>
												<td>${prodVO.prod_updatetime}</td>
												<td>${prodVO.prod_soldcount}</td>
												<td>${prodVO.prod_status}</td>
												<td>${prodVO.prod_count}</td>
												<td>${prodVO.prod_score}</td>
												</div>
												</div>
									</tr>

								</c:forEach>
						</table>
							
						<div class="col-xs-12 col-md-8 col-md-offset-4">
						<%@ include file="page2.file" %>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="container">
	<div class="row">
	
<!-- 聊天區塊 -->
			<div id="messagearea" class="chatbox" style="display: none;" >
				<div class="chatBar">
					<h3 id="test"></h3>
				</div>
				<div class="row">
					<div id="closechatbox" class="closechatbtn text-center btn-info"
						>X</div>
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
							<input type="hidden" id="userName"
								value="${ userVO.user_lastname}${ userVO.user_firstname}" />
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
			<!-- 聊天區塊結束 -->
</div>
</div>
	
	
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/front-end/js_store/bootstrap.min.js"></script>

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
		var MyPoint = "/MyEchoServer/"+<%=storeVO.getStore_id()%>+"/"+<%=userVO.getUser_id()%>;
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
