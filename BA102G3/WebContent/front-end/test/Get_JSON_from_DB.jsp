<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=Big5"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="org.json.JSONObject,com.prod.model.*"%>
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
ProdVO prodVO=prodSvc.getOneProd(rs.getInt("prod_id"));
List<ProdVO> list=prodSvc.getAll();
for(int i=0;i<list.size();i++){
	String str=new Integer(i).toString();
	prod.put(str,list.get(i).getProd_id());
}


//��XJSONObject
out.print( prod);
//����ResultSet���� 	
rs.close();
//����Statement����    
stmt.close();
//����Connection���� 	 
conn.close();
%>   


