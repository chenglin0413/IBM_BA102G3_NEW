<%@page contentType="text/html;charset=Big5"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="com.json.JSONObject"%>
<%
//取得前端送來的資料 
int prod_id = Integer.valueOf(request.getParameter("prod_id"));
  

//載入JDBC驅動程式類別 
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","BA102G3", "123456"); 
  
  
//建立PreparedStatement物件 
PreparedStatement stmt = conn.prepareStatement("select * from prod where prod_id=?");

//代入資料    
stmt.setInt(1,prod_id);

//執行PreparedStatement
ResultSet rs = stmt.executeQuery();
  
//取回一筆資料
rs.next();
  
//將資料轉成JSONObject		 
JSONObject prod = new JSONObject();
prod.put("prod_id",rs.getString("PROD_ID"));
prod.put("prod_name",rs.getString("PROD_NAME"));
prod.put("prod_describe",rs.getString("PROD_DESCRIPT"));
prod.put("prod_count",rs.getString("PROD_COUNT"));
//輸出JSONObject
out.print( prod );
//關閉ResultSet物件 	
rs.close();
//關閉Statement物件    
stmt.close();
//關閉Connection物件 	 
conn.close();
%>   


