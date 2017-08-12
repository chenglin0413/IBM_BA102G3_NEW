<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stpi.model.*,com.stpi.controller.*,com.store.model.*,com.store.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    StpiService stpiSvc = new StpiService();
    List<StpiVO> list = stpiSvc.getAll();
    pageContext.setAttribute("list",list);
   
%>


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>廠商瀏覽</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/stylish-portfolio.css" rel="stylesheet">
    <link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <style type="text/css">
        .item img{
            height: 250px;
            width:100%;
        }
          div[class*=col-] {
        border-right: 1px solid #ccc; border-bottom:1px solid #ccc;
        padding:5px;
        }
		div[class*=col-]:first-child {
		border-left: 1px solid #ccc; border-top:1px solid #ccc;
		padding:5px;
		}
        .content: {
          position: relative;
        }
        .box{
          width: 110px;
          height: 50px;
          
          position: fixed;
          top: 52px;
          left: 5px;
          margin: auto;
        }
    </style>
</head>
<title>所有廠商圖片資料 - listAllStpi.jsp</title>
<body>

 <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a href="../index.html"><img src="../img/logo3.png"></a>
                <a class="navbar-brand page-scroll" href="../index.html">Anytime Grip</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    
                    
                    <li>
                        <a class="page-scroll" href="store.html">商品</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="../rest/rest__2.html">餐廳</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="rest/rest.html">促銷</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="../blog/blog2.html">旅遊日記<span class="badge">10</span></a>
                    </li>
                    
                    <li>
                        <a class="page-scroll" href="#contact">交通運輸資訊</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact"><i class="fa fa-question fa-fw"></i>檢舉管理</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="../about.html"><li>
                        <i class="fa fa-tasks fa-fw"></i>關於我們</a>
                    </li>
                        <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="../memberOrderRecord.html"><i class="fa fa-info fa-fw"></i>消費記錄</a>
                        </li>
                        <li><a href="../memberReserveRecord.html"><i class="fa fa-book fa-fw"></i>餐廳訂位記錄</a>
                        </li>
                        <li><a href="../memberProfile.html"><i class="fa fa-gear fa-fw"></i> 基本資訊修改</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="../login.html"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
                        </li>
                        </li>
                    </ul>
                    </li>
                    <li>
                        <a class="page-scroll " href="../shopcar/shopcar.html"><i class="fa fa-shopping-cart"></i></a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>


        

<div class="container">
    <div class="row">
        <div class="callout"></div>
        <div class="col-md-11 col-xs-12" >
            <h3>廠商瀏覽</h3>
        </div>         
    </div>
</div>
    </div>
    <header id="myCarousel" class="carousel slide">
       <div class="container">
                <div class="row carousel-holder">
    
                    <div class="col-md-12">
                    <div class="row">
                         <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                         <!--    <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol> -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="../img/slidebg09.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="../img/slidebg08.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="../img/slidebg07.jpg" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div> 
                    </div>
                </div>
    </div>
    </header>
   
   <div class="hero-text">
            <h1 >Anytime login, anytime Grip</h1>
            <p>Enjoy your life with our service </p>
            <button class="btn btn-lg btn-dark">Check Out Now!</button>
    </div>

    <!-- Callout間隔區 -->
    <div class="callout"></div>

     
    <!-- Portfolio -->
<!-- Portfolio Grid Section -->
<div class="container content">
        
        <div class="row">
        <header id="top" class="header">
        <div class="text-vertical-center">
            <h1>Anytime Grip</h1>
            <h3>SHOP OUR COLLECTIONS &amp; SHOP WITH RUNWAY</h3>
            <br>
            <a href="#about" class="btn btn-dark btn-lg">Grip Now!!</a>
        </div>
       </header> 
          <ol class="breadcrumb">
               <li>
                   <a href="../index.html">首頁</a>
               </li>
               <li class="active">商店瀏覽</a>
               </li>
               
           </ol>
        
        <ul class="nav nav-tabs">
<!--                             <div class="dropdown"> -->
<!--                               <a href="#" class="dropdown-toggle" data-toggle="dropdown">商品種類 <b class="caret"></b></a> -->
<!--                               <ul class="dropdown-menu"> -->
<!--                                 <li><a href="#">零食、點心</a></li> -->
<!--                                 <li><a href="#">免稅菸酒</a></li> -->
<!--                                 <li><a href="#">國際精品</a></li> -->
<!--                               </ul> -->
<!--                             </div> -->
                            <li class="active"><a href="#"><i class="fa fa-puzzle-piece"></i>第一航廈</a></li>
                            <li><a href="#"><i class="fa fa-arrow-circle-o-up"></i>第二航廈</a></li>
                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i>伴手禮</a></li>
                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i>文具用品</a></li>
                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i>酒類</a></li>
                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i>生活用品</a></li>
                            <li><a href="#"><i class="fa fa-arrow-circle-o-down"></i>化妝用品</a></li>
         </ul>



<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有廠商圖片資料 - ListAllStpi.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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
<div class="row ">

<%@ include file="page1.file" %> 

</div>
</div>
	<c:forEach var="stpiVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
           <div class="col-xs-12 col-md-4">
              
                  <div class="item">
					
                    <img src="<%= request.getContextPath()%>/front-end/stpi/DBGifReader?stpi_id=${stpiVO.stpi_id}">
                    <h4><a href="#">${stpiVO.stpi_id}</a></h4>
                    <h4><a href="#">${stpiVO.store_id}</a></h4>
                    <p>${stpiVO.stpi_name}</p>
<%-- 					<jsp:useBean id="store"  class="com.store.model.StoreService"/> --%>
<%-- 					<jsp:setProperty name="store" property="onestore" value="${stpiVO.store_id}"/> --%>
<%-- 				 <p>${store.onestore}</p> --%>
		
                    
                   
                   <td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/stpi/stpi.do">
					     <input type="submit" value="修改">
					     <input type="hidden" name="stpi_id" value="${stpiVO.stpi_id}">
					     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
					</td>
					<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/stpi/stpi.do">
						    <input type="submit" value="刪除">
						    <input type="hidden" name="stpi_id" value="${stpiVO.stpi_id}">
						    <input type="hidden" name="action"value="delete"></FORM>
					</td>
                  </div>
           </div>
         
	</c:forEach>
<div class="col-xs-12 col-md-8 col-md-offset-4">
<%@ include file="page2.file" %>
</div>


      
	
    <script src="../js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
   
    <script src="js/jquery-1.11.0.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 

</script>
     <!-- Menu Toggle Script -->
   <script>
    // Closes the sidebar menu
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    // Opens the sidebar menu
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    // Scrolls to the selected menu item on the page
    $(function() {
        $('a[href*=#]:not([href=#],[data-toggle],[data-target],[data-slide])').click(function() {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });
    });
    //#to-top button appears after scrolling
    var fixed = false;
    $(document).scroll(function() {
        if ($(this).scrollTop() > 250) {
            if (!fixed) {
                fixed = true;
                // $('#to-top').css({position:'fixed', display:'block'});
                $('#to-top').show("slow", function() {
                    $('#to-top').css({
                        position: 'fixed',
                        display: 'block'
                    });
                });
            }
        } else {
            if (fixed) {
                fixed = false;
                $('#to-top').hide("slow", function() {
                    $('#to-top').css({
                        display: 'none'
                    });
                });
            }
        }
    });

    </script>
</body>
</html>
