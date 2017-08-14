<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trvl.model.*,com.user.model.*"%>

<%	

	UserVO userVO = (UserVO)session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
	Integer user_id =userVO.getUser_id();//取得會員ID
	pageContext.setAttribute("user_id",user_id);
	TrvlService trvlSvc = new TrvlService();
	List<TrvlVO> list = trvlSvc.getTrvlByUserId(user_id);
	 pageContext.setAttribute("list", list);

%>

<html>
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
   

    <title>個人遊記</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<%=request.getContextPath()%>/front-end/css/bootstrap.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/front-end/blog/css/stylish-portfolio.css" rel="stylesheet">
	
    <!-- Custom Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<style type="text/css">
        .item img{
            height: 250px;
            width:100%;
            
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

<body>
 <div class="callout"></div>
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
        <div class="col-md-11 col-xs-12" >
            <h3>_</h3>
        </div>         
    </div>
</div>


<div class="callout"></div>

 <div class="container">	
	<div class="row ">         
            
          <ol class="breadcrumb">
               
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/index.jsp">首頁</a>
               </li>
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/blog/listAllTrvl.jsp">瀏覽遊記</a>
               </li>
               <li class="active">個人遊記
               </li>
               
           </ol>
	<div class="container>">
		<div class="row">
		</div>
			<h4><div class="btn btn-dark"><a href="<%=request.getContextPath()%>/front-end/blog/addTrvl.jsp">新增遊記</a></div></h4>
		</div>
		<div class="container>">
		<div class="row">
		
			<div class="col-md-10 col-md-offset-1">
				<table class="table table-striped table-hover datatable">
					<jsp:useBean id="trpiSvc" scope="page" class="com.trpi.model.TrpiService" />
					<tr>
						<th>遊記編號</th>
						<th>撰寫日期</th>
						<th>旅遊標題</th>
						<th>旅遊地點</th>
						<th>點擊量</th>
						<th>總分</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="trvlVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${trvlVO.trvl_id}</td>
							<td>${trvlVO.trvl_date}</td>
							<td><a href="<%=request.getContextPath()%>/front-end/trvl/trvl.do?trvl_id=${trvlVO.trvl_id}&action=getOne_For_Display">${trvlVO.trvl_tittle}</a></td>
							<td>${trvlVO.trvl_loc}</td>
							<td>${trvlVO.trvl_count}</td>
							<td>${trvlVO.trvl_score}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front-end/trvl/trvl.do">
									<input type="submit" value="修改"> <input type="hidden"
										name="trvl_id" value="${trvlVO.trvl_id}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front-end/trvl/trvl.do">
									<input type="submit" value="刪除"> <input type="hidden"
										name="trvl_id" value="${trvlVO.trvl_id}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>	
		</div>	
	</div>			
	<%@ include file="page2.file"%>
</div>
</div>


 
<%@ include file="/front-end/member_interface/script.file" %>

</body>
</html>