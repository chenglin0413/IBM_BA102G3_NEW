package com.prod.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader extends HttpServlet {

	Connection con;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
			
			try {
				
					PreparedStatement pstmt = con.prepareStatement("SELECT prpi_img FROM prpi WHERE prod_id=?");
//					String prod_id=str.substring(8, str.length());
					pstmt.setString(1,req.getParameter("prod_id"));
					ResultSet rs=pstmt.executeQuery();
					if (rs.next()) {
						BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("prpi_img"));
						byte[] buf = new byte[4 * 4096]; // 4K buffer
						int len;
						while ((len = in.read(buf)) != -1) {
							out.write(buf, 0, len);
						}
						in.close();
						rs.close();
						pstmt.close();
//						con.close();
						}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			

			
	}

	public void init() throws ServletException {
		DataSource ds = null;
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
				con = ds.getConnection();
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}
			
			
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
