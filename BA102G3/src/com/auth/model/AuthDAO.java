package com.auth.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sysu.model.SysuVO;
import com.func.model.FuncVO;

public class AuthDAO implements AuthDAO_Interface {

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
			"INSERT INTO AUTH("
			+ "SYSU_ID, "
			+ "FUNC_ID)"
			+ " VALUES (?,?)";
	private static final String GET_ALL_STMT =
			"SELECT SYSU_ID,"
			+ "FUNC_ID "
			+ "FROM AUTH order by SYSU_ID";
	private static final String GET_ONE_STMT =
			"SELECT SYSU_ID,"
			+ "FUNC_ID "
			+ "FROM AUTH WHERE SYSU_ID=?";
	private static final String GET_ONE_ROW_STMT =
			"SELECT SYSU_ID,"
			+ "FUNC_ID "
			+ "FROM AUTH WHERE SYSU_ID=? and FUNC_ID=?";
	private static final String DELETE=
			"DELETE FROM AUTH WHERE SYSU_ID = ? and FUNC_ID = ?";
	private static final String DELETEID=
			"DELETE FROM AUTH WHERE SYSU_ID = ?";
	private static final String UPDATE=
			"UPDATE SYSU set "
			+ "SYSU_ID=?, "
			+ "FUNC_ID=? "
			+ "WHERE SYSU_ID=? AND FUNC_ID=?";
	
	@Override
	public void insert(AuthVO authVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);		
			pstmt.setInt(1,authVO.getSysu_id());
			pstmt.setInt(2,authVO.getFunc_id());
			
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
	public void update(AuthVO authVO) {

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,authVO.getSysu_id());
			pstmt.setInt(2,authVO.getFunc_id());
			
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
	
	public void updateAuthById(Integer sysu_id, Integer func_id) {

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
						
			pstmt=con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,sysu_id);
			pstmt.setInt(2,func_id);			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public void delete(Integer sysu_id, Integer func_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, sysu_id);
			pstmt.setInt(2, func_id);

			pstmt.executeUpdate();

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
	public List<AuthVO> findByPrimaryKey(Integer sysu_id) {
		
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sysu_id);

			rs = pstmt.executeQuery();

			int count=0;
			
			System.out.println("===AuthDAO.java line235========================");
			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				authVO = new AuthVO();
				authVO.setSysu_id(rs.getInt("sysu_id"));
				authVO.setFunc_id(rs.getInt("func_id"));
				
				System.out.println(rs.getInt("sysu_id")+" "+rs.getInt("func_id"));
				
				
				list.add(authVO);
				
				System.out.println("count: "+ ++count);
			}
			
			System.out.println("========================");

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
	public List<AuthVO> getAll() {

		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 
				authVO = new AuthVO();
				authVO.setSysu_id(rs.getInt("sysu_id"));
				authVO.setFunc_id(rs.getInt("func_id"));
				list.add(authVO); // Store the row in the list
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

	public void deleteId(Integer sysu_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEID);

			pstmt.setInt(1, sysu_id);

			pstmt.executeUpdate();

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
	
}
