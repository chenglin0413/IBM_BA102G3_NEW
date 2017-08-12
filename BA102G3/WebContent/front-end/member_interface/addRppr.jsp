<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.prod.model.*,com.user.model.*"%>
<%	
	Integer prod_id=new Integer(request.getParameter("prod_id"));
	UserVO userVO = (UserVO) session.getAttribute("userVO");
	String account =(String) session.getAttribute("account");
    pageContext.setAttribute("prod_id",prod_id);
    
%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>檢舉商品</title>

</head>
<body>
<%long seconds = new java.util.Date().getTime();%>
		
<form role="form" action="<%=request.getContextPath()%>/front-end/report/rppr.do" method="post">
	<div class="form-group">
		<label for="inputTittle">檢舉商品</label>	
		<input type="text" name="rppr_tittle"class="form-control" id="inputTittle" placeholder="輸入檢舉標題">
	</div>
	<br>
	<div class="form-group">
		<label for="inputReason">檢舉內容</label>	
		<textarea class="form-control" name="rppr_content" id="inputReason" rows="5" cols="35" placeholder="輸入檢舉內容"> </textarea>
	</div>
	
	 <input type="hidden" name="prod_id" value="${prod_id}"> <!--${prodVO.prod_id} -->
	 <input type="hidden" name="user_id" value="${userVO.user_id}"> <!--${userVO.user_id} -->
	 <input type="hidden" name="rppr_date" value="<%= seconds%>">
	 <input type="hidden" name="action" value="ADDTOReport">
	 <button type="submit" class="btn btn-default">送出</button>
</form>
		
</body>
</html>