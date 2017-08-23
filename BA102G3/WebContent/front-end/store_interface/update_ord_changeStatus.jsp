<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*,com.item.model.*"%>
<%
	OrdVO ordVO = (OrdVO) request.getAttribute("ordVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	if (request.getAttribute("oneord_idAllItem") != null) {
		List<ItemVO> list = (List<ItemVO>) request.getAttribute("oneord_idAllItem");
	    pageContext.setAttribute("list",list);
	    
	}



%>
<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>OneStore_order_list</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css_store/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        div>img{
            margin-left:50px;
            
        }
        th{
       text-align:center;
       font-size:14px;
       
        }
    </style>
</head>

<body>

                           
    
 <%@include file="headerBar.file" %>
    <!-- Header -->
    <div class="callout" ></div>

   
	
   			<div id="page-wrapper">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="page-header">修改訂單狀態</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            </div>

 			<div id="page-wrapper col-md-12">
				<div class="col-md-6">
                <ol class="breadcrumb">
                    <li>
                         <a href="<%=request.getContextPath()%>/front-end/store_interface/listOneStore_idAllOrd.jsp">訂單管理</a>
                    </li>
                    <li class="active">修改訂單狀態</li>
                </ol>
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

<div class="container-fluid col-md-12 ">

                <div class="row">
						<div class="col-md-6">
						<div class="row">
							<div class="col-md-3"></div>
							<div class="col-md-8">
							<div class="row">
							<div><c:forEach var="userVO" items="${userSvc.all}">
					                    <c:if test="${ordVO.user_id==userVO.user_id}">
						                    <img  src="<%= request.getContextPath() %>/front-end/user/userImg.do?user_id=${userVO.user_id}" height="150" width="120">
					                    </c:if>
					            </c:forEach>
					        </div>
							<div >會員姓名:
							<c:forEach var="userVO" items="${userSvc.all}">
					                    <c:if test="${ordVO.user_id==userVO.user_id}">
						                    	【${userVO.user_lastname}-${userVO.user_firstname}】
					                    </c:if>
					                </c:forEach>
							</div>
							<div>會員編號:
									<c:forEach var="userVO" items="${userSvc.all}">
					                    <c:if test="${ordVO.user_id==userVO.user_id}">
						                    	【${userVO.user_id}】
					                    </c:if>
					                </c:forEach>
							</div>
							</div>
							</div>
							<div class="col-md-3"></div>
							<div class="col-md-12">
								<div class="row">

							                        <div class="panel panel-default">
							                            <div class="panel-heading">
							                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>訂單明細  : </h3>
							                            </div>
							                            <div class="panel-body">
							                                <div class="table-responsive">
							                                    <table class="table table-bordered table-hover table-striped">
							                                        <thead>
							                                            <tr>
							                                            	<th>產品圖片</th>
																			<th>訂單編號</th>
																			<th>產品編號</th>
																			<th>產品數量</th>
																	</tr>
																	<c:forEach var="itemVO" items="${list}" >
																		<tr align='center' valign='middle'>
																			<td><img src="<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id=${itemVO.prod_id}" width="70" height="50"></td>
																            <td>${itemVO.ord_id}</td>
																			<td>${itemVO.prod_id}</td>
																			<td>${itemVO.item_qty}</td>
																			
																			
																			
																		</tr>
																		 
																	</c:forEach>
																</table>
							
																</div>
																</div>
																</div>
																</div>
																</div>
																</div>
																</div>
						<div class="col-md-5">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>目前的訂單狀態  :</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped text-center">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord/ord.do" name="form1">
												<tr>
													<td>訂單編號:<font color=red><b>*</b></font></td>
													<td><%=ordVO.getOrd_id()%></td>
												</tr>
												<tr>
												<td>會員姓名:</td><td><c:forEach var="userVO" items="${userSvc.all}">
										                    <c:if test="${ordVO.user_id==userVO.user_id}">
											                    	【${userVO.user_lastname}-${userVO.user_firstname}-${userVO.user_id}】
										                    </c:if>
										                </c:forEach>
												</td>
												</tr>
												<tr>
												<td>商店名稱:</td><td><c:forEach var="storeVO" items="${storeSvc.all}">
										                    <c:if test="${ordVO.store_id==storeVO.store_id}">
											                    	【${storeVO.store_id}</a>-${storeVO.store_name}】
										                    </c:if>
										                </c:forEach>
												</td>
												</tr>
												<tr>
													<td>訂單日期:</td>
													<td><%=ordVO.getOrd_date()%></td>
												</tr>
												<tr>
													<td>總價:</td>
													<td>$<%=ordVO.getOrd_total()%></td>
												</tr>
												
												<tr>
													<td>買家對於商店評分:</td>
													<td><%=ordVO.getOrd_sscore()%></td>
												</tr>
