<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" >
<meta http-equiv="expires" content="0">
<title>�ϥ�GET�ШD�覡���oJSON�^���r��</title>         
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
<tr><td>���u�b��</td>
    <td><input type="text" name="prod_id" id="prod_id">( 7566 )</td> 
</tr>
<tr><td colspan="2" align="center">
    <input type="button" value="���o���u���" onClick="getEmployee();"/> 
    </td>
</tr>
</table>  
<div id="showPanel">
</div>  
<script>  
var xhr = null;

function showEmployee(jsonStr){
  //��Rjson�r��,�N���নjsob����
  //��Rjson�r��,�N���নjsob����
  var list = JSON.parse(jsonStr);
console.log(jsonStr);
console.log(list.list.length);
  //�ǳƭn��X���������c
	  var str = "<div class='col-md-12'>";
  for(var i=1;i<list.list.length;i++){
	  str += "<div class='col-md-3'>�ӮaID :"+ list.list[i].prod_id+"</div>";
	  str += "<div class='col-md-3'>�ӮaID :"+ list.list[i].prod_name+"</div>";
	  str += "<div class='col-md-3'>�ӮaID :"+ list.list[i].prod_descript+"</div>";
	  str += "<div id='boxshadow'><img src=<%=request.getContextPath()%>/front-end/prod/DBGifReader?prod_id="+list.list[i].prod_id+"></div>";
	  //��J����
	  console.log(i);
	  document.getElementById("showPanel").innerHTML = str;
  }
	

  
}

function getEmployee(){ 
  var xhr = new XMLHttpRequest();
  //�]�w�n�^�I���   
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
  
  //�إߦnGet�s��
  var url= "Get_JSON_from_DB.jsp?prod_id=" + document.getElementById("prod_id").value;
  url+="&time="+new Date().getTime();
  xhr.open("Get",url,true); 
  //�e�X�ШD 
  xhr.send( null );
}
</script>
</body>
</html>