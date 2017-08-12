<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.prod.model.ProdVO,com.ord.model.*,com.shopping.controller.*,com.item.model.*"%>
<%@ page import="com.user.model.*"%>

<%UserVO userVO =  (UserVO)session.getAttribute("userVO"); 


%>

<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>最後確認_購物明細</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .item img{
            height: 120px;
            width:40%;
            
        }
       
        .content: {
          position: relative;
        }
        .item{
          width: 100%px;
          height: 200px;  
          margin: auto;
        }
    </style>
</head>

<body>
 
     <%@include file="/front-end/member_interface/headerBar.file" %>


        

<div class="container">
    <div class="row">
        <div class="callout"></div>
        <div class="col-md-11 col-xs-12" >
            <h3>親愛的客戶${userVO.user_lastname}${userVO.user_firstname}，最後確認_購物明細</h3>
        </div>         
    </div>
</div>
</div>

   
<div class="container content">
        
        <div class="row">
        
          <ol class="breadcrumb">
               <li>
                   <a href="<%=request.getContextPath() %>/front-end/index.jsp">首頁</a>
               </li>
               <li>
               		<a href="<%=request.getContextPath() %>/front-end/member_interface/listAllProd.jsp">商品</a>
               </li>
               <li>
               		<a href="<%=request.getContextPath() %>/front-end/eshop/Cart.jsp">購物車</a>
               </li>
	            <li class="active">確認_購物明細頁面</a>
	               </li>
               
           </ol>
        
   


	
	<%
		Vector<ProdVO> buylist = (Vector<ProdVO>) session.getAttribute("shoppingcart");
	    Vector<Integer> buyqty = (Vector<Integer>) session.getAttribute("shoppingqty");
		String amount =  (String) request.getAttribute("amount");
		Vector<OrdVO> addOrd=new Vector<OrdVO>();
		Vector<ItemVO> addItem=new Vector<ItemVO>();
		Hashtable<Integer,Integer> ordcount=new Hashtable<Integer,Integer>();//顯示每張訂單的store_total和store_id
		
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			ProdVO   prodVO = buylist.get(i);
			
			Integer  prod_id = prodVO.getProd_id();
			Integer  store_id = prodVO.getStore_id();
			String   prod_name = prodVO.getProd_name();
			String   prod_descript= prodVO.getProd_descript();
			int  prod_price=prodVO.getProd_price();
			String   prod_sort=prodVO.getProd_sort();
// 			String   prod_format=prodVO.getProd_format();
// 			String   prod_brand=prodVO.getProd_brand();
// 			Date     prod_updatetime=prodVO.getProd_updatetime();
// 			Integer  prod_soldcount=prodVO.getProd_soldcount();
// 			Integer  prod_status=prodVO.getProd_status();
// 			Integer  prod_count=prodVO.getProd_count();
// 			Integer  prod_score=prodVO.getProd_score();
			%>
			
<!-- 	%> -->
	<div class="container">
	<div class="row">
	<div class="col-xs-12 col-md-8 col-md-offset-2">
              
         <div class="item">
			<div class="row">
				<div class="col-md-6"><img src="https://api.fnkr.net/testimg/150x100/00CED1/FFF/?text=img+placeholder"></div>
				<div class="col-md-6">
				<div class="panel panel-default"><%=prod_name%></div>
				<div class="col-md-12">
				<div class="row">
				<div><%=prod_descript%></div><div><%=prod_sort%></div>
				</div>
				</div>
				<div class="col-md-12">
				<div class="row">
				<div class="col-md-6"><b>$<%=prod_price%></b></div><div class="col-md-6 text-right"><b>X<%=buyqty.get(i) %></b></div>
				</div>
				</div>
				</div>
			</div> 
        </div>       
	</div>
	</div>
	</div>
<%				
				ItemVO   itemVO = new ItemVO();
				itemVO.setProd_id(prod_id);
				itemVO.setItem_qty(buyqty.get(i));
				itemVO.setItem_score(0);
				itemVO.setItem_review("");
				itemVO.setItem_reviewdate(null);
				addItem.add(itemVO);
				if(ordcount.get(store_id)==null){
					ordcount.put(store_id,prod_price*(int)buyqty.get(i));//新的store_id
					
				}else if(ordcount.containsKey(store_id)){
					ordcount.put(store_id,ordcount.get(store_id)+prod_price*(int)buyqty.get(i));
				}
				
				
				
}%>	
	
	<%
				
				Set<Integer> set =ordcount.keySet();
				Iterator<Integer> it=set.iterator();
				int count=0;
// 				UserVO userVO =  (UserVO)session.getAttribute("userVO");
				while(it.hasNext()){
					OrdVO ordVO = new OrdVO();
					Integer store_id=(Integer)it.next();
					ordVO.setUser_id(userVO.getUser_id()); //以後接上登入後改
					ordVO.setStore_id(store_id);
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis()); 
	   				ordVO.setOrd_date(date_SQL);
	   				ordVO.setOrd_total(ordcount.get(store_id));
					addOrd.add(ordVO);
					count+=1;
					%>
									
					
				      
					
  		   		<%}%>
				
	

	<div class="container">
	<div class="row">
		<div class="text-right"><b>訂單總金額：</b><font color="red"><b>$<%=session.getAttribute("amount")%></b></font></div>
		<div class="text-right"><b>來自幾個店家:<font color="red"><%=count%>個</font></b></div>
		<div class="text-right"><b>注意事項: 訂單成立後請確認信箱或簡訊內容，並注意取貨時間，謝謝</b></div>
	</div>
	</div>
 
 
  <%session.removeAttribute("addOrd");
  session.removeAttribute("addItem");
  session.removeAttribute("shoppingcart");
  session.removeAttribute("shoppingqty");
  session.removeAttribute("amout");
  session.removeAttribute("ord_ids");//訂單編號的集合
  %>
	

<div class="container">
    <div class="row">
   
			<div class="col-xs-12 col-md-11">
        		<div class="btn btn-default"><a href="<%=request.getContextPath()%>/front-end/index.jsp">回到首頁</a></div>
      		</div>
    
    </div>
 	</div>



<!-- 登入模組 開始-->
	<div class="modal fade" id="modal-login">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">會員登入</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
							
								<form action="<%= request.getContextPath() %>/front-end/UserLoginHandler" method="POST" id="loginForm">
									<div class="form-group">
										<label for="name">帳號</label> <input type="text"
											class="form-control" name="account" id="name"
											placeholder="請輸入您的帳號">
									</div>
									<div class="form-group">
										<label for="pw">密碼</label> <input type="password"
											class="form-control" name="password" id="pw" placeholder="請輸入您的密碼">
									</div>
									
									<div>
										<a href="#">忘記密碼</a>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/front-end/user/newMember.jsp">加入會員</a>
									</div>
									
									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

								</form>
								
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="login" type="button" class="btn btn-primary">登入</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-sign-in">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">新生註冊</h4>
				</div>
				<div class="modal-body">註冊表單</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary">註冊</button>
				</div>
			</div>
		</div>
	</div>
<!-- 登入模組 結束-->


<div class="callout"></div>



<%@ include file="/front-end/member_interface/script.file" %>
</body>
</html>
