package com.tlcm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tlcm.model.TlcmDAO_interface;
import com.tlcm.model.TlcmVO;

public class TlcmJDBCDAO implements TlcmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Tlcm (tlcm_id,trvl_id,user_id,tlcm_date,tlcm_content) "
			+ "VALUES (TLCM_ID_SEQ.NEXTVAL, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE Tlcm set trvl_id=?, user_id=?, tlcm_date=?, tlcm_content=? where tlcm_id=?";
	private static final String DELETE = "DELETE FROM Tlcm where tlcm_id = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM Tlcm where tlcm_id = ?";
	private static final String GET_BY_FK = "SELECT * FROM Tlcm where trvl_id = ?";
	
	private static final String GET_ALL_STMT = "SELECT * FROM Tlcm";

	@Override
	public void insert(TlcmVO tlcmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tlcmVO.getTrvl_id());
			pstmt.setInt(2, tlcmVO.getUser_id());
			pstmt.setDate(3, tlcmVO.getTlcm_date());
			pstmt.setString(4, tlcmVO.getTlcm_content());

			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {

			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(TlcmVO tlcmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tlcmVO.getTrvl_id());
			pstmt.setInt(2, tlcmVO.getUser_id());
			pstmt.setDate(3, tlcmVO.getTlcm_date());
			pstmt.setString(4, tlcmVO.getTlcm_content());
			pstmt.setInt(5, tlcmVO.getTlcm_id());

			pstmt.executeUpdate();

			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer tlcm_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tlcm_id);

			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public TlcmVO findByPrimaryKey(Integer tlcm_id) {

		TlcmVO tlcmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tlcm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("Tlcm_ID"));
				tlcmVO.setTrvl_id(rs.getInt("Trvl_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("Tlcm_DATE"));
				tlcmVO.setTlcm_content(rs.getString("Tlcm_CONTENT"));

			}
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return tlcmVO;
	}
	
	@Override
	public List<TlcmVO> findByForeignKey(Integer trvl_id) {
		List<TlcmVO> list = new ArrayList<TlcmVO>();
		TlcmVO tlcmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(GET_BY_FK);

			pstmt.setInt(1, trvl_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("Tlcm_ID"));
				tlcmVO.setTrvl_id(rs.getInt("Trvl_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("Tlcm_DATE"));
				tlcmVO.setTlcm_content(rs.getString("Tlcm_CONTENT"));
				list.add(tlcmVO);
			}
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<TlcmVO> getAll() {
		List<TlcmVO> list = new ArrayList<TlcmVO>();
		TlcmVO tlcmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("Tlcm_ID"));
				tlcmVO.setTrvl_id(rs.getInt("Trvl_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("Tlcm_DATE"));
				tlcmVO.setTlcm_content(rs.getString("Tlcm_CONTENT"));

				list.add(tlcmVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		TlcmJDBCDAO dao = new TlcmJDBCDAO();

//		// 新增
//		TlcmVO tlcmVO1 = new TlcmVO();
//
//		tlcmVO1.setTrvl_id(1100005);
//		tlcmVO1.setUser_id(1000001);
//		tlcmVO1.setTlcm_date(java.sql.Date.valueOf("2017-07-11"));
//		tlcmVO1.setTlcm_content("安 安安安安安安安安  ");
//
//		dao.insert(tlcmVO1);
//
//		// 修改
//		TlcmVO tlcmVO2 = new TlcmVO();
//
//		tlcmVO2.setTlcm_id(1300001);
//		tlcmVO2.setTrvl_id(1100002);
//		tlcmVO2.setUser_id(1000001);
//		tlcmVO2.setTlcm_date(java.sql.Date.valueOf("2011-07-11"));
//		tlcmVO2.setTlcm_content("你好");
//
//		dao.update(tlcmVO2);

		// 刪除
//		dao.delete(1300006);
//		System.out.println("刪除成功");

		// 查詢
//		TlcmVO trvlVO3 = dao.findByPrimaryKey(1300009);
//		System.out.print(trvlVO3.getTlcm_id() + ",");
//		System.out.print(trvlVO3.getTrvl_id() + ",");
//		System.out.print(trvlVO3.getUser_id() + ",");
//		System.out.print(trvlVO3.getTlcm_date() + ",");
//		System.out.println(trvlVO3.getTlcm_content() + ",");
//
//		System.out.println("---------------------");
		
		// 查詢
		List<TlcmVO> list1 = dao.findByForeignKey(1100006);
		for (TlcmVO aTlcmVO : list1) {
			System.out.print(aTlcmVO.getTlcm_id() + ",");
			System.out.print(aTlcmVO.getTrvl_id() + ",");
			System.out.print(aTlcmVO.getUser_id() + ",");
			System.out.print(aTlcmVO.getTlcm_date() + ",");
			System.out.print(aTlcmVO.getTlcm_content() + ",");
			System.out.println();
		}
		System.out.println("++++++++++++++++++++++++++++++++++");
		
		
		// 查詢
//		List<TlcmVO> list = dao.getAll();
//		for (TlcmVO aTlcmVO : list) {
//			System.out.print(aTlcmVO.getTlcm_id() + ",");
//			System.out.print(aTlcmVO.getTrvl_id() + ",");
//			System.out.print(aTlcmVO.getUser_id() + ",");
//			System.out.print(aTlcmVO.getTlcm_date() + ",");
//			System.out.print(aTlcmVO.getTlcm_content() + ",");
//			System.out.println();
//		}
	}
}
