package com.trvlrank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TrvlRankDAO  implements TrvlRankDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO TRVLRANK (trvl_id,user_id,trvlrank_score) VALUES (?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE TRVLRANK set trvlrank_score=? where trvl_id=? and user_id=?";
//	private static final String DELETE = 
//			"DELETE FROM TRVLlRANK where trvl_id=? and user_id=?";
	private static final String GET_ONE_STMT = 
			"SELECT trvlrank_score FROM TRVLRANK where trvl_id =? and user_id=?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM TRVLRANK";
	
	
	
	@Override
	public void insert(TrvlRankVO trvlrankVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,trvlrankVO.getTrvl_id());
			pstmt.setInt(2,trvlrankVO.getUser_id());
			pstmt.setInt(3,trvlrankVO.getTrvlrank_score());

			pstmt.executeUpdate();
			
			System.out.println("新增成功");

			con.commit();
			// Handle any driver errors
		}catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

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
	public void update(TrvlRankVO trvlrankVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,trvlrankVO.getTrvlrank_score());
			pstmt.setInt(2,trvlrankVO.getTrvl_id());
			pstmt.setInt(3,trvlrankVO.getUser_id());
			
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		}catch (SQLException se) {
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

//	@Override
//	public void delete(Integer trvl_id) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public  Integer findByPrimaryKey(Integer trvl_id, Integer user_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer score= null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,trvl_id);
			pstmt.setInt(2,user_id);
			
			rs = pstmt.executeQuery();
			rs.next();				

			score =rs.getInt("TRVLRANK_SCORE");	
			// Handle any driver errors
		}catch (SQLException se) {
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
		return score;
	}

	@Override
	public List<TrvlRankVO> getAll() {
		// TODO Auto-generated method stub
		List<TrvlRankVO> list = new ArrayList<TrvlRankVO>();
		TrvlRankVO trvlrankVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				trvlrankVO = new TrvlRankVO();
				trvlrankVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlrankVO.setUser_id(rs.getInt("USER_ID"));
				trvlrankVO.setTrvlrank_score(rs.getInt("TRVLRANK_SCORE"));

				list.add(trvlrankVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
