<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO");
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
       tbody{
                background-color: lightblue;
            }
        .pagination{
            margin-top: 0;
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
                <a href="index.html"><img src="<%= request.getContextPath() %>/back-end/image/logo1.png"></a>
                <a class="navbar-brand" href="index.html">Anytime Grip</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>administrator</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>administrator</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>administrator</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu alert-dropdown">
                        <li>
                            <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">View All</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Administrator <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="index.html"><i class="fa fa-fw fa-home"></i> 平台管理首頁</a>
                    </li>

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

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#shop"><i class="fa fa-fw fa-shopping-cart"></i>  商家會員管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="shop" class="collapse">
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
                                <a href="admin-rest-wait.html">待審核</a>
                            </li>
                            <li>
                                <a href="admin-rest-add.html">新增</a>
                            </li>
                            <li>
                                <a href="admin-rest-query.html">查詢/修改</a>
                            </li>
                        </ul>
                    </li>

                    <li class="active">
                        <a href="javascript:;" data-toggle="collapse" data-target="#user"><i class="fa fa-fw fa-child"></i>  一般會員管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="user" class="collapse in">
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/user/adminUserAdd.jsp">新增</a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() %>/back-end/user/adminUserQuery.jsp">查詢/修改</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#report"><i class="fa fa-fw fa-bomb"></i>  檢舉管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="report" class="collapse">
                            <li>
                                <a href="admin-report-wait.html">未處理</a>
                            </li>
                            <li>
                                <a href="admin-report-query.html">查詢/修改</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#storeprom"><i class="fa fa-fw fa-shopping-cart"></i> 商家促銷管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="storeprom" class="collapse">
                            <li>
                                <a href="admin-storeprom-current.html">目前促銷專案</a>
                            </li>
                            <li>
                                <a href="admin-storeprom-query.html">查詢/修改</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#restprom"><i class="fa fa-fw fa-coffee"></i> 餐廳促銷管理 <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="restprom" class="collapse">
                            <li>
                                <a href="admin-restprom-current.html">目前促銷專案</a>
                            </li>
                            <li>
                                <a href="admin-restprom-query.html">查詢/修改</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="admin-blog.html"><i class="fa fa-fw fa-picture-o"></i> 旅遊日誌管理</a>
                    </li>

                    <li>
                        <a href="admin-timer.html"><i class="fa fa-fw fa-dashboard"></i> 排程器管理</a>
                    </li>

                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>


        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">一般會員管理</a>
                    </li>
                    <li class="active">新增</li>
                </ol>


            <FORM class="uploadImage" METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/user/user.do" name="form1" enctype="multipart/form-data">

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
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
                            <div class="input-group">
                                <label class="input-group-addon">帳號</label>
                                <input type="text" name="user_account" value="<%= (userVO==null)? "" : userVO.getUser_account()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">姓</label>
                                <input type="text" name="user_lastname" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">名</label>
                                <input type="text" name="user_firstname" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>" class="form-control">
                            </div><br>                                

                            <div class="input-group">
                                <label class="input-group-addon">電話</label>
                                <input type="text" name="user_phone" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">手機</label>
                                <input type="text" name="user_mobile" value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">住址</label>
                                <input type="text" name="user_address" value="<%= (userVO==null)? "" : userVO.getUser_address()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">Email</label>
                                <input type="text" name="user_email" value="<%= (userVO==null)? "" : userVO.getUser_email()%>" class="form-control">
                            </div><br>

                    </div>                
                    
                    
                    
                     
                    <div class="col-lg-6">
                                                                        選擇圖片
                        <input type="file" name="upfile1" id="file-input1" class="file" onchange="previewImages()">
                        </label>
                        <div id="preview"></div>
                        
                    </div>
         
    </div>



                    
                    
                    
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->
            
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-1">
                        <input class="btn btn-primary" type="submit" value="送出">
                    </div>
                    <div class="col-xs-12 col-sm-1">
                        <input class="btn btn-primary" type="reset" value="重設">    
                    </div>
                </div>
            </div>

            <input type="hidden" name="action" value="insert">
            <input type="hidden" name="user_type" value="1">
            <input type="hidden" name="user_status" value="2">

            </form>

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/back-end/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/back-end/js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="<%= request.getContextPath() %>/back-end/js/plugins/morris/raphael.min.js"></script>
    <script src="<%= request.getContextPath() %>/back-end/js/plugins/morris/morris.min.js"></script>
    <script src="<%= request.getContextPath() %>/back-end/js/plugins/morris/morris-data.js"></script>

</body>

</html>

<script>
      
function previewImages() {
    
      var preview = document.getElementById('preview'); 
      
      if (this.files) {
        [].forEach.call(this.files, readAndPreview);
      }

      function readAndPreview(file) {
        
        var reader = new FileReader();
        
        reader.addEventListener("load", function() {
          var image = new Image();
          image.height = 150;
          image.title  = file.name;
          image.src    = this.result;
          preview.appendChild(image);
        }, false);
        
        reader.readAsDataURL(file);
        
      }

}

    document.getElementById('file-input1').addEventListener("change", previewImages, false);

</script>