<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=Big5"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="com.json.JSONObject,com.prod.model.*"%>
<%
//���o�e�ݰe�Ӫ���� 
int prod_id = Integer.valueOf(request.getParameter("prod_id"));
  

//���JJDBC�X�ʵ{�����O 
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","BA102G3", "123456"); 
  
  
//�إ�PreparedStatement���� 
PreparedStatement stmt = conn.prepareStatement("select * from prod where prod_id=?");

//�N�J���    
stmt.setInt(1,prod_id);

//����PreparedStatement
ResultSet rs = stmt.executeQuery();
  
//���^�@�����
rs.next();
  
//�N����নJSONObject		 
JSONObject prod = new JSONObject();
ProdService prodSvc=new ProdService();
List<ProdVO> list=prodSvc.getAll();
prod.put("list",list);
// prod.put("prod_id",rs.getString("PROD_ID"));
// prod.put("prod_name",rs.getString("PROD_NAME"));
// prod.put("prod_describe",rs.getString("PROD_DESCRIPT"));
// prod.put("prod_count",rs.getString("PROD_COUNT"));
//��XJSONObject
out.print( prod.toString() );
//����ResultSet���� 	
rs.close();
//����Statement����    
stmt.close();
//����Connection���� 	 
conn.close();
%>   