<!-- 												<tr> -->
<!-- 													<td>商家檢舉訂單日期:</td> -->
<%-- 													<td><%=ordVO.getOrd_rpdate()%></td> --%>
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td>檢舉訂單理由:</td> -->
<%-- 													<td><%=ordVO.getOrd_rpcomm()%></td> --%>
<!-- 												</tr> -->
<!-- 												<tr> -->
<!-- 													<td>商家檢舉訂單狀態:</td> -->
<%-- 													<td><%=ordVO.getOrd_status()%></td> --%>
<!-- 												</tr> -->
												</table>
		
		

		<div class="col-md-4">
		<h6>買家原付款狀態:&nbsp;&nbsp;&nbsp;&nbsp; </h6>
		<c:if test="${ordVO.ord_bill == '1'}" var="condition" scope="page" > 
	            <h3><font color="red">未付款</font></h3>
	            </c:if>
	            <c:if test="${ordVO.ord_bill  == '2'}" var="condition" scope="page" > 
	           <h3><font color="#F0C964">已付款</font></h3>
	    </c:if>
	     <c:if test="${ordVO.ord_bill  == '3'}" var="condition" scope="page" > 
	           <h3><font color="green">已結案</font></h3>
	    </c:if>
			<select name="ord_bill" id="ord_bill" value="<%=ordVO.getOrd_bill()%>">
			    <c:if test="${ordVO.ord_bill  == '1'}" var="condition" scope="page" > 
			  <option value="1" selected>未付款</option>
			  <option value="2" >已付款</option>
			  <option value="3" >已結案</option>
			  </c:if>
			  <c:if test="${ordVO.ord_bill  == '2'}" var="condition" scope="page" > 
			    <option value="1" >未付款</option>
			  <option value="2" selected >已付款</option>
			  <option value="3" >已結案</option>
			  </c:if>
			   <c:if test="${ordVO.ord_bill  == '3'}" var="condition" scope="page" > 
			    <option value="1" >未付款</option>
			  <option value="2" >已付款</option>
			  <option value="3" selected >已結案</option>
			   </c:if>
			</select>
		</div>
		
		<div class="col-md-4">
		<h6>商店審核原訂單狀態:&nbsp;</h6>
		<c:if test="${ordVO.ord_grant == '1'}" var="condition" scope="page" > 
	            <h3><font color="red">未審核</font></h3>
	            </c:if>
	            <c:if test="${ordVO.ord_grant  == '2'}" var="condition" scope="page" > 
	           <h3><font color="green">已審核</font></h3>
	    </c:if>
		<select name="ord_grant" id="ord_grant" value="<%=ordVO.getOrd_grant()%>">
			    <c:if test="${ordVO.ord_grant  == '1'}" var="condition" scope="page" > 
			  <option value="1" selected>未審核</option>
			  <option value="2">已審核</option>
			  </c:if>
			  <c:if test="${ordVO.ord_grant  == '2'}" var="condition" scope="page" > 
			  <option value="1">未審核</option>
			  <option value="2" selected>已審核</option>
			  </c:if>
			</select>
		
		</div>
		
		<div class="col-md-4">
		<h6>訂單:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h6>
		<c:if test="${ordVO.ord_status == '1'}" var="condition" scope="page" > 
	            <h3><font color="red">備貨中</font></h3>
	            </c:if>
	            <c:if test="${ordVO.ord_status  == '2'}" var="condition" scope="page" > 
	           <h3><font color="#F0C964">待取貨</font></h3>
	    </c:if>
	     <c:if test="${ordVO.ord_status  == '3'}" var="condition" scope="page" > 
	           <h3><font color="green">已取貨</font></h3>
	    </c:if>
		<select name="ord_status" id="ord_status" value="<%=ordVO.getOrd_status()%>">
			    <c:if test="${ordVO.ord_status  == '1'}" var="condition" scope="page" > 
			  <option value="1" selected>備貨中</option>
			  <option value="2" >待取貨</option>
			  <option value="3" >已取貨</option>
			  </c:if>
			  <c:if test="${ordVO.ord_status  == '2'}" var="condition" scope="page" > 
			    <option value="1" >備貨中</option>
			  <option value="2" selected >待取貨</option>
			  <option value="3" >已取貨</option>
			  </c:if>
			   <c:if test="${ordVO.ord_status  == '3'}" var="condition" scope="page" > 
			    <option value="1" >備貨中</option>
			  <option value="2" >待取貨</option>
			  <option value="3" selected >已取貨</option>
			   </c:if>
			</select>
		
		</div>
<br>
<input type="hidden" name="action" value="update_changeStatus">
<input type="hidden" name="ord_id" id="ord_id" value="<%=ordVO.getOrd_id()%>">
<input type="hidden" name="user_id" id="user_id" value="<%=ordVO.getUser_id()%>">
<input type="hidden" name="store_id" id="store_id" value="<%=ordVO.getStore_id()%>">
<input type="hidden" name="ord_date"  id="ord_date" value="<%=ordVO.getOrd_date()%>">
<input type="hidden" name="ord_total" id="ord_total" value="<%=ordVO.getOrd_total()%>">
<input type="hidden" name="ord_sscore" id="ord_sscore" value="<%=ordVO.getOrd_sscore()%>">
<input type="hidden" name="ord_rpdate" id="ord_rpdate" value="<%=ordVO.getOrd_rpdate()%>">
<input type="hidden" name="ord_rpcomm" id="ord_rpcomm" value="<%=ordVO.getOrd_rpcomm()%>">
<input type="hidden" name="ord_rpstatus" id="ord_rpstatus" value="<%=ordVO.getOrd_rpstatus()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
<div class="col-md-12">
<input type="submit" value="送出修改"></FORM>
</div>

</div>
</div>
</div>
</div>





</div>
</div>


<script src="<%= request.getContextPath() %>/front-end/js_store/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/front-end/js_store/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->





</body>
</html>
