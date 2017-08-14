<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- page import="com.prod.model.*"--%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
		
<%long seconds = new java.util.Date().getTime();%>
		
<form role="form" action="<%=request.getContextPath()%>/back-end/report/rptl.do" method="post">
	<div class="form-group">
		<label for="inputTittle">檢舉遊記</label>	
		<input type="text" name="rptl_tittle"class="form-control" id="inputTittle" placeholder="輸入檢舉標題">
	</div>
	<br>
	<div class="form-group">
		<label for="inputReason">檢舉內容</label>	
		<textarea class="form-control" name="rptl_content" id="inputReason" rows="5" cols="35" placeholder="輸入檢舉內容"> </textarea>
	</div>
	
	 <input type="hidden" name="trvl_id" value="2200001"> <!--${prodVO.prod_id} -->
	 <input type="hidden" name="user_id" value="1000055"> <!--${userVO.user_id} -->
	 <input type="hidden" name="rptl_date" value="<%= seconds%>">
	 <input type="hidden" name="action" value="insert">
	 <button type="submit" class="btn btn-default">送出</button>
</form>
		
</body>
</html>