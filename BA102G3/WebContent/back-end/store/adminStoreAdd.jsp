<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>
<%@ page import="com.store.model.*"%>
<%
UserVO userVO = (UserVO) request.getAttribute("userVO");
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<%@include file="includeHeadForStore.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">商店會員管理</a>
                    </li>
                    <li class="active">新增</li>
                </ol>


            <FORM class="uploadImage" METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/user/userstore.do" name="form1" enctype="multipart/form-data">

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
                                <label class="input-group-addon">負責人帳號</label>
                                <input type="text" name="user_account" value="<%= (userVO==null)? "" : userVO.getUser_account()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">負責人姓</label>
                                <input type="text" name="user_lastname" value="<%= (userVO==null)? "" : userVO.getUser_lastname()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">負責人名</label>
                                <input type="text" name="user_firstname" value="<%= (userVO==null)? "" : userVO.getUser_firstname()%>" class="form-control">
                            </div><br>                                

                            <div class="input-group">
                                <label class="input-group-addon">負責人電話</label>
                                <input type="text" name="user_phone" value="<%= (userVO==null)? "" : userVO.getUser_phone()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">負責人手機</label>
                                <input type="text" name="user_mobile" value="<%= (userVO==null)? "" : userVO.getUser_mobile()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">負責人住址</label>
                                <input type="text" name="user_address" value="<%= (userVO==null)? "" : userVO.getUser_address()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">負責人Email</label>
                                <input type="text" name="user_email" value="<%= (userVO==null)? "" : userVO.getUser_email()%>" class="form-control">
                            </div><br>
                            <div>
	                                                                        選擇圖片
    	            	        <input type="file" name="upfile1" id="file-input1" class="file" onchange="previewImages()">
        	            	    </label>
            		            <div id="preview"></div>
                            </div>

                    </div>                
                    <div class="col-lg-6">
                            <div class="input-group">
                                <label class="input-group-addon">商店名稱</label>
                                <input type="text" name="store_name" value="<%= (storeVO==null)? "" : storeVO.getStore_name()%>" class="form-control">
                            </div><br>
                            <div class="input-group">
                                <label class="input-group-addon">商店營業時間</label>
                                <input type="text" name="store_time" value="<%= (storeVO==null)? "" : storeVO.getStore_time()%>" class="form-control">
                            </div><br>
                            <div class="input-group">
                                <label class="input-group-addon">商店電話</label>
                                <input type="text" name="store_phone" value="<%= (storeVO==null)? "" : storeVO.getStore_phone()%>" class="form-control">
                            </div><br>
                            <div class="form-group">
                                <label class="input-group-addon">店家描述</label>
                                <textarea name="store_describe" class="form-control" rows="7"> <%= (storeVO==null)? "" : storeVO.getStore_describe()%> </textarea>
                            </div><br>
                            <div class="col-xs-12 col-sm-4">
                                <div class="form-group">
                                    <label for="name">所在航廈</label>
                                    <select class="form-control" name="store_ter">
                                    <option <c:if test="${storeVO.store_ter == '1'}" var="condition" scope="page">selected</c:if> value="1">T1</option>
                                    <option <c:if test="${storeVO.store_ter == '2'}" var="condition" scope="page">selected</c:if> value="2">T2</option>
                                    </select>
                                </div>                                
                            </div>
                            <div class="col-xs-12 col-sm-4">
                                <div class="form-group">
                                    <label for="name">所在樓層</label>
                                    <select class="form-control" name="store_floor">
                                    <option value="B2" <c:if test="${storeVO.store_floor == 'B2'}" var="condition" scope="page">selected</c:if> >B2</option>
                                    <option value="B1" <c:if test="${storeVO.store_floor == 'B1'}" var="condition" scope="page">selected</c:if> >B1</option>
                                    <option value="1" <c:if test="${storeVO.store_floor == '1'}" var="condition" scope="page">selected</c:if> >1</option>
                                    <option value="2" <c:if test="${storeVO.store_floor == '2'}" var="condition" scope="page">selected</c:if> >2</option>
                                    <option value="3" <c:if test="${storeVO.store_floor == '3'}" var="condition" scope="page">selected</c:if> >3</option>
                                    <option value="4" <c:if test="${storeVO.store_floor == '4'}" var="condition" scope="page">selected</c:if> >4</option>
                                    </select>
                                </div>                               
                            </div>

                            <div class="col-xs-12 col-sm-4">
                                <div class="form-group">
	                            	<label for="name">出入境位置</label>
    	                        	<select class="form-control" name="store_inout">
  									<option value="1" <c:if test="${storeVO.store_inout == '1'}" var="condition" scope="page">selected</c:if> >出境區</option>
  									<option value="2" <c:if test="${storeVO.store_inout == '2'}" var="condition" scope="page">selected</c:if> >入境區</option>
									</select>                            
                    	    	</div>
                    	    </div><br>

                            <div class="col-xs-12 col-sm-6">
                                <div class="input-group">
                                    <label class="input-group-addon" >所在經度</label>
                                    <input type="text" name="store_lon" value="<%= (storeVO==null)? "" : storeVO.getStore_lon()%>" class="form-control">
                                </div>                                
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <div class="input-group">
                                    <label class="input-group-addon" >所在緯度</label>
                                    <input type="text" name="store_lat" value="<%= (storeVO==null)? "" : storeVO.getStore_lat()%>" class="form-control">
                                </div><br>                                
                            </div>
                        
                    </div>
                    
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->
            
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-1">
                        <input class="btn btn-primary" type="submit" value="送出">
                    </div>
                    <div class="col-xs-12 col-sm-1">
                        <input class="btn btn-primary" type="reset" value="重設">    
                    </div>
                </div>
            </div>

            <input type="hidden" name="action" value="insert">
            <input type="hidden" name="user_type" value="2">
            <input type="hidden" name="user_status" value="2">
            <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑-->

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