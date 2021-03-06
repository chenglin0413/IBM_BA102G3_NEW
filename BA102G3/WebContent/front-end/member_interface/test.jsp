<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" >
<meta http-equiv="expires" content="0">
<title>使用GET請求方式取得JSON回應字串</title>         
<style> 
.empTable { border-collapse:collapse}
.empTable th{ background-color:#BFBFFF;
                color:blue; 
        text-align:right;
        border-style:dotted;
        font-weight:normal;
        padding : 5px;
         }  
.empTable td{
            border:2px dotted deepPink;
        padding:5px; 
         }            
       
</style>  
<!-- <script src="json.js"></script> --> 
</head>
<body>
<table align="center">
<tr><td>員工帳號</td>
    <td><input type="text" name="prod_id" id="prod_id">( 7566 )</td> 
</tr>
<tr><td colspan="2" align="center">
    <input type="button" value="取得員工資料" onClick="getEmployee();"/> 
    </td>
</tr>
</table>  
<div id="showPanel">
</div>  
<script>  
var xhr = null;

function showEmployee(jsonStr){
  //剖析json字串,將其轉成jsob物件
  //剖析json字串,將其轉成jsob物件
  var list = JSON.parse(jsonStr);
console.log(jsonStr);
console.log(list.list.length);
  //準備要輸出的網頁結構
	  var str = "<div class='col-md-12'>";
  for(var i=1;i<list.list.length;i++){
	  str += "<div class='col-md-3'>商家ID :"+ list.list[i].prod_id+"</div>";
	  str += "<div class='col-md-3'>商家ID :"+ list.list[i].prod_name+"</div>";
	  str += "<div class='col-md-3'>商家ID :"+ list.list[i].prod_descript+"</div>";
	  str += "<div id='boxshadow'><img src=<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id="+list.list[i].prod_id+"></div>";
	  //放入頁面
	  console.log(i);
	  document.getElementById("showPanel").innerHTML = str;
  }
	

  
}

function getEmployee(){ 
  var xhr = new XMLHttpRequest();
  //設定好回呼函數   
  xhr.onreadystatechange = function (){
    if( xhr.readyState == 4){
      if( xhr.status == 200){
        //document.getElementById("showPanel").innerHTML = xhr.responseText;
        showEmployee(xhr.responseText);
      }else{
        alert( xhr.status );
      }//xhr.status == 200
    }//xhr.readyState == 4
  };//onreadystatechange 
  
  //建立好Get連接
  var url= "Get_JSON_from_DB.jsp?prod_id=" + document.getElementById("prod_id").value;
  url+="&time="+new Date().getTime();
  xhr.open("Get",url,true); 
  //送出請求 
  xhr.send( null );
}
</script>
</body>
</html>