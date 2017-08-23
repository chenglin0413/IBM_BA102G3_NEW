package com.user.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.user.model.UserVO;

public class UserDBGifReader extends HttpServlet {

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
			"SELECT USER_IMG,"
			+ "USER_IMGFMT "
			+ "FROM USER_TABLE WHERE USER_ID=?";	
		
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			                         throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out = res.getOutputStream();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
			
		try {

			Integer user_id  = new Integer( req.getParameter("user_id").trim() );
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);			
			pstmt.setInt(1, user_id);
			
			rs = pstmt.executeQuery();			
						
			if (rs.next()) {
				
				String user_imgfmt=rs.getString("user_imgfmt");
				res.setContentType("image/"+user_imgfmt);
				
				System.out.println("user_imgfmt: "+user_imgfmt);
								
				BufferedInputStream in = 
				  new BufferedInputStream(rs.getBinaryStream("user_img"));
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
			con.close();
			
		} catch (Exception e) {
			  System.out.println(e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
}
