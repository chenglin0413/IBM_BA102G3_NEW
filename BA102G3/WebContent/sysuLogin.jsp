<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		
	    <meta http-equiv="cache-control" content="max-age=0" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
		<meta http-equiv="pragma" content="no-cache" />
		
		<title>平台管理系統登入</title>

	    <!-- Bootstrap Core CSS -->
    	<link href="<%= request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		
		<br>
		
		<h1 class="text-center">Anytime Grip平台管理系統</h1>

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-sm-offset-4">
					<form action="<%= request.getContextPath() %>/SysuLoginHandler" method="post">
					<fieldset class="form-group">
					<legend></legend>
                        
                        <div class="input-group">
                            <label class="input-group-addon">帳號</label>
                            <input type="text" name="account" id="" class="form-control" value="">
                        </div><br>

                        <div class="input-group">
                            <label class="input-group-addon">密碼</label>
                            <input type="password" name="password" id="" class="form-control" value="">
                        </div><br>
                                                
                        <div class="text-center">

						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font color='red'>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<b>${message}</b>
								</c:forEach>
							</ul>
							</font>
						</c:if>
						<%-- 錯誤表列 --%>                          
                        
                        </div>                        

                      	<div class="text-center">
					        <input class="btn btn-primary btn" type="submit" value="登入">
            				<input class="btn btn-primary btn" type="reset" value="重設">    
       					</div>

					</fieldset>
					</form>					
				</div>	
			</div>
		</div>
		
				<h4 class="text-center">測試用 account: admin &nbsp;&nbsp; passwd: ba102g3</h4>	

	</body>
</html>