<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="com.sysu.model.*"%>
<%
SysuVO sysuVO = (SysuVO) request.getAttribute("sysuVO");

AuthService authSvc = new AuthService();
List<AuthVO> list = (List<AuthVO>) request.getAttribute("authVOList");
pageContext.setAttribute("list",list);

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
                    <li>
                        ${sysuVO.sysu_id} ${sysuVO.sysu_account}
                    </li>
                    <li class="active">詳細資料及更新</li>
                </ol>


            <FORM METHOD="post" ACTION="sysu.do" name="form1">

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
                            <div>
                    			員工加入時間: <%=sysuVO.getSysu_joindate()%>
                    		</div><br>
		                    <div>
		                                                                 職務:
        		                 <input type="radio" name="sysu_type" value="1" <c:if test="${sysuVO.sysu_type == '1'}" var="condition" scope="page" >checked</c:if> > 系統管理 &nbsp;&nbsp; 
                		         <input type="radio" name="sysu_type" value="2" <c:if test="${sysuVO.sysu_type == '2'}" var="condition" scope="page" >checked</c:if> > 客服 &nbsp;&nbsp;
                		         <input type="radio" name="sysu_type" value="3" <c:if test="${sysuVO.sysu_type == '3'}" var="condition" scope="page" >checked</c:if> > 營運 &nbsp;&nbsp;
                    		</div><br>
                    		<div>
                        		員工狀態:
                        		<input type="radio" name="sysu_status" value="1" <c:if test="${sysuVO.sysu_status == '1'}" var="condition" scope="page" >checked</c:if> > 正常 &nbsp;&nbsp; 
                        		<input type="radio" name="sysu_status" value="0" <c:if test="${sysuVO.sysu_status == '0'}" var="condition" scope="page" >checked</c:if> > 停權 &nbsp;&nbsp;
                    		</div><br>
                    </div>
                    <div class="col-lg-6">                                     
                    	<div>

                        	<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
                    		員工可用的管理功能: 
                    		<fieldset>
	                    		<c:forEach var="funcVO" items="${funcSvc.all}" >
	                    			<input type="checkbox" name="sysu_func" value="${funcVO.func_id}" 
	                    			
	                    			<c:forEach var="authVO1" items="${list}" >
	                    				<c:if test="${funcVO.func_id == authVO1.func_id}" var="condition" scope="page" >
	                    					checked
	                    				</c:if>
	                    			</c:forEach>
	                    				                    			
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
						<input type="hidden" name="sysu_id" value="<%=sysuVO.getSysu_id()%>">
						<input type="hidden" name="sysu_passwd" value="<%=sysuVO.getSysu_passwd()%>">
						<input type="hidden" name="sysu_joindate" value="<%=sysuVO.getSysu_joindate()%>">
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
