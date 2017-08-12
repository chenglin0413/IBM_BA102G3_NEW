<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.func.model.*"%>
<%
FuncVO funcVO = (FuncVO) request.getAttribute("funcVO");
%>

<%@include file="includeHeadForFunc.jsp" %>

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


            <FORM METHOD="post" ACTION="func.do" name="form1">

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
                                <label class="input-group-addon">功能名稱</label>
                                <input type="text" name="func_name" value="<%= (funcVO==null)? "" : funcVO.getFunc_name()%>" class="form-control">
                            </div><br>

                            <div class="input-group">
                                <label class="input-group-addon">路徑</label>
                                <input type="text" name="func_path" value="<%= (funcVO==null)? "" : funcVO.getFunc_path()%>" class="form-control">
                            </div><br>
                            
                            <div class="col-lg-6">
								功能狀態:&nbsp;&nbsp;
                         		<input type="radio" name="func_status" value="1" <c:if test="${funcVO.func_status == '1'}" var="condition" scope="page" >checked</c:if> > 正常 &nbsp;&nbsp; 
                         		<input type="radio" name="func_status" value="0" <c:if test="${funcVO.func_status == '0'}" var="condition" scope="page" >checked</c:if> > 停用 &nbsp;&nbsp;
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
						<input type="hidden" name="func_id" value="<%=funcVO.getFunc_id()%>">
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
