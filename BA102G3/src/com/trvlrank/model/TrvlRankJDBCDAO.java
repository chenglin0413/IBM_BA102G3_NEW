package com.trvlrank.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrvlRankJDBCDAO implements TrvlRankDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,trvlrankVO.getTrvl_id());
			pstmt.setInt(2,trvlrankVO.getUser_id());
			pstmt.setInt(3,trvlrankVO.getTrvlrank_score());

			pstmt.executeUpdate();
			
			System.out.println("新增成功");

			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,trvlrankVO.getTrvlrank_score());
			pstmt.setInt(2,trvlrankVO.getTrvl_id());
			pstmt.setInt(3,trvlrankVO.getUser_id());
			
			System.out.println("修改");
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

//	@Override
//	public void delete(Integer trvl_id) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public Integer findByPrimaryKey(Integer trvl_id, Integer user_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer score= null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,trvl_id);
			pstmt.setInt(2,user_id);
			
			rs = pstmt.executeQuery();
			rs.next();				

			score =rs.getInt("TRVLRANK_SCORE");			


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	 
		public static void main(String[] args) {
			
			TrvlRankJDBCDAO dao = new TrvlRankJDBCDAO();

			//新增
//			TrvlRankVO trvlrankVO1 = new TrvlRankVO();
//			trvlrankVO1.setUser_id(1000004);
//			trvlrankVO1.setTrvl_id(1100007);;
//			trvlrankVO1.setTrvlrank_score(5);
//
//			dao.insert(trvlrankVO1);
//		
			//修改
			TrvlRankVO trvlrankVO2 = new TrvlRankVO();
			
			trvlrankVO2.setUser_id(1000003);
			trvlrankVO2.setTrvl_id(1100003);;
			trvlrankVO2.setTrvlrank_score(1);

			dao.update(trvlrankVO2);
			System.out.println("XXX");	
			// 刪除
//			dao.delete();
//			System.out.println("已刪除");

			//查詢
//			TrvlRankVO trvlrankVO3 = new TrvlRankVO();
//			trvlrankVO3.setTrvl_id(1100001);
//			trvlrankVO3.setUser_id(1000001);
			
			Integer socre = dao.findByPrimaryKey(1100001,1000001);
			System.out.println(socre);

			System.out.println("---------------------------------");

			//查詢
			List<TrvlRankVO> list = dao.getAll();
			for (TrvlRankVO aTrvlRank : list) {				
				System.out.print(aTrvlRank.getTrvl_id() + ",");
				System.out.print(aTrvlRank.getUser_id()+ ",");
	            System.out.print(aTrvlRank.getTrvlrank_score()+ ",");
				System.out.println();
			}
			System.out.println("查成功");	
		}

	
	}