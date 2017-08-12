<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.func.model.*"%>
<%
    FuncService funcSvc = new FuncService();
    List<FuncVO> list = funcSvc.getAll();
    pageContext.setAttribute("list",list);
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
                    <li class="active">功能調整</li>
                </ol>

            <div class="container-fluid">
                <div class="row">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/func/adminFuncAdd.jsp">
					<input type="submit" value="新增功能" class="btn btn-primary">
					</FORM>												
				</div>
			</div>            
            
            <br>
            
            <div class="container-fluid">

                <div class="row">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>所有的功能</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                            	<th>功能編號</th>
												<th>功能名稱</th>
												<th>功能路徑</th>
												<th>狀態</th>
												<th></th>
												<th></th>
                                            </tr>
                                        </thead>

  									                                                                                

										<%@ include file="page1.file" %>
										<tbody style="background-color: lightblue;">
										<c:forEach var="funcVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
										
                                            <tr align='center' valign='middle' ${(funcVO.func_id==param.func_id) ? 'style="outline: thick solid yellow;;"':''}>
                                                <td>${funcVO.func_id}</td>
                                                <td>${funcVO.func_name}</td>
                                                <td>${funcVO.func_path}</td>
                                                <td>
                                                    <c:if test="${funcVO.func_status == '1'}" var="2" scope="page" > 
                                                		<img src="../image/circleGreen.png" height="15" width="15">
                                                	</c:if>
                                                	<c:if test="${funcVO.func_status == '0'}" var="2" scope="page" > 
                                                		<img src="../image/circleRed.png" height="15" width="15">
                                                	</c:if>
                                                </td>                                                
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/func/func.do">
			     									<input type="submit" value="修改" class="btn btn-primary">
			     									<input type="hidden" name="func_id" value="${funcVO.func_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                                    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			     									
			     									<input type="hidden" name="action" value="getOne_For_Update"></FORM>
												</td>
												<td>
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/func/func.do">
			    									<input type="submit" value="刪除" class="btn btn-danger">
			    									<input type="hidden" name="func_id" value="${funcVO.func_id}">
                                                    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                                    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			    									
			    									<input type="hidden" name="action" value="delete"></FORM>												
												</td>
                                            </tr>
                                                                                    
                                        </c:forEach>    
                                        </tbody>                                        
                                    </table>
                                    <%@ include file="page2.file" %>

                                </div>
                            </div>
                        </div>
                    
                </div>

            </div>

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