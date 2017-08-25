<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.prod.model.ProdVO,com.ord.model.*,com.shopping.controller.*,com.item.model.*"%>
<%@ page import="com.ord.model.*"%>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
 	<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <title>選擇付款方式</title>
    
    
<!--        信用卡css -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/member_interface/assets/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/member_interface/assets/css/demo.css">
    
    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/front-end/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/front-end/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <style type="text/css">
        .item img{
            height: 120px;
            width:40%;
            
        }
       
        .content: {
          position: relative;
        }
        .item{
          width: 100%px;
          height: 200px;  
          margin: auto;
        }
    </style>
    
    
</head>
<body>

<%@include file="/front-end/member_interface/headerBar.file" %>
	<div class="container-fluid">
        <div class="creditCardForm">
            <div class="heading">
                <h1>選擇付款方式</h1>
                
				現金<input  type="radio" id="cash"    name="ord_bill" value="1">
				信用卡<input type="radio" id="credit" name="ord_bill" value="2">                
            </div>
            <div id="a" style="display:none;">
               		
                  <form>
                    <div class="form-group owner">
                        <label for="owner">Owner</label>
                        <input type="text" class="form-control" id="owner">
                    </div>
                    <div class="form-group CVV">
                        <label for="cvv">CVV</label>
                        <input type="text" class="form-control" id="cvv">
                    </div>
                    <div class="form-group" id="card-number-field">
                        <label for="cardNumber">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber">
                    </div>
                    <div class="form-group" id="expiration-date">
                        <label>Expiration Date</label>
                        <select>
                            <option value="01">January</option>
                            <option value="02">February </option>
                            <option value="03">March</option>
                            <option value="04">April</option>
                            <option value="05">May</option>
                            <option value="06">June</option>
                            <option value="07">July</option>
                            <option value="08">August</option>
                            <option value="09">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                        <select>
                            <option value="16"> 2016</option>
                            <option value="17"> 2017</option>
                            <option value="18"> 2018</option>
                            <option value="19"> 2019</option>
                            <option value="20"> 2020</option>
                            <option value="21"> 2021</option>
                        </select>
                    </div>
                   	<div class="form-group" id="credit_cards">
                        <img src="<%=request.getContextPath() %>/front-end/member_interface/assets/images/visa.jpg" id="visa">
                        <img src="<%=request.getContextPath() %>/front-end/member_interface/assets/images/mastercard.jpg" id="mastercard">
                        <img src="<%=request.getContextPath() %>/front-end/member_interface/assets/images/amex.jpg" id="amex">
                    </div>
                    <div class="form-group" id="pay-now">
                        <button type="submit" class="btn btn-default" id="confirm-purchase">Confirm</button>
                    </div>
                     </form> 
            </div>


            <div id="b" style="display:none;" class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Type</th>
                            <th>Card Number</th>
                            <th>Security Code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Visa</td>
                            <td>4716108999716531</td>
                            <td>257</td>
                        </tr>
                        <tr>
                            <td>Master Card</td>
                            <td>5281037048916168</td>
                            <td>043</td>
                        </tr>
                        <tr>
                            <td>American Express</td>
                            <td>342498818630298</td>
                            <td>3156</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
    </div>
    <div class="col-md-6 col-md-offset-4" >
    <div class="row">
					  <button class="btn btn-danger" onclick="magiclittlebtn();">MAGIC</button>
					  &nbsp;&nbsp;&nbsp;&nbsp;	
		              <a href="<%=request.getContextPath()%>/front-end/ord/ord.do?action=decide_Ord_bill"><button class="btn btn-info">確認付款</button></a>
		              </div>
		              </div>
    
  

<!--  displayCreditCard -->

<%@ include file="/front-end/member_interface/script.file" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/front-end/member_interface/assets/js/jquery.payform.min.js" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/front-end/member_interface/assets/js/script.js"></script>

<script type="text/javascript">
	
	function cashCheck() {
	    document.getElementById('a').style.display = 'none';
	    document.getElementById('b').style.display = 'none';
	   
	}
	function creditshow() {
	    document.getElementById('a').style.display = '';
	    document.getElementById('b').style.display = '';
	   
	}

	
		function magiclittlebtn(){
			document.getElementById('owner').value = 'Cheng Lin';
		    document.getElementById('cvv').value = 322;
		    document.getElementById('cardNumber').value = '4556 6897 7316 0486';
		}
		
	
	
	
	
	function init(){
		var a=document.getElementById("cash");
		var b=document.getElementById("credit");
		a.addEventListener("click",cashCheck, false);
		b.addEventListener("click",creditshow,false);
	
	}
	 window.onload = init;
</script>

</body>
</html>