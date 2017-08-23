package com.path.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.path.model.PathVO;

public class PathDAO implements PathDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT INTO PATH("
			+ "PATH_ID, "
			+ "PATH_TERM, "
			+ "PATH_FROMLON, "
			+ "PATH_FROMLAT, "
			+ "PATH_TOLON, "
			+ "PATH_TOLAT, "
			+ "PATH_FROMPLACE, "
			+ "PATH_TOPLACE, "
			+ "PATH_IMG, "
			+ "PATH_IMGFMT"
			+ " VALUES (PATH_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";

	private static final String GET_ALL_STMT =
			"SELECT PATH_ID,"
			+ "PATH_TERM,"
			+ "PATH_FROMLON,"
			+ "PATH_FROMLAT,"
			+ "PATH_TOLON,"
			+ "PATH_TOLAT,"
			+ "PATH_FROMPLACE,"
			+ "PATH_TOPLACE,"
			+ "PATH_IMG,"
			+ "PATH_IMGFMT "
			+ "FROM PATH order by PATH_ID desc";
	
	private static final String GET_ONE_STMT =
			"SELECT PATH_ID,"
			+ "PATH_TERM,"
			+ "PATH_FROMLON,"
			+ "PATH_FROMLAT,"
			+ "PATH_TOLON,"
			+ "PATH_TOLAT,"
			+ "PATH_FROMPLACE,"
			+ "PATH_TOPLACE,"
			+ "PATH_IMG,"
			+ "PATH_IMGFMT "
			+ "FROM PATH WHERE PATH_ID=?";

	private static final String GET_ONE_FROM_TO_STMT =
			"SELECT PATH_ID,"
			+ "PATH_TERM,"
			+ "PATH_FROMLON,"
			+ "PATH_FROMLAT,"
			+ "PATH_TOLON,"
			+ "PATH_TOLAT,"
			+ "PATH_FROMPLACE,"
			+ "PATH_TOPLACE,"
			+ "PATH_IMG,"
			+ "PATH_IMGFMT "
			+ "FROM PATH WHERE PATH_FROMPLACE=? and PATH_TOPLACE=? ";
		
	private static final String DELETE=
			"DELETE FROM PATH WHERE PATH_ID = ?";

	private static final String UPDATE=
			"UPDATE PATH set "
			+ "PATH_TERM=?, "
			+ "PATH_FROMLON=?, "
			+ "PATH_FROMLAT=?, "
			+ "PATH_TOLON=?, "
			+ "PATH_TOLAT=?, "
			+ "PATH_FROMPLACE=?, "
			+ "PATH_TOPLACE=?, "
			+ "PATH_IMG=?, "
			+ "PATH_IMGFMT=? "
			+ "WHERE PATH_ID=?";
	
	@Override
	public void insert(PathVO pathVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String next_path_id=null;
		
		try {
			con=ds.getConnection();
						
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,pathVO.getPath_term());
			pstmt.setDouble(2,pathVO.getPath_fromlon());
			pstmt.setDouble(3,pathVO.getPath_fromlat());
			pstmt.setDouble(4,pathVO.getPath_tolon());
			pstmt.setDouble(5,pathVO.getPath_tolat());
			pstmt.setString(6,pathVO.getPath_fromplace());
			pstmt.setString(7,pathVO.getPath_toplace());
			pstmt.setBytes(8,pathVO.getPath_img());
			pstmt.setString(9,pathVO.getPath_imgfmt());
						
			pstmt.executeUpdate();
									
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(PathVO pathVO, Integer updateImg) {

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);		
			pstmt.setInt(1,pathVO.getPath_id());
			pstmt.setInt(2,pathVO.getPath_term());
			pstmt.setDouble(3,pathVO.getPath_fromlon());
			pstmt.setDouble(4,pathVO.getPath_fromlat());
			pstmt.setDouble(5,pathVO.getPath_tolon());
			pstmt.setDouble(6,pathVO.getPath_tolat());
			pstmt.setString(7,pathVO.getPath_fromplace());
			pstmt.setString(8,pathVO.getPath_toplace());
			pstmt.setBytes(9,pathVO.getPath_img());
			pstmt.setString(10,pathVO.getPath_imgfmt());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
		
	}

	@Override
	public void delete(Integer path_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, path_id);
			pstmt.executeUpdate();

			con.commit();
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public PathVO findByPrimaryKey(Integer path_id) {
		PathVO pathVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, path_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				pathVO = new PathVO();
				pathVO.setPath_id(rs.getInt("path_id"));
				pathVO.setPath_term(rs.getInt("path_term"));
				pathVO.setPath_fromlon(rs.getDouble("path_fromlon"));
				pathVO.setPath_fromlat(rs.getDouble("path_fromlat"));
				pathVO.setPath_tolon(rs.getDouble("path_tolon"));
				pathVO.setPath_tolat(rs.getDouble("path_tolat"));
				pathVO.setPath_fromplace(rs.getString("path_fromplace"));
				pathVO.setPath_toplace(rs.getString("path_toplace"));
				pathVO.setPath_img(rs.getBytes("path_img"));
				pathVO.setPath_imgfmt(rs.getString("path_imgfmt"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return pathVO;

	}
	
	@Override
	public List<PathVO> getAll() {

		List<PathVO> list = new ArrayList<PathVO>();
		PathVO pathVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				pathVO = new PathVO();
				pathVO.setPath_id(rs.getInt("path_id"));
				pathVO.setPath_term(rs.getInt("path_term"));
				pathVO.setPath_fromlon(rs.getDouble("path_fromlon"));
				pathVO.setPath_fromlat(rs.getDouble("path_fromlat"));
				pathVO.setPath_tolon(rs.getDouble("path_tolon"));
				pathVO.setPath_tolat(rs.getDouble("path_tolat"));
				pathVO.setPath_fromplace(rs.getString("path_fromplace"));
				pathVO.setPath_toplace(rs.getString("path_toplace"));
				pathVO.setPath_img(rs.getBytes("path_img"));
				pathVO.setPath_imgfmt(rs.getString("path_imgfmt"));
				list.add(pathVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}

	@Override
	public PathVO findByFromTo(String path_fromplace, String path_toplace) {
		// TODO Auto-generated method stub
		PathVO pathVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("PathDAO.java, line359, from "+path_fromplace);
		System.out.println("PathDAO.java, line360, to "+path_toplace);

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FROM_TO_STMT);

			pstmt.setString(1, path_fromplace);
			pstmt.setString(2, path_toplace);

			rs = pstmt.executeQuery();
			
			System.out.println("pass PathDAO.java line372");

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				pathVO = new PathVO();
				pathVO.setPath_id(rs.getInt("path_id"));
				pathVO.setPath_term(rs.getInt("path_term"));
				pathVO.setPath_fromlon(rs.getDouble("path_fromlon"));
				pathVO.setPath_fromlat(rs.getDouble("path_fromlat"));
				pathVO.setPath_tolon(rs.getDouble("path_tolon"));
				pathVO.setPath_tolat(rs.getDouble("path_tolat"));
				pathVO.setPath_fromplace(rs.getString("path_fromplace"));
				pathVO.setPath_toplace(rs.getString("path_toplace"));
				pathVO.setPath_img(rs.getBytes("path_img"));
				pathVO.setPath_imgfmt(rs.getString("path_imgfmt"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return pathVO;
	}

	
}
