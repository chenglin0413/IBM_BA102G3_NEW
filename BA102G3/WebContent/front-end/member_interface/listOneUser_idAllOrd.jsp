<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.wish.model.*,com.wish.controller.*"%>
<%@ page import="com.prod.model.*,com.user.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%if (session.getAttribute("userVO") != null) {
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
	Integer user_id =userVO.getUser_id();
    pageContext.setAttribute("user_id",user_id);
}

%>
<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>listOneUser_idAllOrd</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/css/jquery-ui.css" />
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
    <style type="text/css">
        .item img{
            height: 250px;
            width:100%;
        }
        .panel-body{
        font-size:13px;
        border:#dcdcdc 1px solid; ;
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
        #accordion{
        font-size:12px;
        }
        #accordion>div{
        padding:5px;
        
        }
        .pop{
        font-size:10px;
        text-align:center;
        }
        
    </style>
</head>

<body>
 
 
     <%@include file="/front-end/member_interface/headerBar.file" %>
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
    <div class="row">
        <div class="callout"></div>
        <div class="col-md-11 col-xs-12" >
            <h3>消費記錄</h3>
        </div>         
    </div>
</div>
    
    <header id="myCarousel top" class="carousel slide">
    </header>
   
<div class="container content">
        <div class="row">
        <header  class="header">
        <div class="text-vertical-center">
            <h1>Anytime Grip</h1>
            <h3>SHOP OUR COLLECTIONS &amp; SHOP WITH RUNWAY</h3>
            <br>
            <a href="#about" class="btn btn-dark btn-lg">Grip Now!!</a>
        </div>
       </header> 
</div>
</div>
<div class="container">
<div class="row">        
          <ol class="breadcrumb">
               <li>
                   <a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a>
               </li>
               <li class="active">消費記錄</a>
               </li>
               
           </ol>
        


<!-- paneltitle -->
<div class="pop">
						<div class="col-xs-12 col-sm-2">訂單編號</div>
<!-- 						<div class="col-xs-12 col-sm-2">會員姓名</div> -->
						<div class="col-xs-12 col-sm-2">商家名稱</div>
						<div class="col-xs-12 col-sm-2">訂單日期</div>
						<div class="col-xs-12 col-sm-1">訂單金額</div>
						<div class="col-xs-12 col-sm-1">付款狀態</div>
						<div class="col-xs-12 col-sm-1">商店審核狀態</div>
						<div class="col-xs-12 col-sm-1">訂單狀態</div>
						<div class="col-xs-12 col-sm-2">對商店評分</div>
</div>
<br>		  
<!-- paneltitleEND -->
<!-- panelbodyStart -->
<div class="container">
<div class="row">

<div id="accordion">
<c:forEach var="ordVO" items="${ordSvc.getOneUser_idAllOrd(user_id)}" varStatus="s">
 
 <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;訂單編號#${ordVO.ord_id}</div>
 <div class="text-center">
			       		<!-- 內容 -->
			       		
			       		<div class="col-xs-12 col-sm-2">
  						【<A HREF="javascript:presses${s.index}()">${ordVO.ord_id}</a>】
  						</div>
<!-- 						<div class="col-xs-12 col-sm-2"> -->
<%-- 						<c:forEach var="userVO" items="${userSvc.all}"> --%>
<%-- 				                    <c:if test="${ordVO.user_id==userVO.user_id}"> --%>
<%-- 					                    	【${userVO.user_lastname}-${userVO.user_firstname}】 --%>
<%-- 				                    </c:if> --%>
<%-- 				                </c:forEach> --%>
<!-- 						</div> -->
						<div class="col-xs-12 col-sm-2">
						<c:forEach var="storeVO" items="${storeSvc.all}">
		                    <c:if test="${ordVO.store_id==storeVO.store_id}">
			                    	【${storeVO.store_name}】
		                    </c:if>
		                </c:forEach>
						</div>
						<div class="col-xs-12 col-sm-2">${ordVO.ord_date}</div>
						<div class="col-xs-12 col-sm-1">$${ordVO.ord_total}</div>
						<div class="col-xs-12 col-sm-1">
								 <c:if test="${ordVO.ord_bill == '1'}" var="condition" scope="page" > 
					            <span class="label label-danger">未付款</span>
					            </c:if>
					            <c:if test="${ordVO.ord_bill  == '2'}" var="condition" scope="page" > 
					           <span class="label label-warning">已付款</span>
					           </c:if>
					            <c:if test="${ordVO.ord_bill  == '3'}" var="condition" scope="page" > 
					           	<span class="label label-success">已結案</span>
					           </c:if>
						</div>
						<div class="col-xs-12 col-sm-1">
								 <c:if test="${ordVO.ord_grant == '1'}" var="condition" scope="page" > 
					            <span class="label label-danger">未審核</span>
					            </c:if>
					            <c:if test="${ordVO.ord_grant  == '2'}" var="condition" scope="page" > 
					          	<span class="label label-success">已審核</span>
					           </c:if>
						</div>
						<div class="col-xs-12 col-sm-1">
								<c:if test="${ordVO.ord_status == '1'}" var="condition" scope="page" > 
					            <span class="label label-danger">備貨中</span>
					            </c:if>
					            <c:if test="${ordVO.ord_status  == '2'}" var="condition" scope="page" > 
					            <span class="label label-warning">待取貨</span>
					           </c:if>
					            <c:if test="${ordVO.ord_status  == '3'}" var="condition" scope="page" > 
					            <span class="label label-success">已取貨</span>
					           </c:if>
						
						</div>
						<div class="col-xs-12 col-sm-2">
							 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do">
							   <c:if test="${ordVO.ord_sscore =='0'}">
								    <div class="col-xs-6"><select name="ord_sscore" value="${ordVO.ord_sscore }">
																	  <option value="1">負評</option>
																	  <option value="2">還行。</option>
																	  <option value="3">沒Fu。</option>
																	  <option value="4">不錯。</option>
																	  <option value="5">優質。</option>
									</select></div>
									 <div class="col-xs-6"><input type="submit" value="給予評價:"></div>
									 <input type="hidden" name="ord_id" value="${ordVO.ord_id }">
									<input type="hidden" name="action"	value="update_ord_sscore">
								</c:if></FORM>
								<c:if test="${ordVO.ord_sscore !='0'}"> 
								<b>已給過評價。</b>

								</c:if>		
						<script>
						 	//跳窗
						          function presses${s.index}(){
						         	 document.open("<%=request.getContextPath()%>/front-end/item/item.do?ord_id=${ordVO.ord_id}&action=getOne_Ord_id_AllItem_formember", "" ,"height=550,width=1270,left=50,top=107,resizable=yes,scrollbars=yes"); 
						          }
						 </script> 
						</div>
			       		<!-- 內容結束 -->
			      </div>
			      
			      </c:forEach>
			    </div>
			  </div>
 
 
 

<%-- 		<a href="<%=request.getContextPath()%>/front-end/item/item.do?ord_id=${ordVO.ord_id}&action=getOne_Ord_id_AllItem_formember">${ordVO.ord_id}</a></td> --%>
                   
</div>
</div>
</div>
			

<div class="callout"></div>
 <a id="to-top" href="#top" class="btn btn-dark btn-lg"><i class="fa fa-chevron-up fa-fw fa-1x"></i></a>
<%@ include file="/front-end/member_interface/script.file" %>	
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 

	
<script>
		$(function() {
			$("#accordion").accordion();
		});
</script>


</body>
</html>
