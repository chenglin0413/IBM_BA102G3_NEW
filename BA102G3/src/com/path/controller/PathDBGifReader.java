package com.path.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PathDBGifReader extends HttpServlet {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ONE_STMT =
			"SELECT PATH_IMG,"
			+ "PATH_IMGFMT "
			+ "FROM PATH WHERE PATH_ID=?";	
		
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			                         throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out = res.getOutputStream();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
			
		try {

			Integer path_id  = new Integer( req.getParameter("path_id").trim() );
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);			
			pstmt.setInt(1, path_id);
			
			rs = pstmt.executeQuery();			
						
			if (rs.next()) {
				
				String path_imgfmt=rs.getString("path_imgfmt");
				res.setContentType("image/"+path_imgfmt);
				
				System.out.println("path_imgfmt: "+path_imgfmt);
								
				BufferedInputStream in = 
				  new BufferedInputStream(rs.getBinaryStream("path_img"));
				byte[] buf = new byte[4 * 1024];  // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
								
			    in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			  System.out.println(e);
		}
	}
	
}
