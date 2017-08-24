<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reta.model.*,com.rest.model.*,com.user.model.*,com.repi.model.*,com.avtb.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	
	
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String account =(String) session.getAttribute("account");
		RestService restSvc= new RestService();
		RestVO restVO=restSvc.getOneRestByUser_Id(userVO.getUser_id());
// 		pageContext.setAttribute("restVO",restVO);

		AvtbService avtbSvc = new AvtbService();
		List<AvtbVO> list_avtb = avtbSvc.findByPrimaryKeyByRest(restVO.getRest_id());
	    pageContext.setAttribute("list_avtb", list_avtb);
	    
// 	    RetaService retaSvc_userid = new RetaService();
// 		RetaVO retaVO_userid = retaSvc_userid.getOneRetaByUserID();
%>

<jsp:useBean id="userSvc"  scope="page" class="com.user.model.UserService"/>
<%-- <jsp:useBean id="avtbSvc" scope="page" class="com.avtb.model.AvtbService"/> --%>
<jsp:useBean id="retaSvc" scope="page" class="com.reta.model.RetaService"/>

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
                    <li class="active">訂位管理</li>
                </ol>
                <c:if test="${restVO.rest_id==repiVO.rest_id}">
                <img src="<%=request.getContextPath()%>/front-end/restaurant/repi/DBGifReader_repi.do?repi_id=${repiVO.repi_id}"
								width="300" height="200">
				</c:if>
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
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>訂位編號</th>
									<th>時段編號</th>
									<th>會員編號</th>
									<th>訂位人數</th>
									<th>已訂位會員</th>
									<th>修改</th>
								</tr>
							</thead>

							<%-- 							<%@ include file="page1.file"%> --%>

							<c:forEach var="avtbVO" items="${list_avtb}">

								<tr>

									<td>${avtbVO.avtb_id}</td>
									<td>${avtbVO.avtb_reservation}</td>
									<td>${avtbVO.avtb_max_reservation}</td>
									<td>${avtbVO.avtb_max_reservation-avtbVO.avtb_reservation}</td>

									<td>
									<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/restaurant/reta/reta.do">
									<select name="user_id">
											<option>"查看會員"</option>
											<c:forEach var="retaVO" items="${retaSvc.all}" >
												<c:if test="${avtbVO.avtb_id == retaVO.avtb_id}">
													<option value="${retaVO.user_id}">${retaVO.user_id}</option>
												</c:if>
											</c:forEach>
									</select>
									<input type="submit" value="修改"> 
									<input type="hidden" name="action" value="getOne_For_Update_By_Userid">
									</FORM>
								</td>
								
								</tr>

							</c:forEach>
							
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%-- 	<%@ include file="page2.file" %> --%>

<script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
<script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->

</body>

</html>
