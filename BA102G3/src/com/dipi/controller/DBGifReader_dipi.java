package com.dipi.controller;


import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader_dipi extends HttpServlet {

	private static final String GET_ONE_STMT = 
			"select * from dipi where dipi_id = ?";
	
	Connection con = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Integer dipi_id = new Integer(req.getParameter("dipi_id"));

		try {
			
			PreparedStatement pstmt = null;
			
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setInt(1, dipi_id);
				
				ResultSet rs= pstmt.executeQuery();
			
			if(rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("dipi_img"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} 
			
			 else {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
			con= ds.getConnection();
		
		} catch (Exception e) {
			throw new UnavailableException("Couldn't get db connection");
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
