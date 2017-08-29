<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.avtb.model.*,com.rest.model.*,com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	
	
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account =(String) session.getAttribute("account");
		RestService restSvc= new RestService();
		RestVO restVO=restSvc.getOneRestByUser_Id(userVO.getUser_id());
// 		pageContext.setAttribute("restVO",restVO);

		AvtbService avtbSvc = new AvtbService();
		List<AvtbVO> list = avtbSvc.findByPrimaryKeyByRest(restVO.getRest_id());
		pageContext.setAttribute("list",list);
	
%>

<jsp:useBean id="userSvc"  scope="page" class="com.user.model.UserService"/>
<jsp:useBean id="repiSvc" scope="page" class="com.repi.model.RepiService"/>

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/3.0.3/normalize.css">

    <title>OneStore_idAllOrdselect.jsp</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/stylish-portfolio.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <style type="text/css">
        .item img{
            height: 300px;
            width: 100%;
        }
        th{
       text-align:center;
       font-size:14px;
       
        }
        li {
			list-style-type:none;
			}
    </style>
</head>

<body id="weather-background" class="default-weather">

    <%@include file="headerBar.file" %>

    <!-- Header -->
    <div class="callout" ></div>

   
	
   			<div id="page-wrapper">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                	
                	<h1>${restVO.rest_name}</h1>
                    <h3 class="page-header">訂單列表</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            </div>

 			<div id="page-wrapper col-md-12">
				<div class="col-md-4">
                <ol class="breadcrumb">
                	<li>
                        <a href="<%=request.getContextPath()%>/front-end/rest_interface/listOneRest_idAllDish.jsp">查看所有料理</a>
                    </li>
                    <li class="active">時段&人數管理</li>
                </ol>
                
<%--                 <img src="<%=request.getContextPath()%>/front-end/restaurant/repi/DBGifReader_repi.do?repi_id=${repiVO.repi_id}" --%>
<!-- 								width="300" height="200"> -->
                </div>
                <div class="col-md-4">
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/front-end/restaurant/reta/reta.do" --%>
<!-- 							name="form1"> -->
<!-- 							<div class="col-md-12"> -->
<!-- 								選擇訂位編號: <select size="1" name="reta_id"> -->
<!-- 									<option name="reta_id" value=""> -->
<%-- 										<c:forEach var="retaVO" --%>
<%--  											items="${retaSvc.findByUserId(userVO.user_id)}">  --%>
<%-- 											<option name="reta_id" value="${retaVO.reta_id}">${retaVO.reta_id} --%>
<%--  										</c:forEach>  --%>
<!-- 								</select> -->
<!-- 								<br> -->
<!-- 								<br> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-12"> -->
<!-- 								商店審核訂單狀態:&nbsp;<select name="ord_grant" value="1"> -->
<!-- 									<option name="ord_grant" value=""> -->
<!-- 									<option value="1">未審核</option> -->
<!-- 									<option value="2">審核</option> -->
<!-- 								</select><br> -->
			
<!-- 								訂單狀態:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="ord_status" -->
<!-- 									value="1"> -->
<!-- 									<option name="ord_status" value=""> -->
<!-- 									<option value="1">備貨中</option> -->
<!-- 									<option value="2">待取貨</option> -->
<!-- 									<option value="3">已取貨</option> -->
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 							<div> -->
<!-- 								<button class="btn btn-default">送出</button> -->
<%-- 								<input type="hidden" name="user_id" value="${userVO.user_id}"> --%>
<!-- 								<input type="hidden" name="action" -->
<!-- 									value="listReta_By_UserId"> -->
<!-- 							</div> -->
						</FORM>
				</div>
						<div class="col-md-4 ">
				
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
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
												<th>時段編號</th>
												<th>該時段目前訂位人數</th>
												<th>最大可訂位人數</th>
											</tr>
										</thead>
										
										<%@ include file="page1.file" %> 
											<c:forEach var="avtbVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
												<tr>
												<td>${avtbVO.avtb_id}</td>
												<td>${avtbVO.avtb_reservation}</td>
												<td>${avtbVO.avtb_max_reservation}</td>
												</tr>
  											</c:forEach>	
  											
									</table>	
						</div>
						</div>
						</div>
</div>
</div>
<%@ include file="page2.file" %>
<script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->

</body>

</html>
