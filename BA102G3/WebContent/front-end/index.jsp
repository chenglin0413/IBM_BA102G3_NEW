<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*,java.util.*,com.prod.model.*,com.trvl.model.*,com.rest.model.*"%>
<%
UserVO userVO = (UserVO) session.getAttribute("userVO");
String account =(String) session.getAttribute("account");
TrvlService trvlSvc = new TrvlService();
List<TrvlVO> trvllist = trvlSvc.getTopTrvlCounts();
pageContext.setAttribute("trvllist", trvllist);

String []rest_types={"","中式","西式","日式","穆斯林","印度","泰國","越南"};
pageContext.setAttribute("rest_types",rest_types);

%>
<jsp:useBean id="trpiSvc" scope="page" class="com.trpi.model.TrpiService" />
<jsp:useBean id="repiSvc" scope="page" class="com.repi.model.RepiService" />
<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Anytime Grip</title>

    <!-- Bootstrap Core CSS -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    
    <link href="<%= request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
   

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->



<style type="text/css">
        .item img{
            height: 270px;
            margin-bottom:1px;
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

<body >
   
     <!-- Navigation -->
    <!-- <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a> -->
    
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a href="<%=request.getContextPath()%>/front-end/index.jsp"><img src="<%=request.getContextPath()%>/front-end/img/logo3.png"></a>
                <a class="navbar-brand page-scroll" href="<%=request.getContextPath()%>/front-end/index.jsp">Anytime Grip</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    
                    
                    <li>
                        <a class="page-scroll" href="<%=request.getContextPath()%>/front-end/member_interface/listAllProd.jsp">商品</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="<%=request.getContextPath()%>/front-end/member_interface_rest/rest/listAllRest.jsp">餐廳</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="<%=request.getContextPath()%>/front-end/member_interface/pm_index.jsp">促銷</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="<%=request.getContextPath()%>/front-end/blog/listAllTrvl.jsp">旅遊日記</a>
                    </li>
                    
                    <li>
                        <a class="page-scroll" href="<%=request.getContextPath()%>/front-end/member_interface/schedule_index.jsp">交通運輸資訊</a>
                    </li>
                    
                   <!-- <li>
                        <a class="page-scroll" href="#contact"><i class="fa fa-question fa-fw"></i>檢舉管理</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="../about.html"><li>
                        <i class="fa fa-tasks fa-fw"></i>關於我們</a>
                    </li>-->
                  	<c:if test="${empty userVO.user_account}" var="condition1" scope="session" > 

						<li><a href='#modal-login' data-toggle="modal">
						    <i class="glyphicon glyphicon-user"></i> 登入</a></li>

					</c:if>		
                    <c:if test="${not empty userVO.user_account}" var="condition2" scope="session" > 
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu dropdown-user">
                    	  <li><a href="<%= request.getContextPath() %>/front-end/user/memberProfile.html"><i class="fa fa-user-md fa-fw"></i>${userVO.user_lastname},您好</a></li>       
				     	<li><a href="<%=request.getContextPath()%>/front-end/member_interface/listOneUser_idAllWish.jsp"><i class="fa fa-heart-o"></i>追蹤商品</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/member_interface/listOneUser_idAllOrd.jsp"><i class="fa fa-bookmark-o fa-fw"></i></i>消費記錄</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/member_interface_rest/rest/listOneUser_idAllReta.jsp"><i class="fa fa-book fa-fw"></i>餐廳訂位記錄</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/blog/listAllByUser.jsp"><i class="fa fa-camera-retro fa-fw"></i> 個人遊記</a></li>
                        <li><a href="<%= request.getContextPath() %>/front-end/user/memberProfile.jsp"><i class="fa fa-gear fa-fw"></i> 基本資訊修改</a></li>
                        <li><a href="<%= request.getContextPath() %>/front-end/user/memberPayFee.jsp"><i class="fa fa-money fa-fw"></i> 繳費</a></li>
                        <li class="divider"></li>
                        <li><a href="<%= request.getContextPath() %>/front-end/UserLogoutHandler"><i class="fa fa-sign-out fa-fw"></i> 登出</a></li>
                        
                        
                    </ul>
                    </li>
                     <li>
                        <a class="page-scroll " href="<%=request.getContextPath()%>/front-end/eshop/Cart.jsp"><i class="fa fa-shopping-cart"></i><span class="badge" name="twelve" style="background:lightgray;">${shoppingcart.size()}</span></a>
                    </li>
                    </c:if>	
                   
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

        

    

        <!-- Full Page Image Background Carousel Header02 -->
 <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
  

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <img src="<%= request.getContextPath() %>/front-end/img/slidebg13.jpg">
                <div class="carousel-caption">
                  <!--   <h2>Caption 1</h2> -->
                </div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <img src="<%= request.getContextPath() %>/front-end/img/slidebg12.jpg">
                <div class="carousel-caption">
                  <!--   <h2>Caption 2</h2> -->
                </div>
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <img src="<%= request.getContextPath() %>/front-end/img/slidebg11.jpg">
                <div class="carousel-caption">
                    <!-- <h2>Caption 3</h2> -->
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>
   <div class="hero-text">
            <h1>Anytime login, anytime Grip</h1>
            <button class="btn btn-lg btn-dark">Check Out Now!</button>
    </div>

    <!-- Callout間隔區 -->
    <div class="callout"></div>
    
    
     <div class="col-md-8">
     			  <section id="portfolio" class="bg-light-gray"> <!-- 商品 /餐廳Section -->
                <div class="col-md-12 text-center">
                    <h2 class="section-heading">熱門商品</h2>
                    <h3 class="section-subheading text-muted">Shopping area.</h3>
                </div>
                </section>	
            <jsp:useBean id="prodSvc" class="com.prod.model.ProdService"/>
            <%
            List<ProdVO> prodlist=prodSvc.getTopThree();
            pageContext.setAttribute("prodlist",prodlist);
            %>
            <c:forEach var="prodVO" items="${prodlist}" >
                <div class="col-md-4 col-sm-12 item text-center">
                     <div class="img-rounded" id="boxshadow"><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" width="300" height="250"></div>
					<div><h2>${prodVO.prod_name}</h2></div>
					<div class="AutoSkip"><h4>商品描述:${prodVO.prod_descript}</h4></div>
					<div><h4>商品種類:${prodVO.prod_sort}</h4></div>
                    <div>
                    
                    <a href='#${prodVO.prod_id}' data-toggle="modal" class="btn btn-info">瀏覽詳情</a>
					<div class="modal fade" id="${prodVO.prod_id}">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">${prodVO.prod_name}</h4>
								</div>
								<div class="modal-body">
									<div><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${prodVO.prod_id}" width="300" height="250"></div>
									<div class="col-md-6">
									<div><b>產品描述:</b><br> ${prodVO.prod_descript}</div>
									<div>評分次數: ${prodVO.prod_count}</div>
									<div>評分總分: ${prodVO.prod_score}</div>
									<div><p>售價:$<font color="red">${prodVO.prod_price}</font></p></div>
									</div>
									<div class="col-md-6">
									<c:if test="${empty userVO.user_account}" var="condition1" scope="session" > 
											<div class="btn btn-info"><a href='#modal-login' data-toggle="modal">請先登入</a></div>
											<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
									</c:if>		
									 <c:if test="${not empty userVO.user_account}" var="condition2" scope="session" > 
									 	<form name="shoppingForm" action="<%=request.getContextPath()%>/front-end/eshop/ShoppingServlet" method="POST">
										<div>數量： <input type="number" name="quantity" size="3" value=1 required></div>
							                 <button class="btn btn-success">加入購物車</button>
										  <input type="hidden" name="prod_id" value="${prodVO.prod_id}">
								      	  <input type="hidden" name="store_id" value="${prodVO.store_id}">
									      <input type="hidden" name="prod_name" value="${prodVO.prod_name}">
									      <input type="hidden" name="prod_descript" value="${prodVO.prod_descript}">
									      <input type="hidden" name="prod_price" value="${prodVO.prod_price}">
									      <input type="hidden" name="prod_sort" value="${prodVO.prod_sort}">
								     		<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->
								      		<input type="hidden" name="action" value="ADD">
								       </form>
								       <form name="wishForm" action="<%=request.getContextPath()%>/front-end/wish/wish.do" method="POST">
								       	  <input type="hidden" name="user_id" value="${userVO.user_id}">			
								       	  <input type="hidden" name="prod_id" value="${prodVO.prod_id}">
									       <button class="btn btn-info">加入追蹤</button>
									       <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->
									       <input type="hidden" name="action" value="ADDTOWish">
								       </form>
								       <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
								       </c:if>
									
									</div>
								</div>
								
								<div class="modal-footer">
									
								</div>
							</div>
						</div>
					</div>
                    
                   </div> 
                </div>
           </c:forEach>
         <!-- 商品前三結尾 -->  
           
           <jsp:useBean id="restSvc" class="com.rest.model.RestService"/>
                  <section id="portfolio" class="bg-light-gray"> <!-- 商品 /餐廳Section -->
                <div class="col-md-12 text-center">
                    <h2 class="section-heading">熱門餐廳</h2>
                    <h3 class="section-subheading text-muted">Shopping area.</h3>
                </div>
                </section>	
               <c:forEach var="restVO" items="${restSvc.getTopThree()}" >
                <div class="col-md-4 col-sm-12 item text-center">
                     <div class="img-rounded" id="boxshadow"><img src="<%=request.getContextPath()%>/front-end/repi/RepiDBGifReader.do?rest_id=${restVO.rest_id}" width="300" height="250"></div>
					<div><h2>${restVO.rest_name}</h2></div>
					<div class="AutoSkip"><h4>餐廳描述:${restVO.rest_detail}</h4></div>
					
					<div><h4>餐廳種類:${rest_types[restVO.rest_type]}</h4></div>
                </div>
                <!-- 餐廳前三結尾 -->
           </c:forEach>
     </div>
     			
     <div class="col-md-4">
     			  <!-- 遊記前四 -->
    <section id="trvl" class="trvl">
        <div class="col-md-12">
                <div class=" text-center">
                    <h2>熱門遊記</h2>
                    <p class="lead">The most popular blog in our website! 旅遊日記</p>
                </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->
    </section>
    <!-- row text-->

		
            <c:forEach var="trvlVO" items="${trvllist}">
					<div class="col-sm-6 col-md-12">
						<div class="ehdiv " >	
									<div class="row">
										<div class="col-md-8 ">
											<h3>${trvlVO.trvl_tittle}</h3> 
										</div>
									</div>				
							<c:forEach var="trpiVO" items="${trpiSvc.all}">
								<c:if test="${trvlVO.trvl_id==trpiVO.trvl_id}">
					            	<div class="item" id="boxshadow">
					            	<a  href="<%=request.getContextPath()%>/front-end/trvl/trvl.do?trvl_id=${trvlVO.trvl_id}&action=getOne_For_Display">
							        	<img  src="<%= request.getContextPath()%>/front-end/trpi/DBGifReader.do?trpi_id=${trpiVO.trpi_id}">
							        </a>
							        </div>
							    </c:if>
							</c:forEach>
							<br>
							
						</div>	
					</div>
					
				</c:forEach>
	</div>
		<!-- 遊記結尾 -->
    
    
   
   
<div class="callout"></div>


    <!-- Map -->
   
    <section id="contact" class="map">
        <!-- <iframe width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;aq=0&amp;oq=twitter&amp;sll=24.967743,121.191699&amp;sspn=0.128789,0.264187&amp;ie=UTF8&amp;hq=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;t=m&amp;z=15&amp;iwloc=A&amp;output=embed"></iframe> -->
        <iframe iframe width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"  src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7227.182606772993!2d121.23307974098319!3d25.081837760241932!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x34429fc062d215d5%3A0x70a3b690a9b5b109!2z6Ie654Gj5qGD5ZyS5ZyL6Zqb5qmf5aC0IChUUEUp!5e0!3m2!1szh-TW!2stw!4v1499268224354'></iframe>

        <br />
        <!-- <small>
            <a href="https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;aq=0&amp;oq=twitter&amp;sll=24.967743,121.191699&amp;sspn=0.128789,0.264187&amp;ie=UTF8&amp;hq=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;t=m&amp;z=15&amp;iwloc=A"></a>
        </small> -->
    </section>
    <div class="callout"></div>
    <!-- Footer -->
    <footer>
        <div class="container" >
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 text-center">
                    <h4><strong>Anytime Grip</strong>
                    </h4>
                    <p>320桃園市中壢區中大路300號
                        <br>"擁有超過30年歷史的資訊專長培訓中心"</p>
                    <ul class="list-unstyled">
                        <li><i class="fa fa-phone fa-fw"></i> (香港) 334-5678</li>
                        <li><i class="fa fa-envelope-o fa-fw"></i> <a href="mailto:name@example.com">Stephen Chow@gmail.com</a>
                        </li>
                    </ul>
                    <br>
                    <ul class="list-inline">
                        <li>
                            <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-dribbble fa-fw fa-3x"></i></a>
                        </li>
                    </ul>
                    <hr class="small">
                    <p class="text-muted">Copyright &copy; Your Website 2017</p>
                </div>
            </div>
        </div>
        <a id="to-top" href="#top" class="btn btn-dark btn-lg"><i class="fa fa-chevron-up fa-fw fa-1x"></i></a>
        
    </footer>
    

<div></div>

 <%@ include file="/front-end/member_interface/script.file" %>
 

</body>

</html>
