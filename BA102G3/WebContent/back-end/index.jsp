<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="includeHeadForIndex.jsp" %>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->

                <!-- /.row -->

                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-bolt fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">10</div>
                                        <div>未結案的檢舉!</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left"><a href="admin-report-wait.html">詳細情形</a></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-shopping-cart fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">14</div>
                                        <div>未審核的商店會員申請!</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left"><a href="admin-shop-wait.html">詳細情形</a></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-coffee fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">16</div>
                                        <div>未審核的餐廳會員申請!</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left"><a href="admin-rest-wait.html">詳細情形</a></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

                <!-- /.row -->               
                <div class="row">
                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>未處理的檢舉</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>檢舉編號</th>
                                                <th>檢舉時間</th>
                                                <th>檢舉人</th>
                                                <th>檢舉項目</th>
                                                <th></th>                                                
                                            </tr>
                                        </thead>
                                        <tbody style="background-color: lightblue;">
                                            <tr>
                                                <td>6100010</td>
                                                <td>2017-08-31 15:00:00</td>
                                                <td>raccoon</td>
                                                <td>產品</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = './admin-rest-6100010.html';"></td>
                                            </tr>
                                            <tr>
                                                <td>6200010</td>
                                                <td>2017-08-31 14:00:00</td>
                                                <td>cat</td>
                                                <td>商店</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = '';"></td>
                                            </tr>
                                            <tr>
                                                <td>6300010</td>
                                                <td>2017-08-31 13:00:00</td>
                                                <td>rat</td>
                                                <td>餐廳</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = '';"></td>
                                            </tr>
                                            <tr>
                                                <td>6000010</td>
                                                <td>2017-08-31 12:00:00</td>
                                                <td>rabbit</td>
                                                <td>日誌</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = '';"></td>
                                            </tr>
                                            <tr>
                                                <td>6300009</td>
                                                <td>2017-08-31 11:00:00</td>
                                                <td>dog</td>
                                                <td>餐廳</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = '';"></td>
                                            </tr>
                                            <tr>
                                                <td>6200009</td>
                                                <td>2017-08-31 10:00:00</td>
                                                <td>squirrel</td>
                                                <td>商店</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = '';"></td>
                                            </tr>
                                            <tr>
                                                <td>6200008</td>
                                                <td>2017-08-31 09:00:00</td>
                                                <td>chipmunk</td>
                                                <td>商店</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = '';"></td>
                                            </tr>
                                            <tr>
                                                <td>6000009</td>
                                                <td>2017-08-31 08:00:00</td>
                                                <td>deer</td>
                                                <td>日誌</td>
                                                <td><input type="button" class="btn btn-xs btn-danger" value="詳情" onclick="window.location = '';"></td>
                                            </tr>
                                        </tbody>
                                    </table>

                                    <div class="text-center">
                                        <ul class="pagination">
                                        <li><a href="#">&laquo;</a></li>
                                        <li class="active"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">&raquo;</a></li>
                                        </ul>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    


                    
                    <div class="col-lg-5">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i>過去一天加入的一般會員</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>會員編號</th>
                                                <th>帳號</th>
                                                <th>加入時間</th>
                                                <th>狀態</th>                                                
                                            </tr>
                                        </thead>
                                        <tbody style="background-color: lightblue;">
                                            <tr >
                                                <td>1000099</td>
                                                <td>apple</td>
                                                <td>2017-08-31 15:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-warning">未確認</button></td>
                                            </tr>
                                            <tr>
                                                <td>1000098</td>
                                                <td>guava</td>
                                                <td>2017-08-31 14:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-success">已確認</button></td>
                                            </tr>
                                            <tr>
                                                <td>1000097</td>
                                                <td>pineapple</td>
                                                <td>2017-08-31 13:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-success">已確認</button></td>
                                            </tr>
                                            <tr>
                                                <td>1000096</td>
                                                <td>summer</td>
                                                <td>2017-08-31 12:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-warning">未確認</button></td>
                                            </tr>
                                            <tr>
                                                <td>1000095</td>
                                                <td>winter</td>
                                                <td>2017-08-31 11:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-success">已確認</button></td>
                                            </tr>
                                            <tr>
                                                <td>1000094</td>
                                                <td>pihai</td>
                                                <td>2017-08-31 10:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-warning">未確認</button></td>
                                            </tr>
                                            <tr>
                                                <td>1000093</td>
                                                <td>oldman</td>
                                                <td>2017-08-31 09:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-success">已確認</button></td>
                                            </tr>
                                            <tr>
                                                <td>1000092</td>
                                                <td>beach</td>
                                                <td>2017-08-31 08:00:00</td>
                                                <td><button type="button" class="btn btn-xs btn-success">已確認</button></td>
                                            </tr>
                                        </tbody>
                                    </table>

                                    <div class="text-center">
                                        <ul class="pagination">
                                          <li><a href="#">&laquo;</a></li>
                                          <li class="active"><a href="#">1</a></li>
                                          <li><a href="#">2</a></li>
                                          <li><a href="#">3</a></li>
                                          <li><a href="#">4</a></li>
                                          <li><a href="#">5</a></li>
                                          <li><a href="#">&raquo;</a></li>
                                        </ul>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

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
