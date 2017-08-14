<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sysu.model.*"%>
<%
SysuVO sysuVO = (SysuVO) request.getAttribute("sysuVO");
%>

<%@include file="includeHeadForSysu.jsp" %>

        <div id="page-wrapper">

                <ol class="breadcrumb">
                    <li>
                        <a href="#">首頁</a>
                    </li>
                    <li>
                        <a href="#">平台員工管理</a>
                    </li>
                    <li class="active">新增員工</li>
                </ol>


            <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back-end/sysu/sysu.do" name="form1">

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
                                <input type="text" name="sysu_account" value="<%= (sysuVO==null)? "" : sysuVO.getSysu_account()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">姓</label>
                                <input type="text" name="sysu_lastname" value="<%= (sysuVO==null)? "" : sysuVO.getSysu_lastname()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">名</label>
                                <input type="text" name="sysu_firstname" value="<%= (sysuVO==null)? "" : sysuVO.getSysu_firstname()%>" class="form-control">
                            </div><br>                                

                            <div class="input-group">
                                <label class="input-group-addon">Email</label>
                                <input type="text" name="sysu_email" value="<%= (sysuVO==null)? "" : sysuVO.getSysu_email()%>" class="form-control">
                            </div><br>
                    </div>
                    <div class="col-lg-6">                                     
		                <div>
		                                                        職務:
        		             <input type="radio" name="sysu_type" value="1" id="radio1" <c:if test="${sysuVO.sysu_type == '1'}" var="condition" scope="page" >checked</c:if> > 系統管理 &nbsp;&nbsp; 
                		     <input type="radio" name="sysu_type" value="2" id="radio2" <c:if test="${sysuVO.sysu_type == '2'}" var="condition" scope="page" >checked</c:if> > 客服 &nbsp;&nbsp;
                		     <input type="radio" name="sysu_type" value="3" id="radio3" <c:if test="${sysuVO.sysu_type == '3'}" var="condition" scope="page" >checked</c:if> > 營運 &nbsp;&nbsp;
                    	</div><br>
                    		
                    	<div>

                        	<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
                    		員工可用的管理功能: 
                    		<fieldset>
	                    		<c:forEach var="funcVO" items="${funcSvc.all}" >
	                    			<input type="checkbox" name="sysu_func" value="${funcVO.func_id}" 
	                    				                    				                    			
	                    			> ${funcVO.func_name}<br>
	                    		</c:forEach>
	                    	</fieldset>
	                    		
                    	</div><br>
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
            <input type="hidden" name="sysu_type" value="1">
            <input type="hidden" name="sysu_status" value="1">

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
