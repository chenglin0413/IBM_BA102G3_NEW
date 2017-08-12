<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="com.sysu.model.*"%>
<%
SysuVO sysuVOLogin = (SysuVO) session.getAttribute("sysuVOLogin");

List<AuthVO> listauth = (List<AuthVO>) session.getAttribute("authVOLogin");
pageContext.setAttribute("listauth",listauth);

%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Anytime Grip 平台管理系統</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath() %>/back-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/back-end/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="<%= request.getContextPath() %>/back-end/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%= request.getContextPath() %>/back-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .pagination{
            margin-top: 0;
        }            
        a {
            text-decoration:none;
        }

    </style>

</head>

<body>

    <div id="wrapper">

            <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="<%= request.getContextPath() %>/back-end/index.jsp"><img src="<%= request.getContextPath() %>/back-end/image/logo1.png"></a>
                <a class="navbar-brand" href="<%= request.getContextPath() %>/back-end/index.jsp">Anytime Grip</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> 您好  ${sysuVOLogin.sysu_lastname}${sysuVOLogin.sysu_firstname} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<%= request.getContextPath() %>/back-end/sysu/adminSysuProfile.jsp"><i class="fa fa-fw fa-user"></i> 基本資料修改</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<%= request.getContextPath() %>/back-end/SysuLogoutHandler"><i class="fa fa-fw fa-power-off"></i> 登出</a>
                        </li>
                    </ul>
                </li>
                
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="<%= request.getContextPath() %>/back-end/index.jsp"><i class="fa fa-fw fa-home"></i> 平台管理首頁</a>
                    </li>

		<c:forEach var="authVO1" items="${listauth}" >
			<c:if test="${authVO1.func_id == '4100001'}" var="condition" scope="page" >

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#sysu"><i class="fa fa-fw fa-users"></i>  平台員工管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="sysu" class="collapse">
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/sysu/adminSysuAdd.jsp">新增</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/sysu/adminSysuQuery.jsp">查詢/修改</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/func/adminFuncListAll.jsp">功能調整</a>
                            </li>                            
                        </ul>
                    </li>
                    
			</c:if>
		</c:forEach>

		<c:forEach var="authVO1" items="${listauth}" >
			<c:if test="${authVO1.func_id == '4100002'}" var="condition" scope="page" >
                    
                    <li class="active">
                        <a href="javascript:;" data-toggle="collapse" data-target="#shop"><i class="fa fa-fw fa-shopping-cart"></i>  商家會員管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="shop" class="collapse in">
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/store/adminStoreWait.jsp">待審核</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/store/adminStoreAdd.jsp">新增</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/store/adminStoreQuery.jsp">查詢/修改</a>
                            </li>

                        </ul>
                    </li>

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#rest"><i class="fa fa-fw fa-coffee"></i>  餐廳會員管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="rest" class="collapse">
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/rest/adminRestWait.jsp">待審核</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/rest/adminRestAdd.jsp">新增</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/rest/adminRestQuery.jsp">查詢/修改</a>
                            </li>

                        </ul>
                    </li>

			</c:if>
		</c:forEach>
		
		<c:forEach var="authVO1" items="${listauth}" >
			<c:if test="${authVO1.func_id == '4100003'}" var="condition" scope="page" >

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#user"><i class="fa fa-fw fa-child"></i>  一般會員管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="user" class="collapse">
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/user/adminUserAdd.jsp">新增</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/user/adminUserQuery.jsp">查詢/修改</a>
                            </li>
                        </ul>
                    </li>

			</c:if>
		</c:forEach>

		<c:forEach var="authVO1" items="${listauth}" >
			<c:if test="${authVO1.func_id == '4100004'}" var="condition" scope="page" >

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#report"><i class="fa fa-fw fa-bomb"></i>  檢舉管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="report" class="collapse">
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/report/adminReportNew.jsp">未處理</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/report/adminReportQuery.jsp">查詢/修改</a>
                            </li>
                        </ul>
                    </li>
                    
			</c:if>
		</c:forEach>

		<c:forEach var="authVO1" items="${listauth}" >
			<c:if test="${authVO1.func_id == '4100005'}" var="condition" scope="page" >

                    <li>
                        <a href="<%= request.getContextPath() %>/back-end/mfee/adminMfeeQuery.jsp"><i class="fa fa-usd"></i> 月費管理</a>
                    </li>

			</c:if>
		</c:forEach>

		<c:forEach var="authVO1" items="${listauth}" >
			<c:if test="${authVO1.func_id == '4100006'}" var="condition" scope="page" >

                    <li>
                        <a href="<%= request.getContextPath() %>/back-end/blog/adminBlog.jsp"><i class="fa fa-fw fa-picture-o"></i> 旅遊日誌管理</a>
                    </li>

                    <li>
                        <a href="<%= request.getContextPath() %>/back-end/scheduler/adminScheduler.jsp"><i class="fa fa-fw fa-dashboard"></i> 排程器管理</a>
                    </li>

			</c:if>
		</c:forEach>

                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
        
<!-- 以上是拉進來的 -->


