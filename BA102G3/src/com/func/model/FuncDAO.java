package com.func.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FuncDAO implements FuncDAO_Interface {

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
			"INSERT INTO FUNC("
			+ "FUNC_ID,"
			+ "FUNC_NAME,"
			+ "FUNC_PATH,"
			+ "FUNC_STATUS)"
			+ " VALUES (FUNC_ID_SEQ.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT FUNC_ID,"
			+ "FUNC_NAME,"
			+ "FUNC_PATH,"
			+ "FUNC_STATUS "
			+ "FROM FUNC order by FUNC_ID desc";
	private static final String GET_ONE_STMT =
			"SELECT FUNC_ID,"
			+ "FUNC_NAME,"
			+ "FUNC_PATH,"
			+ "FUNC_STATUS "
			+ "FROM FUNC WHERE FUNC_ID=?";
	private static final String DELETE=
			"DELETE FROM FUNC WHERE FUNC_ID=?";
	private static final String UPDATE=
			"UPDATE FUNC set "
			+ "FUNC_NAME=?,"
			+ "FUNC_PATH=?,"
			+ "FUNC_STATUS=? "
			+ "WHERE FUNC_ID=?";
	
	@Override
	public void insert(FuncVO funcVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);		
			pstmt.setString(1,funcVO.getFunc_name());
			pstmt.setString(2,funcVO.getFunc_path());
			pstmt.setInt(3,funcVO.getFunc_status());
			
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
	public void update(FuncVO funcVO) {

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
						
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);		
			pstmt.setString(1,funcVO.getFunc_name());
			pstmt.setString(2,funcVO.getFunc_path());
			pstmt.setInt(3,funcVO.getFunc_status());
			pstmt.setInt(4,funcVO.getFunc_id());
			
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
	public void delete(Integer func_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, func_id);

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
	public FuncVO findByPrimaryKey(Integer func_id) {
		FuncVO funcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, func_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				funcVO = new FuncVO();
				funcVO.setFunc_id(rs.getInt("func_id"));
				funcVO.setFunc_name(rs.getString("func_name"));
				funcVO.setFunc_path(rs.getString("func_path"));
				funcVO.setFunc_status(rs.getInt("func_status"));
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
		return funcVO;

	}
	
	@Override
	public List<FuncVO> getAll() {

		List<FuncVO> list = new ArrayList<FuncVO>();
		FuncVO funcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				funcVO = new FuncVO();
				funcVO.setFunc_id(rs.getInt("func_id"));
				funcVO.setFunc_name(rs.getString("func_name"));
				funcVO.setFunc_path(rs.getString("func_path"));
				funcVO.setFunc_status(rs.getInt("func_status"));
				list.add(funcVO); // Store the row in the list
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
	
}
