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

<%@include file="includeHeadForUser.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">
							<c:if test="${userVO.user_type == '1'}" var="condition" scope="page" > 
								一般會員管理
							</c:if>
							<c:if test="${userVO.user_type == '2'}" var="condition" scope="page" > 
								商家負責人								
							</c:if>                        	
							<c:if test="${userVO.user_type == '3'}" var="condition" scope="page" > 
								餐廳負責人
							</c:if>                        	
							                        	
                        </a>
                    </li>
                    <li>
                        ${userVO.user_id} ${userVO.user_account}
                    </li>
                    <li class="active">詳細資料及更新</li>
                </ol>


            <FORM class="uploadImage" METHOD="post" ACTION="user.do" name="form1" enctype="multipart/form-data">

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
                         <input type="radio" name="user_status" value="1" <c:if test="${userVO.user_status == '1'}" var="condition" scope="page" >checked</c:if> > 正常 &nbsp;&nbsp; 
                         <input type="radio" name="user_status" value="0" <c:if test="${userVO.user_status == '0'}" var="condition" scope="page" >checked</c:if> > 停權 &nbsp;&nbsp;
                         <input type="radio" name="user_status" value="2" <c:if test="${userVO.user_status == '2'}" var="condition" scope="page" >checked</c:if> > 未確認
                    </div>
                    <div class="col-lg-6">
                    	會員加入時間: <%=userVO.getUser_joindate()%>
                    </div>
                    <div class="col-lg-6">
						會員圖片<br>
						<img src="<%= request.getContextPath() %>/back-end/user/userImg.do?user_id=<%=userVO.getUser_id()%>" height="200">
                    </div>
                                    
                    <div class="col-lg-6">
                                                               更新圖片
                        <input type="file" name="upfile1" id="file-input1" class="file" onchange="previewImages()">
                        </label>
                        <div id="preview"></div>
                        
                    </div>
                    
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->
            
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-1">
						<input type="hidden" name="user_id" value="<%=userVO.getUser_id()%>">
						<input type="hidden" name="user_passwd" value="<%=userVO.getUser_passwd()%>">
						<input type="hidden" name="user_type" value="<%=userVO.getUser_type()%>">
						<input type="hidden" name="user_joindate" value="<%=userVO.getUser_joindate()%>">
						<input type="hidden" name="user_status" value="<%=userVO.getUser_status()%>">
						<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
                        <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp-->
                        <button class="btn btn-primary" name="action" value="update">送出修改</button>
                    </div>
                    <div class="col-xs-12 col-sm-1">
                    	<button onclick="window.history.back();" class="btn btn-primary">回上一頁</button>
                    </div>
                </div>
            </div>

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